package com.jspminipjt.service.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.omg.CORBA.BAD_INV_ORDER;

import com.jspminipjt.controller.board.BoardFactory;
import com.jspminipjt.dao.board.BoardDao;
import com.jspminipjt.dao.board.BoardDaoCRUD;
import com.jspminipjt.dao.board.BoardDaoSql;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.board.BoardDto;
import com.jspminipjt.service.BoardService;
import com.jspminipjt.vo.UploadFileVo;

public class WriteBoardService implements BoardService {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // 하나의 파일블럭의 버퍼사이즈 (5MB)
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 최대 파일 사이즈 (20MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 25; // 최대 request 사이즈 (25MB)

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardFactory bf = BoardFactory.getInstance();
		System.out.println("게시판 글 저장 시작");
		
		System.out.println(request.getParameter("writer"));

		// 파일 업로드할 디렉토리 생성
		String uploadDir = "\\board_uploads";
		
		String realPath = request.getSession().getServletContext().getRealPath(uploadDir);

		// File 객체 만들기
		File saveFileDir = new File(realPath);
		
		int result = -1;
		String writer = "";
		String title = "";
		String content = "";
		UploadedFileDto ufDto = null;
		String encoding = "utf-8";
		
		// 파일이 저장될 공간의 경로, 사이즈 등의 환경설정 정보를 가지고 있는 객체
		DiskFileItemFactory diskFactory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		
		// 실제 request로 넘겨져온 매개변수를 통해 파일을 upload 처리할 객체 생성
		ServletFileUpload sfu = new ServletFileUpload(diskFactory);
		BoardDao dao = BoardDaoCRUD.getInstance();
		
		try {
			List<FileItem> list = sfu.parseRequest(request);
			for (FileItem file : list) {
				System.out.println(file.toString());
				
				// FileItem 속성에서
				// 1) name 값이 null 이 아니면 파일 (name 값이 파일 이름)
				// 2) isFormField 속성이 true 이면 파일이 아닌 데이터
				// 					   false 이면 파일 데이터
				// 3) FieldName 의 값이 보내온 데이터의 input tag의 name 값
				
				if (file.isFormField()) {
					if (file.getFieldName().equals("writer")) {
						writer = file.getString(encoding);
					} else if (file.getFieldName().equals("title")) {
						title = file.getString(encoding);
					} else if (file.getFieldName().equals("content")) {
						content = file.getString(encoding);
					}
				} else if (!file.isFormField() && file.getName() != ""){
					// 업로드된 파일인 경우
//					// 파일이름 중복 제거 =>  파일명 (순서번호) + 확장자
					List<UploadFileVo> voList = dao.selectAllFile();
					ufDto = getNewFileNameWithSerial(file, voList);
					
					// 파일을 하드디스크에 저장
					File fileToSave = null;
					fileToSave = new File(realPath + File.separator + ufDto.getNewFileName());
					try {
						file.write(fileToSave);
						ufDto.setBase64String(makeImgToBase64String(realPath + File.separator + ufDto.getNewFileName()));
						
					} catch (Exception e) {
						request.setAttribute("ErrorMsg", e.getMessage());
						request.setAttribute("ErrorStack", e.getStackTrace());
						request.getRequestDispatcher("../commonError.jsp").forward(request, response);
						e.printStackTrace();
					}
				}
			}
			
		} catch (FileUploadException e) {
			// 파일이 업로드 될때의 예외
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		} catch (NamingException | SQLException e) {
			//  파일 이름 변경시에 예외
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		// ======== 게시판 내용 등록 진행 ==========
		
		
		// 본문 저장 시 줄바꿈 처리를 해야한다.
		content = content.replaceAll("\r\n", "<br>");
		
		BoardDto dto = new BoardDto(-1, writer, title, null, content, 0);
		try { // 업로드된 파일이 있는 경우
			if (ufDto != null) {
				ufDto.setNewFileName("board_uploads/" + ufDto.getNewFileName());
				result = dao.insertBoardTransactionWithFile(dto, ufDto, "write_board", BoardDaoSql.WRITE_BOARD);
				System.out.println(result + " => 결과 with 파일");
			} else { // 업호드도니 파일이 없는 경우
				result = dao.insertBoardTransactionWithoutFile(dto, "write_board", BoardDaoSql.WRITE_BOARD);
				System.out.println(result + " => 결과 without 파일");
			}
			if (result == 1) {
				System.out.println("글쓰기 성공!!");
			}
			
		} catch (NamingException | SQLException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
			
			// 업로드된 파일이 있다면 삭제해야 함
			String withoutPath = ufDto.getNewFileName().substring("board_uploads/".length());
			if (ufDto != null) {
				File deleteFile = new File (realPath + File.separator + withoutPath);
				deleteFile.delete(); // 파일 삭제
			}
			
			// 글 작성 중 예외 발생 시 => 게시판 페이지로 이동
			bf.setRedirect(true);
			bf.setWhereToGo(request.getContextPath() +"/board/writeBoard.jsp?status=fail");
			
			return bf;
		}

		bf.setRedirect(true);
		bf.setWhereToGo(request.getContextPath() + "/board/listAll.bo");
		
		return bf;
	}

	private String makeImgToBase64String(String uploadedFile) {
		// img 파일을 base64String으로 만들기
		// encoding (파일 -> 문자열)
		String result = null;
		
		File upFile = new File(uploadedFile);
		byte[] file;
		try {
			file = FileUtils.readFileToByteArray(upFile);
			result = Base64.getEncoder().encodeToString(file);
			System.out.println("Base64 인코딩 결과 : " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 디코딩 (base64 문자열 -> 파일)
//		String encodedStr = result;
//		
//		byte[] decodedArr = Base64.getDecoder().decode(encodedStr);
//		String realPath = "D:\\Lectures\\JSP\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp2\\wtpwebapps\\JSP_Mini_Project\\board_uploads";
//		File fi = new File(realPath + File.separator + "aaa.jpg");
//		try {
//			FileUtils.writeByteArrayToFile(fi, decodedArr);
//			System.out.println("베이스 64 문자열을 파일로 저장 완료");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return result;
	}

	private UploadedFileDto getNewFileNameWithSerial(FileItem file, List<UploadFileVo> voList) {
		UploadedFileDto ufDto = null;
		String newFileName = "";
		String originalFileName = "";
		String ext = "";
		int cnt = 0;
		for (UploadFileVo vo : voList) {
			if (file.getName().equals(vo.getOriginalFilename())) {
				++cnt;
			}
			if (file.getSize() > 0) {
				if (!file.getName().equals(vo.getOriginalFilename())) {
					ext = file.getName().substring(file.getName().lastIndexOf("."));
					newFileName = file.getName();
					originalFileName = file.getName();
				} else {
					if (file.getName().equals(vo.getOriginalFilename())) {
						ext = vo.getOriginalFilename().substring(vo.getOriginalFilename().lastIndexOf("."));
						newFileName = vo.getOriginalFilename().substring(0, vo.getOriginalFilename().lastIndexOf(".")) + "(" + cnt +")" + ext;
						originalFileName = vo.getOriginalFilename();
					}
				}
			}
		}

		ufDto = new UploadedFileDto(originalFileName, ext, newFileName, file.getSize());

		return ufDto;
	}

	
	
}

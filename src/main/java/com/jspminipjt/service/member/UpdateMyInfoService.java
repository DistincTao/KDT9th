package com.jspminipjt.service.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.dao.member.MemberDaoCRUD;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.UploadFileVo;

public class UpdateMyInfoService implements MemberService {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // 하나의 파일블럭의 버퍼사이즈 (5MB)
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 최대 파일 사이즈 (20MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 25; // 최대 request 사이즈 (25MB)

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberFactory mf = MemberFactory.getInstance();
		System.out.println("개인정보 변경 절차 시작");
		UploadedFileDto ufDto = null;
		MemberDaoCRUD dao = MemberDaoCRUD.getInstance();

		// 파일 업로드할 디렉토리 생성
		String uploadDir = "\\memberImages";
		// File 객체 만들기
		String realPath = request.getSession().getServletContext().getRealPath(uploadDir);
		int result = -1;
		File saveFileDir = new File(realPath);
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		String existFile = "";
		String userImg = "";
		String encoding = "utf-8";
		// 파일이 저장될 공간의 경로, 사이즈 등의 환경설정 정보를 가지고 있는 객체
		DiskFileItemFactory diskFactory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		
		// 실제 request로 넘겨져온 매개변수를 통해 파일을 upload 처리할 객체 생성
		ServletFileUpload sfu = new ServletFileUpload(diskFactory);

		try {
			List<FileItem> list = sfu.parseRequest(request);
			for (FileItem file : list) {
				System.out.println(file.toString());
				if (file.isFormField()) {
					if (file.getFieldName().equals("userImg")) {
						userImg = file.getString(encoding);
					} else if (file.getFieldName().equals("memberImg")) {
						existFile = file.getString(encoding);
					} else if (file.getFieldName().equals("userId")) {
						userId = file.getString(encoding);
					} else if (file.getFieldName().equals("userPwd")) {
						userPwd = file.getString(encoding);
					} else if (file.getFieldName().equals("userEmail")) {
						userEmail = file.getString(encoding);
					}
					
					
				}else if (!file.isFormField() && file.getName() != ""){
				// 업로드된 파일인 경우
				// 파일이름 중복 제거 =>  파일명 (순서번호) + 확장자
				List<UploadFileVo> voList = dao.selectAllImg();
				ufDto = getNewFileNameWithSerial(file, voList);
				System.out.println(voList.toString());
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
	} catch (FileUploadException | SQLException | NamingException | ServletException | IOException e) {
		request.setAttribute("ErrorMsg", e.getMessage());
		request.setAttribute("ErrorStack", e.getStackTrace());
		request.getRequestDispatcher("../commonError.jsp").forward(request, response);
		e.printStackTrace();
	}
		
		// 내용 수정 진행
		try {
			if (ufDto != null) {
				ufDto.setNewFileName("memberImages/" + ufDto.getNewFileName());
				System.out.println("Test : " + ufDto.toString());
				result = dao.updateMyPageFile(ufDto, userImg);
				System.out.println(result + " => 결과 with 파일");
			
			} else {
				if (userPwd != null) {
					result = dao.updateUserPwd(userPwd, userId);
				} else if (userEmail != null) {
					result = dao.updateUserEmail(userEmail, userId);
				}
			}
			
			if (result == 1) {
				System.out.println("개인정보 변경 성공!!");
			}
		} catch (SQLException | NamingException e) {
			request.setAttribute("ErrorMsg", e.getMessage());
			request.setAttribute("ErrorStack", e.getStackTrace());
			request.getRequestDispatcher("../commonError.jsp").forward(request, response);
			e.printStackTrace();
		}
		
		return mf;
	}
	
	private UploadedFileDto getNewFileNameWithSerial(FileItem file, List<UploadFileVo> voList) {
		UploadedFileDto ufDto = null;
		String newFileName = "";
		String originalFileName = "";
		String ext = "";
		int cnt = 1;
		for (UploadFileVo vo : voList) {
			if (file.getName().equals(vo.getOriginalFilename())) {
				cnt++;
			}
			System.out.println("카운트가 안되는지 확인 해보기 : " + cnt);
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
		System.out.println("이름이 바뀌는 것을 보겠습니다! : " + ufDto.toString());
		return ufDto;
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
			// TODO Auto-generated catch block
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
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return result;
	}
}


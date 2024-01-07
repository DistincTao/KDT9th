package com.jspminipjt.service.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jspminipjt.controller.MemberFactory;
import com.jspminipjt.dao.MemberDao;
import com.jspminipjt.dao.MemberDaoCRUD;
import com.jspminipjt.dao.MemberDaoSql;
import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.UploadedFileVo;

public class RegisterMemberService implements MemberService {
	public static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // 하나의 파일 블럭의 버퍼 사이즈 (5MB)
	public static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 하나의 파일 블럭의 버퍼 사이즈 (10MB)
	public static final int MAX_REQUEST_SIZE = 1024 * 1024 * 25; // 하나의 파일 블럭의 버퍼 사이즈 (15MB)

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberFactory mf = MemberFactory.getInstance();
		System.out.println("회원 가입 절차 시작");
		// "multipart/form-data"
		//파일과 함께 데이터를 받을 경우 => request.getParameter()로 데이터 수집 불가
		//1) 파일이 저장될 디렉토리 생성
		String uploadDir = "\\memberImages";
		// 실제 파일이 저장될 물리적인 경로 불러오기 (realPath)
		String realPath = request.getSession().getServletContext().getRealPath(uploadDir);
		
		// file 객체 만들기
		File saveFileDir = new File (realPath); // 저장경로에 새로운 파일을 생성
		
		int result = -1;
		String userId = "";
		String userPwd = "";
		String userEmail = "";
		String encoding ="utf-8";
		UploadedFileDto ufDto = null;
		
		// 파일이 저장될 공간의 경로, 사이즈 등의 환경설정 정보를 가지고 있는 객체
		DiskFileItemFactory diskFactory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		// 실제 request로 넘겨저온 매개 변수를 통해 파일을 upload 처리할 객체
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFactory);
		MemberDao dao = MemberDaoCRUD.getInstance();
		
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {
				System.out.println(item.toString());
				
				// FileItem 속성
				// 1) name 값이 null이 아닌 파일 (name 값은 파일 이름)
				// 2) isFormField 속성이 true이면 파일이 아닌 데이터
				// 3) FieldName은 보내온 데이터의 input tag의 name 값
				
				if (item.isFormField()) {
					if (item.getFieldName().equals("userId")) {
						userId = item.getString(encoding);
					} else if (item.getFieldName().equals("userPwd")) {
						userId = item.getString(encoding);
					} else if (item.getFieldName().equals("userEmail")) {
						userId = item.getString(encoding);
					}
					
				} else if (!item.isFormField() && item.getName() != ""){
					// upload된 파일이 있는 경우 
					// 파일 이름의 중복을 제거
					// 2) 파일명 (순번) + .ext'
					List<UploadedFileVo> voList = dao.selectAllImg();
					ufDto = getNewFileNameWithSerial(item, voList);
					
					// 파일을 하드디스크에 저장
					File fileToSave = null;
					fileToSave = new File(realPath + File.separator + ufDto.getNewFilename());
					
					item.write(fileToSave);
				}
				System.out.println(userId + " " + userPwd + " " + userEmail + " " + ufDto);

			}
					
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// 파일이 업로드 될 때의 예외
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ================= 회원 가입 ===============
		  
		// 1) upload된 파일이 있는 경우
		try {
			if (ufDto != null) {
				ufDto.setNewFilename("memberImages/" + ufDto.getNewFilename());
				result = dao.registerMemberWithFile(ufDto, new MemberDto(userId, userPwd, userEmail, null, 1, 0), "signin", MemberDaoSql.SIGNIN);
				System.out.println(result + " => 결과 with 파일");
			} else {
				result = dao.registerMemberWithoutFile (new MemberDto(userId, userPwd, userEmail, null, 1, 0), "signin", MemberDaoSql.SIGNIN);
				System.out.println(result + " => 결과 without 파일");
			}
			if (result == 1) {
				System.out.println("회원가입 성공!!");
			}
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2) upload된 파일이 없는 경우
		
		
		return mf;
	}

	private UploadedFileDto getNewFileNameWithSerial(FileItem item, List<UploadedFileVo> voList) {
		UploadedFileDto ufDto = null;
		String originalFilename = null;
		String ext = null;
		String newFilename = null;
		int cnt = 0;
		
		for (UploadedFileVo vo : voList) {
			if (item.getName().equals(vo.getOriginalFilename())) {
				++cnt;
			}
			if (item.getSize() > 0) {
				if (!item.getName().equals(vo.getOriginalFilename())) {
					newFilename = item.getName();
					originalFilename = item.getName();
				} else {
					ext = vo.getOriginalFilename().substring(vo.getOriginalFilename().lastIndexOf("."));
					newFilename = vo.getOriginalFilename().substring(0, vo.getOriginalFilename().lastIndexOf(".")) + "(" + cnt +")" + ext;
					originalFilename = vo.getOriginalFilename();
				}
			}
		}
		
		ufDto = new UploadedFileDto(originalFilename, ext, newFilename, item.getSize());
		
		return ufDto;
	}

}

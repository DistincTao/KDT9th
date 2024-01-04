package com.jspminipjt.service.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.etc.UploadedFile;
import com.jspminipjt.service.MemberService;
import com.jspminipjt.vo.ImageVo;

public class RegisterMemberService implements MemberService {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // 하나의 파일블럭의 버퍼사이즈 (5MB)
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 최대 파일 사이즈 (20MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 25; // 최대 request 사이즈 (25MB)

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원 가입 절차 시작");
		// 파일과 함께 데이터를 받았다면, reqeust.getParameter()로 데이터를 수집하면 안된다.
//		System.out.println(request.getParameter("userId"));

		// 파일 업로드할 디렉토리를 생성
		String uploaderDir = "\\memberImages";
		// 실제 파일이 저장될 물리적 경로 (realPath)
		// realPath를 사용한 코드가 실질적인 코드!!!!
		String realPath = request.getSession().getServletContext().getRealPath(uploaderDir);
//		System.out.println("realPath : " + realPath);

		// File 객체 만들기
		File saveFileDir = new File(realPath);

		int result = -1;
		String userId = "";
		String userPwd = "";
		String userEmail = "";
		String userImg = "";
		String encoding = "utf-8";
		UploadedFile uploadedFile = null;

		// 파일이 저장될 공간의 경로, 사이즈 등의 환경설정 정보를 가지고 있는 객체
		DiskFileItemFactory diskFactory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);

		// 값을 변경해야 할 경우에 기본 생성자 사용
//		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
//		diskFactory.setSizeThreshold(MEMORY_THRESHOLD);
//		diskFactory.setRepository(saveFileDir);

		// 실제 request로 넘겨져온 매개변수를 통해 파일을 upload 처리할 객체
		ServletFileUpload sfu = new ServletFileUpload(diskFactory);
		MemberDao dao = MemberDaoCRUD.getInstance();

		try {
			List<FileItem> list = sfu.parseRequest(request);
			for (FileItem item : list) {
				System.out.println(item.toString());

				// FileItem 속성에서
				// 1) name 값이 null 이 아니면 파일 (name 값이 파일 이름)
				// 2) isFormField 속성이 true 이면 파일이 아닌 데이터
				// false 이면 파일 데이터
				// 3) FieldName 의 값이 보내온 데이터의 input tag의 name 값

				if (item.isFormField()) {
					// 파일이 아닌 일반 데이터
					if (item.getFieldName().equals("userId")) {
						userId = item.getString(encoding);
					} else if (item.getFieldName().equals("userPwd")) {
						userPwd = item.getString(encoding);
					} else if (item.getFieldName().equals("userEmail")) {
						userEmail = item.getString(encoding);
					}

				} else if (!item.isFormField() && item.getName() != "") {
//					// 업로드된 파일인 경우
//					// 파일이름 중복 제거
//					// 1) 중복되지 않는 새이름으로 파일명을 변경 : UUID
//					// userId_uuid
//					
//					uploadedFile = getNewFileName(item, userId);

					// 2) 파일명 (순서번호) + 확장자
					List<ImageVo> voList = dao.selectAllImg();

					uploadedFile = getNewFileNameWithSerial(item, voList);

					// 파일 하드디스크에 저장
					File fileToSave;

					fileToSave = new File(realPath + File.separator + uploadedFile.getNewFileName());

					try {
						item.write(fileToSave);
					} catch (Exception e) {
						e.printStackTrace();
					} // 파일 하드디스크에 저장
				}
				System.out.println(userId + " " + userPwd + " " + userEmail + " " + uploadedFile);
			}

		} catch (FileUploadException | SQLException | NamingException e) {
			// 파일이 업로드 될때의 예외
			e.printStackTrace();
		}
		// ======= 회원 가입 진행 =======

		try { // upload 된 파일이 있는 경우
			if (uploadedFile != null) { // 업로드된 파일이 있는 경우
				uploadedFile.setNewFileName("memberImages/" + uploadedFile.getNewFileName());
				result = dao.registerMemberWithFile(uploadedFile, new MemberDto(userId, userPwd, userEmail, null, 1, 0),
						"signin", 100);
				System.out.println(result + " => 결과");
			} else { // 업로드된 파일이 없는 경우
				result = dao.registerMemberWithoutFile(new MemberDto(userId, userPwd, userEmail, null, 1, 0), "signin",
						100);
			}
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

//	private UploadedFile getNewFileName(FileItem item, String userId) {
//		// userId_UUID로 새파일을 만들기
//		UploadedFile upFile = null;
//		
//		String uuid = UUID.randomUUID().toString();
//		String originalFileName = item.getName();
//		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
//		String newFileName = "";
//		
//		if (item.getSize() > 0) {
//			newFileName += userId + "_" + uuid + ext;
//		}
//		
//		upFile = new UploadedFile(originalFileName, ext, newFileName, item.getSize());
//		
//		return upFile;
//		
//	}

	private UploadedFile getNewFileNameWithSerial(FileItem item, List<ImageVo> list) {
		UploadedFile upFile = null;
		String newFileName = "";
		String originalFileName = "";
		String ext = "";
		int cnt = 0;
		for (ImageVo vo : list) {
			if (item.getName().equals(vo.getOriginalFilename())) {
				++cnt;
			}
			if (item.getSize() > 0) {
				if (!item.getName().equals(vo.getOriginalFilename())) {
					ext = vo.getOriginalFilename().substring(vo.getOriginalFilename().lastIndexOf("."));
					newFileName = item.getName();
					originalFileName = item.getName();
				} else {
					if (item.getName().equals(vo.getOriginalFilename())) {
						ext = vo.getOriginalFilename().substring(vo.getOriginalFilename().lastIndexOf("."));
						newFileName = item.getName().substring(0, item.getName().lastIndexOf(".")) + "(" + cnt +")" + ext;
						originalFileName = vo.getOriginalFilename();
					}
				}
			}
		}

		upFile = new UploadedFile(originalFileName, ext, newFileName, item.getSize());

		return upFile;
	}

}

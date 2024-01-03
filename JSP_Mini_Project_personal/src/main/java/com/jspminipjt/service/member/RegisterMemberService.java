package com.jspminipjt.service.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.MemberFactory;
import com.jspminipjt.dao.MemberDao;
import com.jspminipjt.dao.MemberDaoCRUD;
import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.service.MemberService;

public class RegisterMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원 가입 절차 시작");
		
		MemberDao dao = MemberDaoCRUD.getInstance();
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userEmail = request.getParameter("userEmail");
		System.out.println(userId +" " + userPwd + " " + userEmail);
		
		MemberDto dto = new MemberDto();
		dto.setUserId(userId);
		dto.setUserPwd(userPwd);
		dto.setUserEmail(userEmail);
		
		try {
			int result = dao.InsertUser(dto);
			
			
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}

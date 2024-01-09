package com.jspminipjt.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.service.MemberService;

public class LogoutMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberFactory mf = MemberFactory.getInstance();
		System.out.println("로그아웃 절차 시작");
		HttpSession sess = request.getSession();
		
		sess.removeAttribute("login");
		sess.invalidate();
		
		mf.setRedirect(true);
		mf.setWhereToGo(request.getContextPath() + "/index.jsp");
		
		return mf;
	}

}

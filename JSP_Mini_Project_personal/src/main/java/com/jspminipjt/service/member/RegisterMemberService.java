package com.jspminipjt.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspminipjt.controller.MemberFactory;
import com.jspminipjt.service.MemberService;

public class RegisterMemberService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원 가입 절차 시작");
		
		
		
		return null;
	}

}

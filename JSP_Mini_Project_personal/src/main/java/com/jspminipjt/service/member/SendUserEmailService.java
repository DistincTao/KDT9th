package com.jspminipjt.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jspminipjt.controller.MemberFactory;
import com.jspminipjt.etc.SendEmail;
import com.jspminipjt.service.MemberService;

public class SendUserEmailService implements MemberService{

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userEmailAddr = request.getParameter("tempUserEmail");
		String code = (int) (Math.random() * 100000000) + "";
		
		System.out.println(userEmailAddr + " 로 이메일 전송 시작~!!  인증 코드 : " + code);
		
		request.getSession().setAttribute("authCode", code); // Session 객체에 code 정보 저장
		
		Map<String, String> jsonMap = new HashMap<>();
		// 이메일로 인증번호 전송
		try {
			SendEmail.sendEmail(userEmailAddr, code);
			jsonMap.put("status", "success");
		} catch (MessagingException e) {
			jsonMap.put("status", "fail");
			e.printStackTrace();
		}
		
		JSONObject json = new JSONObject(jsonMap);
		out.print(json.toJSONString());
		
		out.flush();
		out.close();
		
		return null;
	}

}

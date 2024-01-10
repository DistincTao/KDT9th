package com.jspminipjt.service.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jspminipjt.controller.member.MemberFactory;
import com.jspminipjt.etc.SendEmail;
import com.jspminipjt.service.MemberService;

public class SendMailService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		Map <String, String> jsonMap = new HashMap<>();
		
		String userEmailAddr = request.getParameter("tmpUserEmail");
		
		// 인증코드를 만들고 코드를 세션에 저장
		String code = UUID.randomUUID().toString();
		
		System.out.println(userEmailAddr + "로 인증 메일 전송 시작." + code);
		request.getSession().setAttribute("authCode", code);
		
		// 유저 이메일로 인증코드 발송
		try {
			SendEmail.sendEmail(userEmailAddr, code);
			jsonMap.put("status", "success");
		} catch (MessagingException e) {
			e.printStackTrace();
			jsonMap.put("status", "fail");
		}
		
		JSONObject json = new JSONObject(jsonMap);
		out.print(json.toJSONString());
		
		out.flush();
		out.close();
		
		return null;
	}

}

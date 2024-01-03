package com.jspminipjt.service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jspminipjt.controller.MemberFactory;
import com.jspminipjt.service.MemberService;

public class ConfirmEmailCodeService implements MemberService {

	@Override
	public MemberFactory executeService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userInputCode = request.getParameter("userInputCode");
		String savedEmailCode = (String) request.getSession().getAttribute("authCode");
		
		System.out.println("코드 비교 시작 : user - " + userInputCode + " server - " + savedEmailCode);
		
		JSONObject json = new JSONObject();
		if (userInputCode.equals(savedEmailCode)) {
			json.put("activation", "success");
		} else {
			json.put("activation", "fail");			
		}
		
		out.print(json.toJSONString());
		
		out.flush();
		out.close();
		
		return null;
	}

}

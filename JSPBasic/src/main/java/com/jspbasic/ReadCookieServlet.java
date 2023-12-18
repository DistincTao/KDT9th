package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/readCookie.do")
public class ReadCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		// 이름을 알고 있는 쿠키의 값만 출력
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("myCookie")) {
				out.write(c.getValue() + "<br>"); // myCookie라는 이름의 크키의 값을 반환
			}
		}
		
		// 모든 쿠키 출력
		for (Cookie c : cookies) {
			out.write(c.getName() + " : " + c.getValue()  + "<br>"); // myCookie라는 이름의 크키의 값을 반환
		}
		
		// 쿠키 삭제하기 : 이름 myCookie인 쿠키 삭제
		
		for (Cookie c : cookies) {
			if (c.getName().equals("myCookie")) {
				c.setMaxAge(0); // myCookie라는 이름의 유효 시간을 0으로 설정
				c.setPath("/"); // myCookie와 동일한 Path 설정
				resp.addCookie(c);
			}
		}
	}
	
}

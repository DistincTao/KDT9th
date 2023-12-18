package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/createCookie.do")
public class CreateCookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie c = new Cookie("myCookie", "distinctao");
		
		c.setMaxAge(24 * 3600); // 쿠키를 저장할 시간 성정 (하루동안 쿠키 저장)
		c.setPath("/"); // Path 설정
		resp.addCookie(c); // 만들어진 Cookie 객체를 response 객체에 담아 클라이언트에 전송
		
		// 현제의 sessionID 값을 mySession 이라는 이름의 쿠키로 저장
		HttpSession sess = req.getSession();
		Cookie cs = new Cookie("mySession", sess.getId());
		
		cs.setMaxAge(7 * 24 * 3600);
		resp.addCookie(cs);
		
	}
	
	

}

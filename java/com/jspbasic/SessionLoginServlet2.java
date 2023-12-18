package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogin.do")
public class SessionLoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SessionLoginServlet2() {
        super();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		//id = asdf | pw = 1234
		if (userId.equals("asdf") && userPw.equals("1234")) {
			// 로그인 성공 시 session 객체에 로그인 정보 남김
			HttpSession sess = request.getSession(); // sessin 객체를 생성하여 불러옴

			System.out.println("세션 id : " + sess.getId());
			
			sess.setAttribute("loginMemberId", userId);
			sess.setAttribute("loginMemberPwd", userPw);
			
			response.sendRedirect("./mainTest.jsp?status=loginSuccess");
		}
		

		
	}

}

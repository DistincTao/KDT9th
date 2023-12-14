package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogin.do")
public class SessionLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SessionLoginServlet() {
        super();

    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		//id = asdf | pw = 1234
		if (userId.equals("asdf") && userPw.equals("1234")) {
			HttpSession sess = request.getSession();

			System.out.println("세션 id : " + sess.getId());
			
			sess.setAttribute("loginMemberId", userId);
			response.sendRedirect("./mainTest.jsp?status=loginSuccess");
		}
		

		
	}

}

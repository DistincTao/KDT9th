package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendRedirect.do")
public class sendRedirect extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public sendRedirect() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		PrintWriter out = response.getWriter();
		
		//id = asdf | pw = 1234
		if (userId.equals("asdf") && userPw.equals("1234")) {
			request.setAttribute("userId", userId);
			request.setAttribute("userPw", userPw);

			out.print("<script> alert(" + userId + "님 로그인 성공!!) </script>") ;
			// 로그인 성공
			response.sendRedirect("./mainTest.jsp?status=loginSuccess"); // 자바코드 우선 실행
		}

	}

}

package com.jspbasic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogout.do")
public class SessionLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SessionLogoutServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		sess.removeAttribute("loginMemberId"); // 로그인 정보 삭제 => 세션 값은 변하지 않음
		sess.invalidate(); // 세션 무효화 => 세션 갱신 (새로운 세션 번호 부여됨)
		
		System.out.println("세션 id (로그아웃) : " + sess.getId());

		
		response.sendRedirect("./mainTest.jsp?status=logoutSuccess");
	}

}
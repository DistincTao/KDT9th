package com.jspbasic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import com.jspbasic.dto.MemberDTO;

@WebServlet ("/elScope.do")
public class ElScopeTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num1 = 5, num2 = 3;
		int result = num1 - num2;
		
		MemberDTO member = new MemberDTO("dooly","1234", null, "010-1111-1111", null, 20, "male", null, null, null);

		req.setAttribute("member", member);
		
		HttpSession ses = req.getSession();
		ses.setAttribute("result", result);
		
		req.getRequestDispatcher("elScope.jsp").forward(req, resp);
	}
	
	
	
	
}

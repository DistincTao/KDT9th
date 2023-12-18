package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageMovingServlet
 */
@WebServlet("/pagemove.do")
public class PageMovingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PageMovingServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 페이지 이동 방법 => 2), 3) 번 방법은 매우 중요!!! 반드시 알아 둘 것!!!!
		
		// 1) JS의 location.href를 이용
//		out.print("<script>");
//		out.print("location.href='mainTest.jsp'");
//		out.print("</script>");
		
		// 2) response 객체의 sendRedirect()를 이용
//		response.sendRedirect("mainTest.jsp");
		
		// 3) requestDispatcher 객체를 이용 
		// => 페이지 이동이 이뤄지지만 요청했던 url 주소가 변경되지 않음
		// 데이터를 바인딩하여 데이터를 보낼 때 사용
//		request.getRequestDispatcher("mainTest.jsp").forward(request, response);
	
		// 4) meta tag의 refresh 이용
		out.print("<html>");
		out.print("<head>");
		out.print("<meta http-equiv='refresh' content='5; url=mainTest.jsp'>");
		out.print("</head>");
		out.print("</html>");
		
		out.flush();
		out.close();
		
	}

}

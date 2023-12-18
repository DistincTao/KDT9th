package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.filters.EncodingFilter;


@WebServlet("/CallServlet_GET")
public class CallServlet_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CallServlet_GET() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("utf-8");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int total = kor + eng + math;
		float avg = Math.round(total / 3f * 100) / 100;
		
//		response.setContentType("text/html; charset=utf-8");
//		출력 방법 (1)
//		PrintWriter out = response.getWriter();
//		
//		out.print("<!DOCTYPE html>"
//				+ "<html>"
//					+ "<head>"
//						+ "<meta charset=\"UTF-8\">"
//						+ "<title>성적표</title>"
//					+ "</head>"
//					+ "<body>"
//						+ "<h1> 낚이지 마세요~~~</h1>"
//						+ "<div> 국어 : " + kor + "수학 : " + eng + "영어 : " + math + "총점 : " + total + "평균 : " + avg + "</div>"
//					+ "</body>"
//				+ "</html>");	
//		
//		out.flush();
//		out.close();	
		
//		출력 방법 (2)
		// 출력할 변수 바인딩 (binding) : 요청이 들어온 곳에 데이터를 묶어줌
		request.setAttribute("kor", kor);
		request.setAttribute("eng", eng);
		request.setAttribute("math", math);
		request.setAttribute("total", total);
		request.setAttribute("avg", avg);
		
		// 전송 => 바인딩한 내용 보내기
		RequestDispatcher rd = request.getRequestDispatcher("./dataOutputFromServlet.jsp");
		rd.forward(request, response);
	
	}

}

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


@WebServlet("/Score_GET")
public class Score_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Score_GET() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		int total = kor + eng + math;
		float avg = Math.round(total / 3f * 100) / 100;
		
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		
		// 국어 점수 유효성 검사
		if (kor < 0 || kor > 100) {
			out.print("<script>");
			out.print("alert('국어점수가 잘못 입력되었습니다');");
			out.print("location.href='./scoreValidation.jsp';");
			out.print("</script>");
		}
		
		if (eng < 0 || eng > 100) {
			out.print("<script>");
			out.print("alert('영어점수가 잘못 입력되었습니다');");
			out.print("location.href='./scoreValidation.jsp';");
			out.print("</script>");
		}
		
		if (math < 0 || math > 100) {
			out.print("<script>");
			out.print("alert('수학점수가 잘못 입력되었습니다');");
			out.print("location.href='./scoreValidation.jsp';");
			out.print("</script>");
		}
		
		response.setContentType("text/html; charset=utf-8");

//		PrintWriter out = response.getWriter();
		
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
					+ "<head>\r\n"
						+ "<meta charset=\"UTF-8\">\r\n"
						+ "<title>성적표</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
						+ "<h1> 낚이지 마세요~~~</h1>\r\n"
						+ "<div> 국어 : " + kor + "수학 : " + eng + "영어 : " + math + "총점 : " + total + "평균 : " + avg + "</div>\r\n"
					+ "</body>\r\n"
				+ "</html>\r\n");	
		
		out.flush();
		out.close();	
		
	}

}

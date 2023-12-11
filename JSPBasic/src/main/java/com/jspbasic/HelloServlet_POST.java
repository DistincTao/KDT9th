package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloServlet_POST")
public class HelloServlet_POST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet_POST() {
        super();
    }


//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		System.out.println("Post 방식으로 호출됨 ");
		request.setCharacterEncoding("UTF-8"); // 응답할 문서의 종류룰 지정
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
	
		System.out.println("name : " + name + ", age : " + age );
		
		response.setContentType("text/html; charset=utf-8"); // 응답할 문서의 종류룰 지정
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html lang=\"KO\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>이름 나이 찍기</title>"
				+ "</head>"
				+ "<body>"
				+ "<div>" + name + "</div>"
				+ "<div>" + age + "</div>"
				+ "</body>"
				+ "</html>"
				);
		
		out.flush();
		out.close();	

	}

}

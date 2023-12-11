package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet_GET
 */
@WebServlet("/hGET") // 이동할 주소를 () 안에 작성 => Mapping 주소
public class HelloServlet_GET extends HttpServlet {
	private static final long serialVersionUID = 1L; // 클래스를 구분하는 번호
       
    public HelloServlet_GET() { // 기본 생성자
        super();
    }

    // GET 방식 요청시 실행
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath()); // contextPath => 프로젝트명으로 지정됨
		System.out.println("GET 방식이 호출됨");
		
		String name = request.getParameter("name");
		System.out.println(name);
		
		response.setContentType("text/html; charset=utf-8"); // 응답할 문서의 종류룰 지정
		
		PrintWriter out = response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html lang=\"KO\">\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>이름을 찍어보자</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1>" + name
				+ "</h1>"
				+ "</body>"
				+ "</html>"
				);
		
		out.flush();
		out.close();
		
	}

    // POST 방식 요청시 실행
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//		System.out.println("POST 방식이 호출됨");

	}

}

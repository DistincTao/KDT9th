package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculateTriangle_GET")
public class CalculateTriangle_GET extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalculateTriangle_GET() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GET 방식 호출 됨");
		
		int base = Integer.parseInt(request.getParameter("base")) ;
		int height = Integer.parseInt(request.getParameter("height"));
		double area = base * height / 2d;
		
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print ("<!DOCTYPE html>"
				+ "<html>"
					+ "<head>"
						+ "<meta charset=\"UTF-8\">"
						+ "<title>삼각형 넓이 계산</title>"
					+ "</head>"
					+ "<body>"
						+ "<h1> 계산 결과 ~ </h1>"
						+ "<div> 밑변 : " + base + "cm, 높이 : " + height + "cm인 삼각형의 넓이는 : " + area + "cm<sup>2</sup>"
					+ "</body>"
				+ "</html>");	
		
		out.flush();
		out.close();	
		
	}

}

package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserData_POST")
public class UserData_POST extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserData_POST() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 방식 전송");
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		response.setContentType("text/html; charset=utf-8" );
		
		PrintWriter out = response.getWriter();
		out.print ("<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "<meta charset=\"UTF-8\">"
				+ "<title>User 정보</title>"
				+ "</head>"
				+ "<body>"
				+ "<h1> 낚이지 마세요~~~</h1>"
				+ "<div> 이름 : " + name + ", ID : " + id + ",  PW : " + pw + "</div>"
				+ "</body>"
				+ "</html>");	
		
		out.flush();
		out.close();	
	}

}

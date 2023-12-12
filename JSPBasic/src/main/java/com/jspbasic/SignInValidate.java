package com.jspbasic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/completeForm")
public class SignInValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignInValidate() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String psw1 = request.getParameter("psw1");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String bDate = request.getParameter("bDate");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String gender = request.getParameter("gender");
		
		String[] hobby = request.getParameterValues("hobby");
		String hobbyList = "";
		for (int i = 0; i < hobby.length; i++) {
			hobbyList += (hobby[i] + " ");
		}
		
		String job = request.getParameter("job");	
		String memo = request.getParameter("memo");
		
		
		
		System.out.println("ID : " + userId);
		System.out.println("PW : " + psw1);
		System.out.println("Email : " + email);
		System.out.println("Mobile : " + mobile);
		System.out.println("Birth Day : " + bDate);
		System.out.println("Age : " + age);
		
		System.out.println("Gender : " + gender);
		System.out.println("Hobby : " + hobbyList);			


		System.out.println("Job : " + job);
		System.out.println("Memo : " + memo);
		
		request.setAttribute("userId", userId);
		request.setAttribute("psw1", psw1);
		request.setAttribute("email", email);
		request.setAttribute("mobile", mobile);
		request.setAttribute("bDate", bDate);
		request.setAttribute("age", age);
		
		request.setAttribute("gender", gender);
		request.setAttribute("hobby", hobbyList);
		request.setAttribute("job", job);
		request.setAttribute("memo", memo);
		
		RequestDispatcher rd = request.getRequestDispatcher("./confirmation.jsp");
		rd.forward(request, response);
		
		
	}

}

package com.jspbasic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspbasic.dto.MemberDTO;


@WebServlet({"/vInput", "/tInput"})
public class SignInValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignInValidate() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("remember") != null && request.getParameter("remember").equals("Y")) {
			String userId = request.getParameter("userId");
			String psw1 = request.getParameter("psw1");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			String bDate = request.getParameter("bDate");
			Date birthday = Date.valueOf(bDate);
			int age = Integer.parseInt(request.getParameter("age"));
			
			String gender = request.getParameter("gender");
			
			String[] hobby = request.getParameterValues("hobby");
			String hobbies = "";
			for (String h: hobby) {
				hobbies += h + " ";
			}
			
			String job = request.getParameter("job");	
			String memo = request.getParameter("memo");
			
			System.out.println("ID : " + userId);
			System.out.println("PW : " + psw1);
			System.out.println("Email : " + email);
			System.out.println("Mobile : " + mobile);
			System.out.println("Birth Day : " + birthday);
			System.out.println("Age : " + age);
			
			System.out.println("Gender : " + gender);
			System.out.println("Hobby : " + hobbies);			

			System.out.println("Job : " + job);
			System.out.println("Memo : " + memo);			
			
			// Member 객체를 Request 바인딩
			MemberDTO member = new MemberDTO(userId, psw1, email, mobile, birthday, age, gender, hobbies, job, memo);
			request.setAttribute("memberInfo", member);
			
			RequestDispatcher rd = request.getRequestDispatcher("./outputMember.jsp");
			rd.forward(request, response);	
			
		} else {
			PrintWriter out = response.getWriter();
			out.print("<html>\r\n <body>\r\n");
			out.print("동의 항목을 체크해 주세요");
			out.print("<a href='javascript:history.go(-1)'> 돌아가기 </a> \r\n");
			out.print("</body>\r\n</html>");
		}
		
	}

}

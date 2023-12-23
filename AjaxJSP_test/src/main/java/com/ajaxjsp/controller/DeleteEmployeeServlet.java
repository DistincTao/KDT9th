package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.OutputJsonForError;

@WebServlet("/deleteEmployee.do")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteEmployeeServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteEmployeeServlet 서블릿 테스트");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		int empId = (int) request.getAttribute("empId");
		
		try {
			dao.deleteEmployee(empId);
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}
		
		
		
		response.sendRedirect("index.jsp");
	}

}

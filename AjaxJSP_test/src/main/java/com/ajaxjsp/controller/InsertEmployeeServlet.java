package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.dto.EmployeeDto;
import com.ajaxjsp.etc.OutputJsonForError;


@WebServlet("/insertEmployee.do")
public class InsertEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    public InsertEmployeeServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		EmployeeDto dto = new EmployeeDto(request.getParameter("first_name"), 
										  request.getParameter("last_name"),
										  request.getParameter("email"),
										  request.getParameter("phone_number"),
										  Date.valueOf(request.getParameter("hire_date")),
										  request.getParameter("job_id"),
										  Double.parseDouble(request.getParameter("salary")),
										  Double.parseDouble(request.getParameter("commition_pct")),
										  Integer.parseInt(request.getParameter("manager_id")),
										  Integer.parseInt(request.getParameter("department_id")));
	
		try {
			int empId = dao.getNextEmpId();
			dao.insertEmployee(empId, dto); 
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}

		response.sendRedirect("index.jsp");
	}

}

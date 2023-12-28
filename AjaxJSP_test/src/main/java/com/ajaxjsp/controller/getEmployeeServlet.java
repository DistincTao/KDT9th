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
import com.ajaxjsp.etc.JsonParsing;
import com.ajaxjsp.vo.EmployeeVo;


@WebServlet("/getEmployee.do")
public class getEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getEmployeeServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		int employee_id = Integer.parseInt(request.getParameter("empNo"));
		PrintWriter out = response.getWriter();
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		
		try {
			EmployeeVo vo = dao.selectEmployeeByEmpId(employee_id);
			JsonParsing jsonData = new JsonParsing();
			String strJson = jsonData.toJsonWithJsonSimple(vo);
			out.print(strJson);
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

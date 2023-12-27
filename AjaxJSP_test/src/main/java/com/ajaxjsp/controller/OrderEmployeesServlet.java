package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.JsonParsing;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.EmployeeVo;


@WebServlet("/orderByEmp.do")
public class OrderEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OrderEmployeesServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderEmployeesServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		String orderMethod = request.getParameter("sortOrder");
		
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		try {
			List<EmployeeVo> list = dao.empSortByOrder(orderMethod);
			JsonParsing jsonData = new JsonParsing();
			String strJson = jsonData.toJsonWithJsonSimple(list);
			out.print(strJson);
			out.flush();
			out.close();
			
		} catch (SQLException | NamingException e) {
			OutputJsonForError.outputJson(e);
			e.printStackTrace();
		}
		
		
	}

}

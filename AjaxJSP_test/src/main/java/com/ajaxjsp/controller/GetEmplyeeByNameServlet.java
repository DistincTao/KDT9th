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
import com.ajaxjsp.dto.EmployeeByNameDto;
import com.ajaxjsp.etc.JsonParsing;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.EmployeeVo;

@WebServlet("/getEmpByName.do")
public class GetEmplyeeByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetEmplyeeByNameServlet() {
        super();
    }


//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("GetEmplyeeByNameServlet 서블릿 테스트");
//		response.setContentType("application/json; charset=utf-8");
//		EmployeesDao dao = EmployeesDaoImpl.getInstance();
//		PrintWriter out = response.getWriter();
//		
//		String [] fullName = request.getParameter("byName").split(" ");
//
//		EmployeeByNameDto dto = new EmployeeByNameDto();
//		String firstName = fullName[0];
//		String lastName = fullName[1];
//		dto.setFirst_name(firstName);
//		dto.setLast_name(lastName);
//
//		try {
//			List<EmployeeVo> list = dao.selectEmployeeByName(dto);
//			JsonParsing jsonData = new JsonParsing();
//			String strJson = jsonData.toJsonWithJsonSimple(list);
//
//			out.print(strJson);
//			out.flush();
//			out.close();
//			
//		} catch (SQLException | NamingException e) {
//			OutputJsonForError.outputJson(e);
//			e.printStackTrace();
//		}
//	}

    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetEmplyeeByNameServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String findEmpName = request.getParameter("byName");
		String orderMethod = request.getParameter("sortOrder");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		System.out.println(findEmpName + "1");
		System.out.println(orderMethod);

		try {
			List<EmployeeVo> list = dao.selectEmployeeByName(findEmpName, orderMethod);
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

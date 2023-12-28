package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.JsonParsing;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.EmployeeVo;

@WebServlet("/getAllEmployees.do")
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllEmployeesServlet() {
        super();
    }

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
//		response.setContentType("application/json; charset=utf-8");
//		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();
//		PrintWriter out = response.getWriter();
//		
//		try {
////			List<EmployeeVo> list = dao.selectAllEmployees();
//			List<EmployeeVo> list = dao.selectAllEmployees();
//			// 출력 테스트 (dao 단에서 반환되어 왔는지)
//			
//			JsonParsing jsonData = new JsonParsing();
//			String strJson = jsonData.toJsonWithJsonSimple(list);
////			System.out.println(outputJson);
//			
//
//			out.print(strJson);
//			out.close();
//			
////			for (EmployeeVo vo : list) {
////				System.out.println(vo.toString());
////			}
//
//		} catch (SQLException | NamingException e) {
//			out.print(OutputJsonForError.outputJson(e));
//			e.printStackTrace();
//		}
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		String searchName = request.getParameter("searchName");
		String orderMethod = request.getParameter("orderMethod");
		
		try {
//			List<EmployeeVo> list = dao.selectAllEmployees();
			List<EmployeeVo> list = dao.selectAllEmployees(searchName, orderMethod);
			// 출력 테스트 (dao 단에서 반환되어 왔는지)
			
			JsonParsing jsonData = new JsonParsing();
			String strJson = jsonData.toJsonWithJsonSimple(list);
//			System.out.println(outputJson);
			
			
			out.print(strJson);
			out.close();
			
//			for (EmployeeVo vo : list) {
//				System.out.println(vo.toString());
//			}
			
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}
	}
}

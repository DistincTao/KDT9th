package com.ajaxjsp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.vo.EmployeeVo;



@WebServlet("/getAllEmployees.do")
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetAllEmployeesServlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릭 테스트");

		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();

		try {
			List<EmployeeVo> list = dao.selectAllEmployees();
			// 출력 테스트 (dao 단에서 반환되어 왔는지)
			
			for (EmployeeVo vo : list) {
				System.out.println(vo.toString());
			}
			
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private String toJsonWithJsonSimple(List<EmployeeVo> list) {
		JSONObject json = new JSONObject();
		return null;
		
		
	}

}

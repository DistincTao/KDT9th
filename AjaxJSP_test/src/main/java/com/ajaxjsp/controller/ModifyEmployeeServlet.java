package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.dto.EmployeeDto;
import com.ajaxjsp.etc.OutputJsonForError;


@WebServlet("/modifyEmployee.do")
public class ModifyEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyEmployeeServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ModifyEmployeeServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		System.out.println(request.getParameter("employee_id"));
		EmployeeDto dto = new EmployeeDto(Integer.parseInt(request.getParameter("employee_id")),
										  request.getParameter("first_name"), 
				  						  request.getParameter("last_name"),
				  						  request.getParameter("email"),
										  request.getParameter("phone_number"),
										  Date.valueOf(request.getParameter("hire_date")),
										  request.getParameter("job_id"),
										  Double.parseDouble(request.getParameter("salary")),
										  Double.parseDouble(request.getParameter("commition_pct")),
										  Integer.parseInt(request.getParameter("manager_id")),
										  Integer.parseInt(request.getParameter("department_id")));
		
		System.out.println(dto.toString() + " 3");
		try {
			int result = dao.modifyEmployee(dto);
			if (result == 1){ // 사원정보 수정 성공
				JSONObject json = new JSONObject();
				json.put("status" , "success");
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String outputDate = fmt.format(Calendar.getInstance().getTime());
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
				
			} else if (result != 1) { // 사원정보 수정 실패
				JSONObject json = new JSONObject();
				json.put("status" , "fail");
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String outputDate = fmt.format(Calendar.getInstance().getTime());
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
			}
			
		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}
		out.flush();
		out.close();
		
	}

}

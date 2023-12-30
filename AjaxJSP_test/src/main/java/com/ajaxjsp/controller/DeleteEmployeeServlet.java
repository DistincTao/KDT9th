package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ajaxjsp.etc.OutputJsonForError;

@WebServlet("/deleteEmployee.do")
public class DeleteEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteEmployeeServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteEmployeeServlet 서블릿 테스트");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		int empId = Integer.parseInt(request.getParameter("employee_id"));
		
		try {
			int result = dao.deleteEmployee(empId);
			if (result == 1){ // 사원정보 삭제 성공
				JSONObject json = new JSONObject();
				json.put("status" , "success");
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String outputDate = fmt.format(Calendar.getInstance().getTime());
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
				
			} else if (result != 1) { // 사원정보 삭제 실패
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
		
		response.sendRedirect("index.jsp");
	}

}

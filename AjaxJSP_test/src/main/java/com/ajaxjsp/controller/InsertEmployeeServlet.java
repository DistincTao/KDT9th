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


@WebServlet("/insertEmployee.do")
public class InsertEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 

    public InsertEmployeeServlet() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertEmployeeServlet 서블릿 테스트");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		response.setContentType("application/json; charset=utf-8");
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
	System.out.println(dto.toString());
		try {
//			int empId = dao.getNextEmpId();
//			dao.insertEmployee(empId, dto);
			
			String result = dao.insertEmployee(dto);
			if (result.equals("SUCCESS")){
				JSONObject json = new JSONObject();
				json.put("status" , "success");
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
				String outputDate = fmt.format(Calendar.getInstance().getTime());
				json.put("outputDate", outputDate);
				
				out.print(json.toJSONString());
				
			} else if (result.equals("ERROR")) {
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

//		response.sendRedirect("index.jsp"); // 데이터 전송 이후에는 다시 리로드 불가
	}

}

//인사 오지게 박겠습니다 행님!!! 
//안녕하세요 태호햄 저는 조현성이라고 합니다. 앞으로도 잘부탁드립니다.
//행님 덕분에 코딩실력이 늘고있습니다 항상 감사한 마음입니다. 
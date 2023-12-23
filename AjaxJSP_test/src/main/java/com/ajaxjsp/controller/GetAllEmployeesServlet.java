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
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.EmployeeVo;

@WebServlet("/getAllEmployees.do")
public class GetAllEmployeesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllEmployeesServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetAllEmployeesServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDaoImpl dao = EmployeesDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		try {
			List<EmployeeVo> list = dao.selectAllEmployees();
			// 출력 테스트 (dao 단에서 반환되어 왔는지)
			String outputJson = toJsonWithJsonSimple(list);
//			System.out.println(outputJson);
			

			out.print(outputJson);
			out.close();
			
//			for (EmployeeVo vo : list) {
//				System.out.println(vo.toString());
//			}

		} catch (SQLException | NamingException e) {
			out.print(OutputJsonForError.outputJson(e));
			e.printStackTrace();
		}
	}

	private String toJsonWithJsonSimple(List<EmployeeVo> list) {
		JSONObject json = new JSONObject();
		
		json.put("status", "success");
//		String outputDate = new Date(System.currentTimeMillis()).toLocaleString();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String outputDate = fmt.format(Calendar.getInstance().getTime());
		json.put("outputDate", outputDate);
		json.put("count", list.size());
		
		if (list.size() > 0) {
			JSONArray empArr = new JSONArray();
			for (EmployeeVo vo : list) {
				JSONObject emp = new JSONObject();
				emp.put("employee_id", vo.getEmployee_id() + "");
				emp.put("first_name", vo.getFirst_name());
				emp.put("last_name", vo.getLast_name());
				emp.put("email", vo.getEmail());
				emp.put("phone_number", vo.getPhone_number());
				
				Date tempDate = vo.getHire_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				emp.put("hire_date", sdf.format(tempDate));
				emp.put("job_id", vo.getJob_id());
				emp.put("salary", vo.getSalary() + "");
				emp.put("commition_pct", vo.getCommition_pct() + "");
				emp.put("manager_id", vo.getManager_id() + "");
				emp.put("department_id", vo.getDepartment_id() + "");
				emp.put("department_name", vo.getDepartment_name());
				
				empArr.add(emp);				
			}
			
			json.put("employees", empArr);
		}
		return json.toJSONString();
	}
	
}

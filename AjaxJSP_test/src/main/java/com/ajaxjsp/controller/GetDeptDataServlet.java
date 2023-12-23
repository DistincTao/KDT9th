package com.ajaxjsp.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.ajaxjsp.dao.EmployeesDao;
import com.ajaxjsp.dao.EmployeesDaoImpl;
import com.ajaxjsp.etc.OutputJsonForError;
import com.ajaxjsp.vo.DepartmentVo;

@WebServlet("/getDeptData.do")
public class GetDeptDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetDeptDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GetDetpSelectionServlet 서블릿 테스트");
		response.setContentType("application/json; charset=utf-8");
		EmployeesDao dao = EmployeesDaoImpl.getInstance();
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		
		try {
			List<DepartmentVo> list = dao.selectAllDepts();
			
			json.put("status", "success");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			String outputDate = fmt.format(Calendar.getInstance().getTime());
			json.put("outputDate", outputDate);
			json.put("count", list.size());
			
			if (list.size() > 0) {
				JSONArray deptArr = new JSONArray();
				for (DepartmentVo vo : list) {
					JSONObject dept = new JSONObject();
					dept.put("department_id", vo.getDepartment_id() + "");
					dept.put("department_name", vo.getDepartment_name());
					dept.put("manager_id", vo.getManager_id() + "");
					dept.put("location_id", vo.getLocation_id() + "");
					
					
					deptArr.add(dept);				
				}
				
				json.put("departments", deptArr);
			}
		

			out.print(json.toJSONString());
			out.close();
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			out.print(OutputJsonForError.outputJson(e));
		}
		
	}

}

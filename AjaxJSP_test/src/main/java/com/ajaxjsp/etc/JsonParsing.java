package com.ajaxjsp.etc;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajaxjsp.vo.EmployeeVo;

public class JsonParsing {
	public String toJsonWithJsonSimple(List<EmployeeVo> list) {
		JSONObject json = new JSONObject();
		
		json.put("status", "success");
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
	
	public String toJsonWithJsonSimple(EmployeeVo vo) {
		JSONObject json = new JSONObject();

		if (vo != null) {
			
			json.put("status", "success");
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			String outputDate = fmt.format(Calendar.getInstance().getTime());
			json.put("outputDate", outputDate);
			
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
					
			json.put("employee", emp);	
			
			

		}
		return json.toJSONString();
	}
		

}

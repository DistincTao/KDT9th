package com.distinctao.controller;

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

import com.distinctao.dao.EmpDao;
import com.distinctao.dao.EmpDaoImpl;
import com.distinctao.vo.EmpVo;

/**
 * Servlet implementation class GetAllEmpServlet
 */
@WebServlet("/getAllEmp.do")
public class GetAllEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllEmpServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajax 통신 성공");
		response.setContentType("application/json; charset=utf-8");
		EmpDao dao = EmpDaoImpl.getInstance();
		
		try {
			List<EmpVo> list = dao.selectAll();
			String outputJson = toJsonWithGson(list);
		
			PrintWriter out = response.getWriter();
			out.print(outputJson);
			out.close();
			
			for (EmpVo vo : list) {
				System.out.println(vo.toString());
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String toJsonWithGson(List<EmpVo> list) {
		JSONObject json = new JSONObject();
		json.put("status", "success");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		String outputDate = sdf.format(Calendar.getInstance().getTime());
		json.put("outputDate", outputDate);
		json.put("count", list.size());
		
		if (list.size() > 0) {
			JSONArray empArr = new JSONArray();
			for (EmpVo vo : list) {
				JSONObject emp = new JSONObject();
				emp.put("EMPNO", vo.getEmpno() + "");
				emp.put("ENAME", vo.getEname());
				emp.put("JOB", vo.getJob());
				emp.put("MGR", vo.getMgr() + "");
				
				Date temp = vo.getHiredate();
				SimpleDateFormat hireDate = new SimpleDateFormat("yyyy년 MM월 dd일");			
				emp.put("HIREDATE", hireDate.format(temp));
				emp.put("SAL", vo.getSal() + "");
				emp.put("COMM", vo.getComm() + "");
				emp.put("DEPTNO", vo.getDeptno() + "");
				emp.put("DEPTNO", vo.getDname());
				
				empArr.add(emp);
			}

			json.put("emp", empArr);
		}

		return json.toString();
	}

}

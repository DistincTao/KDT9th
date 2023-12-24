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
import com.distinctao.vo.DeptVo;
import com.distinctao.vo.EmpVo;


@WebServlet("/getAllDept.do")
public class GetAllDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllDeptServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajax 통신 성공");
		response.setContentType("application/json; charset=utf-8");
		EmpDao dao = EmpDaoImpl.getInstance();
		PrintWriter out = response.getWriter();
		
		try {
			List<DeptVo> list = dao.getDept();
			JSONObject json = new JSONObject();
			json.put("status", "success");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
			String outputDate = sdf.format(Calendar.getInstance().getTime());
			json.put("outputDate", outputDate);
			json.put("count", list.size());
			
			if (list.size() > 0) {
				JSONArray deptArr = new JSONArray();
				for (DeptVo vo : list) {
					JSONObject dept = new JSONObject();
					dept.put("DEPTNO", vo.getDeptNo() + "");
					dept.put("DNAME", vo.getDname());
					dept.put("LOC", vo.getLoc());
	
					deptArr.add(dept);
				}

				json.put("dept", deptArr);
			}

			out.print(json.toString());
			out.close();
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

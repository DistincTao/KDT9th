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

@WebServlet("/getQuitEmp.do")
public class GetQuitEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetQuitEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ajax 통신 성공 - GetAllEmpSevlet doGet");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		EmpDao dao = EmpDaoImpl.getInstance();
		String order = request.getParameter("order");
		
		try {
			List<EmpVo> list = dao.selectQuits(order);
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
					SimpleDateFormat simpledf = new SimpleDateFormat("yyyy년 MM월 dd일");			
					emp.put("HIREDATE", simpledf.format(temp));
					emp.put("SAL", vo.getSal() + "");
					emp.put("COMM", vo.getComm() + "");
					emp.put("DEPTNO", vo.getDeptno() + "");
					emp.put("DNAME", vo.getDname());
					emp.put("QUITDATE", simpledf.format(vo.getQuitdate()));
					
					empArr.add(emp);
				}

				json.put("emp", empArr);
			}
			out.print(json.toString());
			out.close();
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}

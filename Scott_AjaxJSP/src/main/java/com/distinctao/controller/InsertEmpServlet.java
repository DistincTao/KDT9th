package com.distinctao.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.distinctao.dao.EmpDao;
import com.distinctao.dao.EmpDaoImpl;
import com.distinctao.dto.EmpDto;

@WebServlet("/insertEmp.do")
public class InsertEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertEmpServlet() {
        super();
        
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InsertEmpServlet 호출");
		EmpDao dao = EmpDaoImpl.getInstance();
		EmpDto dto = new EmpDto(request.getParameter("name"),
								request.getParameter("job"),
								Integer.parseInt(request.getParameter("mgr")),
								Date.valueOf(request.getParameter("hiredate")),
								Double.parseDouble(request.getParameter("sal")),
								Double.parseDouble(request.getParameter("comm")),
								Integer.parseInt(request.getParameter("deptNo")));
		int empNo;
		try {
			empNo = dao.getEmpNo();
			dao.insertEmp(empNo, dto);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
	}

}

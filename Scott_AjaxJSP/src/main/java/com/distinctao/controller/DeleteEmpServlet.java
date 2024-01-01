package com.distinctao.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.distinctao.dao.EmpDao;
import com.distinctao.dao.EmpDaoImpl;
import com.distinctao.vo.EmpVo;

@WebServlet("/deleteEmp.do")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteEmpServlet() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDao dao = EmpDaoImpl.getInstance();
		int empId = Integer.parseInt(request.getParameter("empNo"));
		try {
			EmpVo vo = dao.transEmp(empId);
			dao.deleteEmp(vo);	
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
	}

}

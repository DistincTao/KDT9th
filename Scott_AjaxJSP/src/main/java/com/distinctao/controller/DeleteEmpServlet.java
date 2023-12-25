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

@WebServlet("/deleteEmp.do")
public class DeleteEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteEmpServlet() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpDao dao = EmpDaoImpl.getInstance();
		int empId = Integer.parseInt(request.getParameter("empId"));
		try {
			dao.deleteEmp(empId);
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("index.jsp");
	}

}

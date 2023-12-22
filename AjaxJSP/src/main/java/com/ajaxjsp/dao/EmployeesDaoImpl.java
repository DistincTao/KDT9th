package com.ajaxjsp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.vo.EmployeeVo;


public class EmployeesDaoImpl implements EmployeesDao {
	private static EmployeesDaoImpl instance = new EmployeesDaoImpl();
	
	private EmployeesDaoImpl() {}
	
	public static EmployeesDaoImpl getInstance() {
		
		if (instance == null) {
			instance = new EmployeesDaoImpl();
		}
		return instance;
	}

	
	
	@Override
	public List<EmployeeVo> selectAllEmployees() throws SQLException, NamingException {

		System.out.println("DAO 출력");
		
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		List<EmployeeVo> employeeList = new ArrayList<>();
		ResultSet rs = null;
		
		if (con != null) {
			String query = "SELECT E.*, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeList.add(new EmployeeVo(rs.getInt("EMPLOYEE_ID"), 
						rs.getString("FIRST_NAME"), 
						rs.getString("LAST_NAME"), 
						rs.getString ("EMAIL"), 
						rs.getString ("PHONE_NUMBER"),
						rs.getDate ("HIRE_DATE"), 
						rs.getString ("JOB_ID"), 
						rs.getDouble ("SALARY"), 
						rs.getDouble ("COMMISSION_PCT"), 
						rs.getInt ("MANAGER_ID"),
						rs.getInt ("DEPARTMENT_ID"), 
						rs.getString ("DEPARTMENT_NAME")));
			}
			
			DBConnection.dbClose(rs, pstmt, con);
		}

		return employeeList;
	}

}

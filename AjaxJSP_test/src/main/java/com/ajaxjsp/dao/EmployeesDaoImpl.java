package com.ajaxjsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.dto.EmployeeDto;
import com.ajaxjsp.vo.DepartmentVo;
import com.ajaxjsp.vo.EmployeeVo;
import com.ajaxjsp.vo.JobsVo;




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

	@Override
	public List<JobsVo> selectAlljobs() throws SQLException, NamingException {
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<JobsVo> jobList = new ArrayList<>();
		
		if (con != null) {
			String query = "SELECT * FROM JOBS";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				jobList.add(new JobsVo(rs.getString("JOB_ID"), 
									   rs.getString("JOB_TITLE"), 
									   rs.getInt("MIN_SALARY"), 
									   rs.getInt ("MAX_SALARY")));
			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		return jobList;
	}

	@Override
	public List<DepartmentVo> selectAllDepts() throws SQLException, NamingException {
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DepartmentVo> deptList = new ArrayList<>();
		
		if (con != null) {
			String query = "SELECT * FROM DEPARTMENTS";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				deptList.add(new DepartmentVo(rs.getInt("DEPARTMENT_ID"), 
											  rs.getString("DEPARTMENT_NAME"), 
											  rs.getInt("MANAGER_ID"), 
											  rs.getInt ("LOCATION_ID")));

			}
			DBConnection.dbClose(rs, pstmt, con);
		}
		return deptList;
	}

	@Override
	public void insertEmployee(int empId, EmployeeDto dto) throws SQLException, NamingException {
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		
		if (con != null) {
			String query = " insert into employees values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empId);
			pstmt.setString(2, dto.getFirst_name());
			pstmt.setString(3, dto.getLast_name());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone_number());
			pstmt.setDate(6, dto.getHire_date());
			pstmt.setString(7, dto.getJob_id());
			pstmt.setDouble(8, dto.getSalary());
			pstmt.setDouble(9, dto.getCommition_pct());
			pstmt.setInt(10, dto.getManager_id());
			pstmt.setInt(11, dto.getDepartment_id());
			pstmt.executeUpdate();
			
		}
		DBConnection.dbClose(pstmt, con);
	}
	
	@Override
	public int getNextEmpId() throws SQLException, NamingException {
		int empId = 0;
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if (con != null) {
			String query = "SELECT MAX(EMPLOYEE_ID) AS MAX_NO FROM EMPLOYEES";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empId = rs.getInt("MAX_NO") + 1;
			}
			
		}
		
		return empId;
	}

	@Override
	public void deleteEmployee(int empId) throws SQLException, NamingException {
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		
		if (con != null) {
			String query = "DELETE from EMPLOYEES where employee_id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1,empId);
			pstmt.executeUpdate();
			
		}
		DBConnection.dbClose(pstmt, con);
		
	}
	
}

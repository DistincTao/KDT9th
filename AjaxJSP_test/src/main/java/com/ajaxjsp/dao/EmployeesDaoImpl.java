package com.ajaxjsp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.dto.EmployeeByNameDto;
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
	public List<EmployeeVo> selectAllEmployees(String name, String order) throws SQLException, NamingException {

		System.out.println(getClass().getName() + " DAO ");
		
		Connection con = DBConnection.dbConnect();
		PreparedStatement pstmt = null;
		List<EmployeeVo> employeeList = new ArrayList<>();
		ResultSet rs = null;
		String query = "SELECT E.*, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ";
		
		System.out.println(name);
		System.out.println(order);
		if (name.equals("")) {
			query += "";
		} else {
			query += "WHERE UPPER(FIRST_NAME) LIKE '%" + name.toUpperCase() + "%' OR UPPER(LAST_NAME) LIKE '%" + name.toUpperCase() + "%' ";
		}
		
		if (order.equals("orderEmpAsc")) {
			query += "E.EMPLOYEE_ID ASC";
		} else if (order.equals("orderEmpDesc")) {
			query += "E.EMPLOYEE_ID DESC";
		} else if (order.equals("orderHireDateAsc")) {
			query += "E.HIRE_DATE DESC";
		} else if (order.equals("orderHireDateDesc")) {
			query += "E.HIRE_DATE ASC";
		} else if (order.equals("orderSalDesc")) {
			query += "E.SALARY DESC";
		} 
		
		System.out.println(query);
		if (con != null) {
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
		System.out.println(getClass().getName() + " DAO ");
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
		System.out.println(getClass().getName() + " DAO ");
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
	// 저장 프로시저를 사용하지 않는 경우
	
	@Override
	public void insertEmployee(int empId, EmployeeDto dto) throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
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
	// 저장 프로시저를 사용하는 경우
	@Override
	public String insertEmployee(EmployeeDto dto) throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
		Connection con = DBConnection.dbConnect();
		// 저장 프로시저 호출
		CallableStatement cstmt = null;
		String result = null;
		if (con != null) {
			String query = " {CALL PROCEDURE_INSERT_EMP(initcap(?), initcap(?), upper(?), ?, ?, ?, ?, ?, ?, ?, ?)}";
			cstmt = con.prepareCall(query);
			cstmt.setString(1, dto.getFirst_name());
			cstmt.setString(2, dto.getLast_name());
			cstmt.setString(3, dto.getEmail());
			cstmt.setString(4, dto.getPhone_number());
			cstmt.setDate(5, dto.getHire_date());
			cstmt.setString(6, dto.getJob_id());
			cstmt.setDouble(7, dto.getSalary());
			cstmt.setDouble(8, dto.getCommition_pct());
			cstmt.setInt(9, dto.getManager_id());
			cstmt.setInt(10, dto.getDepartment_id());
			cstmt.registerOutParameter(11, java.sql.Types.VARCHAR);
			// 저장 프로실행
			cstmt.executeUpdate();
			
			// out 매게변수에서 반환되는 변수 받아오기
			result = cstmt.getString(11);
			
			System.out.println(result);
			
		}
		DBConnection.dbClose(cstmt, con);
		return result;
	}
	
	@Override
	public int getNextEmpId() throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
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
		System.out.println(getClass().getName() + " DAO ");
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

	@Override
	public List<EmployeeVo> selectEmployeeByName(EmployeeByNameDto dto) throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
		
		Connection con = DBConnection.dbConnect();
		List<EmployeeVo> employeeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println(dto.getFirst_name());
		
		if (con != null) {
			String query = "SELECT E.*, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID WHERE LOWER(E.FIRST_NAME) LIKE ? AND LOWER(E.LAST_NAME) LIKE ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + dto.getFirst_name() + "%");
			pstmt.setString(2, "%" + dto.getLast_name() + "%");
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
	public List<EmployeeVo> selectEmployeeByName(String name, String order) throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
		
		Connection con = DBConnection.dbConnect();

		String query = "SELECT E.*, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID WHERE LOWER(E.FIRST_NAME) LIKE ? OR LOWER(E.LAST_NAME) LIKE ? ORDER BY ";
		System.out.println(order);
		
		if (order.equals("orderEmpAsc")) {
			query += "E.EMPLOYEE_ID ASC";
		} else if (order.equals("orderEmpDesc")) {
			query += "E.EMPLOYEE_ID DESC";
		} else if (order.equals("orderHireDateAsc")) {
			query += "E.HIRE_DATE DESC";
		} else if (order.equals("orderHireDateDesc")) {
			query += "E.HIRE_DATE ASC";
		} else if (order.equals("orderSalDesc")) {
			query += "E.SALARY DESC";
		} 
		
		System.out.println(query);
		List<EmployeeVo> employeeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		
		if (con != null) {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + name + "%");
			pstmt.setString(2, "%" + name + "%");
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
	public List<EmployeeVo> empSortByOrder(String orderMethod) throws SQLException, NamingException {
		System.out.println(getClass().getName() + " DAO ");
		
		Connection con = DBConnection.dbConnect();
		// 동적 SQL : orderMethod에 따라 쿼리문이 달라짐
		String query = "SELECT E.*, D.DEPARTMENT_NAME FROM EMPLOYEES E INNER JOIN DEPARTMENTS D ON E.DEPARTMENT_ID = D.DEPARTMENT_ID ORDER BY ";
		
		if (orderMethod.equals("orderEmpAsc")) {
			query += "E.EMPLOYEE_ID ASC";
		} else if (orderMethod.equals("orderEmpDesc")) {
			query += "E.EMPLOYEE_ID DESC";
		} else if (orderMethod.equals("orderHireDateAsc")) {
			query += "E.HIRE_DATE DESC";
		} else if (orderMethod.equals("orderHireDateDesc")) {
			query += "E.HIRE_DATE ASC";
		} else if (orderMethod.equals("orderSalDesc")) {
			query += "E.SALARY DESC";
		} 
		
		System.out.println(query);
		List<EmployeeVo> employeeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if (con != null) {
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

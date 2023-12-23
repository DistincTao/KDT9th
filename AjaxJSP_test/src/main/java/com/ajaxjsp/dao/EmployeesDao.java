package com.ajaxjsp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.dto.EmployeeDto;
import com.ajaxjsp.vo.DepartmentVo;
import com.ajaxjsp.vo.EmployeeVo;
import com.ajaxjsp.vo.JobsVo;



public interface EmployeesDao {
	// 모든 직원 정보를 얻어오는 메소드
	public abstract List<EmployeeVo> selectAllEmployees() throws SQLException, NamingException;
	public abstract List<JobsVo> selectAlljobs() throws SQLException, NamingException;
	public abstract List<DepartmentVo> selectAllDepts() throws SQLException, NamingException;
	public abstract void insertEmployee(int empId, EmployeeDto dto) throws SQLException, NamingException;
	public abstract int getNextEmpId() throws SQLException, NamingException;
	public abstract void deleteEmployee(int empId) throws SQLException, NamingException;
}

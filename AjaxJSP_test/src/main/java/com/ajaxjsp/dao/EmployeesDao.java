package com.ajaxjsp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.dto.EmployeeByNameDto;
import com.ajaxjsp.dto.EmployeeDto;
import com.ajaxjsp.vo.DepartmentVo;
import com.ajaxjsp.vo.EmployeeVo;
import com.ajaxjsp.vo.JobsVo;



public interface EmployeesDao {
	// 모든 직원 정보를 얻어오는 메소드
	public abstract List<EmployeeVo> selectAllEmployees() throws SQLException, NamingException;
	public abstract List<EmployeeVo> selectAllEmployees(String name, String order) throws SQLException, NamingException;
	public abstract List<JobsVo> selectAlljobs() throws SQLException, NamingException;
	public abstract List<DepartmentVo> selectAllDepts() throws SQLException, NamingException;
	public abstract String insertEmployee(EmployeeDto dto) throws SQLException, NamingException;
	public abstract void insertEmployee(int empId, EmployeeDto dto) throws SQLException, NamingException;
	public abstract int getNextEmpId() throws SQLException, NamingException;
	public abstract int deleteEmployee(int empId) throws SQLException, NamingException;
	public abstract List<EmployeeVo> selectEmployeeByName(EmployeeByNameDto dto) throws SQLException, NamingException;
	public abstract List<EmployeeVo> selectEmployeeByName(String name, String orderMethod) throws SQLException, NamingException;
	public abstract List<EmployeeVo> empSortByOrder(String orderMethod) throws SQLException, NamingException;
	public abstract EmployeeVo selectEmployeeByEmpId(int employee_id) throws SQLException, NamingException;
	public abstract int modifyEmployee(EmployeeDto dto) throws SQLException, NamingException;
}

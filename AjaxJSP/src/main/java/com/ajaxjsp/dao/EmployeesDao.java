package com.ajaxjsp.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.ajaxjsp.vo.EmployeeVo;

public interface EmployeesDao {
	// 모든 직원 정보를 얻어오는 메소드
	public abstract List<EmployeeVo> selectAllEmployees() throws SQLException, NamingException;
}

package com.distinctao.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.distinctao.dto.EmpDto;
import com.distinctao.vo.DeptVo;
import com.distinctao.vo.EmpVo;

public interface EmpDao {
	public abstract List<EmpVo> selectAll() throws NamingException, SQLException;

	public abstract List<DeptVo> getDept() throws NamingException, SQLException;

	public abstract void insertEmp(int empNo, EmpDto tdo) throws NamingException, SQLException;

	public abstract int getEmpNo() throws NamingException, SQLException;
}

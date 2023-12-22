package com.distinctao.dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.distinctao.vo.EmpVo;

public interface EmpDao {
	public abstract List<EmpVo> selectAll() throws NamingException, SQLException;
}

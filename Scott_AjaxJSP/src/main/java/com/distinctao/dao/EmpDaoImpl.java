package com.distinctao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.distinctao.vo.EmpVo;

public class EmpDaoImpl implements EmpDao {
	private static EmpDaoImpl instance;

	public static EmpDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmpDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<EmpVo> selectAll() throws NamingException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EmpVo> list = new ArrayList<>();
		
		if (con != null) {
			String query = "SELECT E.*, D.DNAME FROM EMP E, DEPT D WHERE E.DEPTNO = D.DEPTNO";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new EmpVo(rs.getInt ("EMPNO"),
			   					   rs.getString ("ENAME"),
								   rs.getString ("JOB"),
								   rs.getInt ("MGR"),
								   rs.getDate ("HIREDATE"),
								   rs.getDouble ("SAL"),
								   rs.getDouble ("COMM"), 
								   rs.getInt ("DEPTNO"),
								   rs.getString ("DNAME")));
			}
		}
		return list;
	}
}

package com.distinctao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.distinctao.dto.EmpDto;
import com.distinctao.vo.DeptVo;
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
			DBConnection.closeConnection(rs, pstmt, con);
		}
		return list;
	}
	
	@Override
	public List<DeptVo> getDept() throws NamingException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DeptVo> list = new ArrayList<>();
		
		if (con != null) {
			String query = "SELECT * FROM DEPT";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(new DeptVo(rs.getInt("DEPTNO"),
									rs.getString("DNAME"),
									rs.getString("LOC")));
			}
			DBConnection.closeConnection(rs, pstmt, con);

		}
		
		return list;

	}
	
	public int getEmpNo () throws NamingException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		int empNo = 0;
		
		if (con != null) {
			String query = "SELECT MAX(EMPNO) AS MAX_NO FROM EMP";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empNo = rs.getInt("MAX_NO") + 1;
			}	
			DBConnection.closeConnection(rs, pstmt, con);

		}
		return empNo;
	}
	
	@Override
	public void insertEmp (int empNo, EmpDto dto) throws NamingException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		if (con != null) {
			String query = "INSERT INTO EMP VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empNo);
			pstmt.setString(2, dto.getEname());
			pstmt.setString(3, dto.getJob());
			pstmt.setInt(4, dto.getMgr());
			pstmt.setDate(5, dto.getHiredate());
			pstmt.setDouble(6, dto.getSal());
			pstmt.setDouble(7, dto.getComm());
			pstmt.setInt(8, dto.getDeptno());
			pstmt.executeQuery();
			
		}
		DBConnection.closeConnection(pstmt, con);
	}

	@Override
	public void deleteEmp(int empNo) throws NamingException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		if (con != null) {
			String query = "DELETE FROM EMP WHERE EMPNO = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, empNo);
			pstmt.executeUpdate();
			
		}
		DBConnection.closeConnection(pstmt, con);
	}
}

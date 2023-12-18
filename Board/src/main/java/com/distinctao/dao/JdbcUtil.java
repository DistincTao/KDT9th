package com.distinctao.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcUtil {
	private static JdbcUtil instance = new JdbcUtil();
	private static DataSource ds;
	
	public JdbcUtil getInstance () {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver Loading Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return instance;	
	}
	
	public Connection getConnection() throws SQLException {
		try {
			InitialContext iContext = new InitialContext();
			ds = (DataSource) iContext.lookup("java:comp/env/jdbc/myOracle");
			System.out.println("Connection Pool Created!");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return ds.getConnection(); // pool에서 커넥션 반환
		
	}


}

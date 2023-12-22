package com.distinctao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {

	
	public static Connection getConnection () throws NamingException, SQLException {
		Context initContext = new InitialContext();
		DataSource ds= (DataSource)initContext.lookup("java:/comp/env/jdbc/distincTao1");
		Connection conn = ds.getConnection();
		System.out.println("connection success");
		
		return conn;
		
	}
	
	public static void closeConnection (ResultSet rs, Statement stmt, Connection con) throws SQLException {
		rs.close();
		stmt.close();
		con.close();
	}
}

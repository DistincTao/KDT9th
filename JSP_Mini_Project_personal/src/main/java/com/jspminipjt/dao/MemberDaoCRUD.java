package com.jspminipjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.jspminipjt.vo.MemberVo;

public class MemberDaoCRUD implements MemberDao {
	
	private static MemberDaoCRUD instance = null;
	
	public MemberDaoCRUD () {}
	
	public static MemberDaoCRUD getInstance() {
		if (instance == null) {
			instance = new MemberDaoCRUD();
		}
		return instance;
	}

	@Override
	public MemberVo duplicateUserId(String userId) throws NamingException, SQLException {
		MemberVo result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT * from member where user_id = ?";
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = new MemberVo (rs.getString("user_id"),
								   rs.getString("user_pwd"),
								   rs.getString("user_email"),
								   rs.getDate("regdate"),
								   rs.getString("user_img"),
								   rs.getInt("user_point"));
			
		}
		DBConnection.getInstance().dbConnect();		
		
		return result;
	}

}

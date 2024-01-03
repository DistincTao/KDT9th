package com.jspminipjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.jspminipjt.dto.MemberDto;
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
		
		String query = "SELECT * FROM member WHERE user_id = ?";
		
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

	@Override
	public int InsertUser(MemberDto dto) throws SQLException, NamingException {
		int result = 0;

		System.out.println("회원 가입 정보 등록 진행");
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO MEMBER (user_id, user_pwd, user_email) VALUES (?, ?, ?)";

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, dto.getUserId());
		pstmt.setString(2, dto.getUserPwd());
		pstmt.setString(3, dto.getUserEmail());
		
		result = pstmt.executeUpdate();
		
		return result;
		
	}

}

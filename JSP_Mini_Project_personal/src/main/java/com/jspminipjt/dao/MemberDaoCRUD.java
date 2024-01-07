package com.jspminipjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.vo.MemberVo;
import com.jspminipjt.vo.UploadedFileVo;

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
//
//		System.out.println("회원 가입 정보 등록 진행");
//		Connection con = DBConnection.getInstance().dbConnect();
//		PreparedStatement pstmt = null;
//		
//		String query = "INSERT INTO MEMBER (user_id, user_pwd, user_email) VALUES (?, ?, ?)";
//
//		pstmt = con.prepareStatement(query);
//		pstmt.setString(1, dto.getUserId());
//		pstmt.setString(2, dto.getUserPwd());
//		pstmt.setString(3, dto.getUserEmail());
//		
//		result = pstmt.executeUpdate();
//		
		return result;
		
	}

	@Override
	public List<UploadedFileVo> selectAllImg() throws SQLException, NamingException {
		System.out.println("회원 가입 정보 등록 진행");
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UploadedFileVo> list = new ArrayList<>();
		
		String query = MemberDaoSql.SELECT_ALL_IMAGES;
		
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			UploadedFileVo vo = new UploadedFileVo(rs.getInt("file_id"), rs.getString("original_filename"), rs.getString("ext"), rs.getString("new_filename"), rs.getLong("file_size"), rs.getString("user_id"));
			
			list.add(vo);
		}
		
		return list;
	}

	@Override
	public int registerMemberWithFile(UploadedFileDto ufDto, MemberDto dto, String type, int point) throws SQLException, NamingException {
		System.out.println("regist 호출");
		int result = -1;
		int fileId = -1;
		int insertCnt = -1;
		int logCnt = -1;
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		// 업로드된 파일의 정보를 uploadedFile 테이블에 insert
		fileId = insertUploadedFileInfo(ufDto, con);
		if (fileId != -1) {
			dto.setUserImg(fileId);
			dto.setUserPoint(point);
			// 회원 정보를 insert
			insertCnt = insertNewMember (dto, con);
		}
		if (insertCnt != -1) {
			String userId = dto.getUserId();
			// point 테이블에 회원 가입 포인트 로그 남기기
			logCnt = insertPointLog(type, point, userId, con);
		}
		
		if (fileId != -1 && insertCnt == 1 && logCnt == 1) {
			result = 1;
			con.commit();
		} else {
			con.rollback();
		}
		
		return result;
	}



	@Override
	public int registerMemberWithoutFile(MemberDto dto, String type, int point) throws SQLException, NamingException {
		int result = -1;
		int insertCnt = -1;
		int logCnt = -1;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;

		dto.setUserPoint(point);
		// 회원 정보를 insert
		insertCnt = insertNewMember (dto, con);
		if (insertCnt == 1) {
			String userId = dto.getUserId();
			// point 테이블에 회원 가입 포인트 로그 남기기
			logCnt = insertPointLog(type, point, userId, con);
		}
		if (insertCnt != -1 && logCnt != -1) {
			result = 1;
			con.commit();
		} else {
			con.rollback();
		}
		
		return result;
	}

	@Override
	public int insertUploadedFileInfo(UploadedFileDto ufDto, Connection con) throws SQLException, NamingException {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = MemberDaoSql.INSERT_UPLOADEDFILE;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, ufDto.getOriginalFilename());
		pstmt.setString(2, ufDto.getExt());
		pstmt.setString(3, ufDto.getNewFilename());
		pstmt.setLong(4, ufDto.getFileSize());
		pstmt.executeUpdate();
		
		result = getUploadedFileId(con, ufDto);	
		
		
		return result;
	}

	private int getUploadedFileId(Connection con, UploadedFileDto ufDto) throws SQLException {
		int fileId = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = MemberDaoSql.SELECT_FILE_ID;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, ufDto.getNewFilename());
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			fileId = rs.getInt("file_id");
		}
		
		DBConnection.getInstance().dbClose(rs, pstmt);
		
		return fileId;
	}

	@Override
	public int insertNewMember(MemberDto dto, Connection con) throws SQLException, NamingException{
		int result = -1;
		PreparedStatement pstmt = null;
		int userImg = dto.getUserImg();
		
		if (userImg != 1) {
			String query = MemberDaoSql.INSERT_MEMBER_IMG;
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserEmail());
			pstmt.setInt(4, dto.getUserImg());
			pstmt.setInt(5, dto.getUserPoint());
			
			result = pstmt.executeUpdate();
			
		} else if (userImg == 1) {
			String query = MemberDaoSql.INSERT_MEMBER;
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPwd());
			pstmt.setString(3, dto.getUserEmail());
			pstmt.setInt(4, dto.getUserPoint());
			
			result = pstmt.executeUpdate();
		}
		
		DBConnection.getInstance().dbClose(pstmt);
		
		return result;
	}
	
	public int insertPointLog(String type, int point, String userId, Connection con) throws SQLException, NamingException{
		int result = -1;
		PreparedStatement pstmt = null;
		String query = MemberDaoSql.INSERT_POINTLOG;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, type);
		pstmt.setInt(2, point);
		pstmt.setString(3, userId);
		result = pstmt.executeUpdate();
		
		DBConnection.getInstance().dbClose(pstmt);
		
		return result;
	}
	
}

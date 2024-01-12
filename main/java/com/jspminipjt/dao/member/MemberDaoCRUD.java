package com.jspminipjt.dao.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dao.DBConnection;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.member.MemberDto;
import com.jspminipjt.vo.PagingInfoVo;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.member.MemberPointVo;
import com.jspminipjt.vo.member.MemberVo;

public class MemberDaoCRUD implements MemberDao {
	
	private static MemberDaoCRUD instance = null;
	
	public MemberDaoCRUD () {}
	
	public static MemberDaoCRUD getInstance() {
		if (instance == null) {
			instance = new MemberDaoCRUD();
		}
		return instance;
	}

	// ID 중복여부 확인
	@Override
	public MemberVo duplicateUserId(String userId) throws NamingException, SQLException {
		MemberVo result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = MemberDaoSql.SELECT_BY_USERID;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = new MemberVo (rs.getString("user_id"),
								   rs.getString("user_pwd"),
								   rs.getString("user_email"),
								   rs.getDate("regdate"),
								   rs.getInt("user_img"),
								   rs.getInt("user_point"));
			
		}
		DBConnection.getInstance().dbConnect();		
		
		return result;
	}
	
	// 이미지 제목 목록 조회
	@Override
	public List<UploadFileVo> selectAllImg() throws SQLException, NamingException {
		System.out.println("regist 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UploadFileVo> list = new ArrayList<>();
		
		String query = MemberDaoSql.SELECT_ALL_IMAGES;
		
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			UploadFileVo vo = new UploadFileVo(rs.getInt("file_id"),
									 rs.getString("original_filename"), 
									 rs.getString("ext"), 
									  rs.getString("new_filename"), 
									 rs.getLong("file_size"), 
									 rs.getString("user_id"));
			list.add(vo);
		}
		return list;
	}
	

	// 파일이 있을 때 회원 가입 진행
	@Override
	public int registerMemberWithFile(UploadedFileDto uf, MemberDto dto, String pointType, int eachPoint)
			throws SQLException, NamingException {
		System.out.println("regist 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		int result = -1;
		int fileNo = -1;
		int insertCnt = -1;
		int logCnt = -1;
		// 업로드된 파일의 정보를 uploadedFile  테이블에 insert (1) - 1

		fileNo = insertUploadedFileInfo(uf, con);
		// 회원 정보 insert (1) - 2
		if (fileNo != -1) {
			dto.setUserImg(fileNo);
			dto.setUserPoint(eachPoint);
			insertCnt = insertNewMember(dto, con);		 // 1이면 성공	
		} 
		// point 테이블에 회원가입 포인트 로그 남기기 (1) - 3
		if (insertCnt != -1) {
			String userId = dto.getUserId();
			logCnt = insertPointLog(pointType, eachPoint, userId, con);			
		}
		
		if (fileNo != -1 && insertCnt == 1 && logCnt == 1) {
			result = 1;
			con.commit();
		} else {
			con.rollback();
		}
		
		con.setAutoCommit(true);
		DBConnection.getInstance().dbClose(con);
		
		return result;
		
	}
	
	// 파일이 없을 때 회원 가입 진행
	@Override
	public int registerMemberWithoutFile(MemberDto dto, String pointType, int eachPoint)
			throws SQLException, NamingException {
		System.out.println("regist 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		int result = -1;
		int insertCnt = -1;
		int logCnt = -1;
		// 회원 정보 insert (2) - 1
		dto.setUserPoint(eachPoint);
		insertCnt = insertNewMember(dto, con);
		// point 테이블에 회원가입 포인트 로그 남기기 (2) -2
		if (insertCnt == 1) {
			String userId = dto.getUserId();
			logCnt = insertPointLog(pointType, eachPoint, userId, con);
		}
		
		if (insertCnt == 1 && logCnt == 1) {
			result = 1;
			con.commit();
		} else {
			con.rollback();
		}
		
		con.setAutoCommit(true);
		DBConnection.getInstance().dbClose(con);
		
		return result;
		
	}

	@Override
	public int insertUploadedFileInfo(UploadedFileDto uf, Connection con) throws SQLException, NamingException {
			
		int result = -1;
		PreparedStatement pstmt = null;
		String query = MemberDaoSql.INSERT_UPLOADEDFILE;
			
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, uf.getOriginalFileName());
		pstmt.setString(2, uf.getExt());
		pstmt.setString(3, uf.getNewFileName());
		pstmt.setLong(4, uf.getFileSize());
		
		pstmt.executeUpdate();
		
		result = getUploadedFileId(con, uf);
		
		DBConnection.getInstance().dbClose(pstmt);
	
		return result;
	}
	
	// 현재 업로드된 파일의 file_no를 select 해와서 반환
	private int getUploadedFileId(Connection con, UploadedFileDto uf) throws SQLException {
		int fileNo = -1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = MemberDaoSql.SELECT_FILE_ID;

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, uf.getNewFileName());
		
		rs = pstmt.executeQuery();
		while (rs.next()) {
			fileNo = rs.getInt("file_id");
		}
		
		DBConnection.getInstance().dbClose(rs, pstmt);
		return fileNo;
	}

	@Override
	public int insertNewMember(MemberDto dto, Connection con) throws SQLException, NamingException {
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
			
		} else if (userImg == 1){ 
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
	
	
	// 로그인 확인
	@Override
	public MemberVo loginMember(String userId, String userPwd) throws SQLException, NamingException {
		System.out.println("login Dao 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		MemberVo vo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = MemberDaoSql.SELECT_LOGIN_INFO;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		pstmt.setString(2, userPwd);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			vo = new MemberVo(rs.getString("user_id"),
					          rs.getString("user_pwd"),
					          rs.getString("user_email"),
					          rs.getDate("regdate"),
					          rs.getInt("user_img"),
					          rs.getInt("user_point"),
					          rs.getString("new_filename"),
					          rs.getString("is_admin"));
		}
	
		DBConnection.getInstance().dbClose(rs, pstmt, con);
		return vo;
	
	}
	
	@Override
	public int addPointToMember(String userId, String pointType, int point) throws SQLException, NamingException {
		int result = -1;
		int afterPointLog = -1;
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		PreparedStatement pstmt = null;
		String query = MemberDaoSql.UPDATE_POINT_LOGIN; 
//		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, point);
		pstmt.setString(2, userId);
		
		result = pstmt.executeUpdate();
		System.out.println(result);
		DBConnection.getInstance().dbClose(pstmt);
		
		if (result == 1) {
			afterPointLog = insertPointLog(pointType, point, userId, con);
		  if (afterPointLog == 1) {
			con.commit();
			result = 1;
		  } else {
			  con.rollback();
		  }
		} else {
			con.rollback();
		}
		
		con.setAutoCommit(true);
		DBConnection.getInstance().dbClose(con);
		
		return result;
	}
	
	

	@Override
	public int insertPointLog(String pointType, int eachPoint, String userId, Connection con) throws SQLException, NamingException {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = MemberDaoSql.INSERT_POINTLOG;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, pointType);
		pstmt.setInt(2, eachPoint);
		pstmt.setString(3, userId);
		result = pstmt.executeUpdate();
		
		DBConnection.getInstance().dbClose(pstmt);

		return result;
		
	}

	@Override
	public MemberVo getMemberInfo(String userId) throws SQLException, NamingException {
		MemberVo vo = null;
		
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = MemberDaoSql.SELECT_LOGIN_USER_INFO;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			vo = new MemberVo(rs.getString("user_id"),
					          rs.getString("user_pwd"),
					          rs.getString("user_email"),
					          rs.getDate("regdate"),
					          rs.getInt("user_img"),
					          rs.getInt("user_point"),
					          rs.getString("new_filename"),
					          rs.getString("is_admin"));
		}
		
		DBConnection.getInstance().dbClose(rs, pstmt, con);
		return vo;
	}

	@Override
	public List<MemberPointVo> getMemberPointInfo(String userId, PagingInfoVo paging) throws SQLException, NamingException {
		List<MemberPointVo> volist = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = DBConnection.getInstance().dbConnect();
		
		String query = MemberDaoSql.SELECT_USER_POINTLOG;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		pstmt.setInt(2, paging.getStartRowIndex());
		pstmt.setInt(3, paging.getPagePostCnt());
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			MemberPointVo vo = new MemberPointVo(rs.getInt("pointlog_no"),
												 rs.getDate("action_date"),
												 rs.getString("point_type"),
												 rs.getInt("change_point"),
												 rs.getString("user_id"));
			volist.add(vo);
		}

		DBConnection.getInstance().dbClose(rs, pstmt, con);
		return volist;
	}


}

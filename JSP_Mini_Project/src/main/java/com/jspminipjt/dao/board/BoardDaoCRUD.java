package com.jspminipjt.dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dao.DBConnection;
import com.jspminipjt.dao.member.MemberDaoSql;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.board.BoardDto;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.board.BoardVo;

public class BoardDaoCRUD implements BoardDao {
	private static BoardDaoCRUD instance = null;

	public BoardDaoCRUD() {}

	
	public static BoardDaoCRUD getInstance() {
		if(instance == null) {
			instance = new BoardDaoCRUD();
		} 
		return instance;
	}
	
	@Override
	public int selectBoardNo() throws SQLException, NamingException {
		int boardNo = 0;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = BoardDaoSql.SELECT_BOARD_NO;
		
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			boardNo = rs.getInt("nextRef");
		}
		
		return boardNo;
	}

	
	@Override
	public List<BoardVo> selectAllBoard() throws NamingException, SQLException {
		List<BoardVo> list = new ArrayList<>();
		BoardVo vo = null;
		
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = BoardDaoSql.SELECT_ALL_BOARD_LIST;
		
		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			vo = new BoardVo(rs.getInt("board_no"), 
							 rs.getString("writer"), 
							 rs.getString("title"), 
							 rs.getTimestamp("post_date"), 
							 rs.getString("content"), 
							 rs.getInt("read_count"), 
							 rs.getInt("like_count"), 
							 rs.getInt("ref"), 
							 rs.getInt("step"), 
							 rs.getInt("ref_order"), 
							 rs.getString("isDelete"));
		
			list.add(vo);
		}
		
		DBConnection.getInstance().dbClose(rs, pstmt, con);
		return list;
		
	}


	@Override
	public int insertBoardTransactionWithFile(BoardDto dto, UploadedFileDto ufDto, String pointType, int eachPoint)
			throws NamingException, SQLException {
		System.out.println("게시판 등록 진행 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		int result = -1;
		int boardNo = dto.getBoardNo();;
		int fileId = -1;
		int writeCnt = -1;
		int logCnt = -1;
		// 파일 불러오기
		boardNo = selectBoardNo();
		
		fileId = insertUploadedFileInfo(ufDto, boardNo, con);
		// 게시판에 저장
		writeCnt = insertNewBoardContent(dto, con);
		// 포인트 로그 저장
		logCnt = insertPointLog(pointType, eachPoint, dto.getWriter(), con);
		
		if (fileId == 1 && writeCnt == 1 && logCnt == 1) {
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
	public int insertBoardTransactionWithoutFile(BoardDto dto, String pointType, int eachPoint) throws NamingException, SQLException {
		System.out.println("게시판 등록 진행 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		int result = -1;
		int writeCnt = -1;
		int logCnt = -1;
		// 게시판에 저장
		writeCnt = insertNewBoardContent(dto, con);
		// 포인트 로그 저장
		logCnt = insertPointLog(pointType, eachPoint, dto.getWriter(), con);
		
		if (writeCnt == 1 && logCnt == 1) {
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
	public int insertUploadedFileInfo(UploadedFileDto ufDto, int boardNo, Connection con) throws SQLException, NamingException {
		int result = -1;
		
		PreparedStatement pstmt = null;
		String query = BoardDaoSql.INSERT_UPLOADEDFILE_BOARD;
			
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, ufDto.getOriginalFileName());
		pstmt.setString(2, ufDto.getExt());
		pstmt.setString(3, ufDto.getNewFileName());
		pstmt.setLong(4, ufDto.getFileSize());
		pstmt.setInt(5, boardNo);
		
		result = pstmt.executeUpdate();
		
		
		DBConnection.getInstance().dbClose(pstmt);

		
		return result;
	}
	
	@Override
	public int insertNewBoardContent(BoardDto dto, Connection con) throws SQLException, NamingException {
		int result = -1;
		
		PreparedStatement pstmt = null;
		String query = BoardDaoSql.INSERT_BOARD_CONTENT;
		
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, dto.getWriter());
		pstmt.setString(2, dto.getTitle());
		pstmt.setString(3, dto.getContent());
		
		result = pstmt.executeUpdate();
		
		DBConnection.getInstance().dbClose(pstmt);
		return result;
	}

	// 이미지 제목 목록 조회
	@Override
	public List<UploadFileVo> selectAllFile() throws SQLException, NamingException {
		System.out.println("regist 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<UploadFileVo> list = new ArrayList<>();
		
		String query = BoardDaoSql.SELECT_ALL_IMAGES;
		
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
	
}

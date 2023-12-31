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

	public BoardDaoCRUD() {
	}

	public static BoardDaoCRUD getInstance() {
		if (instance == null) {
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

		String query = BoardDaoSql.SELECT_BOARD_REF;

		pstmt = con.prepareStatement(query);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			boardNo = rs.getInt("board_no");
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
			vo = new BoardVo(rs.getInt("board_no"), rs.getString("writer"), rs.getString("title"),
					rs.getTimestamp("post_date"), rs.getString("content"), rs.getInt("read_count"),
					rs.getInt("like_count"), rs.getInt("ref"), rs.getInt("step"), rs.getInt("ref_order"),
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
		int fileCnt = -1;
		int writeCnt = -1;
		int pointCnt = -1;
		int logCnt = -1;
		String userId = "";
		// 게시판 번호 불러오기
		int ref = selectBoardNo();
//		System.out.println(ref);
		// 게시판에 저장
		dto.setRef(ref);
		writeCnt = insertNewBoardContent(dto, con);
		if (writeCnt != -1) {
		// 파일 저장
			ufDto.setBoardNo(ref);
			fileCnt = insertUploadedFileInfo(ufDto, con);
		}
		// 포인트 추가
		if (fileCnt != -1) {
			userId = dto.getWriter();
			pointCnt = addPointToMember(userId, pointType, eachPoint, con);
		}
		// 포인트 로그 저장
		if (writeCnt == 1 && fileCnt == 1 && pointCnt == 1 && fileCnt == 1) {
			logCnt = insertPointLog(pointType, eachPoint, userId, con);
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
	public int insertBoardTransactionWithoutFile(BoardDto dto, String pointType, int eachPoint)
			throws NamingException, SQLException {
		System.out.println("게시판 등록 진행 호출");
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);

		int result = -1;
		int writeCnt = -1;
		int pointCnt = -1;
		// 게시판에 저장
		writeCnt = insertNewBoardContent(dto, con);
		// 포인트 추가
		if (writeCnt != -1) {
			String userId = dto.getWriter();
			pointCnt = addPointToMember(userId, pointType, eachPoint, con);
		}
		// 포인트 로그 저장
		if (writeCnt == 1 && pointCnt == 1) {
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
	public int insertUploadedFileInfo(UploadedFileDto ufDto, Connection con) throws SQLException, NamingException {
		int result = -1;

		PreparedStatement pstmt = null;
		String query = BoardDaoSql.INSERT_UPLOADEDFILE_BOARD;

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, ufDto.getOriginalFileName());
		pstmt.setString(2, ufDto.getExt());
		pstmt.setString(3, ufDto.getNewFileName());
		pstmt.setLong(4, ufDto.getFileSize());
		pstmt.setInt(5, ufDto.getBoardNo());
		pstmt.setString(6, ufDto.getBase64String());

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
//		pstmt.setInt(4, dto.getRef());

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
			UploadFileVo vo = new UploadFileVo(rs.getInt("file_id"), rs.getString("original_filename"),
					rs.getString("ext"), rs.getString("new_filename"), rs.getLong("file_size"),
					rs.getString("user_id"));
			list.add(vo);
		}
		return list;
	}

	@Override
	public int insertPointLog(String pointType, int eachPoint, String userId, Connection con)
			throws SQLException, NamingException {
		int result = -1;
		PreparedStatement pstmt = null;
		String query = BoardDaoSql.INSERT_POINTLOG;

		System.out.println(pointType + " " + eachPoint + " " + userId);

		pstmt = con.prepareStatement(query);
		pstmt.setString(1, pointType);
		pstmt.setInt(2, eachPoint);
		pstmt.setString(3, userId);
		result = pstmt.executeUpdate();

		DBConnection.getInstance().dbClose(pstmt);

		return result;

	}

	@Override
	public int addPointToMember(String userId, String pointType, int point, Connection con)
			throws SQLException, NamingException {
		int result = -1;
		int afterPointLog = -1;

		PreparedStatement pstmt = null;
		String query = BoardDaoSql.UPDATE_POINT_WRITE;
//		System.out.println(query);
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, point);
		pstmt.setString(2, userId);

		result = pstmt.executeUpdate();
		System.out.println(result);

		DBConnection.getInstance().dbClose(pstmt);

//		if (result == 1 ) {
//			afterPointLog = insertPointLog(pointType, point, userId, con);
//			if (afterPointLog == 1) {
//				con.commit();
//				result = 1;				
//			} else {
//				con.rollback();
//			}
//		} else {
//			con.rollback();
//		}

		return result;
	}

	@Override
	public boolean selectReadCountProcess(String userIp, int boardNo) throws SQLException, NamingException {
		boolean result = false;
		System.out.println("readCountProcess 시작");
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = BoardDaoSql.SELECT_BOARD_READCNT;
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userIp);
		pstmt.setInt(2, boardNo);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			result = true;
		}

		
		DBConnection.getInstance().dbClose(rs, pstmt, con);
		
		return result;
	}

	@Override
	public int selectHourDiff(String userIp, int boardNo) throws SQLException, NamingException {
		int result = -1;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = BoardDaoSql.SELECT_READ_HOUR_BY_NO;
		pstmt = con.prepareStatement(query);
		pstmt.setString(1, userIp);
		pstmt.setInt(2, boardNo);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			result = rs.getInt("diff_hour");
		}
				
		DBConnection.getInstance().dbClose(rs, pstmt, con);

		return result;
	}

	@Override
	public int readCountPocessWithReadCnt(String userIp, int boardNo, String mode)
			throws SQLException, NamingException {
		int result = -1;
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		if (mode.equals("insert")) {
			String query = BoardDaoSql.INSERT_READTIME;
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userIp);
			pstmt.setInt(2, boardNo);
			result = pstmt.executeUpdate();
			
		} else if (mode.equals("update")) {
			String query = BoardDaoSql.UPDATE_READTIME;
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userIp);
			pstmt.setInt(2, boardNo);
			result = pstmt.executeUpdate();	
		}
		
		DBConnection.getInstance().dbClose(pstmt, con);
		return result;
	}

	@Override
	public int updateReadcount(int boardNo) throws SQLException, NamingException {
		int result = -1;
		
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;

		String query = BoardDaoSql.UPDATE_READCOUNT;
		
		pstmt = con.prepareStatement(query);
		pstmt.setInt(1, boardNo);
		result = pstmt.executeUpdate();	

		
		DBConnection.getInstance().dbClose(pstmt, con);
		
		return result;
	}
}

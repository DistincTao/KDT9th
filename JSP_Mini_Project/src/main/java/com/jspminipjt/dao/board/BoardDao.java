package com.jspminipjt.dao.board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.board.BoardDto;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.board.BoardVo;

public interface BoardDao {
	// 게시판 전체 글 목록
	public abstract List<BoardVo> selectAllBoard() throws NamingException, SQLException;
	// 게시판 글 저장 (업로드 파일이 있는 경우)
	public abstract int insertBoardTransactionWithFile(BoardDto dto, UploadedFileDto ufDto, String pointType, int eachPoint) throws NamingException, SQLException;
	// 게시판 글 저장 (업로드 파일이 없는 경우)
	public abstract int insertBoardTransactionWithoutFile(BoardDto dto, String pointType, int eachPoint) throws NamingException, SQLException;
	// 저장된 파일 목록 가져오기
	public abstract List<UploadFileVo> selectAllFile() throws SQLException, NamingException;
	// 파일 업로드
	int insertUploadedFileInfo(UploadedFileDto ufDto,  int boardNo, Connection con) throws SQLException, NamingException;
	// 게시판에 글 등록
	int insertNewBoardContent(BoardDto dto, Connection con) throws SQLException, NamingException;
	//포인트 로그 저장
	public abstract int insertPointLog(String pointType, int eachPoint, String userId, Connection con)
			throws SQLException, NamingException;
	// 다음 Board 번호 가져오기
	public abstract int selectBoardNo() throws SQLException, NamingException;
}

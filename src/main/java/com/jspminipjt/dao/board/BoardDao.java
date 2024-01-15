package com.jspminipjt.dao.board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.board.BoardDto;
import com.jspminipjt.dto.board.LikeCountDto;
import com.jspminipjt.dto.board.SearchCriteriaDto;
import com.jspminipjt.vo.LikeCountVo;
import com.jspminipjt.vo.PagingInfoVo;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.board.BoardVo;

public interface BoardDao {
	// 게시판 전체 글 목록
	public abstract List<BoardVo> selectAllBoard() throws NamingException, SQLException;
	public abstract List<BoardVo> selectAllBoard(PagingInfoVo PageInfo) throws NamingException, SQLException;
	// 게시판 글 저장 (업로드 파일이 있는 경우)
	public abstract int insertBoardTransactionWithFile(BoardDto dto, UploadedFileDto ufDto, String pointType, int eachPoint) throws NamingException, SQLException;
	// 게시판 글 저장 (업로드 파일이 없는 경우)
	public abstract int insertBoardTransactionWithoutFile(BoardDto dto, String pointType, int eachPoint) throws NamingException, SQLException;
	// 저장된 파일 목록 가져오기
	public abstract List<UploadFileVo> selectAllFile() throws SQLException, NamingException;
	// 파일 업로드
	int insertUploadedFileInfo(UploadedFileDto ufDto, Connection con) throws SQLException, NamingException;
	// 게시판에 글 등록
	int insertNewBoardContent(BoardDto dto, Connection con) throws SQLException, NamingException;
	//포인트 로그 저장
	public abstract int insertPointLog(String pointType, int eachPoint, String userId, Connection con) throws SQLException, NamingException;
	// 다음 Board 번호 가져오기
	public abstract int selectBoardNo() throws SQLException, NamingException;
	// 글쓴 포인트 더하기
	public abstract int addPointToMember(String userId, String pointType, int point, Connection con) throws SQLException, NamingException;

	// 조회수 처리
	// 1) readcountprocess 테이블에 ip 주소와 글번호 no가 있는지 여부
	public abstract boolean selectReadCountProcess(String userIp, int boardNo) throws SQLException, NamingException;
	// 2) 24시간이 지났는지 여부
	public abstract int selectHourDiff(String userIp, int boardNo) throws SQLException, NamingException;;
	// 3) 아이피 주소와 글번호, 읽은 시간을 readcountprocess 테이블에 update  또는 insert 
	public abstract int readCountPocessWithReadCnt(String userIp, int boardNo, String mode) throws SQLException, NamingException;
	// board 에 readCount 증가
	public abstract int updateReadcount(int boardNo, Connection con) throws SQLException, NamingException;
	// board no에 따른 내용 불러오기
	public abstract BoardVo selectByBoardNo(int boardNo) throws SQLException, NamingException;
	// board no에 따른 파일 불러오기
	public abstract UploadFileVo getFile(int boardNo) throws NamingException, SQLException;
	// board isDelete update
	public abstract int deletBoard(int boardNo) throws NamingException, SQLException;
	// board 내용 update with File
	public abstract int updateBoardTransactionWithFile(BoardDto dto, UploadedFileDto ufDto, String existFile) throws NamingException, SQLException;
	// board 내용 update without File
	public abstract int updateBoardTransactionWithoutFile(BoardDto dto) throws NamingException, SQLException;
	// board 업로드 파일 내용 수정
	public abstract int updateUploadedFileInfo(UploadedFileDto ufDto, Connection con) throws NamingException, SQLException;
	// board 내용 update (사진 삭제 요청)
	public abstract int updateBoardTransactionDeleteFile(BoardDto dto) throws NamingException, SQLException;
	// board 파일 삭제
	public abstract int deleteUploadedFile(int boardNo, Connection con) throws NamingException, SQLException;
	// reply 작성
	public abstract int insertReplyTransaction(BoardDto dto) throws NamingException, SQLException;
	// 총 페이지수 가져오기
	public abstract int getTotalPostCnt()  throws NamingException, SQLException;
	
	// 검색어 처리
	// 게시글 중 검색어가 있을 때
	public abstract int getTotalPostCnt(SearchCriteriaDto dto) throws NamingException, SQLException;
	// 검색어가 있을 때 게시판 목록 검색
	public abstract List<BoardVo> selectAllBoard(PagingInfoVo paging, SearchCriteriaDto dto) throws NamingException, SQLException;
	// 좋아요 기록 및 좋아요 수 증가 반영하기
	public abstract int addLikeCountTransaction(LikeCountDto dto) throws NamingException, SQLException;
	// 변경 후 좋아요 수 가져오기
	public abstract int selectLikeCount(int boardNo) throws NamingException, SQLException;
	// 좋아요 log 기록 확인하기
	public abstract int selectLikeLog(LikeCountDto dto, Connection con) throws NamingException, SQLException;
	// likecount table 정보 불러오기 
	public abstract LikeCountVo selectLikeLog(int boardNo, String userId) throws NamingException, SQLException;
	// 좋아요 기록 삭제 및 수 감소 반영하기
	public abstract int deleteLikeCountTransaction(LikeCountDto dto) throws NamingException, SQLException;

}

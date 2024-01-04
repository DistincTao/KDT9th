package com.jspminipjt.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.etc.UploadedFile;
import com.jspminipjt.vo.ImageVo;
import com.jspminipjt.vo.MemberVo;

public interface MemberDao {
	// 유저 아이디가 중복 되는지 검사
	public abstract MemberVo duplicateUserId(String userId) throws SQLException, NamingException;
	// 이미지 제목 목록 조회
	public abstract List<ImageVo> selectAllImg() throws SQLException, NamingException;
	// ===== 회원 가입 진행 Transaction ====== (C)
	// 파일이 있을 때 회원 가입 진행 (1)
	public abstract int registerMemberWithFile(UploadedFile uf, MemberDto dto, String pointType, int eachPoint) throws SQLException, NamingException;
	
	// 파일이 없을 때 회원 가입 진행 (2)
	public abstract int registerMemberWithoutFile(MemberDto dto, String pointType, int eachPoint) throws SQLException, NamingException;
	
	// 업로드된 파일의 정보를 uploadedFile  테이블에 insert (1) - 1
	public abstract int insertUploadedFileInfo(UploadedFile uf, Connection con) throws SQLException, NamingException;
	
	// 회원 정보 insert (1) - 2  / (2) - 1
	public abstract int insertNewMember (MemberDto dto, Connection con) throws SQLException, NamingException;
	
	// point 테이블에 회원가입 포인트 로그 남기기 (1) - 3 / (2) -2
	public abstract int insertPointLog(String pointType, int eachPoint, String userId, Connection con) throws SQLException, NamingException;
	// ===================================
	
	// 전체 회원 정보 조회 (R)
	// 아이디로 회원 정보 조회 (R)
	// 회원 정보 수정 (U)
	// 회원 탈퇴 (D)
	
	// 게시판 쓰기 (C)
	// 게시판 조회 (R)
	// 작성자로 게시판 조회 (R)
	// 작성 기간으로 게시판 조회 (R)
	// 게시판 내용 수정 (U)
	// 게시판 삭제 (D)
	
	
	
	
	
}

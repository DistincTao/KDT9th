package com.jspminipjt.dao.member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.dto.member.MemberDto;
import com.jspminipjt.vo.UploadFileVo;
import com.jspminipjt.vo.member.MemberPointVo;
import com.jspminipjt.vo.member.MemberVo;

public interface MemberDao {
	// 유저 아이디가 중복 되는지 검사
	public abstract MemberVo duplicateUserId(String userId) throws SQLException, NamingException;
	// 이미지 제목 목록 조회
	public abstract List<UploadFileVo> selectAllImg() throws SQLException, NamingException;
	// ===== 회원 가입 진행 Transaction ====== (C)
	// 파일이 있을 때 회원 가입 진행 (1)
	public abstract int registerMemberWithFile(UploadedFileDto uf, MemberDto dto, String pointType, int eachPoint) throws SQLException, NamingException;
	
	// 파일이 없을 때 회원 가입 진행 (2)
	public abstract int registerMemberWithoutFile(MemberDto dto, String pointType, int eachPoint) throws SQLException, NamingException;
	
	// 업로드된 파일의 정보를 uploadedFile  테이블에 insert (1) - 1
	public abstract int insertUploadedFileInfo(UploadedFileDto uf, Connection con) throws SQLException, NamingException;
	
	// 회원 정보 insert (1) - 2  / (2) - 1
	public abstract int insertNewMember (MemberDto dto, Connection con) throws SQLException, NamingException;
	
	// point 테이블에 회원가입 포인트 로그 남기기 (1) - 3 / (2) -2
	public abstract int insertPointLog(String pointType, int eachPoint, String userId, Connection con) throws SQLException, NamingException;
	// ===================================
	
	// login 을 위한 아이디 비밀번호 가져오기
	public abstract MemberVo loginMember(String userId, String userPwd) throws SQLException, NamingException;
	// 아이디 비밀번호 확인 후 point 적립하기
	public abstract int addPointToMember(String userId, String pointType, int point) throws SQLException, NamingException;
	// 해당 아이디 맴버 정보 가져오기ㅏ
	public abstract MemberVo getMemberInfo(String userId)throws SQLException, NamingException;
	// 해당 맴버의 포인트 기록 가져오기
	public abstract List<MemberPointVo> getMembePointInfo(String userId)throws SQLException, NamingException;

	
	
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

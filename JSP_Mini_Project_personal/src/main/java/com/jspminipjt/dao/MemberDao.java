package com.jspminipjt.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.dto.UploadedFileDto;
import com.jspminipjt.vo.MemberVo;
import com.jspminipjt.vo.UploadedFileVo;

public interface MemberDao {
	// 유저 아이디가 중복 되는지 검사
	public abstract MemberVo duplicateUserId(String userId) throws SQLException, NamingException;
	public abstract int InsertUser(MemberDto dto) throws SQLException, NamingException;
	public abstract List<UploadedFileVo> selectAllImg() throws SQLException, NamingException;
	public abstract int registerMemberWithFile(UploadedFileDto ufDto, MemberDto dto, String type, int point) throws SQLException, NamingException;
	public abstract int registerMemberWithoutFile(MemberDto dto, String type, int point) throws SQLException, NamingException;
	public abstract int insertUploadedFileInfo(UploadedFileDto ufDto, Connection con) throws SQLException, NamingException;
	public abstract int insertNewMember(MemberDto dto, Connection con) throws SQLException, NamingException;
	public abstract int insertPointLog(String type, int point, String userId, Connection con) throws SQLException, NamingException;

}

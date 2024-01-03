package com.jspminipjt.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.jspminipjt.dto.MemberDto;
import com.jspminipjt.vo.MemberVo;

public interface MemberDao {
	// 유저 아이디가 중복 되는지 검사
	public abstract MemberVo duplicateUserId(String userId) throws SQLException, NamingException;
	public abstract int InsertUser(MemberDto dto) throws SQLException, NamingException;
}

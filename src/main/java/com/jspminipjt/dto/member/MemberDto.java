package com.jspminipjt.dto.member;

import java.sql.Date;

public class MemberDto {
	private String userId;
	private String userPwd;
	private String userEmail;
	private Date regdate; 
	private int userImg;
	private int userPoint;
	
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberDto(String userId, String userPwd, String userEmail, Date regdate, int userImg, int userPoint) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.regdate = regdate;
		this.userImg = userImg;
		this.userPoint = userPoint;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public int getUserImg() {
		return userImg;
	}
	
	public int getUserPoint() {
		return userPoint;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setUserImg(int userImg) {
		this.userImg = userImg;
	}

	public void setUserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail + ", regdate="
				+ regdate + ", userImg=" + userImg + ", userPoint=" + userPoint + "]";
	}
	
	
}

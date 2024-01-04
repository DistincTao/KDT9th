package com.jspminipjt.vo;

import java.sql.Date;

public class MemberVo {
	private String userId;
	private String userPwd;
	private String userEmail;
	private Date regdate; 
	private int userImg;
	private int userPoint;
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVo(String userId, String userPwd, String userEmail, Date regdate, int userImg, int userPoint) {
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
	
	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail + ", regdate="
				+ regdate + ", userImg=" + userImg + ", userPoint=" + userPoint + "]";
	}
	
	
}

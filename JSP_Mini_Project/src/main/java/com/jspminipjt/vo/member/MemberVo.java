package com.jspminipjt.vo.member;

import java.sql.Date;

public class MemberVo {
	private String userId;
	private String userPwd;
	private String userEmail;
	private Date regdate; 
	private int userImg;
	private int userPoint;
	private String memberImg;
	private String isAdmin;
	
	
	public MemberVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberVo(String userId, String userPwd, String memberImg, String isAdmin) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.memberImg = memberImg;
		this.isAdmin = isAdmin;

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
	
	
	
	public MemberVo(String userId, String userPwd, String userEmail, Date regdate, int userImg, int userPoint,
			String memberImg, String isAdmin) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userEmail = userEmail;
		this.regdate = regdate;
		this.userImg = userImg;
		this.userPoint = userPoint;
		this.memberImg = memberImg;
		this.isAdmin = isAdmin;
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
	
	public String getMemberImg() {
		return memberImg;
	}

	public String getIsAdmin() {
		return isAdmin;
	}


	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPwd=" + userPwd + ", userEmail=" + userEmail + ", regdate="
				+ regdate + ", userImg=" + userImg + ", userPoint=" + userPoint + ", memberImg=" + memberImg
				+ ", isAdmin=" + isAdmin + "]";
	}

	


	
}

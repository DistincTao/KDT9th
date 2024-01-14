package com.jspminipjt.dto;

import java.sql.Date;

public class LikeCountDto {
	private int likeNo;
	private int boardNo;
	private String userId;
	private Date likeDate;
	private String ipAddr;
	
	public LikeCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeCountDto(int likeNo, int boardNo, String userId, Date likeDate, String ipAddr) {
		super();
		this.likeNo = likeNo;
		this.boardNo = boardNo;
		this.userId = userId;
		this.likeDate = likeDate;
		this.ipAddr = ipAddr;
	}

	public int getLikeNo() {
		return likeNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public String getUserId() {
		return userId;
	}

	public Date getLikeDate() {
		return likeDate;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Override
	public String toString() {
		return "LikeCountDto [likeNo=" + likeNo + ", boardNo=" + boardNo + ", userId=" + userId + ", likeDate="
				+ likeDate + ", ipAddr=" + ipAddr + "]";
	}
	
}

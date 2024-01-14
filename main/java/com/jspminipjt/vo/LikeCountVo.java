package com.jspminipjt.vo;

import java.sql.Date;

public class LikeCountVo {
	private int likeNo;
	private int boardNo;
	private String userId;
	private Date likeDate;
	private String ipAddr;
	
	public LikeCountVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeCountVo(int likeNo, int boardNo, String userId, Date likeDate, String ipAddr) {
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

	@Override
	public String toString() {
		return "LikeCountVo [likeNo=" + likeNo + ", boardNo=" + boardNo + ", userId=" + userId + ", likeDate="
				+ likeDate + ", ipAddr=" + ipAddr + "]";
	}
	
}

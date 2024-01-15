package com.jspminipjt.dto.board;

import java.sql.Date;

public class LikeCountDto {
	private int likeNo;
	private int boardNo;
	private String userId;
	private Date likeDate;
	
	public LikeCountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeCountDto(int likeNo, int boardNo, String userId, Date likeDate) {
		super();
		this.likeNo = likeNo;
		this.boardNo = boardNo;
		this.userId = userId;
		this.likeDate = likeDate;
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

	@Override
	public String toString() {
		return "LikeCountDto [likeNo=" + likeNo + ", boardNo=" + boardNo + ", userId=" + userId + ", likeDate="
				+ likeDate + "]";
	}
	
}

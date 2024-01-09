package com.jspminipjt.dto.board;

import java.sql.Timestamp;

public class BoardDto {
	private int boardNo;
	private String writer;
	private String title;
	private Timestamp postDate;
	private String content;
	private int ref;

	
	
	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardDto(int boardNo, String writer, String title, Timestamp postDate, String content, int ref) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.postDate = postDate;
		this.content = content;
		this.ref = ref;

	}
	
	public int getBoardNo() {
		return boardNo;
	}
	public String getWriter() {
		return writer;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Timestamp getPostDate() {
		return postDate;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getRef() {
		return ref;
	}
	
	
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	@Override
	public String toString() {
		return "BaordVo [writer=" + writer + ", title=" + title + ", postDate=" + postDate
				+ ", content=" + content + ", ref=" + ref + "]";
	}
	
	
	
}

package com.jspminipjt.dto.board;

import java.sql.Timestamp;

public class BoardDto {
	private int boardNo;
	private String writer;
	private String title;
	private Timestamp postDate;
	private String content;
	private int ref;
	private int step;
	private int refOrder;
	
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

	public BoardDto(int boardNo, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.title = title;
		this.content = content;
		
	}
	
	public BoardDto(int boardNo, String writer, String title, String content) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.content = content;
		
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
	
	
	
	public int getStep() {
		return step;
	}
	public int getRefOrder() {
		return refOrder;
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
	
	
	public void setStep(int step) {
		this.step = step;
	}
	public void setRefOrder(int refOrder) {
		this.refOrder = refOrder;
	}
	@Override
	public String toString() {
		return "BoardDto [boardNo=" + boardNo + ", writer=" + writer + ", title=" + title + ", postDate=" + postDate
				+ ", content=" + content + ", ref=" + ref + ", step=" + step + ", refOrder=" + refOrder + "]";
	}

	
	
	
	
}

package com.distinctao.dto;

import java.sql.Date;

public class BoardDTO {
	private int num;
	private String title;
	private String writer;
	private String contents;
	private Date regDage;
	private int cnt;
	
	public BoardDTO() {}
	
	public BoardDTO(int num, String title, String writer, String contents, Date regDage, int cnt) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.regDage = regDage;
		this.cnt = cnt;
	}

	public int getNum() {
		return num;
	}


	public String getTitle() {
		return title;
	}


	public String getWriter() {
		return writer;
	}


	public String getContents() {
		return contents;
	}


	public Date getRegDage() {
		return regDage;
	}


	public int getCnt() {
		return cnt;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public void setRegDage(Date regDage) {
		this.regDage = regDage;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	@Override
	public String toString() {
		return "BaordDTO [num=" + num + ", title=" + title + ", writer=" + writer + ", contents=" + contents
				+ ", regDage=" + regDage + ", cnt=" + cnt + "]";
	}
	
	
	
}

package com.jspminipjt.vo.board;

import java.sql.Timestamp;

public class BoardVo {
	private int boardNo;
	private String writer;
	private String title;
	private Timestamp postDate;
	private String content;
	private int readCount;
	private int likeCount;
	private int ref;
	private int step;
	private int refOrder;
	private String isDelete;
	private String newFilename;
	
	
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardVo(int boardNo, String writer, String title, Timestamp postDate, String content, int readCount,
			int likeCount, int ref, int step, int refOrder, String isDelete) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.postDate = postDate;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
	}
	
	public BoardVo(int boardNo, String writer, String title, Timestamp postDate, String content, int readCount,
			int likeCount, int ref, int step, int refOrder, String isDelete, String newFileName) {
		super();
		this.boardNo = boardNo;
		this.writer = writer;
		this.title = title;
		this.postDate = postDate;
		this.content = content;
		this.readCount = readCount;
		this.likeCount = likeCount;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
		this.isDelete = isDelete;
		this.newFilename = newFileName;
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
	
	public int getReadCount() {
		return readCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	
	public int getRef() {
		return ref;
	}
	
	public int getStep() {
		return step;
	}
	
	public String getIsDelete() {
		return isDelete;
	}
	
	public int getRefOrder() {
		return refOrder;
	}
	public String getNewFilename() {
		return newFilename;
	}
	@Override
	public String toString() {
		return "BaordVo [boardNo=" + boardNo + ", writer=" + writer + ", title=" + title + ", postDate=" + postDate
				+ ", content=" + content + ", readCount=" + readCount + ", likeCount=" + likeCount + ", ref=" + ref
				+ ", step=" + step + ", ref_order=" + refOrder + ", isDelete=" + isDelete + "]";
	}
	
	
	
}

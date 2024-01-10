package com.jspminipjt.vo;

public class UploadFileVo {
	private int fileId;
	private String originalFilename;
	private String ext;
	private String newFilename;
	private Long fileSize;
	private int boardNo;
	private String base64String;
	private String userId;
	
	public UploadFileVo() {
		super();
	}

	public UploadFileVo(int fileId, String originalFilename, String ext, String newFilename, Long fileSize, String userId) {
		super();
		this.fileId = fileId;
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
		this.userId = userId;
	}

	public UploadFileVo(int fileId, String originalFilename, String ext, String newFilename, Long fileSize, int boardNo) {
		super();
		this.fileId = fileId;
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
		this.boardNo = boardNo;

	}
	
	public UploadFileVo(int fileId, String originalFilename, String ext, String newFilename, Long fileSize, int boardNo,
			String base64String) {
		super();
		this.fileId = fileId;
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
		this.boardNo = boardNo;
		this.base64String = base64String;
	}
	
	public UploadFileVo(int fileId, String originalFilename, String ext, String newFilename, Long fileSize, int boardNo,
			String base64String, String userId) {
		super();
		this.fileId = fileId;
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
		this.boardNo = boardNo;
		this.base64String = base64String;
		this.userId = userId;
	}

	public int getFileId() {
		return fileId;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public String getExt() {
		return ext;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public String getUserId() {
		return userId;
	}
	
	public int getBoardNo() {
		return boardNo;
	}

	public String getBase64String() {
		return base64String;
	}

	@Override
	public String toString() {
		return "UploadFileVo [fileId=" + fileId + ", originalFilename=" + originalFilename + ", ext=" + ext
				+ ", newFilename=" + newFilename + ", fileSize=" + fileSize + ", boardNo=" + boardNo + ", base64String="
				+ base64String + ", userId=" + userId + "]";
	}


	
	
	
}

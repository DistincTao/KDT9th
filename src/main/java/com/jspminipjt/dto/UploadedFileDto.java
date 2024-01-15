package com.jspminipjt.dto;

public class UploadedFileDto {
	private String originalFileName;
	private String ext;
	private String newFileName;
	private long fileSize;
	private int boardNo;
	private String base64String;
	
	public UploadedFileDto(String originalFileName, String ext, String newFileName, long fileSize) {
		super();
		this.originalFileName = originalFileName;
		this.ext = ext;
		this.newFileName = newFileName;
		this.fileSize = fileSize;
	}

	public UploadedFileDto(String originalFileName, String ext, String newFileName, long fileSize, int boardNo) {
		super();
		this.originalFileName = originalFileName;
		this.ext = ext;
		this.newFileName = newFileName;
		this.fileSize = fileSize;
		this.boardNo = boardNo;
	}

	public UploadedFileDto(String originalFileName, String ext, String newFileName, long fileSize, int boardNo,
			String base64String) {
		super();
		this.originalFileName = originalFileName;
		this.ext = ext;
		this.newFileName = newFileName;
		this.fileSize = fileSize;
		this.boardNo = boardNo;
		this.base64String = base64String;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public String getExt() {
		return ext;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	@Override
	public String toString() {
		return "UploadedFileDto [originalFileName=" + originalFileName + ", ext=" + ext + ", newFileName=" + newFileName
				+ ", fileSize=" + fileSize + ", boardNo=" + boardNo + ", base64String=" + base64String + "]";
	}


	
}

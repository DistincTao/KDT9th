package com.jspminipjt.vo;

public class UploadedFileVo {
	private int fileId;
	private String originalFilename;
	private String ext;
	private String newFilename;
	private Long fileSize;
	private String userId;
	
	public UploadedFileVo() {
		super();
	}

	public UploadedFileVo(int fileId, String originalFilename, String ext, String newFilename, Long fileSize, String userId) {
		super();
		this.fileId = fileId;
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
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

	@Override
	public String toString() {
		return "ImageVo [fileId=" + fileId + ", originalFilename=" + originalFilename + ", ext=" + ext
				+ ", newFilename=" + newFilename + ", fileSize=" + fileSize + ", userId=" + userId + "]";
	}
}

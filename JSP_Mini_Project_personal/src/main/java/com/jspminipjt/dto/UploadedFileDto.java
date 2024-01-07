package com.jspminipjt.dto;

public class UploadedFileDto {
	private String originalFilename;
	private String ext;
	private String newFilename;
	private Long fileSize;
	
	public UploadedFileDto() {
		super();
	}

	public UploadedFileDto(String originalFilename, String ext, String newFilename, long fileSize) {
		super();
		this.originalFilename = originalFilename;
		this.ext = ext;
		this.newFilename = newFilename;
		this.fileSize = fileSize;
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

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "UploadedFileDto [originalFilename=" + originalFilename + ", ext=" + ext
				+ ", newFilename=" + newFilename + ", fileSize=" + fileSize + "]";
	}
	
	
}

package com.jspminipjt.etc;

public class UploadedFile {
	private String originalFileName;
	private String ext;
	private String newFileName;
	private long fileSize;
	
	public UploadedFile(String originalFileName, String ext, String newFileName, long fileSize) {
		super();
		this.originalFileName = originalFileName;
		this.ext = ext;
		this.newFileName = newFileName;
		this.fileSize = fileSize;
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

	@Override
	public String toString() {
		return "UploadedFile [originalFileName=" + originalFileName + ", ext=" + ext + ", newFileName=" + newFileName
				+ ", fileSize=" + fileSize + "]";
	}
	
}

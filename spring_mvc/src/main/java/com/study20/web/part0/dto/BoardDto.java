package com.study20.web.part0.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Alias("BoardDto")
public class BoardDto {
	private int articleId;
	private String title;
	private String content;
	private String name;
	private int insertDate;
	private String insertDateDt;
	private String ipaddr;
	MultipartFile file;
	private String fileName;
	private long fileSize;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(int insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertDateDt() {
		return insertDateDt;
	}

	public void setInsertDateDt(String insertDateDt) {
		this.insertDateDt = insertDateDt;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		String retFileName = "";
		
		if ( file != null ) {
			retFileName = file.getOriginalFilename();
		}
		else {
			retFileName = fileName;
		}
		
		return retFileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		long retFileSize = 0;
		
		if ( file != null ) {
			retFileSize = file.getSize();
		}
		else {
			retFileSize = fileSize;
		}
		
		return retFileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}

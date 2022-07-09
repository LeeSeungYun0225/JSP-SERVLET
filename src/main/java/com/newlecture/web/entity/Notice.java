package com.newlecture.web.entity;

import java.sql.Date;

public class Notice {
	private String title;
	private String writer_id;
	private int hit;
	private String files;
	private String content;
	private Date date;
	private int id;
	private boolean pub;
	
	public Notice(int id,String title, String writer_id, Date date, int hit, String files, String content) {
		super();
		this.title = title;
		this.writer_id = writer_id;
		this.hit = hit;
		this.files = files;
		this.content = content;
		this.date = date;
		this.id = id;
		this.pub = false;
	}
	
	
	public Notice(int id,String title, String writer_id, Date date, int hit, String files, String content,boolean pub) {
		super();
		this.title = title;
		this.writer_id = writer_id;
		this.hit = hit;
		this.files = files;
		this.content = content;
		this.date = date;
		this.id = id;
		this.pub = pub;
	}
	
	public void setPub(boolean pub) {
		this.pub = pub;
	}
	public boolean getPub() {
		return pub;
	}
	

	

	@Override
	public String toString() {
		return "Notice [title=" + title + ", writer_id=" + writer_id + ", hit=" + hit + ", files=" + files
				+ ", content=" + content + ", date=" + date + ", id=" + id + ", pub=" + pub + "]";
	}

	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	
}

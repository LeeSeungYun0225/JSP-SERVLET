package com.newlecture.web.entity;

import java.sql.Date;

public class NoticeView extends Notice {
	private int cmt_count;
	private int publ = 0;
	
	public NoticeView(int id,String title, String writer_id, Date date, int hit, String files,int count) {
		// TODO Auto-generated constructor stub
		super(id,title,writer_id,date,hit,files,"");
		this.cmt_count = count;
		
	}
	
	public int getCmt_count() {
		return cmt_count;
	}
	
	
	
	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}
	

	public void setPubl(int pub) {
		this.publ = pub;
	}
	public int getPubl() {
		return publ;
	}
}

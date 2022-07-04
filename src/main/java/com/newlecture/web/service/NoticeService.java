package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	
	public NoticeService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Notice> getNoticeList(){
		return getNoticeList("title","",1);
		
	}
	
	public List<Notice> getNoticeList(int page){
		return  getNoticeList("title","",page);
		
	}
	
	public List<Notice> getNoticeList(String field,String query,int page){
		
		String sql = "SELECT * FROM("
				+ "SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM ,NOTICE.*"
				+ " FROM NOTICE"
				+ " )"
				+ "	WHERE NUM BETWEEN 1 AND 10";
		
		
		return null;
		
	}
	
	public int getNoticeCount()
	{
		
		
		return getNoticeCount("title  ","");
	}
	
	public int getNoticeCount(String field, String query)
	{
		
		String sql = "SELECT * FROM("
				+ "SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM ,NOTICE.*"
				+ " FROM NOTICE"
				+ " )"
				+ "	WHERE NUM BETWEEN 1 AND 10";
		return 0;
	}
	
	
	public Notice getNotice(int id)
	{
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		
		return null;
	}
	
	public Notice getPrevNotice(int id)
	{
		String sql = "SELECT *"
				+ "FROM NOTICE "
				+ "WHERE id IN"
				+ "(SELECT id FROM NOTICE"
				+ "WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE id = 3))"
				+ "ORDER BY REGDATE DESC"
				+ "LIMIT 1";
		
		return null;
	}
	
	public Notice getNextNotice(int id)
	{
		String sql = "SELECT *"
				+ "FROM NOTICE "
				+ "WHERE id IN"
				+ "(SELECT id FROM NOTICE"
				+ "WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE id = 3))"
				+ "ORDER BY REGDATE ASC"
				+ "LIMIT 1";
		return null;
	}
}

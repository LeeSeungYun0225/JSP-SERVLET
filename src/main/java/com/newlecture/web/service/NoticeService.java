package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public class NoticeService {
	
	public NoticeService() {
		// TODO Auto-generated constructor stub
		
		 
	}
	
	
	
	public int removeNoticeAll(int[] ids)// 공지를 삭제하고 몇개가 삭제되었는지 반환
	{
		int deleted = 0;
		
		String params = "";
		
		for(int i=0;i<ids.length;i++)
		{
			params+=ids[i];
			if(i != ids.length-1)
			{
				params+=",";
			}
		}
		
		
		String sql = "DELETE FROM NOTICE WHERE ID IN("+params+")";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 Statement statement = con.createStatement();
			 deleted = statement.executeUpdate(sql);
			 // executeUpdate는 insert / delete / update시에 사용하며
			 // 성공한 튜플만큼 개수를 반환한다. 
			 //statement는 PreparedStatement에비해 경량화되어있음
			 
		
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deleted;
	}
	public int pubNoticeAll(int[] ids)//공지를 공개하고 몇개가 공개되었는지 반환
	{
		return 0;
	}
	
	public boolean insertNotice(Notice notice) //공지를 올리고 성공시 true 실패시 false 반환
	{
		return true;
	}
	
	public boolean deleteNotice(int id) // 공지를 삭제하고 성공시 true 반환
	{
		
		
		return true;
	}
	
	public boolean updateNotice(Notice notice)//공지를 업데이트하고 성공시 true 반환
	{
		return true;
	}
	
	public List<Notice> getNoticeNewestList(){// 최신 공지를 가져온다
		return null;
	}
	
	
	
	
	
	
	public class IdTitle{
		private int id;
		private String title;
		public IdTitle(IdTitle in) {
			// TODO Auto-generated constructor stub
			
			id = in.getId();
			title = in.getTitle();
		}
		
		public IdTitle(int id_,String title_) {
			// TODO Auto-generated constructor stub
			
			id = id_;
			title = title_;
		}
		public String getTitle() {
			return title;
		}
		
		public int getId() {
			return id;
		}
		
		public void setTitle_(String title_) {
			this.title = title_;
		}
		
		public void setId_(int id_) {
			this.id = id_;
		}
	}
	
	public List<NoticeView> getNoticeList(){
		return getNoticeList("title","",1);
		
	}
	
	public List<NoticeView> getNoticeList(int page){
		return  getNoticeList("title","",page);
		
	}
	
	public List<NoticeView> getNoticeList(String field/*title or writer_id*/,String query/*A*/,int page){
		
		List<NoticeView> list = new ArrayList<NoticeView>();
	
		String sql = "SELECT * FROM("
				+ "				SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM , note.*"
				+ "				 FROM  (SELECT * FROM NOTICE_VIEW WHERE " + field + " LIKE ?) as note"
				+ "				  ) as b"
				+ "				 	WHERE NUM BETWEEN ? AND ?";
				
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, "%"+query+"%");
			 statement.setInt(2,1+(page-1)*10);
			 statement.setInt(3,page*10);
			 int cmtcount;
			 String files;
			 int hit;
			 int id;
			 String title;
			 String writer_id;
			 Date regdate;

			 ResultSet result = statement.executeQuery();
			 
			 while(result.next()) {
				 id = result.getInt("ID");
				 title = result.getString("TITLE");
				 writer_id= result.getString("WRITER_ID");
				 regdate = result.getDate("REGDATE");
				 hit = result.getInt("HIT");
				 files = result.getString("FILES");
				 cmtcount = result.getInt("COMMENT_COUNT");
				 NoticeView notice = new NoticeView(id,title,writer_id,regdate,hit,files,cmtcount);
				 list.add(notice);
			 }
			 
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return list;
		
	}
	
	public int getNoticeCount()
	{	
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field, String query)
	{
		int count = 0;
		
		String sql ="SELECT COUNT(ID) COUNT FROM("
				+ "				SELECT row_number() OVER (ORDER BY REGDATE DESC) NUM ,nn.*"
				+ "				 FROM (SELECT * FROM NOTICE n WHERE n."+ field + " LIKE ?) nn ) b";
				
				
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setString(1, "%"+query+"%");
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next())
			 {
				 count = result.getInt("count");
			 }
			
			 System.out.println(count);
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	
	public Notice getNotice(int id)
	{
		
		Notice notice = new Notice();
		
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 while(result.next()) {
				 int id_ = result.getInt("ID");
				 String title = result.getString("TITLE");
				 String writer_id= result.getString("WRITER_ID");
				 Date regdate = result.getDate("REGDATE");
				 int hit = result.getInt("HIT");
				 String files = result.getString("FILES");
				 String content = result.getString("CONTENT");
				 notice = new Notice(id_,title,writer_id,regdate,hit,files,content);
			 }
			 
			 
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
	}
	
	public IdTitle getPrevNotice(int id)
	{
		int id_=0;
		String title_ = null;
		IdTitle notice = new IdTitle(id_,title_);
		String sql = "SELECT id,title FROM NOTICE WHERE id IN"
				+ "(SELECT id FROM NOTICE WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE id = ?))"
				+ "ORDER BY REGDATE DESC LIMIT 1";
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next()) {
				 id_ = result.getInt("ID");
				 title_ = result.getString("TITLE");
				 notice = new IdTitle(id_,title_);
			 }

				
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
	}
	
	public IdTitle getNextNotice(int id)
	{
		
		int id_=0;
		String title_ = null;
		IdTitle notice = new IdTitle(id_,title_);
		String sql = "SELECT id,title FROM NOTICE WHERE id IN"
				+ "(SELECT id FROM NOTICE WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE id = ?))"
				+ "ORDER BY REGDATE ASC LIMIT 1";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 
			 
			 PreparedStatement statement = con.prepareStatement(sql);
			 statement.setInt(1, id);
			 
			 
			 ResultSet result = statement.executeQuery();
			 
			 if(result.next()) {
				 id_ = result.getInt("ID");
				 title_ = result.getString("TITLE");
				 notice = new IdTitle(id_,title_);
			 }

				
			 
			 result.close();
			 con.close();
			 statement.close();
			
			 
			 
			 
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		return notice;
		
		
		
	}




	
	
	

}

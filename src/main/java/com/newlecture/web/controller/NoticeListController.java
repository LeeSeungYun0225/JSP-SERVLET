package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;


@WebServlet("/notice/list")
public class NoticeListController  extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
 

		List<Notice> list = new ArrayList<Notice>();
		
		
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			 String adminId = "root";
			 String adminPass = "!Ekdma0607";
			 Connection con = DriverManager.getConnection(url,adminId,adminPass);
			 
			 String sql = sql = "select * from notice";
			 PreparedStatement statement = con.prepareStatement(sql);
			 
			 ResultSet result = statement.executeQuery();
			while(result.next())
			{
				int id = result.getInt("ID");
				String title = result.getString("TITLE");
				String writer_id = result.getString("WRITER_ID");
				Date date = result.getDate("REGDATE");
				int hit = result.getInt("HIT") ;
				String files = result.getString("FILES");
				String content = result.getString("CONTENT");
				Notice notice = new Notice(id,title,writer_id,date,hit,files,content);
				request.setAttribute("notice",notice);
				
				list.add(notice);
			}
			
			
			con.close();
	    	statement.close();
	    	result.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

			
					
		 request.setAttribute("list", list);
		 
		 try {
			request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
			 .
			 forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

					
		    	

		 
	}
	
	
	

	
}

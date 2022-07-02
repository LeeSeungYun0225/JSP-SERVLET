package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));

		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url  = "jdbc:mysql://localhost:3306/servlet?useSSL=false";
			String adminId = "root";
			String adminPass = "!Ekdma0607";
			String sql = sql = "select * from notice WHERE id=?";
			Connection con = DriverManager.getConnection(url,adminId,adminPass);
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1,id);
			ResultSet result = statement.executeQuery();
			result.next();

			String title =result.getString("TITLE") ;
			String writer_id=result.getString("WRITER_ID"); 
			int hit=result.getInt("HIT");
			String files =result.getString("FILES");
			String content=result.getString("CONTENT");
			Date date =result.getDate("REGDATE");
			
			
			Notice notice = new Notice(id,title,writer_id,date,hit,files,content);
			request.setAttribute("notice",notice);
			//데이터들을 하나의 객체에 담아서 attribute로 전달 

			request.setAttribute("title", title);
			request.setAttribute("writer_id", writer_id);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			request.setAttribute("date", date);
			
			
			con.close();
			statement.close();
			result.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		//redirect//
		 //foward//
		 try {
			request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()	;
		}
		 
		 
	}
}

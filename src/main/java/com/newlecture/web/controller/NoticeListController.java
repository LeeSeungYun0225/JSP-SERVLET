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
import com.newlecture.web.service.NoticeService;


@WebServlet("/notice/list")
public class NoticeListController  extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String field ="";
		String query = "";
		List<Notice> list;
		field = request.getParameter("f");
		query = request.getParameter("q");
		
		
		NoticeService service = new NoticeService();
		if(field== null)
		{
			list = service.getNoticeList();
		}
		else
		{
			list = service.getNoticeList(field,query,1);
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

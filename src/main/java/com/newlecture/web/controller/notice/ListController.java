package com.newlecture.web.controller.notice;

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
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;


@WebServlet("/notice/list")
public class ListController  extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String field_ ="title";
		String query_ = "";
		int page_ = 1;
		List<NoticeView> list;
		int count_ = 1;
		
		
		String field = request.getParameter("f");
		String query = request.getParameter("q");
		String page = request.getParameter("p");	
		
		
		NoticeService service = new NoticeService();
		if(field!= null && !field.equals(""))
		{
			field_ = field;
		}
		if(page!= null && !page.equals(""))
		{
			page_ = Integer.parseInt(page);
		}
		if(query!=null && !query.equals(""))
		{
			query_ = query;
		}
		
		count_ = service.getNoticeCount(field_,query_);
		
		list = service.getNoticePubList(field_,query_,page_);
		
		 request.setAttribute("list", list);
		 request.setAttribute("count", count_);
		 
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

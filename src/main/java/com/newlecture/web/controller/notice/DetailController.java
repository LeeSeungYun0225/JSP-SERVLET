package com.newlecture.web.controller.notice;

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
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.NoticeService.IdTitle;

@WebServlet("/notice/detail")
public class DetailController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		
		NoticeService service = new NoticeService();
		

		
		IdTitle nextOne = service.getNextNotice(id);
		IdTitle prevOne =  service.getPrevNotice(id);
		
		System.out.println("next" + nextOne.getId() + nextOne.getTitle());
		System.out.println("prev" + prevOne.getId() + prevOne.getTitle());
		Notice notice = service.getNotice(id);
		
		request.setAttribute("notice",notice);
		request.setAttribute("prevOne", prevOne);	
		request.setAttribute("nextOne", nextOne);
		
		
	
		//redirect//
		 //foward//
		 try {
			request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()	;
		}
		 
		 
	}
}

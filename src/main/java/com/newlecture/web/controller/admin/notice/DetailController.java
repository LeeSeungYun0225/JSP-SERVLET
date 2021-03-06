package com.newlecture.web.controller.admin.notice;

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
import javax.servlet.http.HttpSession;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.MemberService;
import com.newlecture.web.service.NoticeService;
import com.newlecture.web.service.NoticeService.IdTitle;

@WebServlet("/admin/board/notice/detail")
public class DetailController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		
		NoticeService service = new NoticeService();
		

		
		IdTitle nextOne = service.getNextNotice(id);
		IdTitle prevOne =  service.getPrevNotice(id);
		

		Notice notice = service.getNotice(id);
		
		request.setAttribute("notice",notice);
		request.setAttribute("prevOne", prevOne);	
		request.setAttribute("nextOne", nextOne);
		
		
	
		//redirect//
		 //foward//
		 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()	;
		}
		 
		 
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
			
		
		String logout = request.getParameter("logout");
		if(logout.equals("confirm"))
		{
			HttpSession session = request.getSession();
			session.removeAttribute("member");
			response.sendRedirect("/member/login");
		}
		
	}
}

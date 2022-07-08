package com.newlecture.web.controller.admin.notice;


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


@WebServlet("/admin/board/notice/list")
public class ListController  extends HttpServlet {
	//404 URL이 없는경우
	//405 URL은 있으나 처리해줄 메소드가 없음
	//403 URL / 메소드는 있으나 권한이 없는경우
	
	//jsp에서는 같은 form 안에 있는 데이터만 submit을 통해 전송할 수 있음 
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String[] openIds = request.getParameterValues("open-id");
		String[] delIds = request.getParameterValues("del-id");
		
		String command = request.getParameter("command");
		
		switch(command)
		{
			case "일괄공개":
				for(String str:openIds)
				{
					//
				}
				break;
			case "일괄삭제":
				NoticeService noticeService = new NoticeService();
				int[] ids = new int[delIds.length];
				for(int i=0;i<delIds.length;i++)
				{
					ids[i] = Integer.parseInt(delIds[i]);
				}
				int returns = noticeService.removeNoticeAll(ids);
				break;
		}
		

		response.sendRedirect("list"); //list페이지를 재요청(doget 로직이 실행됨) - 페이지 리로드
		
		
	}
	
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
		
		list = service.getNoticeList(field_,query_,page_);

		 request.setAttribute("list", list);
		 request.setAttribute("count", count_);
		 
		 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp")
			 .
			 forward(request,response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

					
		    	

		 
	}
	
	
	

	
}

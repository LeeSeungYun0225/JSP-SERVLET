package com.newlecture.web.controller.admin.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;


@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
	
	
	 try {
			request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()	;
		}

	}	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String isOpen = request.getParameter("open");
		
		boolean pub = false;

		if(isOpen != null)
		{
			pub = true;
		}
		
		Notice notice = new Notice();
		notice.setContent(content);
		notice.setTitle(title);
		notice.setPub(pub);
		notice.setWriter_id("Tester");
		NoticeService service = new NoticeService();
		
		
		service.insertNotice(notice);
		

		response.sendRedirect("/admin/board/notice/list");
	}

	


}

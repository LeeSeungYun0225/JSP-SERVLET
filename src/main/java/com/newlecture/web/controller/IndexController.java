package com.newlecture.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/index")
public class IndexController extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	
	
	
	
	request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
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

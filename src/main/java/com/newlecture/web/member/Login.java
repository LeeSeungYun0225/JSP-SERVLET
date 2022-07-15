package com.newlecture.web.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@WebServlet("/member/login") // 로그인 서블릿 
public class Login extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		
		//response.sendRedirect("login");
		
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp").forward(request,response);

	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String id = request.getParameter("username");
		String pass = request.getParameter("password");
		System.out.println(id + pass);
		
		Member member = new Member(id,pass);
		MemberService memberService = new MemberService();
		
		boolean result = memberService.memberLogin(member);
		
		if(result) // 로그인 성공시 
		{
			HttpSession session = request.getSession(); // 세션 정보 저장 
			session.setAttribute("member",member);
			response.sendRedirect("/index");	
		}
		else
		{
			System.out.println("login Failed");
		}
		
	}
}

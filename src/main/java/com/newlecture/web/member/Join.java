package com.newlecture.web.member;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@WebServlet("/member/join") // 회원가입 서블릿 
public class Join extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
		
		
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pass = request.getParameter("pwd");
		String pass2 = request.getParameter("pwd2");//비밀번호 확인 
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String email =  request.getParameter("email");
	
		
		if(pass.equals(pass2))
		{
			Member member = new Member(id,pass,name,gender,birthday,phone,email);
			MemberService memberService = new MemberService();
			
			boolean result = memberService.memberJoin(member);
			
			if(result) // 회원가입 성공시 
			{
				response.sendRedirect("/index");
			}
			else
			{
				System.out.println("가입 실패");
				response.sendRedirect("/member/join");
			}
		}
		else
		{
			System.out.println("패스워드 확인해주세요");
		}
		
	}
}

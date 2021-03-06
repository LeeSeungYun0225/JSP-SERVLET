package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet {
	
	
	// request는 클라이언트로부터의 입력/ 요구사항
	//response는 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		
		
		response.setCharacterEncoding("UTF-8");
		
		
		//request.setCharacterEncoding("UTF-8");
		//한글 입력시 깨지는 문제 발생 
				// 한글은 기본적으로 글자당 2바이트, 영어는 1바이트,
				//post로 전송시에 ISO-8859-1형태로 인코딩방식을 이용해 
				//1바이트를 1문자로 인식함 
			//  request를 입력받기 전에 UTF-8이라고 알려주면 된다 .
		//UTF-8형식으로 인코딩 
		// 브라우저에게 UTF-8 형식으로 보낸다고 알려주어야 한다.
		
		
		
		//UTF-8로 설정 안하고 필터를 사용해보기 
		response.setContentType("text/html; charset=UTF-8");
		//파일의 형식 : html , 텍스트 형식 : UTF8로 보낸다고 알려주는 문장 
		
		
		PrintWriter out = response.getWriter();
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//쿼리스트링이 너무 길어지는 문제 발생 
		//html에서 method="post" 사용하면 해결 
		
		
		
		out.println(title);
		out.println(content);
		
	}
}

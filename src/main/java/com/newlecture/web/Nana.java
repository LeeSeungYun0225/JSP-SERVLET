package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/hello")
public class Nana extends HttpServlet {
	
	
	// request는 클라이언트로부터의 입력/ 요구사항
	//response는 
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		
		
		response.setCharacterEncoding("UTF-8");
		//UTF-8형식으로 인코딩 
		// 브라우저에게 UTF-8 형식으로 보낸다고 알려주어야 한다.
		
		response.setContentType("text/html; charset=UTF-8");
		//파일의 형식 : html , 텍스트 형식 : UTF8로 보낸다고 알려주는 문장 
		
		
		PrintWriter out = response.getWriter();
		
		
		int cnt = Integer.parseInt(request.getParameter("req"));
		//파라미터는 서버에 요청시 url이 같아야함 http://localhost:8080/hello?req=?
		
		
		for(int i=0;i<cnt;i++)
		{
			out.println(i+1+ " : 안녕 Servlet!!<br >");
			//브라우저에 컨텐츠 형식을 알려주지 않으면
			//자의적으로 해석하여 보여준다. 
			// 따라서 출력 형식을 지정하여 
			//브라우저가 달라져도 동일한 결과를 나타낼 수 있게 해야함
			
			//한글이 깨지는 이유
			// 1) 한글을 지원하지 않는 문자코드로 인코딩 
			// 2) 서버에서는 UTF-8로 인코딩하였으나 브라우저가 다른 코드로 잘못 해석 
		}
	}
}

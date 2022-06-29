package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculator2")
public class Calculator2 extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		Cookie[] cookies = request.getCookies();
		//쿠키는 클라이언트가 가지고 있다가 통신시마다 사용하는 데이터 
		//배열의 형태로 들어온다 
		//쿠키의 생존주기는 별도로 설정하지 않으면 브라우저 종료시 함께 사라짐
		// 설정해주면 브라우저 닫혀도 유지할수 있다.
		//메모리 혹은 파일로 저장 
		
	
		PrintWriter out = response.getWriter();
		
		int v = Integer.parseInt(request.getParameter("v"));
		String in = request.getParameter("operator");
		
		
		// application 컨텍스트에 value의 이름으로 v를 저장
		// opper의 이름으로 in을 저장 
		
		//ServletContext application = request.getServletContext();
		// 어플리케이션 전역에서 사용할 수 있다
		// 모든이의 데이터가 공용 
		
		
		//HttpSession session = request.getSession();
		// 세션 범주 내에서 사용할 수 있다.
		// 세션 = 현재 접속한 사용자 연결 >> 접속자마다 별도로 관리 
		// 프로세스별로 세션을 관리하기때문에 같은 브라우저는 하나의 프로세스에 다중 스레드이므로 같은 세션 취급
		// 다른 브라우저는 서로 프로세스가 다르기 때문에 다른 세션으로 인식
		//was에 의해 세션 관리 
		// 타임아웃 되면 이후 새로운 요청을 하면 새로운 세션으로 인식 
		
		
		
	
		
		if(in.equals("=")) //연산 버튼 누르면 
		{
			//int x= (int) session.getAttribute("value");
			//int x= (int) application.getAttribute("value");
			int y = v;
			int x=0;
			String operator="";
			for(Cookie c : cookies)
			{
				if(c.getName().equals("value1"))
				{
					x = Integer.parseInt(c.getValue());
				}
				else if(c.getName().equals("operator1"))
				{
					operator = String.valueOf(c.getValue());
				}
			}
			
			
			
			
			
			//String op = (String) session.getAttribute("opper");
			//String op = (String) application.getAttribute("opper");
			int result =-999;
			
			if(operator.equals("+"))
			{

				result = x+y;
			}
			else if(operator.equals("-"))
			{
				result = x-y;
			}
			out.println(result);
		}
		else // 덧셈이나 뺄셈일때 
		{ // 내용을 저장 
			
			//application.setAttribute("value", v); 
			//application.setAttribute("opper", in);
			//session.setAttribute("value", v);
			//session.setAttribute("opper", in);
			
			//쿠키에는 문자열만 저장됨 
			Cookie valueCookie = new Cookie("value1",String.valueOf(v));
			Cookie opCookie = new Cookie("operator1",in);
			valueCookie.setPath("/calculator2");
			valueCookie.setMaxAge(10);//만료날짜 설정 (초단위)
			opCookie.setPath("/calculator2"); // 쿠키의 경로 지정 >> 효과적인 쿠키 사용 가능 
			opCookie.setMaxAge(10);
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			// 이 문장을 통해 클라이언트에게 쿠키가 전달됨 

		}	
		
		

	}
}

/*application :: 전역 범위에서 사용하는 저장공간
 >> 생명주기는 was가 시작해서 종료할때 까지 유지
  >> was 서버의 메모리에 저장 
  

Session : 세션-사용자(프로세스) 범위에서 사용하는 저장 공간
 >> 생명주기는 세션이 시작해서 종료될때까지
  >> was 서버의 메모리에 저장
  
 Cookie : web 브라우저별 지정한 저장소 
  >> 생명주기는 브라우저에 전달한 시간부터 만료시간까지
  >> web브라우저 메모리 혹은 파일에 저장  >> 서버의 생명주기와 무관하게 유지할 수 있다.
  */


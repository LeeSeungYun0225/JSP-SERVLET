package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculator3")
public class Calculator3 extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		Cookie[] cookies = request.getCookies();
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		
		if(cookies != null)
		{
			for(Cookie c : cookies)
			{
				if(c.getName().equals("exp"))
				{
					exp = String.valueOf(c.getValue());
					break;
					
				}
			}
		}
		
		if(operator !=null && operator.equals("="))
		{
			/*ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			// 스크립트 엔진 작동 안함
		}
		else if(operator != null && operator.equals("C"))
		{
			exp ="";
			
		}
		else
		{
			exp+= (value==null) ? "" :value;
			exp+=(operator==null) ? "" :operator;
			exp+=(dot==null) ? "" : dot;
		}
		
		
		Cookie expCookie = new Cookie("exp",exp);
		if(operator != null && operator.equals("C"))
		{
			expCookie.setMaxAge(0);
		}
		
		expCookie.setPath("/calculator3");
		response.addCookie(expCookie);
		response.sendRedirect("/calculatorPage");

	}
}




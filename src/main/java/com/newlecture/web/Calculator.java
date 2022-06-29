package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
		PrintWriter out = response.getWriter();
		
		int first = Integer.parseInt(request.getParameter("first"));
		int second = Integer.parseInt(request.getParameter("second"));
		
		String in = request.getParameter("operator");
		
		if(in.equals("plus"))
		{
			out.println(first+second);
		}
		else
		{
			out.println(first-second);
		}
		
		
	}
}

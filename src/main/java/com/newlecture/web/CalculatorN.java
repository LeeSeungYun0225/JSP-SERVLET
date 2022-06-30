package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/calculatorN")
public class CalculatorN extends HttpServlet{

	//service 내에서 GET메소드 / POST메소드 오버라이드 하던지,
	//doGet / doPost메소드로 각각 오버라이드 하던지 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
	{
	/*	if(request.getMethod().equals("GET"))//get / post를 구분할 수 있다 - 대문자로만 써야된다 
		{
			System.out.println("GET 요청");
		}
		else if(request.getMethod().equals("POST"))
		{
			System.out.println("POST 요청");
		}
		*/
		
		super.service(request, response);
		// doPost / doGet메소드를 찾아 실행 
		//html 파일에서 post로 요청이 오면 doPost실행
		// get으로 요청이 오면 doGet실행 
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
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
		
		expCookie.setPath("/calculatorN");
		response.addCookie(expCookie);
		response.sendRedirect("/calculatorN"); // redirect는 get요청임

	}
	
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
	Cookie[] cookies = request.getCookies();
		
		String exp = "0";
		if(cookies != null)
		{
			for(Cookie c: cookies)
			{
				if(c.getName().equals("exp"))
				{
					exp = String.valueOf(c.getValue());
					break;
				}
			}
		}
	
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");


		out.write("<style>");
		out.write("input{");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}.output{");
		out.write("height:50px;");
		out.write("background:#e9e9e9;");
		out.write("font-size:24px;");
		out.write("font-weight:bold;");
		out.write("text-align:right;");
		out.write("padding:0px 5px;}</style>");
		out.write("</head>");
		out.write("<body>");

		out.write("	<div>");
		out.write("	<form method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		out.printf("				<td class =\"output\" colspan=\"4\">%s</td>",exp);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"CE\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"C\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"BS\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"/\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"7\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"8\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"9\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"*\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"4\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"5\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"6\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"-\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"1\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"2\"/></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"3\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"+\"/></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input name=\"value\" type=\"submit\" value=\"0\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\".\"/></td>");
		out.write("				<td><input name=\"operator\" type=\"submit\" value=\"=\"/></td>");
		out.write("			</tr>");
		out.write("		</table>");

					
		out.write("		</form>	");
			
		out.write("	</div>	");

		out.write("</body>");
		out.write("</html>");
		
		

	}
	
}

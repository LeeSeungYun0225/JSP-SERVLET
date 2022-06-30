package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/spa")
public class spag extends HttpServlet{

	//컨트롤러 역할 // 실행은 컨트롤러에서..
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		int num = 0;
		String num_ = request.getParameter("num");
		if(num_ != null && !num_.equals(""))
		{
			num = Integer.parseInt(num_);
		}


		String result; // >> 모델 역할 

		if(num%2 !=0)
		{
			result = "홀수";
		}
		else
		{
			result = "짝수";
		}

		
		///포워딩 : 현재 작업한 내용을 이어가면서 연결  /// 
		// 리디렉트 : 현재 작업한 내용과 무관하게 연결
		
		request.setAttribute("result", result);
		
		String[] names = {"num1","num2"};
		request.setAttribute("names", names);
		
		Map<String,Object> notice = new HashMap<String,Object>();
		notice.put("id", 1);
		notice.put("title", "ELtest");
		request.setAttribute("map",notice);
		
		String sec = "tester";
	
		
		request.setAttribute("aa",sec);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("spa.jsp");
		
		try {
			dispatcher.forward(request,response); // 포워드로 공유하며 사용할 수 있는 저장소 request.
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

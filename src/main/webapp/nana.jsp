<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

int cnt = 100;


String temp =request.getParameter("req");

if(temp != null && !temp.equals(""))
{
	cnt =Integer.parseInt(temp);
} %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% for(int i=0;i<cnt;i++){%>
		안녕 Servlet!!<br>
	<%} %>
	 
</body>
</html>
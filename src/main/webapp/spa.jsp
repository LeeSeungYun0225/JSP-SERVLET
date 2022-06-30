<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 

%>
<!DOCTYPE html>
<html>
<head>
<%
pageContext.setAttribute("aa", 7);
%>

<!-- 페이지 컨텍스트 : 현재 페이지에서 공유되는 문맥 -->
<!-- 세션이나 request,response,attribute등을 get/set할수있음  -->

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${result}입니다.
	${names[1]}
	${map.id}
	${map.title}
	${aa}
	
<!-- 	페이지 > 리퀘스트 > 세션 > 어플리케이션 순의 순서를 갖고 -->
<!-- 	만약 같은 키값을 갖는 경우 위와 같은 우선순위대로 값이 적용된다. -->
<!--  scope를 붙이면 탐색 범위를 제한할 수 있다. -->
	${requestScope.aa}
	<br >
	
	${param.num}
	<!--  num파라미터의 값을 가져옴  -->
	${header.accept }
	<!--  헤더의 accept 정보를 가져옴  -->
	<br >
	
	${aa>4}
	${aa gt 4 }
	${aa ge 4 }
	${aa lt 4 }
	${aa le 4 }
	<!--  >< <=>=가 있는데도 lt gt le ge를 쓰는 이유는 html에서 등호가 오류가 발생하는 환경이 있기 때문 -->
	<!-- [] . () not ! empty * / div % mod + - == != eq ne && and || or ? : -->
	${ empty param.num?'값이 비어있습니다.' : param.num}
	${param.num/2 }
	<!--  empty @@@ 은 isempty의 역할 / not empty도 가능  -->
	
</body>
</html>
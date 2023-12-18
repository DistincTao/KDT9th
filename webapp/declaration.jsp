<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>declaration</title>
</head>
<body>


<%!
	// 선언문 
	// JSP 페이지가 초기화 될 때 초기화 되어 해당 클래스의 맴버 변수, 맴버 메소드가 되는 부분
	// 페이지 내의 어떤 한 스크립틀릿이나 표현식에서도 접근해서 사용할 수 있다.
	String str = "안녕하세요!!"; // 맴버 변수
	public int abs(int n) { // 맴버 메소드
		if (n < 0) { 
			n = -n;
		} 
		return n;
	}
%>

<%
	out.print("<div>" + str + "</div>");
	out.println("<div>" + abs(-4) + "</div>");
%>


</body>
</html>
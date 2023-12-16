<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page1</title>
</head>
<body>
	<h1>Scope Page 1</h1>
	<%
	//각 내장 객체의 영역에 정보 바인딩
	
	pageContext.setAttribute("name", "page data");
	request.setAttribute("name", "request data");
	session.setAttribute("name", "session data");
	application.setAttribute("name", "application data");
	
	// 각 내장 객체의 영역에 바인딩 된 정보 확인
	request.getRequestDispatcher("./scope_Page2.jsp").forward(request, response);
// 	response.sendRedirect("./scope_Page2.jsp");
	%>
	
	<div><%= pageContext.getAttribute("name") %></div>
	<div><%= request.getAttribute("name") %></div>
	<div><%= session.getAttribute("name") %></div>
	<div><%= application.getAttribute("name") %></div>
	
	<a href="./scope_Page2.jsp">이동</a>
	
</body>
</html>
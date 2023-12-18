<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page2</title>
</head>
<body>
	<h1>Scope Page 2</h1>
	
	<div><%= pageContext.getAttribute("name") %></div>
	<div><%= request.getAttribute("name") %></div>
	<div><%= session.getAttribute("name") %></div>
	<div><%= application.getAttribute("name") %></div>
	<a href="./scope_Page3.jsp">이동</a>
</body>
</html>
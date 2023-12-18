<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP</title>
</head>
<body>
	<h1>Hello JSP</h1>
	<div>세션 아이디: <%= session.getId() %></div>
	<div>로그인한 유져 : <% out.print((String) session.getAttribute("loginMemberId")); %></div>
</body>
</html>
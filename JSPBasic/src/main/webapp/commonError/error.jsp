<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공통 짬처리장 (공통 예외를 처리할 떄 사용)</title>
</head>
<body>
	<h1>Error Page</h1>
	<div>Error occurred. call 건우 </div>
	<h4><%=exception.getMessage() %></h4>
	<h4><%=exception.getStackTrace() %></h4>
</body>
</html>
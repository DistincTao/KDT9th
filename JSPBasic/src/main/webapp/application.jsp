<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>application 내장 객체</title>
</head>
<body>
	<div>웹 어플리케이션의 Context Path : <%=application.getContextPath() %></div>
	<div>웹 어플리케이션의 실제 저장 경로 : <%=application.getRealPath("application.jsp") %></div>
	<div><a href="./applicationTest.jsp">applicationTest.jsp로 이동하기</a></div>
</body>
</html>
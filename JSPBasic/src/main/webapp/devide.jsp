<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="commonError/error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일부로 예외를 발생시키는 페이지</title>
</head>
<body>
	<%=2/0 %>
</body>
</html>
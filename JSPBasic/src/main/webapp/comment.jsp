<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>comment</title>
</head>
<body>
<!--  간단한 주석문 -->
<%-- JSP 주석 문 => servlet 코드로 변환되지 않음 --%>
<% String name = "dooly"; %>

Hello <%=name %>

</body>
</html>
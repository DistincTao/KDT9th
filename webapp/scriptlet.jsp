<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>scriptlet</title>
</head>
<body>

<%
	// 스크립트릿에서 선언된 변수는 _jspService()의 지역변수로 만들어 진다
	int num1 = 3, num2 = 5;
	int result = num1 + num2;
	
	out.print("<div>" + num1 + " + " + num2 + " = " + result + "</div><br>");
%>

<div><%=num1 %> + <%=num2 %> = <%=result %></div>

</body>
</html>
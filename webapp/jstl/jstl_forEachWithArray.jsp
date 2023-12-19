<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL forEach with Array</title>
</head>
<body>
<% 
	String [] heros = {"캡틴아메리카", "헐크", "아이언맨", "스파이더맨", "스칼렛위치"};
	pageContext.setAttribute("heros", heros);
%>

<ul>
	<c:forEach var="hero" items="${heros }">
		<li>${hero }</li>
	</c:forEach>
</ul>

</body>
</html>
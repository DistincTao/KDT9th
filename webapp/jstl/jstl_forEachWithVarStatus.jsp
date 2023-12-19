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
<title>JSTL forEach with VarStatus</title>
</head>
<body>

<% 
	String [] heros = {"캡틴아메리카", "헐크", "아이언맨", "스파이더맨", "스칼렛위치"};
	pageContext.setAttribute("heros", heros);
%>

<!-- 짝수번째 index 컬럼의 td의 배경색을 노란색으로 바꾸기 -->
<table border="1">
	<tr>
		<th>index</th><th>count</th><th>hero</th>
	</tr>
	
	<c:forEach var="hero" items="${heros }" varStatus="status">
	<tr>
	<c:choose>
		<c:when test="${status.index % 2 == 0 }">
		<td style="background-color : yellow;">${status.index }</td>
		</c:when>
		<c:otherwise>
		<td>${status.index }</td>
		</c:otherwise>
	</c:choose>
		
		<td>${status.count }</td><td>${hero }</td>
	</tr>
	</c:forEach>
</table>

<!--  첫번쨰 tr에 글자 색을 빨간 색으로 첫번째 tr이 아니면 글자색을 검정색으로 -->

<table border="1">
	<tr>
		<th>index</th><th>count</th><th>hero</th>
	</tr>
	<c:forEach var="hero" items="${heros }" varStatus="status">
	<c:choose>
		<c:when test="${status.first }">
		<tr style="color : red">
		<c:choose>
		
			<c:when test="${status.index % 2 == 0 }">
			<td style="background-color : yellow;">${status.index }</td>
			</c:when>
			
			<c:otherwise>
			<td>${status.index }</td>
			</c:otherwise>
			
		</c:choose>
			<td>${status.count }</td><td>${hero }</td>		
		</tr>
		</c:when>
		
		<c:otherwise>
		<tr>
		<c:choose>
			<c:when test="${status.index % 2 == 0 }">
			<td style="background-color : yellow;">${status.index }</td>
			</c:when>
			
			<c:otherwise>
			<td>${status.index }</td>
			</c:otherwise>
			
		</c:choose>
			<td>${status.count }</td><td>${hero }</td>		
		</tr>
		</c:otherwise>
		
	</c:choose>
	</c:forEach>
</table>








</body>
</html>
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
<title>JSTL forEach Begin End Step</title>
</head>
<body>

	<c:forEach var="i" begin="1" end="10">
		<div>${i }번째 div</div>
	</c:forEach>
	<hr>
	
	<c:forEach var="i" begin="1" end="10" step="2">
		<div>${i }번째 div</div>
	</c:forEach>
	<hr>
	
	<h3>구구단</h3>
<ul>
	<c:forEach var="i" begin="2" end="9">
			<div>${i } 단 </div>
		<c:forEach var="j" begin="1" end="9">	
			<li>${i } * ${j } = ${i * j }</li>
		</c:forEach>
		<br>
	</c:forEach>	
</ul>

</body>
</html>
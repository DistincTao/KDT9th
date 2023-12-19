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
<title>컴퓨터 언어</title>
</head>
<body>
	<h1>내가 선택한 프로그래밍 언어</h1>
	<c:forEach var="lang" items="${paramValues.lang}" varStatus="status">
	<div>${status.count } : ${lang }</div>
	</c:forEach>
	
	<c:forEach var="lang" items="${paramValues.lang}" varStatus="status">
		${lang } <c:if test="${not status.last }">, </c:if>
	</c:forEach>

</body>
</html>
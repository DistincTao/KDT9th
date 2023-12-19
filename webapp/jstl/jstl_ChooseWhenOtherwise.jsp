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
<title>JSTL If Result</title>
</head>
<body>

	<c:choose>
		<c:when test="${param.favSeason == '봄' }">
		<!--  조건문이 참일 동안 실행할 문장 -->
			<div style="color : green;">${param.nickName }씨는 봄을 좋아하시네요</div>
		</c:when>
		
		<c:when test="${param.favSeason == '여름' }">
			<div style="color : blue;">${param.nickName }씨는 여름을 좋아하시네요</div>
		</c:when>
		
		<c:when test="${param.favSeason == '가을' }">
			<div style="color : brown;">${param.nickName }씨는 가을을 좋아하시네요</div>
		</c:when>
		
		<c:otherwise>
			<div style="color : gray;">${param.nickName }씨는 겨울을 좋아하시네요</div>
		</c:otherwise>
	</c:choose>

</body>
</html>
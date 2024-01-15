<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>오류 알림</title>
<link rel="stylesheet" href="./css/header.css?after">
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="container">
	<h1>Error</h1>
	<div>${requestScope.errorMsg }</div>
	<hr>
	<c:forEach var="errorStack" items="${request.errorStack }">
		<div style="color : red;">${errorStack }</div>
	</c:forEach>
	
	
</div>

<jsp:include page="./footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>action tag include</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<h1>action tag의 include 를 이용</h1>
	<div>본문 내용 입력 부분</div>
	<% 
		int a = 30;
	
	%>
	<%= a %>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
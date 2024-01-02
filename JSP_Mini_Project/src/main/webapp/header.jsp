<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<c:set var = "contextPath" value="<%=request.getContextPath() %>"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <link href="css/header.css" rel="stylesheet"> -->
<style>
.header {
	background-image: url("./img/night.jpg");
}
</style>

<title>header</title>
</head>
<body>
<div class="p-5 bg-primary text-white text-center header">
  <h1>JSP MINI PROJECT</h1>
  <p>2024 Jan Version</p> 
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link active" href="${contextPath }/index.jsp">distinctao</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${contextPath }/member/board.jsp">Board</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${contextPath }/member/register.jsp">Sign In</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="${contextPath }/member/login.jsp">Log In</a>
      </li>
    </ul>
  </div>
</nav>

</body>
</html>
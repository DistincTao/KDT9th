<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당신이 좋아하는 계절</title>
</head>
<body>
	<div>당신이 좋아하는 계절은?
	<!--  파라미터 출력 -->
	<%=request.getParameter("favSeason") %>
	</div>
	
	<div> 별명 : <%=request.getParameter("nickName") %></div>
	<hr>
	<div>
	${param.nickName }이 좋아하는 계절은 ${param.favSeason }입니다.
	</div>
	<hr>
	
	<div>
	${param.nickName }이 좋아하는 계절은 ${paramValues.favSeason[0] }와 ${paramValues.favSeason[1] }입니다.
	</div>
	
	
</body>
</html>
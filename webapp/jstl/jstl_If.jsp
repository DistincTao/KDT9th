<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL If Test</title>
</head>
<body>
<!-- <form action="./jstl_ChooseWhenOtherwise.jsp"> -->
<form action="./jstl_If2.jsp">
	<div>좋아하는 계절
		<select name="favSeason">
			<option>봄</option>	
			<option>여름</option>
			<option>가을</option>
			<option>겨울</option>
		</select>
	</div>
	
	<div>
		<input type="text" name="nickName">
	</div>
	<div><input type="submit" value="전송"></div>
</form>
</body>
</html>
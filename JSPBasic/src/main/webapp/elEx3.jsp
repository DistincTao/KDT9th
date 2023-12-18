<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>좋아하는 계절 고르기</title>
</head>
<body>
	<form action="elEx3_1.jsp">
		<div>좋아하는 계절
		<select name="favSeason" multiple="multiple">
			<option>봄</option>
			<option>여름</option>
			<option>가을</option>
			<option>겨울</option>
		</select>
		</div>
		
		<div>별명:
			<input type="text" name="nickName">
		</div>
		<div>
			<input type="submit" value="전송">
		</div>
		
	</form>
	

	
</body>
</html>
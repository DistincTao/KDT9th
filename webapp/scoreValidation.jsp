<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>성적표</h1>
		<form action= "./Score_GET">
			<div>국어 : <input type="text" name="kor"></div>
			<div>영어 : <input type="text" name="eng"></div>
			<div>수학 : <input type="text" name="math"></div>
			<div><input type="submit" value="전송"></div>			
		</form>
	</body>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>쿠키 저장하기</title>
</head>
<body>
	<div>
		<form action="./createCookie.do">
			<input type="submit" value="쿠키저장">
		</form>
	</div>
	
	<div>
		<form action="./readCookie.do">
			<input type="submit" value="쿠키읽기">
		</form>
	</div>
</body>
</html>
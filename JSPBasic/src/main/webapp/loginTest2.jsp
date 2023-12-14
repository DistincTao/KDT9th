<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 1</title>
</head>
<body>
	<form action="./sessionLogin.do" method="POST">
	<div>아이디 : <input type="text" name="userId" value="asdf"></div>
	<div>비 번 : <input type="password" name="userPw" value="1234"></div>
	<input type="submit" value="로그인">
	<input type="reset" value="초기화">
	
	</form>
</body>
</html>
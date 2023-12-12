<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>정보를 입력해주세요 ~ 비밀 보장!</h1>
		<form action = "./UserData_POST" method="POST">
			<div>이름 : <input type="text" name="name"></div>
			<div>ID : <input type="text" name="id"></div>
			<div>PW : <input type="password" name="password"></div>
			<div><input type="submit" value="전송!!!"></div>
		</form>
	</body>
</html>
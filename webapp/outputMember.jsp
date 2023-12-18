<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 내용 확인</title>
</head>
<body>
	<h1>내용 확인 해주세요~</h1>
	<div>ID : ${memberInfo.userId}</div>
	<div>PW : ${memberInfo.psw1}</div>
	<div>Email : ${memberInfo.email}</div>
	<div>Mobile : ${memberInfo.mobile}</div>
	<div>Birth Day : ${memberInfo.bDate}</div>
	<div>Age : ${memberInfo.age}</div>
	<div>Gender : ${memberInfo.gender}</div>
	<div>Hobby : ${memberInfo.hobby}</div>
	<div>Job : ${memberInfo.job}</div>
	<div>Memo : ${memberInfo.memo}</div>
	
</body>
</html>
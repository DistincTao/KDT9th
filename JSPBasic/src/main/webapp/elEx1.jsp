<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 표현 가능한 데이터</title>
</head>
<body>
	<div>정수형 : ${10 }</div>
	<div>실수형 : ${3.14 }</div>
	<div>문자열 : ${"오늘은 매우 춥습니다." }</div>
	<div>논리형 : ${true }</div>
	<div>null : ${null }</div> <%-- null의 경우 빈 값의 형태로 표현됨 --%>
	
</body>
</html>
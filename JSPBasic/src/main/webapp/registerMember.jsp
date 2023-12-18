<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 회원 정보</title>
</head>
<body>
<!--  Bean 객체 생성 -->
	<jsp:useBean id="member1" class="com.jspbasic.dto.MemberBeanDTO"></jsp:useBean>
	
	<jsp:setProperty property="userId" name="member1"/>
	<jsp:setProperty property="psw1" name="member1"/>
	<jsp:setProperty property="email" name="member1"/>
	<jsp:setProperty property="mobile" name="member1"/>
	
<%-- 	<jsp:setProperty property="*" name="member1"/> --%>
	
	<div>아뒤 : <jsp:getProperty property="userId" name="member1"/></div>
	<div>비번 : <jsp:getProperty property="psw1" name="member1"/></div>
	<div>이멜 : <jsp:getProperty property="email" name="member1"/></div>
	<div>전화 : <jsp:getProperty property="mobile" name="member1"/></div>
</body>
</html>
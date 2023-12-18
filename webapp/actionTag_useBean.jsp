<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  기본 생성자를 이용해서 id 속성 값의 이름으로 Bean(객체)를 생성 -->
	<jsp:useBean id="member1" class="com.jspbasic.dto.MemberDTO"></jsp:useBean>

<!--  useBean으로 생성한 객체에 속성 값을 설정 (setter 호출) -->
<!-- 	property => 멤버 변수 , name => 지정한 객체 명 , value => 변경할 변수 값 -->
	<jsp:setProperty property="userId" name="member1" value="gkd1234"/>
	<jsp:setProperty property="psw1" name="member1" value="1234"/>
	<jsp:setProperty property="email" name="member1" value="gkd@123.com"/>
	
		<% out.print(member1.toString()); %>
	
<!--  useBean으로 생성된 객체에서 속성 값을 얻어옴 (getter 호출)-->
	<div>아이디 : <jsp:getProperty property="userId" name="member1"/></div>
	<div>비 번 : <jsp:getProperty property="psw1" name="member1"/></div>
	<div>이메일 : <jsp:getProperty property="email" name="member1"/></div>
</body>
</html>
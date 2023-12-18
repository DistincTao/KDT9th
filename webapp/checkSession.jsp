<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>새션 정보</title>
</head>
<body>
	<% 
	long createTime = session.getCreationTime();
	long lastTime = session.getLastAccessedTime();
	long elapsedTime = lastTime - createTime;
	int inactiveTime = session.getMaxInactiveInterval();
	boolean isNewSession = session.isNew();
	String strNewSession = "";
	
	if (isNewSession) {
		strNewSession = "새로운 세션이 만들어 졌습니다.";
	} else {
		strNewSession = "기존 세션입니다. 새로 만들어지지 않았습니다.";
	}
	
	%>
	<div>
	<% out.print("로그인한 유져 : " + session.getAttribute("loginMemberId")); %>
	</div>
	<div>세션 아이디 : <%= session.getId() %></div>
	<div>세션 시작 시간 : <%= createTime %>ms</div>
	<div>세션 종료 시간 : <%= lastTime %>ms</div>
	<div>세션에 머문 시간 : <%= elapsedTime / 1000 %>s</div>
	<div>세션 유효 시간 : <%= inactiveTime / 60 %>s </div>
	<div>새로운 세션 : <%= strNewSession %> </div>

	
</body>
</html>
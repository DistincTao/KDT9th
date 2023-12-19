<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>response 객체</title>

<script>
function getParameter(parameter) {
	let url = location.href;
	let reval = "";
	if (url.indexOf("?") != -1) {
		let queryString = url.split("?")[1];
		let queryArr = queryString.split("&");
		
		for (let item of queryArr) {
			if (item.split("=")[0] == parameter){
				reval = item.split("=")[1];
				break;
			}
		}
	 }
	return reval;
}
onload = function (){

	let status = getParameter("status")
	if (status == 'loginSuccess') {
		alert("login 성공")
	} else if (status == 'logoutSuccess') {
		alert("logout 성공")
	}

}
</script>
<% 
// 자바로 자바스크립트에 alert 창 띄우기
if (request.getQueryString() != null && request.getParameter("status").equals("loginSuccess")){
	String query = request.getParameter("status");
	out.print("<script> alert('login 성공!!!'); </script>");
} else if (request.getQueryString() != null && request.getParameter("status").equals("logoutSuccess")){
	String query = request.getParameter("status");
	out.print("<script> alert('logout 성공!!!'); </script>");
}
%>

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
	
	//getArrtibuteNames() 메소드 호춣하여 session에 바인딩 된 정보를 출력
	Enumeration <String> sessionInfo = session.getAttributeNames();


	while (sessionInfo.hasMoreElements()) {	
		String sName = sessionInfo.nextElement();
		String sValue = (String) session.getAttribute(sName);
		
		out.print("속성이름 : " + sName + ", 속성값 : " + sValue + "<br>");
	}
%>


</head>
<body>

	<div>로그인한 유져 : <%= session.getAttribute("loginMemberId") %></div>
	<div>세션 아이디 : <%= session.getId() %></div>
	<div>세션 시작 시간 : <%= createTime %>ms</div>
	<div>세션 종료 시간 : <%= lastTime %>ms</div>
	<div>세션에 머문 시간 : <%= elapsedTime / 1000 %>s</div>
	<div>세션 유효 시간 : <%= inactiveTime / 60 %>s </div>
	<div>새로운 세션 : <%= strNewSession %> </div>


	<h1>mainTest.jsp</h1>
	<c:choose>
		<c:when test="${sessionScope.loginMemberId == null}">
		<div>
			<a href="./loginTest1.jsp">로그인 1</a>
		</div>
		<br>
		<div>
			<a href="./loginTest2.jsp">로그인 2 (w/ session)</a>
		</div>
		
		<div><a href="./hello.jsp">hello.jsp로 이동</a></div>
		<br>
		</c:when>
		<c:otherwise>
		<form action="./sessionLogout.do" method="get">
			<input type="submit" value="로그아웃">
		</form>
		</c:otherwise>
	</c:choose>
	<div><a href="./checkSession.jsp">이동</a></div>
</body>
</html>
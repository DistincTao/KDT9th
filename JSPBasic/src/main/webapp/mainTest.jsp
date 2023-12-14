<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
// 자바로 자바스크립트에 alert 창 우기
if (request.getQueryString() != null && request.getParameter("status").equals("loginSuccess")){
	String query = request.getParameter("status");
	out.print("<script> alert('login 성공!!!'); </script>");
} else if (request.getQueryString() != null && request.getParameter("status").equals("logoutSuccess")){
	String query = request.getParameter("status");
	out.print("<script> alert('logout 성공!!!'); </script>");
}
%>
<%
out.print("로그인한 유져 : " + session.getAttribute("loginMemberId"));
%>
</head>
<body>
	<h1>mainTest.jsp</h1>
	<div>
		<a href="./loginTest1.jsp">로그인 1</a>
	</div>
	<br>
	<div>
		<a href="./loginTest2.jsp">로그인 2 (w/ session)</a>
	</div>
	
	<div><a href="./hello.jsp">hello.jsp로 이동</a></div>
	<br>
	<form action="./sessionLogout.do" method="get">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>
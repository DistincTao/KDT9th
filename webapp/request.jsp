<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>Context Path : <%=request.getContextPath() %></div>
	<div>요청 방식 : <%=request.getMethod() %></div>
	<div>요청 URL : <%=request.getRequestURL() %></div>
	<div>요청 URI : <%=request.getRequestURI() %></div>
	<div>쿼리스트링 : <%=request.getQueryString() %></div>
	<div>요청한 프로토콜 : <%=request.getProtocol() %></div>
	<div>요청한 클라이언트 주소 : <%=request.getRemoteAddr() %></div>
	<div>요청한 파일의 실제 경로 : <%=request.getRealPath("request.jsp") %></div>
	
</body>
</html>
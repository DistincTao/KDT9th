<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>JSTL locale</title>
</head>
<body>
	<c:set var="now" value="<%=new java.util.Date() %>"/>
	
	<div>톰켓 서버의 기본 로케일 : <%=response.getLocale() %></div>
	<div>통화 : <fmt:formatNumber value="123456.78"  type="currency"></fmt:formatNumber></div>
	<div>현재 날짜 시간 : <fmt:formatDate value="${now }"  type="both"></fmt:formatDate></div>
	
	<hr>
	
	<fmt:setLocale value="en_US"/>
	<c:set var="nowUS" value="<%=new java.util.Date() %>"/>
	<div>로케일 (us) : <%=response.getLocale() %></div>
	<div>통화 : <fmt:formatNumber value="123456.78"  type="currency"></fmt:formatNumber></div>
	<div>현재 날짜 시간 : <fmt:formatDate value="${nowUS }" type="both" dateStyle="full" timeStyle="full"></fmt:formatDate></div>
	<fmt:timeZone value="GMT-7">
	<div>AZ 시간 GMT-7 : <fmt:formatDate value="${nowUS }" type="both" dateStyle="full" timeStyle="full"></fmt:formatDate></div>
	</fmt:timeZone>
	
	<hr>
	
	<fmt:setLocale value="en_US"/>
	<div><fmt:formatDate value="${now }" type="both" timeZone="GMT"></fmt:formatDate></div>
	<div><fmt:formatDate value="${now }" type="both" timeZone="US/Eastern"></fmt:formatDate></div>
	<div><fmt:formatDate value="${now }" type="both" timeZone="US/Western"></fmt:formatDate></div>
	
	<hr>
	
	<fmt:setLocale value="ja_JP"/>
	<div>로케일 (jp) : <%=response.getLocale() %></div>
	<div>통화 : <fmt:formatNumber value="123456.78"  type="currency"></fmt:formatNumber></div>
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"></fmt:formatDate>
	
</body>
</html>
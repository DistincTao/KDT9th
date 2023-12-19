<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Core set remove out </title>
</head>
<body>
<%
	pageContext.setAttribute("msg", "Hello");
%>
<c:set var="msg2" value="JSTL"/>
<div>${pageScope.msg }</div>
<div>${msg }</div>
<div>${pageScope.msg2 }</div>
<div>${msg2 }</div>

<div><c:out value='${msg }'></c:out></div>
<div><c:out value="오늘 점심도 춥겠구나"></c:out></div>

<c:remove var="msg2"/>
<div>${pageScope.msg2 }</div>
<div><c:out value='${msg2 }'></c:out></div>
</body>
</html>
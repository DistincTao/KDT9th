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
<title>JSTL formatNumber</title>
</head>
<body>
	<c:set var="now" value="<%=new java.util.Date() %>"/>
	
	<div>${now }</div>
	
	<div><fmt:formatDate value="${now }"/></div>
	
	<div><fmt:formatDate value="${now }" type="time"/></div>
	<div><fmt:formatDate value="${now }" type="date"/></div>
	<div><fmt:formatDate value="${now }" type="both"/></div>
	
	<div>time (medium) : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/></div>
	<div>time (long) : <fmt:formatDate value="${now }" type="time" timeStyle="long"/></div>
	<div>time (short) : <fmt:formatDate value="${now }" type="time" timeStyle="short"/></div>

	<div>date (medium) : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/></div>
	<div>date (long) : <fmt:formatDate value="${now }" type="date" dateStyle="long"/></div>
	<div>date (short) : <fmt:formatDate value="${now }" type="date" dateStyle="short"/></div>

	<div>date : <fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss"/> </div>

</body>
</html>
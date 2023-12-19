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
<title>checkbox 다중 입력</title>
</head>
<body>
	<h1>사용 가능 언어</h1>
	<form action="./paramValues.jsp">
		<input type="checkbox" name="lang" value="java"> java
		<input type="checkbox" name="lang" value="js"> javaScript
		<input type="checkbox" name="lang" value="c"> C
		<input type="checkbox" name="lang" value="html"> HTML
		<input type="checkbox" name="lang" value="pyson"> Python
		<input type="checkbox" name="lang" value="fortan"> Fortan
		<input type="submit" value="전송">
	</form>
</body>
</html>
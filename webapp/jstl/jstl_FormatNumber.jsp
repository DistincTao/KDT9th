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
	<div><fmt:formatNumber value="123456.78"></fmt:formatNumber></div>  <!-- 세자리 마다 구분 -->
	<div><fmt:formatNumber value="123456.78" groupingUsed="false"></fmt:formatNumber></div>

	<div><fmt:formatNumber value="0.574" type="percent"></fmt:formatNumber></div>
	<hr>
	<!-- 통화 형식 지정 -->
	<div><fmt:formatNumber value="123456" type="currency"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="123456" type="currency" currencySymbol="$"></fmt:formatNumber></div>
	<hr>
	<!--  #는 채워야할 자리에 비해서 데이터가 모자라면 아무것도 표시 하지 않고 공백으로 표시 -->
	<div><fmt:formatNumber value="1234567.8" pattern="#,###.#"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="1234567.8956" pattern="#,###.###"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="1234567.89" pattern="#,###.###"></fmt:formatNumber></div>
	<hr>
	<!--  0은 채워야할 자리에 비해서 데이터가 모자라면 0으로 채워 표시 -->
	<div><fmt:formatNumber value="1234567.8" pattern="000,000,000.0"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="1234567.8956" pattern="0,000.000"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="1234567.89" pattern="0,000.000"></fmt:formatNumber></div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.jspbasic.dto.ProductDTO, java.util.Map, java.util.HashMap"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>application 영역</title>
</head>
<body>
	<h1>application 영역</h1>
	<% 
		Map<String, ProductDTO> maps = new HashMap<>();
		maps.put("iPhone", new ProductDTO("Xs max", "스페이스 그레이", 2, 1500000));
		maps.put("galaxy", new ProductDTO("23S", "사파이어블루", 3, 1000000));
	
		application.setAttribute("phones", maps);
	%>
	<div><a href="appPage.jsp">appPage.jsp로 이동</a></div>
</body>
</html>
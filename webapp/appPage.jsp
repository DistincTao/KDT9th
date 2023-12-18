<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.jspbasic.dto.ProductDTO, java.util.Map, java.util.HashMap"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>application 영역</title>
</head>
<body>
	<h1>application 영역에서 바인딩한 객체 읽어오기</h1>

<%
	Map<String, ProductDTO> phoneMap = (Map<String, ProductDTO>)application.getAttribute("phones");

	Set<String> keys = phoneMap.keySet();
	
	for (String key : keys){
		ProductDTO product = phoneMap.get(key);
		out.print("상품 이름 : " + product.getName() + ", 색상 : " + product.getColor() + ", 수량 : " + product.getQty() + ", 가격 : "+ product.getPrice() + ", 총 가격 : "+ product.getTotalPrice() + "<br>");
	}
%>

</body>
</html>
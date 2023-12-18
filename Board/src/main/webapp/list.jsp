<%@page import="com.distinctao.dao.BoardDaoImplements"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.distinctao.dto.*, java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	BoardDaoImplements dao = new BoardDaoImplements();
	List <BoardDTO>list = dao.selectAll();

%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>게시 목록</title>
</head>
<body>
<c:forEach var="board" items="${list}"></c:forEach>
</body>
</html>
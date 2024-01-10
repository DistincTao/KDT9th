<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>
<c:set var="now" value="<%=new java.util.Date()%>" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" href="../css/header.css?after">
<link rel="stylesheet" href="../css/listAll.css?after">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/listAll.js"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	<div class="container">
		<h1>게시판 전체 목록</h1>
		<div class="boardList">
		<c:choose>
			<c:when test="${boardList != null }">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach var="board" items="${boardList }">
							<c:choose>
								<c:when test="${board.isDelete == 'N' }">
									<tr id="board${board.boardNo }" class="board" onclick="location.href='viewBoard.bo?boardNo=${board.boardNo}'">
										<td>${board.boardNo }</td>
										<td>${board.title }</td>
										<td>${board.writer }</td>
										<td>${board.postDate }</td>
										<td>${board.readCount }</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr id="board${board.boardNo }" class="deletedBoard">
										<td><del>${board.boardNo }</del></td>
										<td><del>${board.title }</del></td>
										<td><del>${board.writer }</del></td>
										<td><del>${board.postDate }</del></td>
										<td><del>${board.readCount }</del></td>
									</tr>							
								</c:otherwise>
							</c:choose> 
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				텅!!!
			</c:otherwise>
		</c:choose>
		</div>
		<div class="btns">
			<button type="button" class="btn btn-primary" onclick="location.href='writeBoard.jsp'">글쓰기</button>
		</div>

	</div>

	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
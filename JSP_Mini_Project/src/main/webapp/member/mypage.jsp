<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"></c:set>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/register.js"></script>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/mypage.css">

<title>My Page</title>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">
		<h2>MY PAGE</h2>
		<p>개인 정보 조회 및 수정</p>
		<div class="card" style="width: 400px">
			<img class="card-img-top"
				src="${contextPath }/${sessionScope.login.memberImg }" alt="userImg"
				style="width: 100%">
			<button type="button" class="btn btn-success" id="selectImg">사진
				수정</button>
			<button type="button" class="btn btn-danger" id="modifyImg">전송</button>
			<div class="card-body">
				<h4 class="card-title">
					User ID : ${sessionScope.login.userId }
					<button type="button" class="btn btn-success" id="selectPwd">비밀 번호 수정</button>
				</h4>
				<div class="modifyPwd">
					<div class="mb-3 mt-3">
						<label for="uesrPwd" class="form-label">PASSWORD:</label>
						 <input
							type="password" class="form-control" id="userPwd"
							placeholder="Enter password" name="userPwd">
					</div>
					<div class="mb-3 mt-3">
						<label for="uesrPwd2" class="form-label">PASSWORD CONFIRM:</label>
						<input type="password" class="form-control" id="userPwd2"
							placeholder="Enter password">
					</div>
					<button type="button" class="btn btn-danger" id="modifyPwd">전송</button>
				</div>
				<p class="card-text">
					회원 이메일 : ${sessionScope.login.userEmail }
					<button type="button" class="btn btn-success" id="selectEmail">수정</button>
<!-- 				<div class="mb-3 mt-3" class="modifyEmail"> -->
<!-- 					<label for="userEmail" class="form-label">EMAIL:</label>  -->
<!-- 					<input type="text" class="form-control" id="userEmail" placeholder="example@example.com" name="userEmail"> -->
<!-- 					<button type="button" class="btn btn-warning" id="sendEmailBtn">Email Validate</button> -->
<!-- 					<div id="codeDiv" style="display : none;"> -->
<!-- 						<input type="text" class="form-control" id="emailCode" placeholder="Enter Valification Code" name="userImg"> -->
<!-- 						<button type="button" class="btn btn-warning confirmCode">Check Code</button> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<button type="button" class="btn btn-danger" id="modifyEmail">전송</button> -->
			
				<p class="card-text">회원 가입일 : ${sessionScope.login.regdate }</p>
				<p class="card-text">
					보유 포인트 : ${sessionScope.login.userPoint } 
					<button type="button" class="btn btn-success" id="selectPwd">포인트 조회</button>
				</p>
				<button type="button" class="btn btn-primary">Delete
					Account</button>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
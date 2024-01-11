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
<script type="text/javascript" src="../js/mypage.js"></script>
<link rel="stylesheet" href="../css/header.css?after">
<link rel="stylesheet" href="../css/mypage.css?after">

<title>My Page</title>
</head>
<body>
<!-- 로그인 하지 않은 유저는 login.jsp로 돌려보내기 -->
<c:if test="${sessionScope.login == null }">
	<c:redirect url="./login.jsp"></c:redirect>
</c:if>	


	<jsp:include page="../header.jsp"></jsp:include>

	<div class="container">

		<div class="mb-3 mt-3">
			<img alt="userImg"
				src="${contextPath }/${requestScope.memberInfo.memberImg }">
		</div>
		<div class="mb-3 mt-3">
			<label for="userImg" class="form-label">change image</label> <input
				type="file" class="form-control" id="userImg" name="userImg">
		</div>
		<div class="mb-3 mt-3">
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="userId" readonly
					value="${requestScope.memberInfo.userId }">
				<button class="btn btn-success" type="button" id="changePwdBtn">change
					PW</button>
			</div>
		</div>

		<div class="mb-3 mt-3">
			<div class="input-group mb-3">
				<input type="text" class="form-control" readonly
					value="${requestScope.memberInfo.userEmail }">
				<button class="btn btn-success" type="submit" id="changEmailBtn">Change</button>
			</div>
			<div class="codeDiv" style="display: none;">
				<input type="text" class="form-control" id="emailCode"
					placeholder="Enter Valification Code" name="userImg">
				<button type="button" class="btn btn-warning confirmCode">
					Check Code</button>
			</div>
		</div>
		
		<div class="mb-3 mt-3 pointlog">
			<div class="pointInfo">
				<h3>보유 적립금</h3>
				<p>
					총 적립금 : <span>${requestScope.memberInfo.userPoint } 점</span>
				</p>
			</div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>적립 일시</th>
						<th>적립 내용</th>
						<th>적립 포인트</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="point" items="${requestScope.pointlog }">
						<tr>
							<td>${point.actionDate }</td>
							<td>${point.pointType }</td>
							<td>${point.changePoint }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<!-- The Modal -->
		<div class="modal changePwd">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">비밀번호 변경</h4>
						<button type="button" class="btn-close modalClose"
							data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="mb-3 mt-3">
							<label for="uesrPwd" class="form-label">PASSWORD:</label> <input
								type="password" class="form-control" id="userPwd"
								placeholder="Enter password" name="userPwd">
						</div>
						<div class="mb-3 mt-3">
							<label for="uesrPwd2" class="form-label">PASSWORD
								CONFIRM:</label> <input type="password" class="form-control"
								id="userPwd2" placeholder="Enter password">
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger modalClose"
								data-bs-dismiss="modal">Close</button>
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- The Modal -->
		<div class="modal changeEmail">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">이메일 변경</h4>
						<button type="button" class="btn-close modalClose"
							data-bs-dismiss="modal"></button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<div class="mb-3 mt-3">
							<label for="userEmail" class="form-label">EMAIL:</label> <input
								type="text" class="form-control" id="userEmail"
								placeholder="example@얀.com" name="userEmail">
							<button type="button" class="btn btn-warning" id="sendEmailBtn">Email
								Validate</button>
							<div class="codeDiv" style="display: none;">
								<input type="text" class="form-control" id="emailCode"
									placeholder="Enter Valification Code" name="userImg">
								<button type="button" class="btn btn-warning confirmCode">
									Check Code</button>
							</div>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger modalClose"
								data-bs-dismiss="modal">Close</button>
						</div>

					</div>
				</div>
			</div>

		</div>
</div>
		<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="./jquery-3.7.1.min.js"></script>
<link rel="stylesheet" href="./validateEx.css" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<form action="./registerMember.jsp" style="border: 1px solid #ccc" method="post">
		<div class="container">
			<h1>회원 가입</h1>
			<p>아래 항목을 기입해주세요</p>
			<hr />

			<div class="mb-3 mt-3">
				<label for="userId"><b>아이디</b></label>
				<input type="text" placeholder="Enter ID" name="userId" id="userId" />
			</div>
			<div class="mb-3 mt-3">
				<label for="psw1"><b>비밀번호</b></label> 
				<input type="password" placeholder="Enter Password" name="psw1" id="psw1" />
			</div>
			<div class="mb-3 mt-3">
				<label for="email"><b>E-mail</b></label> 
				<input type="text" placeholder="Enter e-mail" name="email" id="email" /> 
			</div>
			<div class="mb-3 mt-3">
				<label for="mobile"><b>Mobile</b></label> 
				<input type="text" placeholder="Enter mobile" name="mobile" id="mobile" /> 
			</div>
			<div class="clearfix">
				<button type="reset" class="cancelbtn">Cancel</button>
				<button type="submit" class="signupbtn" onclick="return check()">Sign Up</button>
			</div>
		</div>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
	<script src="./jquery-3.7.1.min.js"></script>
	<link rel="stylesheet" href="./validateEx.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
	<script src="./validateEx.js"></script>

</head>
<body>
	<form action="./completeForm" style="border: 1px solid #ccc" method="post">
		<div class="container">
			<h1>회원 가입</h1>
			<p>아래 항목을 기입해주세요</p>
			<hr />

			<label for="userId"><b>아이디</b></label> <input type="text"
				placeholder="Enter ID" name="userId" id="userId" /> <label
				for="psw1"><b>비밀번호</b></label> <input type="password"
				placeholder="Enter Password" name="psw1" id="psw1" /> <label
				for="psw2"><b>비밀번호 확인</b></label> <input type="password"
				placeholder="Repeat Password" name="psw2" id="psw2" /> <label
				for="email"><b>E-mail</b></label> <input type="text"
				placeholder="Enter e-mail" name="email" id="email" /> <label
				for="mobile"><b>Mobile</b></label> <input type="text"
				placeholder="Enter mobile" name="mobile" id="mobile" /> <label
				for="bDate"><b>Birth Day</b></label> <input type="date"
				placeholder="Birth Day" name="bDate" id="bDay" /> <label
				for="bDate"><b>Age</b></label> <input type="text" placeholder="Age"
				name="age" id="age" />

			<fieldset>
				<legend style="font-size: 1rem" id="gender">
					<b>성별</b>
				</legend>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radio1"
						name="gender" value="male" /> <label class="form-check-label"
						for="radio1">남성</label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radio2"
						name="gender" value="female" /> <label class="form-check-label"
						for="radio2">여성</label>
				</div>
				<div class="form-check">
					<input type="radio" class="form-check-input" id="radio3"
						name="gender" value="unknown" checked /> <label
						class="form-check-label" for="radio3">기타</label>
				</div>
			</fieldset>
			<br />
			<fieldset>
				<legend style="font-size: 1rem">
					<b>취미</b>
				</legend>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="check1"
						name="hobby" value="study" checked /> <label
						class="form-check-label" for="check1">공부</label>
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="check2"
						name="hobby" value="music" checked /> <label
						class="form-check-label" for="check2">음악감상</label>
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="check3"
						name="hobby" value="travel" checked /> <label
						class="form-check-label" for="check3">여행</label>
				</div>
			</fieldset>
			<br /> <label for="job" class="form-label"><b>직업 (select
					one):</b></label> <select class="form-select" id="job" name="job">
				<option>-- 하나만 선택 하셔요 --</option>
				<option>학생</option>
				<option>개발자</option>
				<option>영업</option>
				<option>건물주</option>
				<option>운동선수</option>
				<option>백수</option>
			</select> <br />

			<div class="mb-3 mt-3">
				<label for="comment"><b>Memo:</b></label>
				<textarea class="form-control" rows="5" id="comment" name="memo"></textarea>
			</div>
			<br /> <label> <input type="checkbox" id="agree"
				name="remember" style="margin-bottom: 15px" /> 가입 조항에 동의 합니다.
			</label>

			<p>
				By creating an account you agree to our <a href="#"
					style="color: dodgerblue">Terms & Privacy</a>.
			</p>

			<div class="clearfix">
				<button type="reset" class="cancelbtn">Cancel</button>
				<button type="submit" class="signupbtn"
					onclick="return registerValid()">Sign Up</button>
			</div>
		</div>
	</form>
</body>
</html>
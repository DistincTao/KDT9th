<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삼각형 넓이 계산</title>
<script type="text/javascript">
	function validation() {
		isValid = false;
		let base = document.getElementById("idBase").value;
		let height = document.getElementById("idHeight").value;
		
		if (base <= 0 || height <= 0 ) {
			alert("유효하지 않습니다.");
		} else {
			isValid = true;
		}
		return isValid;
	}

</script>
</head>
<body>
	<h1>삼각형 넓이 구하기~!</h1>
	<form action = "./CalculateTriangle_GET" method="get">
		밑변 : <input type="text" name="base" id="idBase"><br>
		높이 : <input type="text" name="height" id="idHeight"><br>
		<input type="submit" value = "전송!!" onmouseover="return validation();">
	</form>
</body>
</html>
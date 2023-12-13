<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 입력</title>
</head>
<body>
<h1>원하시는 상품을 작성하세요</h1>
<form action="./inputProd.do" method="POST">
	<div>상 품 명 : <input type="text" name="name">
		<select name="color">
			<option>스페이스 그레이</option>
			<option>핑크</option>
			<option>블랙</option>
			<option>블루</option>
			<option>에메랄드 그린</option>
			<option>옐로</option>
			<option>스타라이트</option>
		</select>
	</div>
	<div>수   량 : <input type="number" name="qty" min="1" max="10"></div>
	<div>가   격 : <input type="number" name="price" min="1580000"></div>

	<button type="reset">취소</button>
	<button type="submit">전송</button>

</form>

</body>
</html>
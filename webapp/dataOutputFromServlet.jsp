<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
	<head>
		<meta charset="UTF-8">	
		<title>성적표</title>
		<style>
			th {
				background-color: gray;
			}
		</style>
	</head>
	<body>
		<h1> 점수는????</h1>
		<div> 국어 : ${kor}, 수학 : ${eng}, 영어 : ${math}, 총점 : ${total}, 평균 : ${avg} </div>
		<table border = "1">
			<tr>
				<th>국어</th>
				<th>영어</th>
				<th>수학</th>
				<th>총점</th>
				<th>평균</th>
			</tr>
			<tr>
				<td>${kor}</td>
				<td>${eng}</td>
				<td>${math}</td>
				<td>${total}</td>
				<td>${avg}</td>
			</tr>
		</table>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>왜 Servlet을 사용하는가?</title>
<style>
h1 {
	background-color: lightgreen;
}
</style>
<script type="text/javascript">
	function hello() {
		alert("!");
	}
</script>
</head>
<body>
	<h1 onclick="hello();">왜 Servlet을 사용하는가?</h1>
	<!--  변수에 임의의 값을 저장한 후, 그 값이 홀수이면 파란색 글씨로 "홀수 입니다.
		그 값이 짝수이면 빨간 글씨로 "짝수입니다." 라고 출력해보자 -->

	<%
		int num = 4;
		if (num % 2 == 0) { // 짝수이면
	%>
	<div style="color: red;">짝수입니다.</div>

	<%
		} else {
	%>
		<div style="color: blue;">홀수입니다.</div>

	<%
	}
	%>

	<!-- 
		**장단점**
				 
    1. **장점**
     	- 구조가 단순하여 **직관적**
        - **개발시간이 비교적 짧아** 개발비용이 감소
    2. **단점**
        - view 코드와 로직처리를 위한 java 코드가 섞여있어 JSP **코드 자체가 복잡**
        - JSP 코드에 Back-End와 Front-End가 혼재되어있어 **분업이 어려움**
        - 프로젝트 규모가 커지면 코드가 복잡해져 **유지보수가 어려움**
        - **확장성이 나쁨**
     -->

</body>
</html>
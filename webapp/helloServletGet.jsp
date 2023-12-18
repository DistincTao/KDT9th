<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GET 방식으로 요청하기</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	function callServlet() {
		location.href = "./hGET?name=길동";
	}

	function callServletAjax() {
		$.ajax({
			url : "./hGET?name=희동이",
			type : "GET",
			success : function(data) {
				console.log("ajax success");
				document.getElementById("ajaxText").innerHTML = data;
			},
			error : function() {
			},
			complete : function() {
			}
		});
	}
</script>
</head>
<body>
	<!-- anchor 태그로 서블릿 요청 -->
	<h1>HelloServlet_GET 파일을 GET 방식으로 호출</h1>
	<div>
		<a href="./hGET?name=둘리">anchor 태그로 서블릿 요청</a>
	</div>
	<hr>
	
	<!-- Form 태그로 서블릿 요청 -->
	<div>
		<form action="./hGET" method="GET">
			<input type="text" name="name">
			<button type="submit">form 태그로 GET방식 요청</button>
		</form>
	</div>
	<hr>
	
	<!-- location.href 로 서블릿 요청 -->
	<button onclick="callServlet();">location.href로 서블릿 요청</button>
	<hr>
	
	<!-- ajax로 서블릿 요청 -->	
	<button onclick="callServletAjax();">Ajax로 서블릿 요청</button>
	<div id="ajaxText"></div>


</body>
</html>
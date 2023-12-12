<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POST 방식으로 요청</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

	function callServletAjax() {
		$.ajax({
			url : "./HelloServlet_POST",
			type : "POST",
			data : {
				"name" : "둘리",
				"age" : "10"
			},
		    dataType:"html",
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
	<form action ="./HelloServlet_POST" method = "POST">
		<div>이름 : <input type="text" name = "name"></div>
		<div>나이 : <input type="text" name = "age"></div>
		<input type="submit" value ="전송">
	</form>
	
	<button onclick="callServletAjax();">Ajax로 서블릿 요청</button>
	<div id="ajaxText"></div>
	
</body>
</html>
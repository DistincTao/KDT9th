<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL에서 사용가능한 연산자</title>
</head>
<body>
	<div>\${5 + 2} : ${5 + 2 }</div>
	<div>\${5 / 2} : ${5 / 2 }</div>
<%-- 	<div>\${5 div 2 } : ${5 div 2 }</div> --%>
	<div>\${5 % 2 } : ${5 % 2 }</div>
	<div>\${5 mod 2 } : ${5 mod 2 }</div>
	<div>\${5 > 2 } : ${5 > 2 }</div>
	<div>\${5 gt 2 } : ${5 gt 2 }</div>
	<div>\${5 < 2 } : ${5 < 2 }</div>
	<div>\${5 lt 2 } : ${5 lt 2 }</div>
	<div>\${5 >= 2 } : ${5 >= 2 }</div>
	<div>\${5 ge 2 } : ${5 ge 2 }</div>
	<div>\${5 <= 2 } : ${5 <= 2 }</div>
	<div>\${5 le 2 } : ${5 le 2 }</div>
	<div>\${5 gt 2 and 3 lt 4 } : ${5 gt 2 and 3 lt 4 }</div>
	<div>\${5 gt 45 ? "참" : "거짓" } : ${5 gt 45 ? "참" : "거짓" }</div>
	
	<% 
		String input = null;
	%>
	<div>\${empty input } : ${empty input }</div>

</body>
</html>
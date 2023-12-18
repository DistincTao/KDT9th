<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>action tag forward</title>
</head>
<body>

<jsp:forward page="actionTag_forwardParam_1.jsp">
	<jsp:param value="gkd" name="userId"/>
	<jsp:param value="39" name="age"/>
	<jsp:param value="고길동" name="name"/>
</jsp:forward>

</body>
</html>
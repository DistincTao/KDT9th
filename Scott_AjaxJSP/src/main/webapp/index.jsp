<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Scott 계정 직원 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script>
	$(document).ready(function(){
		getEmps();

	});

	function getEmps() {
		let url = "getAllEmp.do"
		$.ajax({
			url : url,
			type : "post",
			dataType : "json",
			async : false,
			success : function (data){
				console.log(data);
				outputList(data);
			},
			error : function(){},
			complete : function () {},
		});
	}

	function outputList(json){
		$("#outputCnt").html("데이터 : " + json.count + "개");
		$("#outputDate").html("출력 일시 : " + json.outputDate);
	}
</script>

</head>
<body>
	<h1>직원 목록 출력</h1>
	<div id="outputCnt"></div>
	<div id="outputDate"></div>
	<div id="empData"></div>
	
</body>
</html>
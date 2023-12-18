<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="KO">
<head>
<meta charset="UTF-8">
<title>표현식</title>
</head>
<body>
<%!
	// 선언문 
	// JSP 페이지가 초기화 될 때 초기화 되어 해당 클래스의 맴버 변수, 맴버 메소드가 되는 부분
	// 페이지 내의 어떤 한 스크립틀릿이나 표현식에서도 접근해서 사용할 수 있다.
	String str = "안녕하세요!!"; // 맴버 변수
	public int abs(int n) { // 맴버 메소드
		if (n < 0) { 
			n = -n;
		} 
		return n;
	}
%>

<div><%=str %></div> <!-- 표현식에는 out.print()로 변환 되므로, ;릏 입력하지 않는다 -->
<div><%=abs(-4) %></div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>    
<c:set var = "contextPath" value="<%=request.getContextPath() %>"></c:set>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="../js/login.js"></script>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/register.css">
<title>header</title>
</head>
<body>
<div class="p-5 bg-primary text-white text-center header">
  <h1><a href="${contextPath }/index.jsp" id="goHome" style="color : white; text-decoration: none;">JSP MINI PROJECT</a></h1>
  <p>2024 Jan Version</p> 
</div>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav">
      <li class="nav-item">
      <c:choose>
      <c:when test="${pageContext.request.requestURI == '/JSPMiniProject/index.jsp' }">
        <a class="nav-link active" href="${contextPath }/index.jsp">Distinctao</a>
      </c:when>
      <c:otherwise>
        <a class="nav-link" href="${contextPath }/index.jsp">Distinctao</a>
      </c:otherwise>
      </c:choose>
      </li>

      <li class="nav-item">
	  <c:choose>
      <c:when test="${pageContext.request.requestURI == '/JSPMiniProject/board/listAll.jsp' }">
        <a class="nav-link active" href="${contextPath }/board/listAll.bo">BOARD</a>
      </c:when>
      <c:otherwise>
        <a class="nav-link" href="${contextPath }/board/listAll.bo">BOARD</a>
      </c:otherwise>
      </c:choose> 
      </li>
		<c:choose>
			<c:when test="${sessionScope.login == null }" >
		        <li class="nav-item">
		        <c:choose>
                <c:when test="${pageContext.request.requestURI == '/JSPMiniProject/member/register.jsp' }">
		            <a class="nav-link active" href="${contextPath }/member/register.jsp">SIGN IN</a>
                </c:when>
                <c:otherwise>
		            <a class="nav-link" href="${contextPath }/member/register.jsp">SIGN IN</a>
                </c:otherwise>
			    </c:choose>
		        </li>
		        <li class="nav-item">
		        <c:choose>
				<c:when test="${pageContext.request.requestURI == '/JSPMiniProject/member/login.jsp' }">
		            <a class="nav-link active" href="${contextPath }/member/login.jsp">LOGIN</a>
				</c:when>
				<c:otherwise>
		            <a class="nav-link" href="${contextPath }/member/login.jsp">LOGIN</a>
				</c:otherwise>
				</c:choose>
		        
		     	</li>
			</c:when>
		  	<c:otherwise>
	     	    <li class="nav-item">
	     	    <c:choose>
	     	    <c:when test="${pageContext.request.requestURI == '/JSPMiniProject/member/mypage.jsp' }">
	     	    	 <a class="nav-link active" href="${contextPath }/member/myPage.mem?userId=${sessionScope.login.userId}"> ${sessionScope.login.userId}
	     	    	 	<img src="${contextPath }/${sessionScope.login.memberImg }" id="userImg">
	     	    	 </a>
	     	    </c:when>
	     	    <c:otherwise>
	     	    	 <a class="nav-link" href="${contextPath }/member/myPage.mem?userId=${sessionScope.login.userId}"> ${sessionScope.login.userId}
	     	    	 	<img src="${contextPath }/${sessionScope.login.memberImg }" id="userImg">
	     	    	 </a>
	     	    </c:otherwise>
	     	    </c:choose>

	     	    </li>
		        <li class="nav-item">
		            <c:choose>
		            <c:when test="${pageContext.request.requestURI == '/JSPMiniProject/member/logout.jsp' }">
		            <a class="nav-link active" href="${contextPath }/member/logout.mem">LOGOUT</a>
		            </c:when>
		            <c:otherwise>
		            <a class="nav-link" href="${contextPath }/member/logout.mem">LOGOUT</a>
		            </c:otherwise>
		            </c:choose>
	     	    </li>  
		  	</c:otherwise>
		</c:choose>
  		<c:if test="${sessionScope.login.isAdmin == 'Y' }">
			<li class = "nav-item">
				<c:choose>
				<c:when test="${pageContext.request.requestURI == '/JSPMiniProject/admin/admin.jsp' }">
       			<a class="nav-link active" href="${contextPath }/admin/admin.jsp">Admin Page</a>
				</c:when>
				<c:otherwise>
       			<a class="nav-link" href="${contextPath }/admin/admin.jsp">Admin Page</a>
				</c:otherwise>
				</c:choose>
       		</li>
		</c:if>
    </ul>
  </div>
</nav>

</body>
</html>
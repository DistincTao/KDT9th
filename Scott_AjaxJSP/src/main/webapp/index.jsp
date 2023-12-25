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
<script type="text/javascript" src="javaScript/Scott_AjaxJSP.js"></script>
<style>
img {
width : 48px;
height : 48px;
background-color : white;
}

#insertEmp {
position: fixed;
top : 20px;
right : 20px;
}

</style>
</head>
<body>
	<h1>직원 목록 출력</h1>
	<div id="outputCnt"></div>
	<div id="outputDate"></div>
	<div id="empData"></div>
	<div><img src="img/insert.png" id="insertEmp"></div>
	
		<!-- 사원 입력 The Modal -->
	<div class="modal" id="writeModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 정보 등록</h4>
					<button type="button" class="btn-close modalClose"
						data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form action="insertEmp.do" method="post">
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Name</span>
							<input type="text"class="form-control" id="writeName" name="name">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Hire Date</span>
							<input type="date" class="form-control" id="writeHireDate" name="hiredate">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Department</span> 
							<select class="form-select form-select-lg mt-3" id="writeDeptName" name="deptNo">
						    	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Job</span>
							<select class="form-select form-select-lg mt-3" id="writeJobId" name="job" required="required">
								<option>PRESIDENT</option>
								<option>MANAGER</option>
								<option>ANALYST</option>
								<option>SALESMAN</option>
								<option>CLERK</option>
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
						    <label for="writeSalary" class="form-label">Salary : </label><span id="salval" >$0</span>
   							<input type="range" class="form-range" id="writeSal" min="100" max="5000" step="10" name="sal">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Commition</span>
							<input type="number" class="form-control" id="writeComm" name="comm" value="0.0"> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Manager</span>
							<select class="form-select form-select-sm mt-3" id="writeMgrId" name="mgr">
			
						   	</select> 
						</div>
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Send</button>
							<button type="button" class="btn btn-danger modalClose" data-bs-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
			<!-- 사원 수정 The Modal -->
	<div class="modal" id="updateModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 정보 수정</h4>
					<button type="button" class="btn-close modalClose"
						data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body">
					<form action="updateEmp.do" method="post">
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Name</span>
							<input type="text"class="form-control" id="updateName" name="name">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Hire Date</span>
							<input type="date" class="form-control" id="updateHireDate" name="hiredate">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Department</span> 
							<select class="form-select form-select-lg mt-3" id="updateDeptName" name="deptNo">
						    	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Job</span>
							<select class="form-select form-select-lg mt-3" id="updateJobId" name="job" required="required">
								<option>PRESIDENT</option>
								<option>MANAGER</option>
								<option>ANALYST</option>
								<option>SALESMAN</option>
								<option>CLERK</option>
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
						    <label for="writeSalary" class="form-label">Salary : </label><span id="salval" >$0</span>
   							<input type="range" class="form-range" id="updateSal" min="100" max="5000" step="10" name="sal">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Commition</span>
							<input type="number" class="form-control" id="updateComm" name="comm" value="0.0"> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Manager</span>
							<select class="form-select form-select-sm mt-3" id="updateMgrId" name="mgr">
			
						   	</select> 
						</div>
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Send</button>
							<button type="button" class="btn btn-danger modalClose" data-bs-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 사원 삭제 The Modal -->
	<div class="modal" id="deleteModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">사원 정보 등록</h4>
					<button type="button" class="btn-close modalClose"
						data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body -->
				<div class="modal-body" id="delete-modal-body">

				</div>
			</div>
		</div>
	</div>
	
</body>
</html>
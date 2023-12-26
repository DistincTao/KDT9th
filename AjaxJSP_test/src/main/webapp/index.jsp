<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>hr 계정 직원 목록</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript" src="javaScript/AjaxJSP_test.js"></script>
<style>
.modifyIcon {
	height: 48px;
	width: 48px;
}

.writeIcon {
	width: 48px;
	height: 48px;
	position: fixed;
	right: 50px;
	top: 15px;
}

.salval{
	display : flex;
	flex-direction: row;
	justify-content: space-between;
	width : 500px
}

.errMsg {
	color : red;

}
#salval {
	color : blue;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="container">

		<h1>hr 계정 직원 목록</h1>
		<div id="outputDate"></div>
		<div id="outputCnt"></div>
	</div>

	<img alt="직원 추가" src="img/insert.png" class="writeIcon">
	<div class="empInfo"></div>

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
<!-- 					<form action="insertEmployee.do" method="post"> -->
					<form action="" method="post">
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">First_name</span>
							<input type="text"class="form-control" id="writeFirstName" name="first_name">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Last_name</span>
							<input type="text"class="form-control" id="writeLastName" name="last_name" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Eamil</span>
							<input type="email" class="form-control" id="writeEmail" name="email" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Phone Number</span>
							<input type="text" class="form-control" id="writePhoneNumber" name="phone_number">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Hire Date</span>
							<input type="date" class="form-control" id="writeHireDate" name="hire_date" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Department</span> 
							<select class="form-select form-select-lg mt-3" id="writeDepartmentName" name="department_id">
						    	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">job position</span>
							<select class="form-select form-select-lg mt-3" id="writeJobId" name="job_id" required="required">
	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
						    <label for="writeSalary" class="form-label">Salary : </label><span id="salval" >$0</span>
   							<input type="range" class="form-range" id="writeSalary" step="10" name="salary">
    						<div class="salval"><div id="salval-min" >$0</div><div id="salval-max" >$0</div></div>
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Commition</span>
							<input type="number" class="form-control" min="0" max="0.99" step="0.01" id="commition" name="commition_pct" value="0.0"> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Manager</span>
							<select class="form-select form-select-sm mt-3" id="writeManagerId" name="manager_id">
			
						   	</select> 
						</div>
					</form>
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="insertSubmit">Send</button>
<!-- 							<button type="submit" class="btn btn-primary" data-bs-dismiss="modal" id="insertSubmit">Send</button> -->
							<button type="button" class="btn btn-danger modalClose" data-bs-dismiss="modal">Close</button>
						</div>
<!-- 					</form> -->
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
					<h4 class="modal-title">사원 정보 삭제</h4>
					<button type="button" class="btn-close modalClose"
						data-bs-dismiss="modal"></button>
				</div>
				<!-- Modal body  -->
				<div class="modal-body">		
					<form action="">
						<div> 선택하신 사원을 삭제하시겠습니까? </div>
<!-- 						<div id="empIdForDelete"></div> -->
<!-- 						<div id="nameForDelete"></div> -->
					<!-- Modal footer -->
						<div class="modal-footer">
<!-- 							<button type="submit" class="btn btn-primary" data-bs-dismiss="modal">YES</button> -->
							<button type="button" class="btn btn-primary" data-bs-dismiss="modal" id="deleteSubmit">YES</button>
							<button type="button" class="btn btn-danger modalClose" data-bs-dismiss="modal">NO</button>
						</div>				
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 사원 입력 The Modal -->
	<div class="modal" id="modifyModal">
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
					<form action="" method="post">
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">First_name</span>
							<input type="text"class="form-control" id="writeFirstName" name="first_name">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Last_name</span>
							<input type="text"class="form-control" id="writeLastName" name="last_name" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Eamil</span>
							<input type="email" class="form-control" id="writeEmail" name="email" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Phone Number</span>
							<input type="text" class="form-control" id="writePhoneNumber" name="phone_number">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Hire Date</span>
							<input type="date" class="form-control" id="writeHireDate" name="hire_date" required="required">
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Department</span> 
							<select class="form-select form-select-lg mt-3" id="writeDepartmentName" name="department_id">
						    	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">job position</span>
							<select class="form-select form-select-lg mt-3" id="writeJobId" name="job_id" required="required">
	
						   	</select> 
						</div>
						<div class="input-group input-group-sm mb-3">
						    <label for="writeSalary" class="form-label">Salary : </label><span id="salval" > $0</span>
   							<input type="range" class="form-range" id="writeSalary" step="10" name="salary">
    						<div class="salval"><div id="salval-min" >$0</div><div id="salval-max" >$0</div></div>
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Commition</span>
							<input type="number" class="form-control" min="0" max="0.99" step="0.01" id="commition" name="commition_pct" value="0.0"> 
						</div>
						<div class="input-group input-group-sm mb-3">
							<span class="input-group-text">Manager</span>
							<select class="form-select form-select-sm mt-3" id="writeManagerId" name="manager_id">
			
						   	</select> 
						</div>
						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-primary" id="deleteEmp" data-bs-dismiss="modal">Send</button>
							<button type="button" class="btn btn-danger modalClose" data-bs-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>






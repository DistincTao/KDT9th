$(document).ready(function() {
	getAllEmployees();
	let empByName = $("#byName").val();
	let orderMethod = "orderEmpAsc"
	getJobsData();
	getDeptData();
	getAllEmp (null, orderMethod);
	// 사원 이름으로 검색
//	$("#byName").keyup(function(){
//		empByName = $(this).val();
//		orderMethod = $(".orderMethod:checked").val();
//		if (empByName > 1) { // 검색어가 두글자 이상인 경우
//			getAllEmp (empByName, orderMethod);
//		}
//	});

	$("#byName").keyup(function(e){
		if (e.keyCode == 13) {
			orderMethod = $(".orderMethod:checked").val();
			// 엔터키를 눌렀을 때
			if ($(this).val().length > 1){
				empByName = $("#byName").val();
				getAllEmp (empByName, orderMethod);
			}
		}
	});
	
	$(".orderMethod").change(function (){
		orderMethod = $(".orderMethod:checked").val();
		empByName = $("#byName").val();
		getAllEmp (empByName, orderMethod);
	});
	
	$("#resetList").click(function (){
		orderMethod = $(".orderMethod:checked").val();
		$("#byName").val(null);
		getAllEmp (null, orderMethod);
	});
		
	$(".writeIcon").click(function() {
		//사원 입력 시 필요한 부가 정보 모달창 띄우기
		$("#writeJobId").html(makeJobSelection());
		$("#writeDepartmentName").html(makeDeptSelection());
		$("#writeModal").show();
	});
	
	$(".modalClose").click(function() {
		$("#writeModal").hide();
		$("#modifyModal").hide();
		$("#deleteModal").hide();
		
	});
	
	// 급여 범위 가져오기
	// 1) 내가 짠 코드
	$("#writeJobId").change(function (){
		makeSalaryRange(null);
	});

	$("#modifyJobId").change(function (){
		makeSalaryRange("modify");
	});
	
	// 지속적으로 입력시 중복 검사도 가능 

	
//	$(".orderMethod").change(function(e){
//		searchByName(empByName, orderMethod);
//	});
	
//	// 지속적으로 입력시 중복 검사도 가능 
//	$(".orderMethod").on("change", this, function(){
//			let orderMethod = $(".orderMethod:checked").val();
//			searchByName(empByName);
//		}
//	});
	
	// 2) 수업시간에 짠 코드
//	$("#writeJobId").change(function (){
//		if ($(this).val() != "") {
////			let index = document.getElementById("writeJobId").selectedIndex;
////			let selecedJobId = document.getElementById("writeJobId").option[index].getAttribute("id");
//			let selecedJobId = $("#writeJobId option:selected").val();
////			console.log(selecedJobId);
//			makeSalaryRange(selecedJobId);
//		}
//	});
	
	$("#writeDepartmentName").change(function (){
		$("#writeManagerId").html(makeMgrSelection(null));
	});

	$("#modifyDepartmentName").change(function (){
		$("#modifyManagerId").html(makeMgrSelection("modify"));
	});

	// 사원 저장 버튼 클릭시 (Ajax로 통신하여 전송하기)
	$("#insertSubmit").click (function(){
		let writeFirstName = $("#writeFirstName").val();
		let writeLastName = $("#writeLastName").val();
		let writeEmail = $("#writeEmail").val().toUpperCase();
		let writePhone = $("#writePhoneNumber").val();
		let writeHireDate = $("#writeHireDate").val();
		let writeDepartmentName = $("#writeDepartmentName").val();
		let writeJobId = $("#writeJobId").val();
		let writeSalary = Number($("#writeSalary").val());
		let commition = Number($("#commition").val());
		let writeManagerId = $("#writeManagerId").val();
		
		let empTemp = {
			first_name : writeFirstName, 
			last_name : writeLastName,
			email : writeEmail,
			phone_number : writePhone,
			hire_date : writeHireDate,
			job_id : writeJobId,
			salary : writeSalary,
			commition_pct : commition,
			manager_id : writeManagerId,
			department_id : writeDepartmentName
			}
		console.log(empTemp);

		inputEmpValidate(empTemp);

	});
	
		// 사원 수정 버튼 클릭시 (Ajax로 통신하여 전송하기)
	$("#modifyEmp").click (function(){
		let modifyEmployeeId = $("#modifyEmployeeId").val();
		let modifyFirstName = $("#modifyFirstName").val();
		let modifyLastName = $("#modifyLastName").val();
		let modifyEmail = $("#modifyEmail").val().toUpperCase();
		let modifyPhone = $("#modifyPhoneNumber").val();
		let modifyHireDate = $("#modifyHireDate").val();
		let modifyDepartmentName = $("#modifyDepartmentName").val();
		let modifyJobId = $("#modifyJobId").val();
		let modifySalary = Number($("#modifySalary").val());
		let modifyCommition = Number($("#modifyCommition").val());
		let modifyManagerId = $("#modifyManagerId").val();
		
		let empTemp = {
			employee_id : modifyEmployeeId,
			first_name : modifyFirstName, 
			last_name : modifyLastName,
			email : modifyEmail,
			phone_number : modifyPhone,
			hire_date : modifyHireDate,
			job_id : modifyJobId,
			salary : modifySalary,
			commition_pct : modifyCommition,
			manager_id : modifyManagerId,
			department_id : modifyDepartmentName
			}
		console.log(empTemp);

		modifyEmpValidate(empTemp);

	});
	
	$("#deleteSubmit").click (function(){
		let empNo = $(this).val();
		console.log(empNo);
		let empByName = $("#byName").val();
		let orderMethod = $(".orderMethod:checked").val();
		$.ajax({
			url : 'deleteEmployee.do',
			type : 'post',
			data : {employee_id : empNo},
			dataType : 'json',
	//		async: false,
			success : function(data) {
				if (data.status == "fail") {
					alert("정보가 삭제되지 않았습니다.")
				} else if (data.status == "success"){
					console.log(data);
					alert("삭제 완료");
				}
				$("#deleteModal").hide();
				getAllEmp(empByName, orderMethod)
				},
			error : function() {
				alert("fail");
			},
			complete : function() {
			}
		});
	});
	
	
	
	$("#writeSalary").change(function() {
		$("#salval").html("$" + new Intl.NumberFormat('en-US').format($("#writeSalary").val()));
	});
	
	$("#modifySalary").change(function() {
		$("#modifySalval").html("$" + new Intl.NumberFormat('en-US').format($("#modifySalary").val()));
	});
	
//	$(".orderMethod").click(function (){
//		let orderMethod = $(".orderMethod:checked").val();
//		selectOrderMethod(orderMethod);
//	});

});

let empData = null; // 전체 사원 데이터
let jobsData = null;
let deptData = null;

// 직원 정보 가저오기
function getAllEmployees() {
	let url = "getAllEmployees.do";
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		// 			async: false,
		success : function(data) {
			console.log(data);
			empData = data;
//			outputEntireEmployees(data);
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

function getAllEmp(name, order) {
	if (order == 'orderEmpAsc') {
		order = 'e.employee_id'
	} else if (order == 'orderEmpDesc'){
		order = 'e.employee_id desc'
	} else if (order == 'orderHireDateAsc'){
		order = 'e.hire_date'
	} else if (order == 'orderHireDateDesc'){
		order = 'e.hire_date desc'
	} else if (order == 'orderSalDesc'){
		order = 'e.salary desc'
	}
	
	let url = "getAllEmployees.do?orderMethod=" + order;
	
	if (name != null) {
		url += '&searchName='+ name.toLowerCase();
	} 
	
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		// 			async: false,
		success : function(data) {
			console.log(data);
//			empData = data;
			outputEntireEmployees(data);
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

// 직종 정보 가저오기
function getJobsData() {
	let url = "getJobsData.do";
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		// 			async: false,
		success : function(data) {
			if (data.status == "fail") {
				alert("데이터를 불러오지 못했습니다.")
			} else if (data.status == "success"){
				console.log(data);
				jobsData = data;
			}
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

// 부서정보 가져오기
function getDeptData() {
	let url = "getDeptData.do";
	$.ajax({
		url : url,
		type : 'get',
		dataType : 'json',
		// 			async: false,
		success : function(data) {
			if (data.status == "fail") {
				alert("데이터를 불러오지 못했습니다.")
			} else if (data.status == "success"){
				console.log(data);
				deptData = data;
			}
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

// 모든 직원 데이터 파싱하여 출력
function outputEntireEmployees(json) {
	$("#outputCnt").html("데이터 : " + json.count + "개");
	$("#outputDate").html("출력 일시 : " + json.outputDate);

	let output = "<table class='table table-striped'><thead>";
	output += "<tr><th>사원번호</th><th>이름</th><th>이메일</th><th>전화번호</th>";
	output += "<th>입사일</th><th>직급</th><th>급여</th><th>커미션</th><th>관리자</th><th>부서명</th><th>수정</th><th>삭제</th>";
	output += "</tr></thead><tbody>";
	$.each(json.employees, function(i, item) {
		output += "<tr><td id='"+ item.employee_id + "'>" + item.employee_id + "</td>";
		output += "<td>" + item.first_name + " " + item.last_name + "</td>";
		output += "<td>" + item.email + "</td>";
		output += "<td>" + item.phone_number + "</td>";
		output += "<td>" + item.hire_date + "</td>";
		output += "<td>" + item.job_id + "</td>";
		output += "<td> $" + new Intl.NumberFormat('en-US').format(item.salary) + "</td>";
		output += "<td>" + item.commition_pct * 100 + "% </td>";

		let managerId = item.manager_id;
		let managerName = "";
		$.each(empData.employees, function(i, items) {
			if (managerId == items.employee_id) {
				managerName = items.first_name + " "
						+ items.last_name;
			};
		});
			
	output += "<td>" + managerName + "</td>";
	output += "<td>" + item.department_name + "</td>";
	output += "<td ><Image src='img/modify.png' class='modifyIcon' onclick='modifyModalShow(" + item.employee_id + ");'></td>";
	output += "<td ><Image src='img/remove.png' class='removeIcon' onclick='deleteModalShow(" + item.employee_id + ");'></td></tr>";
	
	});
	output += "</tbody></table>";

	$(".empInfo").html(output);
	
}

// 직급을 직업명으로 출력하여 입력 받기
function makeJobSelection() {
	let output = "<option value=''>=== 직급을 입력하세요 ===</option>";
	$.each(jobsData.jobs, function(i, item){
		output += "<option  id='" + item.job_id +"' value='" + item.job_id + "'>" + item.job_title + "</option>";
	});	

	return output;
}

//function makeMgrSelection(){
//
//	let output = "<option>=== 담당 메니저를 입력하세요 ===</option>";
//	$.each(empData.employees, function(i, item){
//		output += "<option id='" + item.employee_id +"' value='" + item.employee_id + "'>" + item.first_name + " "
//				+ item.last_name + "</option>"
//	});
//	return output;
//}

// 부서  id를 부서명으로 출력 하여 입력 받기
function makeDeptSelection() {
	let output = "<option value=''>=== 부서를 입력하세요 ===</option>";
	$.each(deptData.departments, function(i, item){
		output += "<option  id='" + item.department_id +"' value='" + item.department_id + "'>" + item.department_name + "</option>";
	});
	
	return output;
}

// 직종 선택에 따른 급여 범위 설정 및 출력
// 1) 내가 짠 코드
function makeSalaryRange(mode) {
	if (mode == null){
		$.each(jobsData.jobs, function(i, item){
			if ($("#writeJobId").val() == item.job_id) {
				$("#writeSalary").attr('min', item.min_salary).attr('max', item.max_salary);;
				$("#salval-min").html("$" + new Intl.NumberFormat('en-US').format(item.min_salary));
				$("#salval-max").html("$" + new Intl.NumberFormat('en-US').format(item.max_salary));
			}
		});
	} else if (mode == "modify"){
		$.each(jobsData.jobs, function(i, item){
			if ($("#modifyJobId").val() == item.job_id) {
				$("#modifySalary").attr('min', item.min_salary).attr('max', item.max_salary);;
				$("#modifySalval-min").html("$" + new Intl.NumberFormat('en-US').format(item.min_salary));
				$("#modifySalval-max").html("$" + new Intl.NumberFormat('en-US').format(item.max_salary));
			}
		});
	}
}

// 2) 수업시간에 짠 코드
//function makeSalaryRange(info) {
//	let minSal = 0;
//	let maxSal = 0;
//	let avgSal = 0;
//	// 최소급여와 최대 급여 가져오기
//	$.each(jobsData.jobs, function(i, item){
//		if (info == item.job_id) {
//			minSal = Number(item.min_salary);
//			maxSal = Number(item.max_salary);
//		};
//	});
//	
//	avgSal = (minSal + maxSal) / 2;
//	$("#writeSalary").attr('min', minSal);
//	$("#writeSalary").attr('max', maxSal);
//	$("#writeSalary").val(avgSal);
//	
//	$("#salVal").html(Number(avgSal).toLocaleString);
//
//}

// 부서에 따라 담당 직원을 사수로 선택할 수 있도록 출력
function makeMgrSelection(mode) {
	let output = "<option value=''>=== 직속 상사를 입력하세요 ===</option>";
	if (mode == null){
		$.each(empData.employees, function(i, item){
			if ($("#writeDepartmentName").val() == item.department_id) {
				output += "<option id='" + item.employee_id +"' value='" + item.employee_id + "'>" + item.first_name + " "
				+ item.last_name + "</option>"
			}
		});
	} else if (mode == "modify"){	
		$.each(empData.employees, function(i, item){
			if ($("#modifyDepartmentName").val() == item.department_id) {
				output += "<option id='" + item.employee_id +"' value='" + item.employee_id + "'>" + item.first_name + " "
				+ item.last_name + "</option>"
			}
		});
	}
	return output;
}

function inputEmpValidate(empTemp){
	// last_name: not null
	// email : not null &  Unique
	// hire_date : not null
	
	let isLastNameValid = lastNameValid(empTemp.last_name);
	let isEmailValid = emailValid(empTemp.email);
	let isHiredateValid = hiredateValid(empTemp.hire_date);
	let isJobIdValid = jobIdValid(empTemp.job_id);
	let isDepartmentIdValid = departmentIdValid(empTemp.department_id);
	let isManagerIdValid = managerIdValid(empTemp.manager_id);
//	let isMobileValid = mobileValid(empTemp.mobile);
	// 모두 통과 하면
	if (isLastNameValid && isEmailValid && isHiredateValid && isJobIdValid && isDepartmentIdValid && isManagerIdValid) {
		let empByName = $("#byName").val();
		let orderMethod = $(".orderMethod:checked").val();
		$.ajax({
			url : 'insertEmployee.do',
			type : 'post',
			data : empTemp,
			dataType : 'json',
//  			async: false,
			success : function(data) {
				if (data.status == "fail") {
					alert("데이터를 불러오지 못했습니다.")
				} else if (data.status == "success"){
					console.log(data);
					alert("저장 완료");
				}
				$("#writeModal").hide();
				getAllEmp(empByName, orderMethod)
				deleteWriteModalContents();	

			},
			error : function() {
			},
			complete : function() {
			}
		});
	}
}

function modifyEmpValidate(empTemp){
	// last_name: not null
	// email : not null &  Unique
	// hire_date : not null
//	console.log("여기까지");
	let isLastNameValid = lastNameValid(empTemp.last_name);
	let isEmailValid = emailValidModify(empTemp.email);
	let isHiredateValid = hiredateValid(empTemp.hire_date);
	let isJobIdValid = jobIdValid(empTemp.job_id);
	let isDepartmentIdValid = departmentIdValid(empTemp.department_id);
	let isManagerIdValid = managerIdValid(empTemp.manager_id);
	console.log(isLastNameValid, isEmailValid, isHiredateValid, isJobIdValid, isDepartmentIdValid, isManagerIdValid)
	// 모두 통과 하면
	if (isLastNameValid && isEmailValid && isHiredateValid && isJobIdValid && isDepartmentIdValid && isManagerIdValid) {
		let empByName = $("#byName").val();
		let orderMethod = $(".orderMethod:checked").val();
		$.ajax({
			url : 'modifyEmployee.do',
			type : 'post',
			data : empTemp,
			dataType : 'json',
//  			async: false,
			success : function(data) {
				if (data.status == "fail") {
					alert("정보가 수정되지 않았습니다.")
				} else if (data.status == "success"){
					console.log(data);
					alert("수정 완료");
				}
				$("#modifyModal").hide();
				getAllEmp(empByName, orderMethod)


			},
			error : function() {
			},
			complete : function() {
			}
		});
		
	}
}

function lastNameValid(lastname) {
	let isLastNameValid = true;
	
	if (lastname.length < 1) {
		printErrMsg ("writeLastName", "성은 필수 항목입니다.");
		printErrMsg ("modifyLastName", "성은 필수 항목입니다.");
		isLastNameValid = false;
	} 
	return isLastNameValid;
}

function emailValid(email){
	let isEmailNN = true;
	let isEmailUQ = true;
	// NN Check
	if (email.length < 1) {
		printErrMsg ("writeEmail", "Email은 필수 항목입니다");
		isEmailNN = false;
	}
	
	// unique Check => 사실은 DB를 통해서 확인 해봐야 한다.
	$.each(empData.employees, function (i, item){
		if (email.toUpperCase() == item.email.toUpperCase()) {
			printErrMsg ("writeEmail", '이미 등록된 Email입니다.');
			isEmailUQ = false;
		}
	})

	if (isEmailNN && isEmailUQ) {
		return true;
	} else { 
		return false;
	}
}
function emailValidModify(email){
	let isEmailNN = true;
	// NN Check
	if (email.length < 1) {
		printErrMsg ("modifyEmail", "Email은 필수 항목입니다");
		isEmailNN = false;
	}
		return isEmailNN;
}

function hiredateValid(hire_date) {
	isHiredateValid = true;
	if (hire_date.length < 1) {
		printErrMsg ("writeHireDate", "입사일을 기입하세요");
		printErrMsg ("modifyHireDate", "입사일을 기입하세요");
		isHiredateValid = false;
	}
	return isHiredateValid;
}

function jobIdValid(jobId){
	isJobIdValid = true;
	if (jobId == '') {
		printErrMsg ("writeJobId", "직무를 선택하세요");
		printErrMsg ("modifyJobId", "직무를 선택하세요");
		isJobIdValid = false;
	}
	return isJobIdValid;
}
function departmentIdValid(departmentId){
	isDepartmentValid = true;
	if (departmentId == '') {
		printErrMsg ("writeDepartmentName", "부서를 선택하세요");
		printErrMsg ("modifyDepartmentName", "부서를 선택하세요");
		isDepartmentValid = false;
	}
	return isDepartmentValid;
}

function managerIdValid(managerId){
	isManagerIdValid = true;
	if (managerId == '') {
		printErrMsg ("writeManagerId", "매니저를 선택하세요");
		printErrMsg ("modifyManagerId", "매니저를 선택하세요");
		isManagerIdValid = false;
	}
	return isManagerIdValid;
}

// 유효성 검사 Error 메시지
function printErrMsg (id, msg) {
	let errMsg = "<div class='errMsg'>" + msg + "</div>";
	$(errMsg).insertAfter($("#" + id).parent());
	$("#" + id).focus();
	$(".errMsg").hide(2000);
}

//function searchByName(name, orderMethod) {
//	$.ajax({
//			url : 'getEmpByName.do',
//			type : 'post',
//			data : {
//					"byName" : name.toLowerCase(),
//					"sortOrder" :orderMethod
//					},
//			dataType : 'json',
//// 			async: false,
//			success : function(data) {
//				if (data.status == "fail") {
//					alert("데이터를 불러오지 못했습니다.")
//				} else if (data.status == "success"){
//					console.log(data);
//					outputEntireEmployees(data)
//				}
//			},
//			error : function() {
//			},
//			complete : function() {
//			}
//		});
//}

function deleteWriteModalContents(){
	$("#writeFirstName").val("");
	$("#writeLastName").val("");
	$("#writeEmail").val("");
	$("#writePhoneNumber").val("");
	$("#writeHireDate").val("");
	$("#writeDepartmentName").val("");
	$("#writeJobId").val("");
	$("#writeSalary").val("");
	$("#writeManagerId").val("");			
	$("#salval-min").val("");			
	$("#salval-max").val("");			
	$("#salval").val("");
}

//function selectOrderMethod(orderMethod) {
//	$.ajax({
//		url : 'orderByEmp.do',
//		type : 'post',
//		data : {"sortOrder" : orderMethod},
//		dataType : 'json',
////		async: false,
//		success : function(data) {
//			if (data.status == "fail") {
//				alert("데이터를 불러오지 못했습니다.")
//			} else if (data.status == "success"){
//				console.log(data);
//				outputEntireEmployees(data);	
//
//			}
//		},
//		error : function() {
//		},
//		complete : function() {
//		}
//	});
//}

function modifyModalShow(empNo){
	
	$("#modifyModal").show();
	$("#modifyEmployeeId").val(empNo); // 사원 수정 모달에 바인딩
	
		$.ajax({
		url : 'getEmployee.do',
		type : 'post',
		data : {"empNo" : empNo},
		dataType : 'json',
//		async: false,
		success : function(data) {
			if (data.status == "fail") {
				alert("데이터를 불러오지 못했습니다.")
			} else if (data.status == "success"){
				console.log(data);
				bindingDataModifyModal(data);
			}
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

function bindingDataModifyModal(data){
	$("#modifyFirstName").val(data.employee.first_name);
	$("#modifyLastName").val(data.employee.last_name);
	$("#modifyEmail").val(data.employee.email);
	$("#modifyPhoneNumber").val(data.employee.phone_number);
	$("#modifyHireDate").val(data.employee.hire_date);
	
	$("#modifyJobId").html(makeJobSelection());
	$("#modifyJobId").val(data.employee.job_id);
	$("#modifySalval").html(" $ " + new Intl.NumberFormat('en-US').format(data.employee.salary));
	$("#modifySalary").val(data.employee.salary);
		makeSalaryRange("modify")

	$("#modifyDepartmentName").html(makeDeptSelection());
	$("#modifyDepartmentName").val(data.employee.department_id);
	$("#modifyManagerId").html(makeMgrSelection("modify"));
	$("#modifyManagerId").val(data.employee.manager_id);
}


function deleteModalShow(empNo){
	$("#deleteModal").show();
	$("#deleteEmpNo").html(empNo);
	$("#deleteSubmit").attr("value", empNo); 

}

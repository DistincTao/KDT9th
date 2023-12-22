$(document).ready(function() {
	getAllEmployees();
	getJobsData();
	getDeptData();
	$(".writeIcon").click(function() {
		//사원 입력 시 필요한 부가 정보 모달창 띄우기
		$("#writeJobId").html(makeJobSelection());
		$("#writeManagerId").html(makeMgrSelection());
		$("#writeDepartmentName").html(makeDeptSelection());
		$("#writeModal").show();
	})
	$(".modalClose").click(function() {
		$("#writeModal").hide();
	})
	
	$("#writeSalary").change(function() {
		$("#salval").html("$" + $("#writeSalary").val());
	})
});

let empData = null;
let jobsData = null;
let deptData = null;
let mgrData = null;
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
			outputEntireEmployees(data);
		},
		error : function() {
		},
		complete : function() {
		}
	});
}

function getJobsData() {
	let url = "getJobsData.do";
	$.ajax({
		url : url,
		type : 'post',
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

function getDeptData() {
	let url = "getDeptData.do";
	$.ajax({
		url : url,
		type : 'post',
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
		output += "<tr><td>" + item.employee_id + "</td>";
		output += "<td>" + item.fisrt_name + " "
				+ item.last_name + "</td>";
		output += "<td>" + item.email + "</td>";
		output += "<td>" + item.phone_number + "</td>";
		output += "<td>" + item.hire_date + "</td>";
		output += "<td>" + item.job_id + "</td>";
		output += "<td> $" + item.salary + "</td>";
		output += "<td>" + item.commition_pct * 100
				+ "% </td>";

		let managerId = item.manager_id;
		let managerName = "";
		$.each(json.employees, function(i, items) {
			if (managerId == items.employee_id) {
				managerName = items.fisrt_name + " "
						+ items.last_name;
			}
		});
			
	output += "<td>" + managerName + "</td>";
	output += "<td>" + item.department_name + "</td>";
	output += "<td><Image src='img/modify.png' class='maintain'></td>";
	output += "<td><Image src='img/remove.png' class='maintain'></td></tr>";
	
	});
	output += "</tbody></table>";

	$(".empInfo").html(output);
}

function makeJobSelection() {
	let output = "<option>=== 직급을 입력하세요 ===</option>";
	$.each(jobsData.jobs, function(i, item){
		output += "<option  id='" + item.job_id +"' value='" + item.job_id + "'>" + item.job_title + "</option>";
	})
	
	return output;
}

function makeMgrSelection(){

	let output = "<option>=== 담당 메니저를 입력하세요 ===</option>";
	$.each(empData.employees, function(i, item){
		output += "<option id='" + item.employee_id +"' value='" + item.employee_id + "'>" + item.fisrt_name + " "
				+ item.last_name + "</option>"
	});
	return output;
}

function makeDeptSelection() {
	let output = "<option>=== 부서를 입력하세요 ===</option>";
	$.each(deptData.departments, function(i, item){
		output += "<option  id='" + item.department_id +"' value='" + item.department_id + "'>" + item.department_name + "</option>";
	})
	
	return output;
}

	
	
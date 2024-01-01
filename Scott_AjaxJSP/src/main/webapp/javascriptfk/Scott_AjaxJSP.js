$(document).ready(function(){
	
	let order = "orderEmpAcs"
	let empName = null;
	getAllEmps(null, "orderEmpAcs");
	getEmps();
	getDepts();
	
	$("#insertEmp").click(function(){
		$("#writeDeptName").html(makeDeptList());
		$("#writeMgrId").html(makeMgrList());
		$("#salval").html("$ " + $("#writeSal").val());
		$("#writeModal").show();
	});
		
	$(".modalClose").click(function(){
		$("#writeModal").hide();
		$("#updateModal").hide();
		$("#deleteModal").hide();
	});
	
	$("#writeSal").change(function(){
		$("#salval").html("$ " + $("#writeSal").val());
	});
	
	$(".order").change(function () {
		order = $(".order:checked").val();
		empName = $("#searchName").val();	
		getAllEmps(empName, order);
	});
	
	$("#searchName").keyup(function(e){
		if (e.keyCode == 13) {
			empName = $(this).val();
		}
		order = $(".order:checked").val();
		getAllEmps(empName, order);
	});
	
	$("#showAll").click(function(){
		order = $(".order:checked").val();
		getAllEmps(null, order);
	});
	
	$("#showQuits").click(function(){
		order = $(".order:checked").val();
		getQuitEmps(order);
	});
	
	$("#deleteSubmit").click(function (){
		let empNo = $(this).val();
		
		$.ajax({
			url : "deleteEmp.do",
			type : "get",
			data : {"empNo" : empNo},
			dataType : "json",
	//		async : false,
			success : function (data){
				console.log(data);
			getAllEmps(null, order);	
			},
			error : function(){},
			complete : function () {
				$("#deleteModal").hide();
			},
		});
		
	});
	
	
});

let empData = null;
let deptData = null;

function getEmps() {
	let url = "getAllEmp.do"
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
//		async : false,
		success : function (data){
			console.log(data);
			empData = data;
		},
		error : function(){},
		complete : function () {},
	});
}

function getAllEmps(empName, order) {
		
	if (order == "orderEmpAcs") {
		order = ".EMPNO";
	} else if (order == "orderEmpDecs") {
		order = ".EMPNO DESC";
	} else if (order == "orderHireDateAcs") {
		order = ".HIREDATE";
	} else if (order == "orderHireDateDecs") {
		order = ".HIREDATE DESC";
	} else if (order == "orderSalAcs") {
		order = ".SAL";
	} else if (order == "orderSalDecs") {
		order = ".SAL DESC";
	}
	console.log(order);
	let url = "getAllEmp.do?order=" + order;
	if (empName != null) {
		url += "&eName=" + empName.toUpperCase();
	}
	$.ajax({
		url : url,
		type : "get",
		dataType : "json",
//		async : false,
		success : function (data){
			console.log(data);
			outputList(data);
		},
		error : function(){},
		complete : function () {},
	});
}

function getQuitEmps(order) {
		
	if (order == "orderEmpAcs") {
		order = ".EMPNO";
	} else if (order == "orderEmpDecs") {
		order = ".EMPNO DESC";
	} else if (order == "orderHireDateAcs") {
		order = ".HIREDATE";
	} else if (order == "orderHireDateDecs") {
		order = ".HIREDATE DESC";
	} else if (order == "orderSalAcs") {
		order = ".SAL";
	} else if (order == "orderSalDecs") {
		order = ".SAL DESC";
	}
	
	let url = "getQuitEmp.do?order=" + order;
	$.ajax({
		url : url,
		type : "get",
		dataType : "json",
//		async : false,
		success : function (data){
			console.log(data);
			outputQuitList(data);
		},
		error : function(){},
		complete : function () {},
	});
}

function getDepts() {
	let url = "getAllDept.do"
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
//		async : false,
		success : function (data){
			console.log(data);
			deptData = data;
		},
		error : function(){},
		complete : function () {},
	});
}

function outputList(json){
	$("#outputCnt").html("데이터 : " + json.count + "개");
	$("#outputDate").html("출력 일시 : " + json.outputDate);
	
	let output = "<table class='table table-dark table-striped'><thead>"
	output += " <tr><th>EMPNO</th><th>ENAME</th><th>JOB</th>"
    output += "<th>MGR</th><th>HIREDATE</th><th>SAL</th><th>COMM</th><th>DNAME</th>"
   	output += "<th>Update</th><th>Delete</th>"
   	output += "</tr></thead><tbody>"
    $.each(json.emp, function (i, item){
		output += "<tr><td id='empNo'>" + item.EMPNO + "</td>";
        output += "<td>" + item.ENAME + "</td>";
        output += "<td>" + item.JOB + "</td>";
        
        let mgrId = item.MGR;
        let mgrName = "";
        $.each(json.emp, function(i, item) {
			if (mgrId == item.EMPNO) {
				mgrName = item.ENAME;				
			};
		});
        output += "<td>" + mgrName + "</td>";
        output += "<td>" + item.HIREDATE + "</td>";
        output += "<td> $ " + Intl.NumberFormat("en-US").format(item.SAL) + "</td>";
        output += "<td> $ " + Intl.NumberFormat("en-US").format(item.COMM)  + "</td>";
        output += "<td>" + item.DNAME + "</td>";
        output += "<td><img src='img/modify.png' class='updateEmp' onclick='modifyModal(" + item.EMPNO + ");'></td>";
        output += "<td><img src='img/remove.png' class='deleteEmp' onclick='deleteModal(" + item.EMPNO + ");'></td></tr>";
	})  
	output += "</tbody></table>";
	
	$("#empData").html(output);
	
//	$(".deleteEmp").on ("click", this, function(){
//		console.log(this.id);
//		$("#delete-modal-body").html(deleteModal(this.id));
//		$("#deleteModal").show();
//		$(".modalClose").click(function(){
//			$("#deleteModal").hide();
//		});
//	});
//	
//	$(".updateEmp").on ("click", this, function(){
//		$("#writeDeptName").html(makeDeptList());
//		$("#writeMgrId").html(makeMgrList());
//		$("#salval").html("$ " + $("#writeSal").val());
//		$("#updateModal").show();
//		$(".modalClose").click(function(){
//			$("#updateModal").hide();
//		});
//
//	});
	
}

function outputQuitList(json){
	$("#outputCnt").html("데이터 : " + json.count + "개");
	$("#outputDate").html("출력 일시 : " + json.outputDate);
	
	let output = "<table class='table table-dark table-striped'><thead>"
	output += " <tr><th>EMPNO</th><th>ENAME</th><th>JOB</th>"
    output += "<th>HIREDATE</th><th>COMM</th><th>DNAME</th>"
   	output += "<th>QUITDATE</th>"
   	output += "</tr></thead><tbody>"
    $.each(json.emp, function (i, item){
		output += "<tr><td id='empNo'>" + item.EMPNO + "</td>";
        output += "<td>" + item.ENAME + "</td>";
        output += "<td>" + item.JOB + "</td>";
        
        let mgrId = item.MGR;
        let mgrName = "";
        $.each(json.emp, function(i, item) {
			if (mgrId == item.EMPNO) {
				mgrName = item.ENAME;				
			};
		});
        output += "<td>" + mgrName + "</td>";
        output += "<td>" + item.HIREDATE + "</td>";
        output += "<td>" + item.DNAME + "</td>";
        output += "<td>" + item.QUITDATE + "</td></tr>";
	})  
	output += "</tbody></table>";
	
	$("#empData").html(output);

}

function makeDeptList(){
	let output = "<option value=''> ---- 부서를 선택하세요 ---- </option>";
	$.each(deptData.dept, function (i, item){
		output += "<option value='"+ item.DEPTNO + "'/>" + item.DNAME + "</option>";
	});
	
	return output;
}

function makeMgrList () {
	let output = "<option value=''> ---- 상관을 선택하세요 ---- </option>";
	$.each(empData.emp, function(i, item){
		output += "<option value='" + item.EMPNO + "'>" + item.ENAME + "</option>";
	});
	return output;
}

function modifyModal(empNo){
		$("#writeDeptName").html(makeDeptList());
		$("#writeMgrId").html(makeMgrList());
		$("#salval").html("$ " + $("#writeSal").val());
		$("#updateModal").show();
		$(".modalClose").click(function(){
			$("#updateModal").hide();
		});
}

function deleteModal(empNo) {
		$("#deleteModal").show();
		$("#deleteEmpNo").html(empNo);
		$("#deleteSubmit").val(empNo);
}




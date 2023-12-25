$(document).ready(function(){
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
	});
	
	$("#writeSal").change(function(){
		$("#salval").html("$ " + $("#writeSal").val());
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
			outputList(data);
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
        output += "<td><img src='img/modify.png' class='updateEmp' id='" + item.EMPNO + "'></td>";
        output += "<td><img src='img/remove.png' class='deleteEmp' id='" + item.EMPNO + "'></td></tr>";
	})  
	output += "</tbody></table>";
	
	$("#empData").html(output);
	
	$(".deleteEmp").on ("click", this, function(){
		console.log(this.id);
		$("#delete-modal-body").html(deleteModal(this.id));
		$("#deleteModal").show();
		$(".modalClose").click(function(){
			$("#deleteModal").hide();
		});
	});
	
	$(".updateEmp").on ("click", this, function(){
		$("#writeDeptName").html(makeDeptList());
		$("#writeMgrId").html(makeMgrList());
		$("#salval").html("$ " + $("#writeSal").val());
		$("#updateModal").show();
		$(".modalClose").click(function(){
			$("#updateModal").hide();
		});

	});
	
}

function makeDeptList(){
	let output = '';
	$.each(deptData.dept, function (i, item){
		output += "<option value='"+ item.DEPTNO + "'/>" + item.DNAME + "</option>";
	});
	
	return output;
}

function makeMgrList () {
	let output = "";
	$.each(empData.emp, function(i, item){
		output += "<option value='" + item.EMPNO + "'>" + item.ENAME + "</option>";
	});
	return output;
}

function deleteModal(item) {
	let output = "<form action='deleteEmp.do' method='post'>";
	output += "<div> 선택하신 직원 정보를 삭제 하시겠습니까?</div>";
	output += "<input type='hidden' name='empId' value='" + item + "'>";
	output += "<div class='modal-footer'>";
	output += "<button type='submit' class='btn btn-primary' data-bs-dismiss='modal'>Send</button>";
	output += "<button type='button' class='btn btn-danger modalClose' data-bs-dismiss='modal'>Close</button>";
	output += "</div></form>";
	
	return output;
}

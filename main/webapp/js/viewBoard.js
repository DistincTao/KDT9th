$(function() {
	
	$(".modalClose").click(function(){
		$("#updateBoardModal").hide();
		$("#deleteBoardModal").hide();

	})	
	
	$("#likeCnt").click (function(){
		
	})
	$("#fileDelete").change(function() {
		if ($("input[type=checkbox][name=fileDelete]:checked").val() == 'Y'){
			$("#changeFile").hide()		
		} else {
			$("#changeFile").show()
		}
		
	})

	
})


function updateBoard() {
//	alert("test");
	// 모달 띄우기
	$("#updateBoardModal").show();
	
}

function deleteBoard() {
//	alert("test");
	// 모달 띄우기
	$("#deleteBoardModal").show();
	
}


//function deleteBoard () {
//	let boardNo = '${requestScope.board.boardNo}'; // js에서 EL 사용 시 ''로 감싸 주어야 함
//	$.ajax({
//		url : "",
//		type : "get",
//		data : {"boardNo" : boardNo},
//		datatype : "json",
//		success : function (data) {
//			console.log(data);
//		},
//		error : function () {},
//		complete : function () {}
//	});
//}

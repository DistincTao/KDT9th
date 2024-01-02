$(function() {

	// 아이디 유효성 검사
	$("#userId").blur (function(e){
		validUserId();
	}); 
	
	
	
	// 검증
	$("#signInBtn").click (function (){
		validCheck()
		
	})




}); // End of Doc


	// 함수 시작!!!


	// 회원 가입을 눌렀을 때 유효성 검사 함수
	function validCheck() {
		let isValid = false;
		
		let userIdValid = validUserId();
		
		
		return isValid;
		
	}

	// 아이디 유효성 검사 함수
	function validUserId () {
		// 아이디 유효성 검사
		// 3자 이상 8자 이하
		let isValid = false;
		let tmpUserId = $("#userId").val();
// 		alert("1");
		if (tmpUserId.length > 2 && tmpUserId.length < 9){
			// 아이디 중복 검사
			$.ajax({
				url : 'duplicateUserId.mem',
				type : 'get',
				data : {
						"tmpUserId" : tmpUserId
					   },
				dataType : 'json',
				// async: false,
				success : function(data) {
					console.log(data);
 					if (data.responseCode != "201") {
 						alert("DB에 문제가 있습니다. 다시 시도해주세요.")
 					} else {
						alert("DB")
						if (data.isDuplicate == "true"){
						 // 아이디 중복
						 printErrMsg("userId", "이미 사용된 아이디입니다.", true);
 						} else if (data.isDuplicate == "false"){
						 // 사용 가능한 아이디
 						 printErrMsg("userId", "사용 가능한 아이디입니다.", false);
 						 isValid = true;
						}
 					}	
				},
				error : function() {
				},
				complete : function() {
				}
			});
		} else {
			printErrMsg("userId", "아이디는 3자 이상 8자 이하로 필수입니다.", true);
		}
		return isValid;
	}
	
	// 유효성 검사 Error 메시지
	function printErrMsg (id, msg, isFocus) {
		let errMsg = "<div class='errMsg'>" + msg + "</div>";
		$(errMsg).insertAfter($("#" + id).parent());
		if (isFocus) {
			$("#" + id).focus();
		}			
		$(".errMsg").hide(2000);
	}

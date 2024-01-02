$(function() {

	// 아이디 유효성 검사
	$("#userId").blur (function(e){
		validUserId();
	});
	
	$("#userPwd").change (function (e){
		validUserPwd();
	})
	
	$("#userPwd2").change (function (e){
		matchingPwd();
	})

	
	
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
	let userPwdValid = matchingPwd();
	let userEmailValid = validUserEmail();
	
	
	return isValid;
	
}

// 아이디 유효성 검사 함수
function validUserId () {
	// 아이디 유효성 검사
	// 3자 이상 8자 이하
	let isValid = false;
	let tmpUserId = $("#userId").val();
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
//					alert("DB")
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

function validUserPwd() {
	let reg = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
	let userPwd = $("#userPwd").val();
	if (!reg.test(userPwd) || userPwd.length < 8) {
		$("#userPwd").val("");	
   		printErrMsg("userPwd", "비밀번호는 8자리 이상, 최소 하나 이상의 대문자 및 특수기호를 포함해야 합니다.", true);
	}
}

function matchingPwd(){
	let isValid = false;	
	let userPwd = $("#userPwd").val();
	let userPwd2 = $("#userPwd2").val();
	if (userPwd != userPwd2) {
		printErrMsg("userPwd2", "비밀번호가 일치하지 않습니다.", true);
		$("#userPwd2").val("");	
	} else if (userPwd == userPwd2) {
		printErrMsg("userPwd2", "비밀번호가 일치합니다.", false);
		isValid = true;
	}
	return isValid;
}

function validUserEmail(){
	let isValid = false;	
	
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
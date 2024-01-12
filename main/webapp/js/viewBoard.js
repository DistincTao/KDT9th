$(function() {
	
	$(".modalClose").click(function(){
		$("#updateBoardModal").hide();
		$("#deleteBoardModal").hide();

	})	
	
	$("#likeCnt").click (function(){
		
	})
	$("#fileDelete").change(function() {
		if ($("input[type=checkbox][name=fileDelete]:checked").val() == 'delete'){
			$("#changeFile").hide()
		} else {
			$("#changeFile").show()
		}
		
	})

	$('#likeCnt').click(function (e) {
		 console.log('1');
		$(this).toggleClass('fa-solid');
	});
	
//	$('#likeCnt').on('click', '.fa-heart', function (e) {
//		if (!getCookie(this.id)) {
//			saveCookie(this.id, `BookMark`, 10);
//			$(this).toggleClass('fa-solid');
//		} else {
//			delCookie(this.id, `BookMark`);
//			$(this).toggleClass('fa-solid');
//		}
//	});
	
	
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


// 쿠키 북마크 내용 출력
function bookMarkList() {
	let cookies = document.cookie;
	let cookieArr = cookies.split('; ');
	console.log(cookieArr);
	$.each(cookieArr, function (index, cookie) {
		let title = cookieArr[index].split('=')[0];
		let value = cookieArr[index].split('=')[1];
		console.log(title, value, cookie);
		if (value == 'BookMark') {
			$('#likeList').append(`<div>${title}</div>`);
		}
	});
	// $('.likeList').css({ display: 'block' });
}

// 쿠키 관련 함수
function saveCookie(name, val, expDate) {
	let now = new Date();
	now.setDate(now.getDate() + Number(expDate));
	let newCookie = name + '=' + val + ';expires=' + now.toUTCString();
	document.cookie = newCookie; // 쿠키 저장
}

// 해당 cookie를 삭제하는 함수
function delCookie(name, val) {
	// 쿠키 삭제
	let now = new Date();
	let cookie = name + '=' + val + ';expires=' + now.toUTCString();
	document.cookie = cookie;
}
//쿠키를 읽어와 해당 내용이 있는지 없는지를 확인하는 함수
let cookName = [];
function getCookie(name) {
	if (document.cookie != '') {
		let cookArr = document.cookie.split('; ');
		// console.log(cookArr);
		// console.log(document.cookie);
		$.each(cookArr, function (index, cookie) {
			cookName[index] = `${cookie.replace(`=BookMark`, '')}`;
		});
		for (let i in cookName) {
			if (cookName[i] == name) {
				return true;
			}
		}
	}
}

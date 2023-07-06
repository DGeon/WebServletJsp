//메인배너 슬라이더
$(document).ready(function() {
	//로딩이슈로 인한 지연 시키는 것
	setTimeout(function() {
		$(".slider").bxSlider();
	});

	$('.slider').bxSlider({
		mode: 'fade',
		pager: false, //하단 점 없애는것
		controls: true, //왼쪽오른쪽 버튼 없애는 것
		auto: true,//자동으로 배너 넘기는것
		//로딩 이슈있음
		onSliderLoad: function() {
			$("#edd").css("visibility", "visible").animate({ opacity: 1 });
		}
	});
});

//게시판 호버시
$(function() {
	$(".board").hover(function() {
		$(".dropMenu").stop().slideDown(500);
	}, function() {
		$(".dropMenu").stop().slideUp(500);
	})
});

//게시판 탭인덱스
$(function() {
	$(".aboard").focus(function() {
		$(".dropMenu").stop().slideDown(500);
	})

	//게시판 탭인덱스 벗어났을 때
	$(".index-free").blur(function() {
		$(".dropMenu").stop().slideUp(500);
	})
});

//jq
//첫화면 로그인 버튼
$(function() {
	$(".index-login-btn").click(function() {
		/* $(location).attr("href","member/signin");*/
		let frm = document.getElementById("index-form");
		frm.submit();
	});

	$("#noticeWrite").click(function() {
		$(location).attr("href", "write.html");
	});

	//공지사항 글쓰기 버튼 
	$("#writeCommit").click(function() {
		let frm = document.getElementById("writeFrm");
		let regdate = document.getElementById("regdate");
		let day = new Date;
		regdate.name = day.toLocaleString();
		// console.log(regdate.value); 벨류로 보내기
		frm.action = "notice.html";
		frm.method = "get";
		if (frm.noticeTitle.value.trim().length < 4) {
			alert("4글자 이상 제목을 입력하세요");
			frm.noticeTitle.focus();
		}
		else if (frm.noticeContent.value.trim().length < 1) {
			alert("10글자 이상 제목을 입력하세요");
			frm.noticeContent.focus();
		} else frm.submit();
	});

//로그인 ID
$("#signinId").blur(function() {
	let frm = document.getElementById("signinFrm");

	if (frm.signinId.value.length == 0){ 
		frm.signinId.focus();
	frm.signinOutput.innerHTML = "아이디를 입력하세요";

	frm.signinOutput.innerHTML = "";
}
});
//로그인 PW
$("#signinPw").blur(function() {
	let frm = document.getElementById("signinFrm");
	if (frm.signinPw.value.length == 0) {
		m.signinPw.focus();
		frm.signinOutput.innerHTML = "비밀번호를 입력하세요";
	} else {
		frm.signinOutput.innerHTML = "";

	}
});
//로그인 버튼
$("#signinBtn").click(function() {
	let frm = document.getElementById("signinFrm");
	if (frm.signinId.value.length == 0 || frm.signinPw.value.length == 0) {
		frm.signinOutput.innerHTML = "로그인양식과 맞지 않습니다.";
		$("#signinBtn").blur(function() {
			frm.signinOutput.innerHTML = "";
		})
	}
	else {
		frm.submit();
	}
});

//글쓰기 버튼에서 목록 버튼
$("#writeList").click(function() {
	$(location).attr("href", "notice.html");
});

//자유게시판에서 글쓰기 버튼
$("#freeWrite").click(function() {
	$(location).attr("href", "write.html");
});

//수정
$("#detailUpdate").click(function() {
	$(location).attr("href", "write.html");
});
$("#datailList").click(function() {
	$(location).attr("href", "notice?${cri.fullQueryString}");
});

//약관 동의
// $("#agree").click(function() {
//   console.log(document.getElementById("agree"));
//   if(document.getElementById("agree").checked==true);
//     // $(location).attr("href","signup.html");
// });
//회원가입 체크박스 비동의
$("#contractSignup").click(function() {
	let agree = document.getElementById("agree");
	let disagree = document.getElementById("disagree");
	if (agree.checked) {
		sessionStorage.setItem("agree", "ckeck");
		$(location).attr("href","signup");
		
        } else if(disagree.checked){
			alert("동의 하지 않을 시 가입 하실 수 없습니다.");
		}else {
			alert("약관을 체크 하셔야 합니다.");
	}
});


//회원가입 시 유효성
/*$("#singupId").blur(function() {
	
	let frm = document.getElementById("signupFrm");
	let id = /^[a-z]+[a-z0-9]{3,11}$/g;
	if (!id.test(frm.singupId.value)){
		frm.singupId.focus();
		frm.outId.innerHTML = "아이디는 4글자 이상 12글자 이하 입니다.";
		
	} else {
		frm.outId.innerHTML = " ";
	}
});*/
$("#singupPw").blur(function() {
	let frm = document.getElementById("signupFrm");
	let pw = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/; // 4~16글자 영 숫 정규식
	if (!pw.test(frm.singupPw.value)) {

		frm.outPw.innerHTML = "비밀번호는 4글자 이상입니다.(영문+숫자)";
	} else {
		frm.outPw.innerHTML = "";
	}
});
$("#singupPwCheck").blur(function() {
	let frm = document.getElementById("signupFrm");
	if (frm.singupPwCheck.value.trim() != frm.singupPw.value.trim()) {
		frm.outPwCheck.innerHTML = "동일한 비밀번호가 아닙니다";
	} else {
		frm.outPwCheck.innerHTML = "";
	}
});
//필요 시 주석 해제 할 것 이메일, 전화번호, 본문에서 tabindex 수정해야됨
/*
$("#singupEmail").blur(function() {
	let frm = document.getElementById("signupFrm");
	email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;//이메일 정규식
	if (!email.test(frm.singupEmail.value)) {
		frm.singupEmail.focus();
		frm.outEmail.innerHTML = "이메일을 다시 입력해주세요";
	} else {
		frm.outEmail.innerHTML = "";
	}
});
$("#singupTel").blur(function() {
	let frm = document.getElementById("signupFrm");
	let tel = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/;  //전화번호 정규식
	if (!tel.test(frm.singupTel.value)) {
		frm.singupTel.focus();
		frm.outTel.innerHTML = "전화번호를 다시 입력해주세요(010-1234-5678)";
	} else {
		frm.outTel.innerHTML = "";
	}
});*/
//  $("#singupGender").blur(function() {
//   let frm = document.getElementById("signupFrm");
//     if(frm.singupGender.checked){
//       frm.outTel.innerHTML ="dd";
//     }else{
//       frm.outTel.innerHTML ="";
//     }
//  });
//  
//  || 

/* 마이페이지 수정*/
$("#mypagePw").blur(function() {
	let frm = document.getElementById("mypageFrm");
	let pw = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,16}$/; // 4~16글자 영 숫 정규식
	if (!pw.test(frm.mypagePw.value)) {
		frm.mypagePw.focus();
		frm.outPw.innerHTML = "비밀번호는 4글자 이상입니다.(영문+숫자)";
	} else {
		frm.outPw.innerHTML = "";
	}
});
$("#mypagePwCheck").blur(function() {
	let frm = document.getElementById("mypageFrm");
	if (frm.mypagePwCheck.value.trim() != frm.mypagePw.value.trim()) {
		frm.outPwCheck.innerHTML = "동일한 비밀번호가 아닙니다";
	} else {
		frm.outPwCheck.innerHTML = "";
	}
});

$("#mypageBtn").click(function() {
	let frm = document.getElementById("mypageFrm");
	
	if (frm.mypageId.value.length == 0 || frm.mypagePw.value.length == 0 || frm.mypagePwCheck.value.length == 0 || frm.mypagePwCheck.value != frm.mypagePw.value) {
		//|| frm.mypageEmail.value.length==0 ||frm.mypageTel.value.length==0 필요시 주석 해제 할 것 
		frm.formCheck.innerHTML = "회원양식과 맞지 않습니다.";
		frm.preventDefault();
		$("#mypageBtn").blur(function() {
			frm.formCheck.innerHTML = "";
		})
	} else {
		 if(confirm("회원정보를 수정 하시겠습니까?")){
		frm.submit();}
	}
});

	$("#mypageRemoveBtn").click(function() {
		let frm = document.getElementById("mypageFrm");
		
		let meberRemove = prompt("'회원탈퇴'를  입력해주세요");
		if (meberRemove=="회원탈퇴") {
			$(location).attr("href","remove");
		}
	});
/* 마이페이지 수정*/


/* 게시글 */

		
/* 게시글 */






});
    //js 
  //   window.onload = function(){
  //   // //버튼(index login)
  //   document.getElementsByClassName("index-login-btn")[0].onclick = function () {
  //     location.href="signin.html";
  //   };
  //   //버튼(notice login)
  //   document.getElementsByClassName("noticeWrite")[0].onclick = function () {
  //       console.log("asdfasdf");
  //       location.href="write.html";
  //   };

  // }

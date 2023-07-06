<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../common/head.jsp" %>
</head>
<body>
  <div id="wrap">
   <%@include file="../common/header.jsp" %>
    <main class="signup-main">
      <form method="post" id="signupFrm">
        <label>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" placeholder="아이디를 입력하세요" tabindex="6" id="singupId"></label><br><output id="outId"></output><br>
        <label>비밀번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pw" placeholder="비밀번호를 입력하세요"tabindex="7" id="singupPw"></label><br><output id="outPw"></output><br>
        <label>비밀번호 확인&nbsp;<input type="password" name="pwCheck" placeholder="비밀번호를 입력하세요"  tabindex="8" id="singupPwCheck"></label><br><output id="outPwCheck"></output><br>
        <label>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" placeholder="이름을 입력하세요"  tabindex="9" id="singupName"></label><br><output id="outNameCheck"></output><br>
       <!--  <label>이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="emailId" placeholder="이메일을 입력하세요" tabindex="10" id="singupEmail"></label><br><output id="outEmail"></output><br>
        <label>전화번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tel1" placeholder="전화번호를 입력하세요" tabindex="11" id="singupTel"></label><br><output id="outTel"></output><br> -->
        <!-- <label>성별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
          <label>남<input type="radio" name="gender" tabindex="11" id="singupGender"></label>
          <label>여<input type="radio" name="gender" tabindex="12" id="singupGender"></label><br>
        <label>관심태그</label><br>
        <label>시뮬레이션<input type="checkbox" name="simulation" tabindex="13" id="singuptag"></label>
        <label>기지건설<input type="checkbox" name="construction" tabindex="14" id="singuptag"></label>
        <label>생존<input type="checkbox" name="survival" tabindex="15" id="singuptag"></label>
        <label>자원관리<input type="checkbox" name="res" tabindex="16" id="singuptag"></label>
        <label>디펜스<input type="checkbox" name="defense" tabindex="17" id="singuptag"></label> -->
        <hr>
        <output id="formCheck"></output><br>
        <button type="button" tabindex="10" id="signupBtn">계속하기</button>
      </form>
    </main>
    <p class="members"></p>
  </div>
  <%@include file="../common/footer.jsp" %>
<script>

let memberPath = "${pageContext.request.contextPath}/memberselect";
let frm = document.getElementById("signupFrm");
$("#singupId").blur(function() {

	showList();
	let id = /^[a-z]+[a-z0-9]{3,11}$/g;
	
	if (!id.test(frm.singupId.value)){
		frm.outId.innerHTML = "아이디는 4글자 이상 12글자 이하 입니다.";
	} else {
		frm.outId.innerHTML = " ";
	}
});

$("#signupBtn").click(function() {
	let frm = document.getElementById("signupFrm");
	if (frm.singupId.value.length == 0 || frm.singupPw.value.length == 0 || frm.singupPwCheck.value.length == 0 || frm.singupPwCheck.value != frm.singupPw.value) {
		//|| frm.singupEmail.value.length==0 ||frm.singupTel.value.length==0 필요시 주석 해제 할 것 
		frm.formCheck.innerHTML = "회원양식과 맞지 않습니다.";
		frm.preventDefault();
		$("#signupBtn").blur(function() {
			frm.formCheck.innerHTML = "";
		})
	} else {
		frm.submit();
	}
});

function showList() {
	$.ajax({
		url: memberPath,
		method :"GET",
		success : list
	});
}
function list(members) {
	let checkid = document.getElementById("singupId").value;
	 let str = "";
     if(!members.length){
			return ;
     }
     for (let i in members) {
		let r = members[i];
		
		if(r.id === checkid){
			document.getElementById("singupId").value = "";
			alert("중복된 아이디 입니다");
		}
     }
}
</script> 
</body>
</html>


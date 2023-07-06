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
    <main class="mypage-main">
      <form method="post" id="mypageFrm">
      	
        <label>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id" placeholder="아이디를 입력하세요"  tabindex="6" id="mypageId" value="${member.id}" readonly></label><br><output id="outId"></output><br>
        <label>비밀번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pw" placeholder="비밀번호를 입력하세요"tabindex="7" id="mypagePw" ></label><br><output id="outPw"></output><br>
        <label>비밀번호 확인&nbsp;<input type="password" name="pwCheck" placeholder="비밀번호를 입력하세요"  tabindex="8" id="mypagePwCheck"></label><br><output id="outPwCheck"></output><br>
        <label>이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="name" placeholder="이름을 입력하세요"  tabindex="9" id="mypageName" value="${member.name}"></label><br><output id="outNameCheck"></output><br>
       <!--  <label>이메일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="emailId" placeholder="이메일을 입력하세요" tabindex="10" id="mypageEmail"></label><br><output id="outEmail"></output><br>
        <label>전화번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="tel1" placeholder="전화번호를 입력하세요" tabindex="11" id="mypageTel"></label><br><output id="outTel"></output><br> -->
        <!-- <label>성별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
          <label>남<input type="radio" name="gender" tabindex="11" id="mypageGender"></label>
          <label>여<input type="radio" name="gender" tabindex="12" id="mypageGender"></label><br>
        <label>관심태그</label><br>
        <label>시뮬레이션<input type="checkbox" name="simulation" tabindex="13" id="mypagetag"></label>
        <label>기지건설<input type="checkbox" name="construction" tabindex="14" id="mypagetag"></label>
        <label>생존<input type="checkbox" name="survival" tabindex="15" id="mypagetag"></label>
        <label>자원관리<input type="checkbox" name="res" tabindex="16" id="mypagetag"></label>
        <label>디펜스<input type="checkbox" name="defense" tabindex="17" id="mypagetag"></label> -->
        <hr>
        <output id="formCheck"></output><br>
        <div><button type="button" tabindex="10" id="mypageBtn">계속하기</button></div>
        <div><button type="button" tabindex="10" id="mypageRemoveBtn">회원탈퇴</button></div>
      </form>
    </main>
  </div>
  <%@include file="../common/footer.jsp" %> 
</body>
</html>
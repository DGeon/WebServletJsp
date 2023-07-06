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
    <main class="signin-main">
      <div>
        <form id="signinFrm" method="post">
          <input type="text" placeholder="아이디를 입력하세요" name="id" tabindex="6" id="signinId"><br>
          <input type="password" placeholder="비밀번호를 입력하세요" name="pw"tabindex="7" id="signinPw"><br>
          <output id="signinOutput"></output>
      </form>
      </div>
      <!-- 로그인 우측 div-->
      <div>
        <p class="loginfail">${param.loginFail}</p><br>        
        <button type="button" tabindex="8" id="signinBtn">로그인</button>
      </div>
      <div>
        <span>계정이 없으신가요?</span><a href="contract" tabindex="9"> 무료 계정 만들기</a> <a href="#"> ID/PW 찾기</a>
      </div>
    </main>
  </div>
  <%@include file="../common/footer.jsp" %>
</body>
</html>
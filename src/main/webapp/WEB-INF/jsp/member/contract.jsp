<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/head.jsp"%>
</head>
<body>
	<div id="wrap">
		<%@include file="../common/header.jsp"%>
		<main class="contract-main">
			<h2>회원가입 약관</h2>
			<textarea readonly>
				<%@include file="../common/tos.txt"%>
      		</textarea>
			<br>
			<div>
			<%-- <%=request.setAttribute("agree", "chk") %> --%>
				<label><input type="radio" tabindex="6" id="agree" name="agreeChk">동의함</label><br> 				
				<label><input type="radio" tabindex="7" id="disagree" name="agreeChk">동의하지 않음</label>
			</div>
			<div>
				<button type="button" tabindex="8" id="contractSignup">가입하기</button>
			</div>
		</main>
		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>
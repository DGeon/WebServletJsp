<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header">
	<a href="${pageContext.request.contextPath}/index" class="header-logo"><img src="${pageContext.request.contextPath}/img/logo_ver4.png" tabindex="1"></a>
	<h1>Steam Sale</h1>
	<!-- 메뉴 -->
	<nav class="nav">
		<ul class="mainUl">
			<li class="home"><a href="${pageContext.request.contextPath}/index" tabindex="2">홈</a></li>
			<li data-num="4" class="board"><a href="#" tabindex="3"
				class="aboard">게시판</a>
				<ul class="dropMenu">
					<li><a href="${pageContext.request.contextPath}/board/notice" tabindex="4" class="index-notice">공지사항</a></li>
					<li><a href="${pageContext.request.contextPath}/board/free" tabindex="5" class="index-free">자유게시판</a></li>
				</ul></li>
		</ul>
	</nav>
</header>

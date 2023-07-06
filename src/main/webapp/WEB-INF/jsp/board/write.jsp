<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/head.jsp"%>
</head>
<body>
	<div id="wrap">
		<%@include file="../common/header.jsp"%>

		<form id="writeFrm" method="post">
			<main class="write-main">
				<div>
					<div class="write-top-div">
						<div>
							<label for="title">제목</label>
						</div>
						<input class="write-title" name="title" id="title"
							placeholder="제목을 입력하세요"></input><br>
						<div>
							<label for="wrier">작성자</label>
						</div>
						<input class="write-writer" name="writer" id="writer"
							value="${member.id}" readonly></input>
					</div>
					<div>
						<label for="content">내용</label>
						<textarea class="write-content" id="content" name="content"
							placeholder="내용을 입력하세요"></textarea>
					</div>

					<input type="hidden" name="pageNumber" value="${cri.pageNumber}">
					<input type="hidden" name="amount" value="${cri.amount}"> <input
						type="hidden" name="category" value="${cri.category}">
					<div class="write-btn-list">
						<button tabindex="8" id="writeSumbit" type="submit">글쓰기</button>

						<c:choose>
							<c:when test="${cri.category eq 2 }">
								<a href="notice?${cri.fullQueryStr}" tabindex="7"
									id="datailList">목록</a>
							</c:when>
							<c:when test="${cri.category eq 3 }">
								<a href="free?${cri.fullQueryStr}" tabindex="8" id="datailList">목록</a>
							</c:when>
						</c:choose>
					</div>
				</div>

			</main>
		</form>

		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>
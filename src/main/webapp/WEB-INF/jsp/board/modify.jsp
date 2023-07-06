<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../common/head.jsp"%>
</head>
<body>
  <div id="wrap">
   <%@include file="../common/header.jsp"%>



      <form method="post">
    <main class="modify-main">
      <div>
        <div class="modify-top-div">
          <div>
          <label for="title">제목</label>
          </div>
          <input class="modify-title" name="title" id="title" value="${board.title}"></input><br>
          <div>
          <label for="wrier">작성자</label>
          </div>
          <input class="modify-writer" name="writer" id="writer" value="${board.writer}" readonly></input>
        </div>
        <div>
          <label for="content">내용</label>
          <textarea class="modify-content" id="content" name="content" >${board.content}</textarea>
        </div>
          <div>
          <input type="hidden" name="bno" value="${board.bno}">
						<input type="hidden" name="pageNumber" value="${cri.pageNumber}">
						<input type="hidden" name="amount" value="${cri.amount}">
						<input type="hidden" name="category" value="${cri.category}">
						<c:if test="${not empty cri.check}">
						  <c:forEach items="${cri.check}" var="check">
						    <input type="hidden" name="check" value="${check}">
						  </c:forEach>
						  <input type="hidden" name="search" value="${cri.search}">
						</c:if>
          		<button type="submit" tabindex="6" id="modifyUpdate">수정</button>
		  <c:choose>
		  	<c:when test="${cri.category eq 2 }">
          		<a href="notice?${cri.fullQueryStr}" tabindex="7" id="datailList">목록</a>
          	</c:when>
          	<c:when test="${cri.category eq 3 }">
          	<a href="free?${cri.fullQueryStr}" tabindex="8" id="datailList">목록</a>
          	</c:when>
         </c:choose>
          	<button type="button"<%--href= "remove?bno=${board.bno}&${cri.fullQueryStr}" --%> tabindex="8" id="modify-delete">삭제</button>
        </div>
      </div>
    </main>
      </form>
<%@include file="../common/footer.jsp"%>
</div>
<script>
//게시글 삭제
	$("#modify-delete").click(function() {
		if (prompt("'삭제'를  입력해주세요") == "삭제") {
			$(location).attr("href", "remove?bno=${board.bno}&${cri.fullQueryStr}");
		}
	});
</script>
</body>
</html>
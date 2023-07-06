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
		<main class="free-main">
			<div>
				<div>
					<c:set var="amounts" value="5,10,15,20" />
					<c:set var="i" value="6"/>
					<select class="amount">
						<c:forTokens items="${amounts}" delims="," var="amount">
							<option value="${amount}"
								${page.cri.amount == amount ? 'selected':''}>${amount}개씩
								보기</option>
						</c:forTokens>
					</select>
				</div>
				<!--글머리 -->

				<input type="hidden" name="page" value="1"> <input
					type="hidden" name="amount" value="${page.cri.amount}"> <input
					type="hidden" name="category" value="${page.cri.category}">

				<div class="free-main-div">
					<span>글번호</span> <span>글제목</span> <span>작성자</span> <span>작성시간</span>
					<hr>
				</div>
				<!-- 글내용 -->
				<div class="free-main-div">

					<c:forEach var="board" items="${boards}" varStatus="stat">
						<span>${board.bno} </span>
						<span><a
							href="detail?bno=${board.bno}&${page.cri.fullQueryStr}"
							tabindex="6" style="text-align: left">${board.title}</a><span class="replyCtn">[${board.replyCnt}]</span></span>
						<span>${board.writer}</span>
						<span>${board.regDate}</span>
						<hr>
						<div class="finish ${stat.last ? '':'border-bottom' }">
					</c:forEach>
				</div>

				<ul>
					<c:if test="${page.doublePrev}">
						<li class="free-next-li"><a class="page-link"
							href="free?pageNumber=${page.startPage-1}&${page.cri.queryStr}"><i
								class="fas fa-angle-double-left"></i></a></li>
					</c:if>
					<c:if test="${page.prev}">
						<li class="free-prev-li"><a class="page-link"
							href="free?pageNumber=${page.cri.pageNumber-1}&${page.cri.queryStr}">
								<i class="fas fa-angle-left"></i>
						</a></li>
					</c:if>
					<c:forEach begin="${page.startPage}" end="${page.endPage}" var="i">
						<li class="free-page-item"><a
							class="page-link ${page.cri.pageNumber== i ? 'active' : ''}"
							href="free?pageNumber=${i}&${page.cri.queryStr}">${i}</a></li>
					</c:forEach>
					<c:if test="${page.next}">
						<li class="free-next-li"><a class="page-link"
							href="free?pageNumber=${page.cri.pageNumber+1}&${page.cri.queryStr}">
								<i class="fas fa-angle-right"></i>
						</a></li>
					</c:if>
					<c:if test="${page.doubleNext}">
						<li class="free-next-li-a"><a class="page-link "
							href="free?pageNumber=${page.endPage+1}&${page.cri.queryStr}"><i
								class="fas fa-angle-double-right"></i></a></li>
					</c:if>
				</ul>

			</div>
			<div class="free-check">
			<form>
	            <div>
	          	  <input class="free-check-input" type="checkbox" id="check1" name="check" value="title" ${fn:contains(fn:join(page.cri.check, ','), 'title') ? 'checked' : ''} >
	          	  <label for="check1">제목</label>
	          
	          	  <input class="free-check-input" type="checkbox" id="check2" name="check" value="content" ${fn:contains(fn:join(page.cri.check, ','), 'content') ? 'checked' : ''}>
	          	  <label for="check2">내용</label>
	          
	          	  <input class="free-check-input" type="checkbox" id="check3" name="check" value="writer" ${fn:contains(fn:join(page.cri.check, ','), 'writer') ? 'checked' : ''}>
	          	  <label for="check3">작성자</label>
	            </div>
	            <div class="free-search">
	              <input type="text" class="free-search" placeholder="Search" name="search" value="${page.cri.search}">
	              <button class="free-btn" type="submit"><i class="fas fa-search"></i></button>
	            </div>
		  </form>
          </div>  
          
			<div class="free-btn-div">
				<a href="${pageContext.request.contextPath}/board/write?${page.cri.getFullQueryStr()}" tabindex="20" id="boardWrite">글쓰기</a>
			</div>

		</main>
	</div>
	<%@include file="../common/footer.jsp"%>
	<script>
		$(".amount").change(function() {
			let page = "${page.cri.pageNumber}";
			let amount = $(this).val();
			let category = "${page.cri.pageNumber}";
			let check = "${page.cri.checkStr}";
			let obj = {
				pageNumber : page,
				amount : amount,
				category : category
			};
			location.search = $.param(obj) + check;
		});
		
		$(".free-check :checkbox:checked").length ? '' : $(".free-check :checkbox:eq(0)").prop("checked", true);
		$(".free-check form").submit(function (){
			if(!$(this).find(":checkbox:checked").length || !$(this).find(":text").val().trim() ){
				alert("검색 타입을 지정하고 검색어를 입력하세요");
				return false;
			}
		})
	</script>
</body>
</html>
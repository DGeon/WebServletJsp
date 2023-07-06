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



    <main class="detail-main">
      <div>
        <div class="detail-top-div">
          <div>
          <label for="title">제목</label>
          </div>
          
          <input class="detail-title" name="title" id="title" value="${board.title}" readonly></input><br>
          <div>
          <label for="wrier">작성자</label>
          </div>
          <input class="detail-writer" name="writer" id="writer" value="${board.writer}" readonly></input>
        </div>
        <div>
          <label for="content">내용</label>
          <textarea class="detail-content" id="content" name="content" readonly>${board.content}</textarea>
        </div>
          <div>
          <c:if test="${param.msg eq 1}">
          	<script>alert("권한이 없습니다.");</script>
          	</c:if>
          		<a href="modify?bno=${board.bno}&${cri.fullQueryStr}" tabindex="6" id="detailUpdate">수정</a>
		  <c:choose>
		  	<c:when test="${cri.category eq 2 }">
          		<a href="notice?${cri.fullQueryStr}" tabindex="7" id="datailList">목록</a>
          	</c:when>
          	<c:when test="${cri.category eq 3 }">
          	<a href="free?${cri.fullQueryStr}" tabindex="8" id="datailList">목록</a>
          	</c:when>
          	
         </c:choose>
        </div>

      <form method="post">
      <div class="replyDiv" id="replyArea">
						<label class="replyHead" for="commentArea">replies</label>
						<div class="replyText">
							<textarea name="commentArea" id="commentArea" placeholder="Enter comments" rows="2" ></textarea>
							<div class="comDiv">
							<button type="button" class="comBtn">댓글 작성</button>
							</div>
						</div>
						<ul class="replies">
							<li>
								<div class="replyList">
									<p>댓글이 없습니다.</p>
								</div>
							</li>
						</ul>
					</div>
					
		</form>	
      </div>		
    </main>
<%@include file="../common/footer.jsp"%>
</div>
<script>

let contextPath = "${pageContext.request.contextPath}";
let replyPath = contextPath + "/reply";
let bno = "${board.bno}";
let writer = "${member.id}";
showList();
function showList() {
$.ajax({
	url: replyPath,
	data : {bno:bno},
	success : list
});
}

function list(replies) {
    
    let str = "";
    if(!replies.length){
    	str += `<li>
			<div class="replyList">
			<p>댓글이 없습니다.</p>
			</div>
		</li>`;
		$(".replies").html(str);
		return ;
    }
    for (let i in replies) {
      let r = replies[i];
	  let isMine = writer === r.writer;
      str += `<li data-rno="\${r.rno}" data-writer="\${r.writer}">
          <div class="spanHead">
              <span class="spanWriter">\${r.writer}</span>
              <span class="spanRegdate">\${r.regDate}</span>
            <div class="replyDeleteDiv">`;
          
          str += isMine ? '<a href="" class="replyDelete"><i class="fas fa-trash-alt "></i></a>' : ''
          str += `</div>
          
          </div>
          <div>
            <p>\${r.content}</p>
          </div>
        </li><hr>`;
    }
    $(".replies").html(str);
}

$(".comBtn").click(function (){
  let content = $("#commentArea").val();
  if(!writer){
	  alert("로그인 후 작성하세요");
	  location.href=contextPath + "/member/signin?href=" + encodeURIComponent(location.pathname + location.search+"#replyArea");
	  
	  return;
  }else if(!content){
	  alert("댓글 내용을 입력하세요");
	  return;
  }
  console.log({bno:bno, content:content, writer:writer});
  $.ajax({
	  url : replyPath,
	  data : {bno:bno, content:content, writer:writer},
	  method : "POST",
	  success : function(data){
		  alert("댓글이 성공적으로 작성되었습니다.");
		  $("#commentArea").val("");
		  showList();
	  }
  })
  
})
//댓글 삭제(세션 체크해야됨)
$(".replies").on("click",".replyDelete", function() {
  event.preventDefault();
  if(!confirm("댓글을 삭제하시겠습니까?")){
	  return false;
  }
  let rno = $(this).closest("li").data("rno");
  let rid = $(this).closest("li").data("writer");
  let member = '<%=session.getAttribute("member")%>';
  $.ajax({
	  url : replyPath + "?rno=" + rno,
	  data : {rno:rno}, //문자열 타입
	  method : "DELETE",
	  success : function(data){
		  if(data==1){
    		  showList();
		  }
	  }
  })
})
</script>
</body>
</html>
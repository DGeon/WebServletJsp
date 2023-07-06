<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@include file="common/head.jsp" %>
  
</head>
<body>
  <div id="wrap">
  <%@include file="common/header.jsp" %>    
    <main class="index-main">
      <!-- 배너 -->
      <div>
        <section class="index-section" tabindex="-1">
          <div class="slider">
            <img class="sliderimg" src="img/slider/againt_the_storm.JPG">
            <img class="sliderimg" src="img/slider/dota2.JPG">
            <img class="sliderimg" src="img/slider/farthest_frontier.JPG">
            <img class="sliderimg" src="img/slider/garry_mod.JPG">
            <img class="sliderimg" src="img/slider/going_medieval.JPG">
            <img class="sliderimg" src="img/slider/no_mans_sky.JPG">
            <img class="sliderimg" src="img/slider/noita.JPG">
            <img class="sliderimg" src="img/slider/northgard.JPG">
            <img class="sliderimg" src="img/slider/plan_b.JPG">
            <img class="sliderimg" src="img/slider/stardeus.JPG">
            <img class="sliderimg" src="img/slider/stray.JPG">
          </div>
        </section>
    
    
        <aside class="index-aside">
          <!-- 로그인 아이디 좌측 div -->
          <div>
          <% if(session.getAttribute("member")==null) {%>
            <form method="post" id="index-form">
              <input type="text" placeholder="아이디를 입력하세요" tabindex="6" name="id" id="indexId"><br>
              <input type="password" placeholder="비밀번호를 입력하세요" name="pw" id="indexPw"tabindex="7">
              
            </form>
          </div>
          <!-- 로그인 우측 div-->
          <div>
              <button class="index-login-btn" type="button" tabindex="8">로그인</button>
              <p class="loginfail">${param.loginFail}</p>
          </div>
          <div>
            <span>계정이 없으신가요?</span><a href="member/contract" tabindex="9"> 무료 계정 만들기</a>
            <br>
            <a href="#"> ID/PW 찾기</a>
            <% }else{%>
            	<div class="index-login-sucess">
            		<div>
            		<span>${member.name}님</span> <span>환영합니다</span>
            		</div>
            		<div class="index-login-in2">
            		<a href="member/myPage"><div>마이페이지</div></a><a href="member/logout"><div>로그아웃</div></a>
            		</div>            		
            		<p>
            	</div>
            <%} %>
          </div>
        </aside>
      </div>
      
      <h3>세일기간</h3>
      <div>
        <div class="sale-list">
          <span>여름 세일</span>
          
          <span>할로윈 세일</span>
          <span>가을 세일</span>
          <span>겨울 세일</span>
        </div>
        <div class="sale-info">
          <span>6~7월 중. 여름휴가 시즌 14일</span><br>
          <span>10월 말. 할로윈과 관련되거나 호러 게임 소규모 할인 5일</span><br>
          <span>11월 말. 블랙 프라이데이 시즌 14일</span><br>
          <span>12월 말~1월 초. 성탄절과 신정 세일 14일</span>
        </div>
      </div>
          
      <h3>추천 하기</h3>
      <div class="recommand">
        <div class="card">
          <a href="https://store.steampowered.com/app/1127400/Mindustry/" tabindex="10">
            <div>
            <img src="img/card/mindustry.JPG" >
            </div>
            <div >
              <div>10,500</div>
              <div>0%</div>
              <h4>Mindustry</h4>
              <hr>
              <span>타워디펜스</span><br>
              <span>생존</span><br>
              <span>기지건설</span><br>
              <span>자원관리</span> 
            </div>
          </a>
        </div>
          <div class="card">
            <a href="https://store.steampowered.com/app/1700870/Clanfolk/" tabindex="11">
              <div>
                <img src="img/card/clanfolk.JPG">
              </div>
              <div>
                <div>19,900</div>
                <div>0%</div>
                <h4>Clanfolk</h4>
                <hr>
                <span>전략</span><br>
                <span>시뮬레이션</span><br>
                <span>기지건설</span><br>
                <span>자원관리</span> 
              </div>
            </a>
          </div>

          <div class="card">
            <a href="https://store.steampowered.com/app/1062090/Timberborn/" tabindex="12">
              <div>
                <img src="img/card/timberborn.JPG">
              </div>
              <div>
                <div>27,000</div>
                <div>0%</div>
                <h4>Timberborn</h4>
                <hr>
                <span>앞서 해보기</span><br>
                <span>도시건설</span><br>
                <span>개척 시뮬레이션</span><br>
                <span>생존</span> 
              </div>
            </a>
          </div>
    
        </div>

        <h3>공지사항</h3>
        <div class="notice">
          <a href="https://store.steampowered.com/category/mystery_detective" tabindex="13">
          <div></div>
          </a>
          <!-- <img src="img/mainbanner.JPG"> -->
          <!-- <div><img src="img/wintersale.JPG"></div> -->
          <div></div>
        </div>
    </main>
<%@include file="common/footer.jsp" %>
</div>
</body>
</html>
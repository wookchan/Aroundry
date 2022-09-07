<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <!-- Custom CSS -->
  <link href=" ${pageContext.request.contextPath}/resources/css/index.css?<%=new java.util.Date()%>'" rel='stylesheet'/>
<title>한울빨래방</title>
</head>
<body>
 
  <section class="intro">
    <div class="element pencil"></div>
    <div class="element pencilcase"></div>
    <div class="element note"></div>
    <div class="element headphone"></div>
    <div class="inner_wrap">
      <div class="cts">
      ${loginInfo.ownerid}
        <h1>빨래방 공간 예약 앱 한울빨래방</h1>
        <h2>빨래방, 편의시설 다 모았다!</h2>
        <div class="logo_title"></div>
        <div class="download_group">
          <button class="googleplay second">
          	<img src='img/dwbtn_googleplay2.png' onclick='window.open("https://play.google.com/store/apps/details?id=com.studymoa_app")'>
          </button>
        </div>
      </div>
    </div>
  </section>
  <section class="first">
    <div class="element"></div>
    <div class="inner_wrap">
      <div class="cts">
        <h1>내 주변 빨래 공간을 한번에</h1>
        <h2>언제 어디서든 빨래 타이밍!</h2>
        <download_label>
          <div>
            <p>한울빨래방<br>
              앱다운로드</p>
            <div class="download_group">
              <button class="googleplay"
                onclick="window.open('https://play.google.com/store/apps/details?id=com.studymoa_app')"></button>
            </div>
          </div>
        </download_label>
      </div>
    </div>
  </section>
  <section class="second">
    <div class="element"></div>
    <div class="inner_wrap">
      <div class="cts">
        <h1>가기 전에 미리 확인</h1>
        <h2>내가 항상 쓰던 그 공간 오늘은 비었을까?</h2>
        <download_label>
          <div>
            <p>한울빨래방<br>
              앱다운로드</p>
            <div class="download_group">
              <button class="googleplay"
                onclick="window.open('https://play.google.com/store/apps/details?id=com.studymoa_app')"></button>
            </div>
          </div>
        </download_label>
      </div>
    </div>
    <a class="scrol_down"><i class="arrow"></i></a>
  </section>
  <section class="third">
    <div class="inner_wrap">
      <div class="element"></div>
      <div class="cts">
        <h1>시간을 확인!</h1>
        <h2>지루한 빨래시간,<br>
          세탁물의 남은시간을 실시간으로 알 수 있어 밖에서 볼 일을 편하게!</h2>
        <download_label>
          <div>
            <p>한울빨래방<br>
              앱다운로드</p>
            <div class="download_group">
              <button class="googleplay"
                onclick="window.open('https://play.google.com/store/apps/details?id=com.studymoa_app')"></button>
            </div>
          </div>
        </download_label>
      </div>
    </div>
    <a class="scrol_down"><i class="arrow"></i></a>
  </section>

  <div class="contens">
    <div class="bottom_btn_group" style="margin-bottom:88px;">
      <button onclick="location.href='about'"><i class="home"></i><span>회사소개 바로가기</span></button>
      <button onclick="location.href='khjsolution'"><i class="solution"></i><span>솔루션소개 바로가기</span></button>
      <button
        onclick="window.open('https://kitworks.s3.ap-northeast-2.amazonaws.com/%EC%8A%A4%ED%84%B0%EB%94%94%EB%AA%A8%EC%95%84+%EC%86%8C%EA%B0%9C%EC%84%9C.pdf');"><i
          class="download"></i><span>소개서 다운로드</span></button>
    </div>
  </div>


</body></html>
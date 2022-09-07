<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 통합시 지워야함  -->
  <!-- Custom CSS -->
  <link href=" ${pageContext.request.contextPath}/resources/css/style.css" rel='stylesheet'/>
  <link rel="stylesheet" type="text/css" href="resources/kimcss/kimcommon.css?<%=new java.util.Date()%>">
  <!-- Fonts -->
  <link href=" ${pageContext.request.contextPath}/resources/fonts/fonts_style.css" rel='stylesheet'/>
<!-- 지울예정  -->
<title>한울빨래방 - 입점안내</title>
</head>
<body>

<div class="sub_wrap">
    <h1 class="sub_title">입점안내</h1>
    <div class="contens">
      <div class="inbox">
        <div class="guide_banner">
          <div class="image"></div>
          <div class="title_wrap">
            <div class="coment">
              <p>AROUNDRY </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="contens">
      <div class="contens left">
        <div class="image"><img src="${pageContext.request.contextPath}/resources/images/addimg_price.png"></div>
      </div>
      <div class="contens right">
        <div class="inbox"> <i class="price"></i>
          <h2>PRICE</h2>
          <h3>서비스 요금</h3>
          <p class="price">POINT 결제</p>
          <p>간편한 포인트 결제로 키오스크도 필요없고 간편한 결제시스템.</p>
          <p class="price">부분 기능</p>
          <p>세탁 시간 확인 및 매출 확인 현재 이용중인 세탁기 확인기능을 하나의 앱과 웹으로 사용하실 수 있습니다. </p>
          <p class="price">일괄납부</p>
          <p>한번만 결제하고 계속 이용하고 싶으신가요? 문의주시면 상담 후, 합리적인 가격으로 제공해드리겠습니다.</p>
        </div>
      </div>
    </div>
    <div class="contens">
      <div class="inbox"> <i class="process"></i>
        <h2>PROCESS</h2>
        <h3>입점절차</h3>
        <p>함께 윈-윈 할 수 있다면 어떤 제안도 환영합니다. 저희의 기술력과 창업/운영 노하우는 언제든 준비 되어 있습니다.</p>
        <div class="process_wrap">
          <div class="frame"></div>
          <div class="images"><img src="${pageContext.request.contextPath}/resources/images/addimg_process_01.png"></div>
          <div class="text">
            <div class="inset">
              <p class="numbers">01</p>
              <p class="title">계약</p>
              <p>계약서를 작성하고, 계약금을 지급합니다.<br>
                이때, 궁금한점은 모두 알려 드릴께요.</p>
            </div>
          </div>
        </div>
        <div class="process_wrap">
          <div class="frame"></div>
          <div class="images"><img src="${pageContext.request.contextPath}/resources/images/addimg_process_02.png"></div>
          <div class="text">
            <div class="inset">
              <p class="numbers">02</p>
              <p class="title">세팅</p>
              <p>몇 가지 내용만 준비해주세요.<br>배치도부터 상품까지 운영에 필요한 모두를 세팅해드립니다.</p>
            </div>
          </div>
        </div>
        <div class="process_wrap">
          <div class="frame"></div>
          <div class="images"><img src="${pageContext.request.contextPath}/resources/images/addimg_process_03.png"></div>
          <div class="text">
            <div class="inset">
              <p class="numbers">03</p>
              <p class="title">교육</p>
              <p>운영 현황에 맞게 자유롭게 운영할 수 있도록<br>APP과 관리자페이지 사용방법을 쉽게 알려드립니다.</p>
            </div>
          </div>
        </div>
        <div class="process_wrap">
          <div class="frame"></div>
          <div class="images"><img src="${pageContext.request.contextPath}/resources/images/addimg_process_04.png"></div>
          <div class="text">
            <div class="inset">
              <p class="numbers">04</p>
              <p class="title">오픈</p>
              <p>매월의 첫 평일이 되면 지정한 계좌로 정산금을 입금해드립니다.<br>정산내역만 승인해주세요.</p>
            </div>
          </div>
        </div>
        <div class="process_wrap">
          <div class="frame"></div>
          <div class="images"><img src="${pageContext.request.contextPath}/resources/images/addimg_process_05.png"></div>
          <div class="text">
            <div class="inset">
              <p class="numbers">05</p>
              <p class="title">A/S</p>
              <p>궁금한점이나 필요한 점이 생기면 언제든 문의주세요.<br>친절하고 빠르게 대응해드립니다.</p>
            </div>
          </div>
        </div>
      </div>
    </div>
   <!--  <div class="contens">
      <div class="bottom_btn_group">
        <button class="next modal-button" href="#myModal"><i class="contact"></i><span>문의하기</span></button>
      </div>
    </div> -->
  </div>


</body>

<!-- link_menu JS -->
<script type="text/javascript" async="" src="js/link_menu.js"></script>
<!-- Menu Scroll JS -->
<script type="text/javascript" async="" src="js/menu_scroll.js"></script>
<!-- Carousel Modal JS -->
<script type="text/javascript" async="" src="js/modal.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="js/footer.js"></script>
</html>
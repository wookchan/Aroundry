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
    <link rel="shortcut icon" type="image/x-icon" href="resources/images/logo.ico" />
<!-- 지울예정  -->
<title>AROUNDRY - 회사소개</title>
</head>
<body>
 
  <div class="sub_wrap">
    <h1 class="sub_title">회사소개</h1>
    <div class="contens">
      <div class="contens left">
        <div class="image"><img src="${pageContext.request.contextPath}/resources/images/addimg_pencilandbook.png"></div>
      </div>
      <div class="contens right">
        <div class="inbox">
          <i class="company"></i>
          <h2>COMPANY</h2>
          <h3>RAINOK</h3>
          <p>세탁을 기다릴때 보내던 지루한 시간을 현명하게 소비를 해보자는 생각을 시작으로 이 기획을 시작하게 되었다 </p>
        </div>
      </div>
    </div>
    <div class="contens">
      <div class="contens left order">
        <div class="inbox">
          <i class="ceo"></i>
          <h2>GREETING</h2>
          <h3>대표인사말</h3>
          <p></p>
          <div class="profile">
            <h1>주요 프로필</h1>
            <ul>          
              <li>대표이력 : AROUNDRY프로젝트 (2022.6 ~ 2022.9)</li>
            </ul>
          </div>
          <div class="signature">
            <p class="name"><small>(주)AROUNDRY 대표</small>강민재 </p>
          </div>
        </div>
      </div>
      <div class="contens right order">
        <div class="image"><img src="${pageContext.request.contextPath}/resources/images/addimg_ceos.png"></div>
      </div>
    </div>
    <div class="contens">
      <div class="inbox">
        <i class="history"></i>
        <h2>HISTORY</h2>
        <h3>연혁</h3>
        <p></p>
        <div class="table_wrap">
          <table>
            <tbody>
              <tr>
                <td class="bgtitle" rowspan="1">2022</td>
                <td class="bgtitlem">6<small>월</small></td>
                <td class="">
                  <ul>
                    <li>프로젝트 시작</li>
                  </ul>
                </td>
              </tr>
              <tr>
                <td class="bgtitle" rowspan="1">2022</td>
                <td class="bgtitlem">7<small>월</small></td>
                <td class="">
                  <ul>
                    <li>안드로이드 제작</li>
                  </ul>
                </td>
              </tr>
              <tr>
                <td class="bgtitle" rowspan="1">2022</td>
                <td class="bgtitlem">8<small>월</small></td>
                <td class="">
                  <ul>
                    <li>웹 제작, iot 제작</li>
                  </ul>
                </td>
              </tr>
               <tr>
                <td class="bgtitle" rowspan="1">2022</td>
                <td class="bgtitlem">9<small>월</small></td>
                <td class="">
                  <ul>
                    <li>프로젝트 완료</li>
                  </ul>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div class="contens">
      <div class="inbox">
        <i class="location"></i>
        <h2>LOCATION</h2>
        <h3>오시는길</h3>
        <div class="map_wrap">
          <div class="top"></div>
          <div class="bottom">
            <div class="ico_pin"></div>
            <div class="location">
              <div class="address">광주 서구 경열로 3</div>
              <div class="old">
                <label>지번</label>
               농성동 271-4 (우)61928
              </div>
            </div>
            <div class="tel">
              <div class="number"><i class="phone"></i>010-6833-0769</div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="contens">
      <div class="bottom_btn_group">
        <button onclick="location.href='khjsolution.jsp'"><i class="solution"></i><span>솔루션소개 바로가기</span></button>
<!--    <button onclick="location.href='solution.jsp'"><i class="solution"></i><span>솔루션소개 바로가기</span></button> -->
     	
      </div>
    </div>
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
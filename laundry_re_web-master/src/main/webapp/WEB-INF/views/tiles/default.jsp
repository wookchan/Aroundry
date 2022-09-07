<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:choose>
	<c:when test="${category eq 'about'}"><c:set var="title" value="- 회사소개"/></c:when>
	<c:when test="${category eq 'khjsolution'}"><c:set var="title" value="- 솔루션소개"/></c:when>
	<c:when test="${category eq 'partner'}"><c:set var="title" value="- 입점안내"/></c:when>
	<c:when test="${category eq 'join'}"><c:set var="title" value="- 관리자 회원가입"/></c:when>
	<c:when test="${category eq 'error'}"><c:set var="title" value="- 오류"/></c:when>
</c:choose>

<title>AROUNDRY ${title}</title>
<link rel="shortcut icon" type="image/x-icon" href="resources/images/logo.ico" />
<script type="text/javascript" 
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='js/common.js?<%=new java.util.Date()%>'></script>
<!-- link_menu JS -->
<script type="text/javascript" async="" src="js/link_menu.js"></script>
<!-- Menu Scroll JS -->
<script type="text/javascript" async="" src="js/menu_scroll.js"></script>
<!-- Carousel Modal JS -->
<script type="text/javascript" async="" src="js/modal.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="js/footer.js"></script>
</head>
<body>
<tiles:insertAttribute name='container'/>

<!-- Modal myModal -->
  <div id="myModal" class="modal">
    <div class="modal-content input">
      <div class="modal-header"> <span class="close">×</span>
      <h2>문의하기</h2>
      </div>
      <div class="modal-body" style="padding:0; overflow:hidden;">
        <iframe src="//studymoa.me/partnership/?"></iframe>
      </div>
    </div>
  </div>
</body>
</html>
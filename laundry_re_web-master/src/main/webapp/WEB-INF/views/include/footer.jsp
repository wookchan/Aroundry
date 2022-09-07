<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
     <!-- Bootstrap Core CSS -->
    <link href="resources/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    <!-- This page CSS -->
    <!-- chartist CSS -->
    <link href="resources/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <!--c3 CSS -->
    <link href="resources/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="resources/khj/css/style.css" rel="stylesheet">
    <!-- Dashboard 1 Page CSS -->
    <link href="resources/khj/css/pages/dashboard1.css" rel="stylesheet">
    <!-- You can change the theme colors from here -->
    <link href="resources/khj/css/colors/default.css" id="theme" rel="stylesheet">
    
<footer>
	<div class='inner_wrap'>
		<logo>
			
			<span class='mongddang'><img src="resources/assets/images/logo-icon1.png"/><a href="index" style="
    text-decoration: none;
    color: white;
">AROUNDRY</a></span>
		</logo>
		<info>
			<ul>
				<li>aroundry</li>
				<li>광주 서구 경열로 3 2층</li>
				<li>사업자번호 : 123-45-67890</li>
				<li>통신판매번호 : 제2022-광주서구-0001호</li>
				<li>대표메일 : project334@naver.com</li>
			</ul>
			<ul class='social_group'>
				<li>
					<a href='https://www.facebook.com/studymoa1' target='_blank'>
						<i class='facebook'></i>
					</a>
				</li>
				<li>
					<a href='https://www.instagram.com/' target='_blank'>
						<i class='instagram'></i>
					</a>
				</li>
				<li>
					 <a href='https://www.youtube.com/' target='_blank'>
						<i class='youtube'></i>
					</a>
				</li>
				<li class='boss'>
					<a href='${empty loginInfo? "login":"detail.cu"}'>
					
						<i class='ceo'></i>
						<span>관리자</span>
					</a>
				</li>
				<li class='kakao' id='kakao-chat-btn'>
					<a href='https://pf.kakao.com/_xgxoHExj' target='_blank'>
						<img src="https://developers.kakao.com/assets/img/about/logos/channel/consult_small_yellow_pc.png" title='카카오톡 채널 1:1 채팅 버튼' alt='카카오톡 채널 1:1 채팅버튼'>
					</a>
				</li>
			</ul>
		</info>
	</div>
</footer>

<!-- All Jquery -->
    <!-- ============================================================== -->
    <script src="resources/assets/node_modules/jquery/jquery.min.js"></script>
    <!-- Bootstrap popper Core JavaScript -->
    <script src="resources/assets/node_modules/bootstrap/js/popper.min.js"></script>
    <script src="resources/assets/node_modules/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="resources/khj/js/perfect-scrollbar.jquery.min.js"></script>
    <!--Wave Effects -->
    <script src="resources/khj/js/waves.js"></script>
    <!--Menu sidebar -->
    <script src="resources/khj/js/sidebarmenu.js"></script>
    <!--Custom JavaScript -->
    <script src="resources/khj/js/custom.min.js"></script>
    <!-- ============================================================== -->
    <!-- This page plugins -->
    <!-- ============================================================== -->
    <!--morris JavaScript -->
    <script src="resources/assets/node_modules/raphael/raphael-min.js"></script>
    <script src="resources/assets/node_modules/morrisjs/morris.min.js"></script>
    <!--c3 JavaScript -->
    <script src="resources/assets/node_modules/d3/d3.min.js"></script>
    <script src="resources/assets/node_modules/c3-master/c3.min.js"></script>
    <!-- Chart JS -->
    <script src="resources/khj/js/das<input type="hidden" name="storeid" value="29">hboard1.js"></script>
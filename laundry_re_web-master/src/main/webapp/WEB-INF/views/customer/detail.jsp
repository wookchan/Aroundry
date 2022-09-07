<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="resources/assets/images/favicon.png">
    <title>AROUNDRY</title>
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
    
    <!-- carousel css -->
    <link rel="stylesheet" type="text/css" href="resources/kimcss/kimcommon.css?<%=new java.util.Date()%>">
	<link rel="stylesheet" type="text/css" href="resources/kimfonts/fonts_style.css">
	<link rel="shortcut icon" type="image/x-icon" href="resources/kimimages/favicon.png" />
    
    
    <!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  	
  	<!-- font -->
  	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">    
	
	<!-- alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
	<style>
    .carousel-inner > .carousel-item > img{
      /* width: 640px;
      height: 720px; */
    }
     body {
    	/* font-family: 'Sunflower', sans-serif; */
    	/* font-size: 0rem; */
    } 
    a{
    	text-decoration: none;
    }

        #navbar{
    	display: none;
    	
    }
    

</style>




<script>
window.onload = function () {
	
	var datas = [];
	
var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	animationEnabled: true,

	axisX: {
		interval: 1,
		intervalType: "month",
		valueFormatString: "MMM"
	},
	axisY:{
		title: "",
		includeZero: true,

	},
	data: [{        
		type: "line",
		markerSize: 12,
		xValueFormatString: "MMM",
		yValueFormatString: "#원",
		
		dataPoints: datas
// 			[        
// 			{ x: new Date(2022, 00, 1), y: 61, label: "1월", markerType: "triangle",  markerColor: "#6B8E23" },
// 			{ x: new Date(2022, 01, 1), y: 71, label: "2월", markerType: "triangle",  markerColor: "#6B8E23" },
// 			{ x: new Date(2022, 02, 1) , y: 55, label: "3월", markerType: "triangle", markerColor: "6B8E23" },
// 			{ x: new Date(2022, 03, 1) , y: 50, label: "4월", markerType: "triangle", markerColor: "6B8E23" },
// 			{ x: new Date(2022, 04, 1) , y: 65, label: "5월", markerType: "triangle", markerColor: "#6B8E23" },
// 			{ 'x': '05' , 'y': 24, label: "5월", 'markerType': "triangle", 'markerColor': "6B8E23" },
// 			{ x: '10' , y: 15000, label: "10월", indexLabel: '15000', markerType: "triangle", markerColor: "#6B8E23" },
// 			{ x: '08' , y: 45000, label: "8월", indexLabel: '45000', markerType: "triangle", markerColor: "6B8E23" },
// 			{ x: '07' , y: 80000, label: "7월", indexLabel: '80000', markerType: "triangle", markerColor: "6B8E23" },
// 			{ x: new Date(2022, 08, 1) , y: 34, label: "9월", markerType: "triangle", markerColor: "#6B8E23" },
// 			{ x: new Date(2022, 10, 1) , y: 44, label: "11월", markerType: "triangle", markerColor: "#6B8E23" },
// 			{ x: new Date(2022, 11, 1) , y: 34, label: "12월", markerType: "triangle", markerColor: "6B8E23" }
// 		]
	}]
});

var today=new Date();
today = today.getFullYear();
<c:forEach items='${graph}' var ='g'>
	datas.push({
		x : new Date( today, ${g.MONTH}, 1 ),
		y : ${g.COST},
		label: '${g.MONTH}월',
		markerType: "triangle",
		markerColor: "#6B8E23",
		indexLabel: '${g.COST}',
	});	
</c:forEach>


chart.render();

}

</script>

</head>

<body class="fix-header fix-sidebar card-no-border">
   
    <div class="preloader">
        <div class="loader">
            <div class="loader__figure"></div>
            <p class="loader__label">AROUNDRY</p>
        </div>
    </div>
    
    <!-- Main wrapper - style you can find in pages.scss -->
    
    <div id="main-wrapper">
    
        <!-- Topbar header - style you can find in pages.scss -->
    
        <header class="topbar">
            <nav class="navbar top-navbar navbar-expand-md navbar-light">
           
               <!--  Logo -->
           
                <div class="navbar-header">
                    <a class="navbar-brand" href=detail.cu>
                        <!-- Logo icon --><b>
                            <!-- You can put here icon as well<i class="wi wi-sunset"></i>--> 
                            <!-- Dark Logo icon -->
                            <img src="resources/assets/images/logo-icon1.png" alt="homepage" class="dark-logo" />                         
                        </b>
                        <!--End Logo icon -->
                        <!-- Logo text --><span>
                         <!-- dark Logo text -->
                         <img src="resources/assets/images/logo-text.png" alt="homepage" class="dark-logo" />
                        </span></a>
                </div>
                
                <!-- End Logo -->
                
                <div class="navbar-collapse">
                    <!--  ============================================================== -->
                   <!--  toggle and nav items -->
                    <!--  ============================================================== -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up waves-effect waves-dark" href="javascript:void(0)"><i class="fa fa-bars"></i></a> </li>
                        <!--  ============================================================== -->
                        <!-- Search -->
                        <!--  ============================================================== -->
                       <form class="container-fluid justify-content-start">
                       <input type='hidden' name='storeid' value='0' id='storeid'>
                       <c:forEach items="${store}" var="info" varStatus='s'>
                       <button class="btn btn-outline-success me-2" onclick="$('#storeid').val(${info.storeid });  $('form').submit()" type="button">${info.location}</button>
                    	</c:forEach>
                    	</form>
                    </ul>
                    
                    <!-- User profile and search -->
                    
                    <ul class="navbar-nav my-lg-0">
                       <!--  ============================================================== -->
                        <!-- Profile -->
                        <!-- ============================================================== -->
                         <li class="nav-item dropdown u-pro">
                            
                            <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="${vo.profile}"  class="" /> <span class="hidden-md-down">${info.location}&nbsp;</span> </a>
               				
                        </li> 
                    </ul>
                </div>
            </nav>
        </header>
        <!-- ============================================================== -->
        <!-- End Topbar header -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <aside class="left-sidebar">
            <!-- Sidebar scroll -->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation -->
                <nav class="sidebar-nav">								
                    <ul id="sidebarnav">
                    	
                        <li><a class="waves-effect waves-dark" href="store?storeid=${info.storeid}" aria-expanded="false"><img src="resources/assets/images/iconplus.png"/><span class="hide-menu">매장추가</span></a>
                        </li>								
                        <li> <a class="waves-effect waves-dark" href="storeinfo?storeid=${info.storeid}" aria-expanded="false"><img src="resources/assets/images/iconmodify.png"/><span class="hide-menu">매장수정</span></a>
                        </li>										
                        <li> <a class="waves-effect waves-dark" 
                        
                        onclick="if( confirm('정말 삭제하시겠습니까?') ) href='delete?storeid=${info.storeid}'" 
                        
                        aria-expanded="false"><img src="resources/assets/images/iconsdelete.png"/><span class="hide-menu">매장삭제</span></a>
                        </li>
                         <li> <a class="waves-effect waves-dark" id='kakao-chat-btn' href='https://pf.kakao.com/_xgxoHExj' target='_blank' aria-expanded="false"><img src="resources/images/kakaotalk.png" title='카카오톡 채널 1:1 채팅 버튼' alt='카카오톡 채널 1:1 채팅버튼'><span class="hide-menu">카톡상담</span></a>
                        </li>
<!--                         <li class="waves-effect waves-dark, kakao" id='kakao-chat-btn'>
							<a href='https://pf.kakao.com/_xgxoHExj' target='_blank'>
							<img src="resources/images/kakaotalk.png" title='카카오톡 채널 1:1 채팅 버튼' alt='카카오톡 채널 1:1 채팅버튼'>
							</a>
				</li> -->
                        <!-- <li> <a class="waves-effect waves-dark" href="profile" aria-expanded="false"><img src="resources/assets/images/iconperson.png"/><span class="hide-menu">프로필</span></a>
                        </li> -->
                        
                  
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll -->
        </aside>
        <!-- ============================================================== -->
        <!-- End Left Sidebar - style you can find in sidebar.scss  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- Page wrapper  -->
        <!-- ============================================================== -->
        <div class="page-wrapper">
            <!-- ============================================================== -->
            <!-- Container fluid  -->
            <!-- ============================================================== -->
            <div class="container-fluid">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">매출차트</h3>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Sales Chart and browser state-->
                <!-- ============================================================== -->
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex no-block">
                                    <div>
                                        <h5 class="card-title m-b-0">월간매출</h5>
                                    </div>
                                  <!--   <div class="ml-auto">
                                        <ul class="list-inline text-center font-12">
                                            <li><i class="fa fa-circle text-success"></i> SITE A</li>
                                            <li><i class="fa fa-circle text-info"></i> SITE B</li>
                                            <li><i class="fa fa-circle text-primary"></i> SITE C</li>
                                        </ul>
                                    </div> -->
                                </div>
                                            
                                <div class="" id="chartContainer" style="height: 413px; width: 100%; "></div>
                            </div>
                        </div>
                    </div>
                    
                  <!-- Column -->  
                      <div class="col-lg-4">
                        <div class="card">                        	                            
                            <div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
							  <div class="carousel-inner" >
							    <div class="carousel-item active" >
							      <div class="up-img" style="background-image:url(resources/kimimages/downy.png)"></div>							      
                              		<div class="card-body">                       	
		                               <div><h5 class=" card-title">다우니 초고농축 섬유유연제 50L</h5></div>
		                               <span class="label label-info label-rounded">내일도착</span>
		                               <p class="m-b-0 m-t-20">가격: 300,000원</p>
                            		</div>							      
							    </div>
							    <div class="carousel-item">
							       <div class="up-img" style="background-image:url(resources/kimimages/downy123.png)"></div>
							       	<div class="card-body">                       	
		                               <div><h5 class=" card-title">다우니 초고농축 생화향기 섬유유연제 50L</h5></div>
		                               <span class="label label-info label-rounded">내일도착</span>
		                               <p class="m-b-0 m-t-20">가격: 350,000원</p>
                            		</div>
							    </div>
							    <div class="carousel-item">
							       <div class="up-img" style="background-image:url(resources/kimimages/downy1234.png)"></div>
							       	<div class="card-body">                       	
		                               <div><h5 class=" card-title">다우니 프리미엄 세탁세제 액체형 50L</h5></div>
		                               <span class="label label-info label-rounded">내일도착</span>
		                               <p class="m-b-0 m-t-20">가격: 400,000원</p>
                            		</div>
							    </div>
							  </div>
		<!-- 					  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
							    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
							    <span class="visually-hidden">Previous</span>
							  </button>
							  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
							    <span class="carousel-control-next-icon" aria-hidden="true"></span>
							    <span class="visually-hidden">Next</span>
							  </button> -->
							</div>                           
                        </div>
                    </div>                    
                  </div>              
                </div>
                <!-- ============================================================== -->
                <!-- End Sales Chart -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Projects of the Month -->
                <!-- ============================================================== -->
                <div class="row">
                    <!-- Column -->
                    <div class="col-lg-8">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex">
                                    <div>
                                        <h5 class="card-title">이용고객</h5>
                                    </div>

                                </div>
                                <div class="table-responsive m-t-20 no-wrap" style="overflow:scroll; width:100%; height:431px;">
                                    <table class="table vm no-th-brd pro-of-month">
                                    <colgroup>                                    	
										<col width="50px">
										<col width="100px">
										<col width="150px">
										<col width="200px">
									</colgroup>                                                                       
                                            <tr><th></th>
                                            	<th>아이디</th>
                                                <th>이용날짜</th>
                                                <th>이용금액</th>                                            	
                                            </tr>                                               
                                        	<c:forEach items="${detail}" var="detail"> 
                                            <tr style="width:50px"class="active">
                                            	<td><span class="round"><img src="${detail.profile}" onerror="this.src='resources/assets/images/person.png'" class="round" width="35" /></span></td>
                                                <td><h6><a href="profile?userid=${detail.userid}&storeid=${detail.storeid}">${detail.userid}</a></h6></td>
                                                <td><fmt:formatDate value="${detail.costdate}" pattern="yy-MM-dd"/></td>
                                                <td>${detail.cost}원</td>
                                            </tr>
                                            </c:forEach>
                                   
                                    </table>
                                </div>

                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                      <div class="col-lg-4">
                        <div class="card">
                            <div class="up-img" style="background-image:url(${info.imageurl})"></div>
                            <div class="card-body">                       	
                                <div><h5 class=" card-title">${info.location}</h5></div>
                                <span class="label label-info label-rounded">${info.location}</span>
                                <p class="m-b-0 m-t-20">주소 : ${info.address}</p>
                                <div class="d-flex m-t-20">
                         
                                    <div class="ml-auto align-self-center">
                                        <a href="javascript:void(0)" class="link m-r-10"><i class="fa fa-heart-o"></i></a>
                                        <a href="javascript:void(0)" class="link m-r-10"><i class="fa fa-share-alt"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                         
                
                <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
               <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

                
<script src='https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/d3/5.16.0/d3.min.js'></script>                

                           </div>
                    </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
        <!-- ============================================================== -->
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
    
    



    
   
    
</body>

</html>
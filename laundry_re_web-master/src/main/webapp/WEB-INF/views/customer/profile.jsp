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
    <title>PROFILE</title>
    <!-- Bootstrap Core CSS -->
    <link href="resources/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="resources/khj/css/style.css" rel="stylesheet">
    <!-- chartist CSS -->
    <link href="resources/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <!--c3 CSS -->
    <link href="resources/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <!-- Dashboard 1 Page CSS -->
    <link href="resources/khj/css/pages/dashboard1.css" rel="stylesheet">    
    <!-- You can change the theme colors from here -->
    <link href="resources/khj/css/colors/default.css" id="theme" rel="stylesheet">

	<!-- 부트스트랩 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  
  	<!-- font -->
  	<!-- <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Sunflower:wght@500&display=swap" rel="stylesheet">    
 --><style>
    .carousel-inner > .carousel-item > img{
      /* width: 640px;
      height: 720px; */
    }
/*     body {
    	font-family: 'Sunflower', sans-serif;
    } */
    a{
    	text-decoration: none;
    }
    #navbar{
    	display: none;
    }
    

</style>

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
                            
                            <%-- <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                             <span class="hidden-md-down">${info.location}&nbsp;</span> </a>
               				
               			 --%>	                       
                            <a class="nav-link dropdown-toggle waves-effect waves-dark profile-pic" href="" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <img src="${vo.profile}" onerror="this.src='resources/assets/images/person.png'"  class="" /> <span class="hidden-md-down">${vo.userid} &nbsp;</span> </a>
                        
               				
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
                    	
                        <li><a class="waves-effect waves-dark" href="store" aria-expanded="false"><img src="resources/assets/images/iconplus.png"/><span class="hide-menu">매장추가</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" href="storeinfo?storeid=${info.storeid}" aria-expanded="false"><img src="resources/assets/images/iconmodify.png"/><span class="hide-menu">매장수정</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" href="delete" aria-expanded="false"><img src="resources/assets/images/iconsdelete.png"/><span class="hide-menu">매장삭제</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" id='kakao-chat-btn' href='https://pf.kakao.com/_xgxoHExj' target='_blank' aria-expanded="false"><img src="resources/images/kakaotalk.png" title='카카오톡 채널 1:1 채팅 버튼' alt='카카오톡 채널 1:1 채팅버튼'><span class="hide-menu">카톡상담</span></a>
                        </li>
                        <!-- <li> <a class="waves-effect waves-dark" href="profile" aria-expanded="false"><img src="resources/assets/images/iconperson.png"/><span class="hide-menu">프로필</span></a>
                        </li> -->
                        
                  
                    </ul>
                </nav>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll -->
        </aside>
        
        <div class="page-wrapper">
                      <div class="container-fluid">
                              <div class="row page-titles">
                    <div class="col-md-5 align-self-center">
                        <h3 class="text-themecolor">회원정보</h3>
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="detail.cu">Home</a></li>
                            <li class="breadcrumb-item active">회원정보</li>
                        </ol>
                    </div>
                </div>
                               <div class="row">
                    <!-- Column -->
                    <div class="col-lg-4 col-xlg-3 col-md-5">
                        <div class="card">
                            <div class="card-body">
                                <center class="m-t-30"><img src="${vo.profile}" onerror="this.src='resources/assets/images/person.png'" class="img-circle" width="150" />
                                    <h4 class="card-title m-t-10">${vo.userid}</h4>                                     
                                </center>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                    <!-- Column -->
                    <div class="col-lg-8 col-xlg-9 col-md-7">
                        <div class="card">
                            <!-- Tab panes -->
                            <div class="card-body">
                                <form class="form-horizontal form-material">
                                    <div class="form-group">
                                        <label class="col-md-12">이름</label>
                                        <div class="col-md-12">
                                            <input type="text" placeholder="${vo.name}" class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="example-email" class="col-md-12">Email</label>
                                        <div class="col-md-12">
                                            <input type="email" placeholder="${vo.email}" class="form-control form-control-line" name="example-email" id="example-email">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">포인트</label>
                                        <div class="col-md-12">
                                            <input type="number" placeholder="${vo.point}" class="form-control form-control-line">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-12">전화번호</label>
                                        <div class="col-md-12">
                                            <input type="text" placeholder="${vo.phone}" class="form-control form-control-line">
                                        </div>
                                    </div>
                        
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- Column -->
                </div>
                <!-- Row -->
                <!-- ============================================================== -->
                <!-- End PAge Content -->
                <!-- ============================================================== -->
            </div>
            
        </div>
        <!-- ============================================================== -->
        <!-- End Page wrapper  -->
        <!-- ============================================================== -->
    </div>
    <script src="resources/assets/node_modules/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
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
</body>

</html>
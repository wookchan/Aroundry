<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AROUNDRY-매장수정</title>
<link rel="shortcut icon" type="image/x-icon" href="resources/images/logo.ico" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css/common.css?<%=new java.util.Date()%>">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src='js/common.js?<%=new java.util.Date()%>'></script>
<script src='js/member_check.js?<%=new java.util.Date()%>'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/js/all.min.js'></script>

<!-- Bootstrap Core CSS -->
   <!--  <link href="resources/assets/node_modules/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/assets/node_modules/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet">
    --> <!-- This page CSS -->
    <!-- chartist CSS -->
    <link href="resources/assets/node_modules/morrisjs/morris.css" rel="stylesheet">
    <!--c3 CSS -->
    <link href="resources/assets/node_modules/c3-master/c3.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="resources/khj/css/styles.css" rel="stylesheet">
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
	<link href="https://fonts.googleapis.com/css2?family=Dongle&display=swap" rel="stylesheet">    

	<style>
table tr td { text-align:left; padding-left: 10px }
p { margin: 20px auto; text-align:right; color: #3367d6 }
.valid, .invalid { font-size:14px; font-weight:bold; } 
.valid { color:green }
.invalid { color:red }
[name=address]{ width:calc(100% - 22px); margin-top:3px }
.ui-datepicker table tr, .ui-datepicker table tr td { height: inherit; }
/* 달력날짜 삭제 이미지가 날짜input 태그 안에 위치하게  */
#delete { position: relative; right: 30px; }
header {
	display: none;
}	
.carousel-inner > .carousel-item > img{
      /* width: 640px;
      height: 720px; */
}
body {
    	font-family: 'Dongle', sans-serif;
}
a{
    	text-decoration: none;
}
h3{
    	padding: 80px;
}
   	.btnSet {
   		padding: 50px;
   	}
    #navbar{
    	display: none;
    }


</style>

</head>
<body>

<aside class="kjoin-left-sidebar">
            <!-- Sidebar scroll -->
                            
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
                
            <div class="scroll-sidebar">
            
                <!-- Sidebar navigation -->
                <nav class="sidebar-nav">								
                    <ul id="sidebarnav">
                    	
                        <li><a class="waves-effect waves-dark" href="store?storeid=${param.storeid}" aria-expanded="false"><img src="resources/assets/images/iconplus.png"/><span class="hide-menu">매장추가</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" href="storeinfo?storeid=${param.storeid}" aria-expanded="false"><img src="resources/assets/images/iconmodify.png"/><span class="hide-menu">매장수정</span></a>
                        </li>
                        <li> <a class="waves-effect waves-dark" 
                        
                        onclick="if( confirm('정말 삭제하시겠습니까?') ) href='delete?storeid=${param.storeid}'" 
                        
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

<h3>매장 정보</h3>
<p class='w-px500'></p>
<form action='storeupdate' method='post' enctype='multipart/form-data'>
<!-- 	<form action='store' method='post'> -->
	<table class='w-px700' style= "height: 700px;">
	<tr><th class='w-px120'>업체명</th>
		<td><input type='text' name='location' value= '${store.location }'></td>
	</tr>
	<c:set var = 'address' value='${fn:split(store.address, ",")}'/>
	<tr><th>사업장주소 </th>
		<td>
			<a class='btn-fill-s' onclick='post()'>우편번호찾기</a>

			<input type='text' name='address' readonly value='${address[0] }'>
			<input type='text' name='address' value='${address[1] }'> 
		</td>
	</tr>
	
	<tr><th>사업장사진</th>
	<c:set var = 'file' value='${fn:split(store.imageurl, ",")}'/>
		<td class='text-left' style='padding-left:10px'>
		<c:forEach var='i' begin="1" end="5">
		<div class='middle'>
			<label>
				<input type='file' name='file' class='attach-file' >
				<a><i class="font-btn fa-solid fa-file-arrow-up"></i></a>
 				<input type= 'hidden' name='filename' class='file-name' value='${file[i-1] }'>
			</label>
			<span class='preview'><c:if test="${not empty file[i-1] }"><img src='${file[i-1] }'></c:if></span>
			<a class='delete' style =" display : ${ empty file[i-1] ? 'none' : 'inline'}"><i class="font-btn fa-solid fa-trash-can"></i></a>
			</div>
		</c:forEach>
		
		</td>
	</tr>
	
	
	
	<tr><th>편의시설 유무</th>
	
		<td>
			<label><input type='checkbox'  name='f_cctv' value='1' ${store.f_cctv eq 1 ? 'checked' : ''}>CCTV</label>
			<label><input type='checkbox'  name='f_game' value='1' ${store.f_game eq 1 ? 'checked' : ''} > 오락기</label>
			<label><input type='checkbox'  name='f_toilet' value='1' ${store.f_toilet eq 1 ? 'checked' : ''}>화장실</label><br>
			<label><input type='checkbox'  name='f_concent' value='1' ${store.f_concent eq 1 ? 'checked' : ''}>콘센트</label>
			<label><input type='checkbox'  name='f_wifi' value='1' ${store.f_wifi eq 1 ? 'checked' : ''}>WIFI</label>
			<label><input type='checkbox'  name='f_coin' value='1' ${store.f_coin eq 1 ? 'checked' : ''}>코인노래방</label>
			
		</td>
		</tr>
		
		
		<tr><th>사용가격</th>
	<td><select name='cost'>
			<c:forEach var='i' begin="5" end="9">
			<option ${store.cost eq i*1000 ? 'selected' : ''}  value='${i*1000}'>${i*1000}원</option>
<!-- 			<option value='5000'>5000원</option><option  value='6000'>6000원</option><option >7000원</option><option >8000원</option><option >9000원</option> -->
			</c:forEach>
		</select>
	</td>
</tr>

	<tr><th>세탁기 갯수</th>
	<td><select name='machine'>
		<c:forEach var='i' begin="1" end="5">
		<option ${store.machine eq i ? 'selected' : ''} value='${i}'>${i}개</option>
		</c:forEach>
<!-- 			<option value='5000'>1개</option><option value='5000'>1개</option><option >3개</option><option >4개</option><option >5개</option> -->
			
		</select>
	</td>
</tr>

	<tr><th>위도,경도</th>
		<td>
			<input type='text' name='latitude' value='${store.latitude}' >
			<div class='valid'>위도를 입력하세요</div>
			<input type='text' name='longitude' value='${store.longitude}' >
			<div class='valid'>경도를 입력하세요</div>
		</td>
	</tr>

</table>
	<input type="hidden" name="ownerid" value="${loginInfo.ownerid}">
<input type="hidden" name="storeid" value="${store.storeid}"/> 
<!-- <input type="hidden" name="storeid" value=82> -->


</form>
<div class='btnSet'>
	<a class='btn-fill' onclick='kjoin()'>수정</a>
	<a class='btn-empty' onclick='history.go(-1)'>취소</a>
</div>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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

<script>




// 우편번호찾기
function post(){
    new daum.Postcode({
        oncomplete: function(data) {
        	console.log( data )
        	$('[name=post]').val( data.zonecode ); //우편번호
        	//도로명:R, 지번:J
        	var address = data.userSelectedType=='R' 
        				? data.roadAddress : data.jibunAddress;
        	if( data.buildingName != '' ) 
        		address += ' ('+data.buildingName+')';
        	$('[name=address]').eq(0).val( address );
//         	$('[name=address]:eq(0)').val( address );
        }
    }).open();	
}

//form태그 submit처리
function kjoin(){
	$('[name=f_cctv]').val( $('[name=f_cctv]:checked') ? 1 : 0 ); 
	$('[name=f_game]').val( $('[name=f_game]:checked') ? 1 : 0 ); 
	$('[name=f_toilet]').val( $('[name=f_toilet]:checked') ? 1 : 0 ); 
	$('[name=f_concent]').val( $('[name=f_concent]:checked') ? 1 : 0 ); 
	$('[name=f_wifi]').val( $('[name=f_wifi]:checked') ? 1 : 0 ); 
	$('[name=f_coin]').val( $('[name=f_coin]:checked') ? 1 : 0 );
	
	$('form').submit();
}
</script>

</body>
</html>
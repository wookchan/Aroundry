<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>로그인</title>
    <link href=" ${pageContext.request.contextPath}/resources/css/login.css?<%=new java.util.Date()%>'" rel='stylesheet'/>
  </head>
  <body>
  	<div class='sub_wrap'>
	    <div class="login">
	      <!-- <h1>로그인</h1> -->
	      <form method="post" action='chart.ch'>
	        <div class="txt_field">
	          <input type="text" id="id" class='chk' required>
	          <span></span>
	          <label for="loginid" >아이디</label>
	        </div>
	        <div class="txt_field">
	          <input type="password" id="pw" class='chk' required>
	          <span></span>
	         <label for="loginpw">비밀번호</label>
	        </div>
	        <div class="pass">비밀번호를 잊어버리셨나요?</div>
	        <input type="button" value="로그인" onclick="login()">
	        <div class="signup_link">
	           <a href="member">회원가입</a>
	        </div>
	      </form>
	    </div>
    </div>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js">
</script>
<script type="text/javascript">
$('#pw').keyup(function(e){
	if( e.keyCode==13 ) login();
});

function login(){
	if( emptyCheck() ){
		
		$.ajax({
			url: 'laundry_login',
			data: { id:$('#id').val(), pw:$('#pw').val() },
			success: function( response ){
				if( response )
					location = '<c:url value="/detail.cu"/>';
				else{
					alert('아이디나 비밀번호가 일치하지 않습니다!')
					$('#id').focus();
				}
			},error: function(req, text){
				alert( text+':'+req.status);
			}			
		});
	}	
}

</script>

  </body>

</html>

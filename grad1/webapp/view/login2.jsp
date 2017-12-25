<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>后台登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style1.css" tppabs="${pageContext.request.contextPath}/css/style1.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="http://www.jq22.com/jquery/1.11.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/verificationNumbers.js" tppabs="${pageContext.request.contextPath}/js/verificationNumbers.js"></script>
<script src="${pageContext.request.contextPath}/js/Particleground.js" tppabs="${pageContext.request.contextPath}/js/Particleground.js"></script>

<script src="${jsPath }/login.js"></script>
<script type="text/javascript">
$(document).ready(function() {
  
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
 
  createCode();
  
  /* $(".submit_btn").click(function(){
	  location.href="http://localhost:8080/grad/home/toLoginUI.do";
	  }); */
});
function keyLogin(){
	if (event.keyCode==13){
		login();
	}
}

</script>
</head>
<body  class="login" onkeydown="keyLogin()">
<dl class="admin_login">
 <dt>
  <strong>小区物业管理系统</strong>
  <em>Residential property management system</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="acc"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" id="pwd"/>
 </dd>
 
 <dd>
  <input type="button" value="立即登录" class="submit_btn" onclick="login()"/>
 </dd>
 <dd>
 </dd>
</dl>
</body>
</html>

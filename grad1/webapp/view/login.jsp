<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css">
<link rel="stylesheet" href="${cssPath }/style.css" />
<link rel="stylesheet" href="${cssPath }/login.css" />

<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/bootstrap.min.js"></script>
<script src="${jsPath }/login.js"></script>
</head>

<body class="login" onkeydown="keyLogin()">
	<section>
	<h1>
		<strong>小区物业管理系统</strong>
	</h1>
		<input type="text" placeholder="请输入账号"  id="acc"/> 
		<input value="" type="password" placeholder="请输入密码"  id="pwd"/>
		<button class="blue" onclick="login()">登录</button>
	
	</section>
</body>
<script type="text/javascript">
function keyLogin(){
	if (event.keyCode==13){
		login();
	}
}

</script>
</html>
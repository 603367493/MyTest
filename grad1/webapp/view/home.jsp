<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="robots" content="" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/home.js"></script>
<body>
<!-- 首页 -->
	<div class="testing">
		<!--头文件-->
		<header class="main">
		<h1>
			<strong>小区物业管理系统</strong>
		</h1>
		<!-- <input type="text" value="查找相关信息" /> --> </header>
		<!--头部中间栏-->
		<section class="user">
		<div class="profile-img">
			<p>
				<img src="/grad/${sessionScope.user.image_url}" alt=""
					height="40" width="40" />${sessionScope.user.name },欢迎来到小区物业管理            			
			</p>
			<p>账户余额：<span id="finallyPrice">${sessionScope.user.price }</span></p>
		</div>
	
				<div class="buttons">
		<button class="green" onclick="logout()" type="">退出</button>

		</div>
		</section>
	</div>
	<nav>
	<ul id="left_title">
	</ul>
	</nav>

	 <section class="alert">
	    <div class="green">
		<p>
			尊敬的${sessionScope.user.name}, 你有<a onclick="subway()">新的反馈信息</a>
		</p>
	</div>
	</section>
	<section class="content"> <section class="widget">
		<iframe src="/grad/index/toIndexUI.do" id="contentIframe" name="contentIframe" width="100%"  frameborder="0" scrolling="yes" onload = "initIframeHeight(114)"></iframe>
	</section>
</body>
</html>
<script>

	function logout(){
		alert("退出登录成功");
		window.location.href="/grad/home/logOut.do"
	}
   //  function subway(){
    //	 window.location.href="/grad/index/toIndexUI.do"
     //}
	
	if("${sessionScope.user.power}" == "1"){
		$("#contentIframe").attr("src","/grad/user/toUserManagerUI.do");
	}else{
		$("#contentIframe").attr("src","/grad/index/toIndexUI.do");
	}
	
	$(function() {
		//iframe自适应高度
		//initIframeHeight(114);
		var root = '/grad';
		$.ajax({
			type: "get",
			async: true,
			url: root+"/menu/getMenu.do?power=${sessionScope.user.power}",
			success: function(data) {
				console.info(data);
				$("#left_title").html("");
				console.info("${sessionScope.user.power}")
				var html = "";
				console.info(data);
				$.each(data, function(index, item) {
					console.info(item.url)
					if(item.url != "" && item.url != undefined) {
						html += '<li class="section" id="' + item.id + '" ><a href="' + root+item.url + '" target="contentIframe"><img src="'+root+item.image_url+'" >' + item.title + '</a></li>';
					} else {
						
						$.ajax({
							type: "get",
							async: false,
							url: "/grad/menu/getMenuByParentId.do?power=${sessionScope.user.power}&parent_id=" + item.id,
							success: function(data1) {
								console.info(data1)
								html += '<li onclick="showMenu(this,\'.submenu\')" class="section"><a ><img src="'+root+item.image_url+'"/>' + item.title + '</a>';
								//1级权限
								html += '<ul class="submenu">';
								
								$.each(data1, function(index1, item1) {
									//2级权限
									html += '<li><a href="' + root + item1.url + '" target="contentIframe">' + item1.title + '</a></li>';
								});
								html += '</ul></li>';
							}
						})
					}
				})
				console.info(html);
				console.info($("#left_title"))
				$("#left_title").append(html);
			}
		})
		
		

	});
</script>
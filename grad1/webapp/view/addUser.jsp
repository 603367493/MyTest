<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增用户</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增用户 -->
	<input type="hidden" id="id" value="${user1.id }"/>

	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>用户</h1>
				<h2>user</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap">
				<label>账号</label><input type="text"  placeholder="账号" id="acc" value="${user1.acc }"/>
			</div>
			<div class="field-wrap">
				<label>密码</label><input type="password"  placeholder="密码" id="pwd" value="${user1.pwd }"/>
			</div>
			<div class="field-wrap">
				<label>姓名</label><input type="text"  placeholder="姓名" id="name" value="${user1.name }"/>
			</div>
			<div class="field-wrap">
				<label>地址</label><input type="text"  placeholder="地址" id="address" value="${user1.address }"/>
			</div>
			<div class="field-wrap">
				<label>手机</label><input type="text"  placeholder="手机" id="phone" value="${user1.phone }"/>
			</div>
			<div class="field-wrap">
				<label>电话</label><input type="text"  placeholder="电话" id="mobile" value="${user1.mobile }"/>
			</div>
			<div class="field-wrap">
				<label>身份证</label><input type="text"  placeholder="身份证" id="idcard" value="${user1.idcard }"/>
			</div>
			<div class="field-wrap">
				<label>账号金额</label><input type="text"  placeholder="账号金额" id="price" value="${user1.price }"/>
			</div>
			<button type="button" class="green"  onclick="beforeAdd()" id="save">保存</button> 
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
		//修改情况下的初始化
		if("${flag}" == "2"){
			$("#acc").attr("readonly",true);
		}
		//查看情况下的初始化，将保存按钮屏蔽
		if("${flag}" == "3"){
			$("#save").css("display","none");
			$("#pwd").parent().css("display","none");
			$("#acc").attr("readonly",true);
			$("#pwd").attr("readonly",true);
			$("#name").attr("readonly",true);
			$("#address").attr("readonly",true);
			$("#phone").attr("readonly",true);
			$("#mobile").attr("readonly",true);
			$("#idcard").attr("readonly",true);
			$("#price").attr("readonly",true);
		}
	})
	
	function beforeAdd(){
		//新增情况下
		if("${flag}" == "1"){
			var flag = confirm("账号一旦保存，将没办法再次修改，是否确认保存");
			if(flag == true){
				add();
			}
		}else if("${flag}" == "2"){
			add();
		}
	}
	
	
	function add(){	
		var param = {};
		var acc = $("#acc").val();
		var pwd = $("#pwd").val();
		var name = $("#name").val();
		var address = $("#address").val();
		var phone = $("#phone").val();
		var mobile = $("#mobile").val();
		var idcard = $("#idcard").val();
		
		var price = $("#price").val();
		var id = $("#id").val() == ""?-1: $("#id").val();
		param.acc = acc;
		param.pwd = pwd;
		param.name = name;
		param.address = address;
		param.phone = phone;
		param.mobile = mobile;
		param.idcard = idcard;
		param.price = price;
		param.id = id;
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/user/addUser.do", 
	        dataType:"json",      
	        contentType:"application/json",               
	       	data:JSON.stringify(param), 
	        success:function(data){ 
	            alert(data.msg);
	            if(data.flag == true){
					parent.query();
					parent.$("#closeImg").click();
				}
	        } 
	    }); 
	}
	
</script>
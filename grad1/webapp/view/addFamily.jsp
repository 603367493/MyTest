<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增家庭成员</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增家属 -->
	<input type="hidden" id="id" value="${familyVO.id }"/>
	<input type="hidden" id="user_id" value="${user_id }"/>
	<input type="hidden" id="relation_id" value="${familyVO.relation_id }"/>

	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>家庭成员</h1>
				<h2>family</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap" id="relation">
				<label>与户主关系</label>
			</div>
			<div class="field-wrap">
				<label>姓名</label><input type="text"  placeholder="姓名" id="name" value="${familyVO.name }"/>
			</div>
			<div class="field-wrap">
				<label>手机</label><input type="text"  placeholder="手机" id="phone" value="${familyVO.phone }"/>
			</div>
			<button type="button" class="green"  onclick="add()" id="save">保存</button> 
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
		
		
		
		//查看情况下的初始化，将保存按钮屏蔽
		if("${flag}" == "3"){
			$("#save").css("display","none");
			$("#name").attr("readonly",true);
			$("#phone").attr("readonly",true);
			getData("#relation",50000001,$("#relation_id").val(),true);
		}else{
			getData("#relation",50000001,$("#relation_id").val());
		}
	})

	
	//点击后添加
	function add(){	
		var param = {};
		//获取文本框里面的数值
		var relation_id = $("#select50000001").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var id = $("#id").val() == ""?-1: $("#id").val();
		var user_id = $("#user_id").val();
		param.relation_id = relation_id;
		param.name = name;
		param.phone = phone;
		param.id = id;
		param.user_id = user_id;
		console.info(param)
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/account/addFamily.do", 
	        dataType:"json",      
	        contentType:"application/json",               
	       	data:JSON.stringify(param), 
	        success:function(data){ 
	            alert(data.msg);
	            if(data.flag == true){
					parent.query(user_id);
					parent.$("#closeImg").click();
				}
	        } 
	    }); 
	}
	
</script>
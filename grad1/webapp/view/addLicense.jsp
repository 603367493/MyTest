<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增车牌</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增车牌 -->
	<input type="hidden" id="id" value="${user1.id }"/>
	<input type="hidden" id="user_id" value="${user_id }"/>
	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>车牌</h1>
				<h2>license</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap">
				<label>车牌号</label><input type="text"  placeholder="车牌号" id="license" value="${user1.acc }"/>
			</div>
			
			<button type="button" class="green"  onclick="add()" id="save">保存</button> 
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
	
	})
	function add(){	
		var param = {};
		var license = $("#license").val();
		//判断id是否为空  空 ：-1  否：Id
		var id = $("#id").val() == ""?-1: $("#id").val();
		//判断车牌号是否为空
		if(license == ""){
			alert("车牌号不能为空");
			return ;
		}
		param.license = license;
		param.id = id;
		param.delete_flag = 0;
		param.user_id = $("#user_id").val();
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/license/addLicense.do", 
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
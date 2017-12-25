<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增车位</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<script src="${jsPath}/layer.js"></script>
<body>
<!-- 新增车位 -->
<input type="hidden" value='${carVO.id }' id="id"/>
	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>车位</h1>
				<h2>car location</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap" >
				<label>车位编号</label><input type="text"  placeholder="车位编号" id="no" value="${carVO.no }"/>
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
			$("#no").attr("readonly",true);
			
		}
	})
	
	//添加车位编号
	function add(){	
		var param = {};
		var no = $("#no").val();
		if(no == ""){
			alert("车位编号不能为空");
			return;
		}
		var id = $("#id").val() == ""?-1: $("#id").val();
		param.no = no;
		param.state = 2;
		param.delete_flag = 0;
		param.id = id;
		console.info(param);
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/carLocation/addCar.do", 
	        dataType:"json",      
	        contentType:"application/json",               
	       	data:JSON.stringify(param), 
	        success:function(data){ 
	            alert(data.msg);
	             if(data.flag == true){
	            	var state = parent.$("#select50000002").val();
					parent.query(state);
					parent.$("#closeImg").click();
				} 
	        } 
	    }); 
	}
</script>
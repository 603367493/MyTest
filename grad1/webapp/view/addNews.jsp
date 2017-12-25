<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增公告</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增公告 -->
	<input type="hidden" id="id" value="${newVO.id }"/>

	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>公告</h1>
				<h2>news</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap">
				<label>标题</label><input type="text"  placeholder="标题" id="title" value="${newVO.title }"/>
			</div>
			<div class="field-wrap wysiwyg-wrap">
				<label>内容</label><textarea class="post" rows="6" placeholder="内容" id="content">${newVO.content }</textarea>
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
			//设置title属性
			$("#title").attr("readonly",true);
			$("#content").attr("readonly",true);
		}
	})

	
	
	function add(){	
		var param = {};
		var title = $("#title").val();
		var content = $("#content").val();
		var id = $("#id").val() == ""?-1: $("#id").val();

		if(title == ""){
			alert("标题不能为空");
			return ;
		}
		
		if(content ==""){
			alert("内容不能为空");
			return ;
		}
		
		param.title = title;
		param.content = content;
		param.id = id;
		console.info(param)
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/news/addNews.do", 
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
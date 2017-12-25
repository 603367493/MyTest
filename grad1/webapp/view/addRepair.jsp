<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增报修</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增报修 -->
<input type="hidden" value='${repairVO.type_id }' id="type_id"/>
<input type="hidden" value='${repairVO.id }' id="id"/>

	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>新增报修</h1>
				<h2>repair</h2>
			</hgroup>
		</header>
		<div class="content">
			<div id="bxType">
				<label >请选择报修类型</label>
			</div>
			
			<div class="field-wrap wysiwyg-wrap">
				<textarea class="post" rows="6" placeholder="详细描述" id="xq">${repairVO.content }</textarea>
			</div>
			<button type="button" class="red"  onclick="bx()">提交</button> 
		</button>
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
		var type_id = $("#type_id").val();
		getData("#bxType",50000004,type_id);
	})
	
	function bx(){
		var bxCode = $("#select50000004").val();
		var content = $("#xq").val();
		if(content == ""){
			alert("详情不能为空");
			return;
		}
		var id = $("#id").val() == ""?-1: $("#id").val();
		console.info("id"+id);
		$.ajax({
			url:"/grad/bxts/bx.do?content="+content+"&bx_code="+bxCode+"&id="+id,
			type:"get",
			async:true,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					parent.query();
					parent.$("#closeImg").click();
				}
			}
		})
	}
</script>
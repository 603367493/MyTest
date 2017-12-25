<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增投诉</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script src="${jsPath }/common.js"></script>
<body>
<!-- 新增投诉 -->
<input type="hidden" value='${complainVO.type_id }' id="type_id"/>
<input type="hidden" value='${complainVO.id }' id="id"/>
	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>投诉</h1>
				<h2>complain</h2>
			</hgroup>
		</header>
		<div class="content">
			<div id="tsType">
				<label >请选择投诉类型</label>
			</div>
			
			<div class="field-wrap wysiwyg-wrap">
				<textarea class="post" rows="6" placeholder="详细描述" id="xq">${complainVO.content }</textarea>
			</div>
			<button class="green"  onclick="ts()">提交</button> 
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
		var type_id = $("#type_id").val();
		getData("#tsType",50000005,type_id);
	})
	
 	function ts(){
		//005结尾为投诉
		var ts_code = $("#select50000005").val();
		//详细内容
		var content = $("#xq").val();
		if(content == ""){
			alert("详情不能为空");
			return;
		}
		var id = $("#id").val() == ""?-1: $("#id").val();
		//根据实际情况提交
		$.ajax({
			url:"/grad/bxts/ts.do?content="+content+"&ts_code="+ts_code+"&id="+id,
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
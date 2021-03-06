<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>投诉管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
<link rel="stylesheet" href="${cssPath }/page.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<script type="text/javascript" src="${jsPath }/paging.js"></script>


<body>
<!-- 投诉管理 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看投诉信息</h1>
			<h2>check complain info</h2>
		</hgroup>
	</header>

	</div> 
	
	<table id="myTable" border="0" width="100" class="myTable">
		<thead id="thead">
			<tr>
				<td style="width: 10%;text-align: center;">业主</td>
				<td style="width: 10%;text-align: center;">详情</td>
				<td style="width: 10%;text-align: center;">状态</td>
				<td style="width: 20%;text-align: center;">反馈结果</td>
				<td style="width: 40%;text-align: center;">操作</td>
			</tr>
		</thead>
		<tbody id="tbody">
		
		</tbody>
	</table>
	<div value="1 0"></div> 
	<div id="page" class="page_div"></div>
</section>
		
</body>
</html>
<script>
	function query(){
			$.ajax({
				type:"get",
				url:"/grad/bxts/getAllComplain.do?state=-1",
				success:function(data){
					var html = "";
					$.each(data,function(index,item){
						html += "<tr id='"+item.id+"' >";
						html += "<td style='width: 10%;'>"+item.user_name+"</td>";
						html += "<td style='width:20%'>" +item.content +"</td>";
						html += "<td style='width:20%'>" +item.state_name +"</td>";
						if(item.feedback != null){
							html += "<td style='width:20%'>" +item.feedback +"</td>";
						}else{
							html += "<td style='width:20%'></td>";

						}
						if(item.state == 1){
							html +="<td style='width: 20%;text-align: center;'></td>";
						}else if(item.state ==2){
							html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='fk("+item.id+")'>反馈结果</button></td>";
						}else{
							html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='cl("+item.id+")' id='cl'>处理</button><button type='button' class='blue' onclick='fk("+item.id+")'>反馈结果</button></td>";
						}
					})
					$("#tbody").html("");
					$("#tbody").append(html);
					console.info($("#tbody").find("tr"))
					page(1);
				}
			})
		
	}
	

	
	$(function(){
		console.info("${isHSZ}")
		query();
		
	})
	
	
	
	function cl(id){
		$.ajax({
			type:"get",
			url:"/grad/bxts/clComplain.do?id="+id,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					console.info($("#tbody").find("#"+id).find("td"))
					$("#tbody").find("#"+id).find("td").eq(2).html("处理中");
					//处理按钮隐藏
					$("#tbody").find("#"+id).find("td").eq(4).find("#cl").css("display","none")
				}
			}
			
		})
	}
	
	function fk(id){
		var content = prompt("请输入反馈结果","");
		if(content  == ""){
			alert("反馈结果不能为空");
			fk(id);
			return;
		}
		
		$.ajax({
			type:"get",
			url:"/grad/bxts/fkComplain.do?id="+id+"&content="+content,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					query();
				}
			}
			
		})
	}
	
	
</script>
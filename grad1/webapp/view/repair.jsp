<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>报修管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
<!-- 报修管理 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看报修信息</h1>
			<h2>check repair info</h2>
		</hgroup>
		<aside >
			
				<button class="green" onclick="add()">新增</button>
			
		</aside>
	</header>
	<table id="myTable" border="0" width="100" class="myTable">
		<thead>
			<tr>
				<th style="width: 10%;">报修类型</th>
				<th style="width: 20%;">详情描述</th>
				<th style="width: 20%;">处理状态</th>
				<th style="width: 30%;">反馈结果</th>
				<th style="width: 20%;">操作</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<!-- <tr>
				<td style="width: 10%;text-align: center;">A001</td>
				<td style="width: 20%;text-align: center;">粤A-0001</td>
				<td style="width: 20%;text-align: center;">已缴费</td>
				<td style="width: 30%;text-align: center;">206天</td>
				<td style="width: 20%;text-align: center;">
					<button type="button" class="blue" onclick="buy()">续费</button>
				</td>
			</tr> -->
		</tbody>
	</table>
</section>
		
</body>
</html>
<script>
	function query(){
		$.ajax({
			type:"get",
			url:"/grad/bxts/getRepairList.do",
			success:function(data){
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html +="<tr>";
					html +="<td style='width: 10%;text-align: center;'>"+item.type_name+"</td>";
					html +="<td style='width: 20%;text-align: center;'>"+item.content+"</td>";
					html +="<td style='width: 20%;text-align: center;'>"+item.state_name+"</td>";
					if(item.feedback == null){
						html +="<td style='width: 30%;text-align: center;'></td>";
					}else{
						html +="<td style='width: 30%;text-align: center;'>"+item.feedback+"</td>";

					}					
					if(item.state == 3){
						html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='xg("+item.id+")'>修改</button></td>";
					}else{
						html +="<td style='width: 20%;text-align: center;'></td>";
					}
					html +="</tr>";
				})
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		query();
	})
	
	
	function xg(id){
		openWindow(800,500,"/grad/bxts/toAddRepairUI.do?isView=true&id="+id,'修改报修');
	}
	
	function add(){
		openWindow(800,500,"/grad/bxts/toAddRepairUI.do?isView=false",'新增报修');
	}
</script>
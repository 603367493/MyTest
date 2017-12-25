<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看车位信息</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
<!-- 查看车位 -->
	<section class="widget" style="min-height:300px">
				<header>
					<span class="icon">&#128196;</span>
					<hgroup>
						<h1>查看小区车位信息</h1>
						<h2>check car info</h2>
					</hgroup>
					
				</header>
				<table id="myTable" border="0" width="100" class="myTable">
					<thead>
						<tr>
							<th style="width: 10%;">车位编号</th>
							<th style="width: 60%;">状态</th>
							<th style="width: 30%;">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
			</section>
</body>
</html>
<script>
	$(function(){
		query();
	})
	
	function query(){
		$.ajax({
			url:"/grad/carLocation/getCarLocationInfo.do",
			type:"get",
			success:function(data){
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html += "<tr>";
					html += "<td style='width:10%;text-align:center'>"+item.no +"</td>";
					html += "<td style='width:60%;text-align:center'>"+item.state_name +"</td>";
					html += "<td style='width:30%;text-align:center'><button type='button' class='blue' onclick='buy("+item.id+")'>购买</button></td>";
					html += "</tr>";	
				});			
				//插入至tbody
				$("#tbody").append(html)
			}
		})
	}
	
	function buy(id){
		openWindow(800,400,"/grad/carLocation/toBuyCarLocationUI.do?id=" + id,'购买车位');
	}
		
</script>
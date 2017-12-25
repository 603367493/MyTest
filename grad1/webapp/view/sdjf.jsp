<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>水电缴费</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
	<section class="widget">
<!-- 水电缴费 -->
	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看水电缴费信息</h1>
			<h2>check sdjf info</h2>
		</hgroup>
	</header>
	<table id="myTable" border="0" width="100" class="myTable">
		<thead>
			<tr>
				<th style="width: 10%;">期间</th>
				<th style="width: 20%;">水费</th>
				<th style="width: 20%;">电费</th>
				<th style="width: 30%;">状态</th>
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
			url:"/grad/sdjf/getSdjfList.do",
			success:function(data){
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html +="<tr>";
					html +="<td style='width: 10%;text-align: center;'>"+getPeriod(item.period)+"</td>";
					html +="<td style='width: 20%;text-align: center;'>"+item.water_price+"</td>";
					html +="<td style='width: 20%;text-align: center;'>"+item.electricity+"</td>";
					html +="<td style='width: 30%;text-align: center;'>"+item.state_name+"</td>";
					var allPrice = parseFloat(item.water_price)+parseFloat(item.electricity);
					if(item.state == 2){
						html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='sn("+item.id+","+allPrice+")'>缴纳</button></td>";
					}else{
						html +="<td></td>";
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
	
	
	function sn(id,allPrice){
		var flag = confirm("是否确定缴纳费用?");
		if(flag == true){
			$.ajax({
				type:"get",
				url:"/grad/sdjf/jn.do",
				data:{
					id:id,
					allPrice:allPrice
				},
				success:function(data){
					alert(data.msg);
					if(data.flag == true){
						query();
					}
				}
			})
		}
	}
	
	function add(){
		openWindow(800,500,"/grad/bxts/toAddRepairUI.do?isView=false",'新增报修');
	}
</script>
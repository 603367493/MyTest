<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看已缴费情况</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
<!-- 查看已缴费 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看缴费信息</h1>
			<h2>check JF info</h2>
		</hgroup>
	</header>
	<table id="myTable" border="0" width="100" class="myTable">
		<thead>
			<tr>
				<th style="width: 10%;">车位编号</th>
				<th style="width: 20%;">车牌号</th>
				<th style="width: 20%;">缴费状态</th>
				<th style="width: 30%;">到期时间</th>
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
			url:"/grad/carLocation/getYJFInfo.do",
			success:function(data){
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html +="<tr>";
					html +="<td style='width: 10%;text-align: center;'>"+item.no+"</td>";
					html +="<td style='width: 20%;text-align: center;'>"+item.license_name+"</td>";
					if(item.pay_flag == 1){
						html +="<td style='width: 20%;text-align: center;'>已缴费</td>";
					}else{
						html +="<td style='width: 20%;text-align: center;'>未缴费</td>";
					}
					//var userable_time = item.userable_time;
					
					html +="<td style='width: 30%;text-align: center;'>"+getBTDate(item.userable_time)+"</td>";
					html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='xf("+item.id+",\""+item.userable_time+"\")'>续费</button></td>";
					html +="</tr>";
				})
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		query();
	})
	
	function xf(id,userable_time){
		openWindow(800,400,"/grad/carLocation/toXFUI.do?id=" + id+"&userable_time="+userable_time,'续费');
	}
</script>
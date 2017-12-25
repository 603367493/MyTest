<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车位管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
<!-- 车位管理 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看车位信息</h1>
			<h2>check car info</h2>
		</hgroup>
		<aside >
			
				<button class="red" onclick="add()">新增车位</button>
			
		</aside>
	</header>
	 <div style="padding:20px" id="stateSelect">
		<label>请选择对应的状态</label>
	</div> 
	
	<table id="myTable" border="0" width="100" class="myTable">
		<thead id="thead">
			
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
	function query(state){
		var theadHtml = "";	
		//已购买  state： 1已购买    2 未购买
		if(state == 1){
			theadHtml += "<tr >";
			theadHtml += "<td style='width: 20%;'>车位编号</td>";
			theadHtml += "<td style='width:20%'>业主姓名</td>";
			theadHtml += "<td style='width:20%'>车牌号</td>";
			theadHtml += "<td style='width:20%'>过期时间</td>";
			theadHtml += "<td style='width:20%'>缴费状态</td>";
			theadHtml += "</tr>";
		}else{
			theadHtml += "<tr >";
			theadHtml += "<td style='width: 50%;'>车位编号</td>";
			theadHtml += "<td style='width: 50%;'>操作</td>"
			theadHtml += "</tr>";
		}
		$("#thead").html("");
		$("#thead").append(theadHtml);
		
		$.ajax({
			type:"get",
			url:"/grad/carLocation/getCarListByState.do?state="+state,
			success:function(data){
				var html = "";
				//已购买
				if(state == 1){
					$.each(data,function(index,item){
						html += "<tr id='"+item.id+"'>";
						html += "<td style='width: 10%;'>"+item.no+"</td>";
						html += "<td style='width:20%'>" +item.user_name +"</td>";
						html += "<td style='width:20%'>" +item.license_name +"</td>";
						html += "<td style='width:20%'>" +item.userable_time +"</td>";
						var pay_name = "";
						if(item.pay_flag == 1){
							pay_name = "已缴费"
						}else{
							pay_name="未缴费";
						}
						html += "<td style='width:20%'>" + pay_name+"</td>";
						html += "</tr>";
					})	
				}else{
					$.each(data,function(index,item){
						html += "<tr id='"+item.id+"' onclick='ck(\""+item.id+"\")'>";
						html += "<td style='width: 10%;'>"+item.no+"</td>";
						html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='sc("+item.id+")'>删除</button></td>";
						html += "</tr>";
					})	
				}
				
				$("#tbody").html("");
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		//状态下拉框初始化
		var defalutValue=1;//设置状态默认值
		query(defalutValue);
		getData($("#stateSelect"),50000002,defalutValue,false,function(){
			query($("#select50000002").val());
		})
	})
	
	function ck(id){
		openWindow(800,500,"/grad/carLocation/toAddCarLocationUI.do?flag=3&id="+id,'查看车位');
	}
	
	function xg(id){
		openWindow(800,500,"/grad/carLocation/toAddCarLocationUI.do?flag=2&id="+id,'修改车位');
		event.stopPropagation();  
	}
	
	function add(){
		openWindow(800,500,"/grad/carLocation/toAddCarLocationUI.do?flag=1",'新增车位信息');
	}
	function sc(id){
		$.ajax({
			type:"get",
			url:"/grad/carLocation/deleteCar.do?id="+id,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					$("#tbody").find("#"+id).remove();
				}
			}
		})
		event.stopPropagation();  
	}

</script>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>水电管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
<link rel="stylesheet" type="text/css" href="${cssPath }/jquery-ui.css"/ >
<style type="text/css">
	.ui-datepicker-calendar { 
		display: none; 
	}
	#ui-datepicker-div{
		background-color:gray;
	} 
</style>
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<script src="${jsPath }/jquery-ui.js"></script>
<script type="text/javascript" src="${jsPath }/jquery.ui.datepicker-zh-CN.js"></script>
<body>
<!-- 水电管理 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看水电信息</h1>
			<h2>check sd info</h2>
		</hgroup>
		<aside >
		
				<button class="red" style="font-size:12px;" onclick="add()">新增</button>
			
		</aside>
	</header>
	<div style="padding:20px">
		<label>请选择对应的账号</label>
		<select id="accountSelect">
			
		</select>
		<label>请选择期间</label><input type="text" name="datetimepicker" class="it date-pick" id="datetimepicker" readonly = "readonly" style="width:30%"/>
	</div>
	
	<table id="myTable" border="0" width="100" class="myTable">
		<thead>
			<tr>
				<th style="width: 20%;">期间</th>
				<th style="width: 10%;">水费</th>
				<th style="width: 10%;">电费</th>
				<th style="width: 10%;">状态</th>
				<th style="width: 10%;">缴费时间</th>
				<th style="width: 40%;">操作</th>
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
	function query(id,period){
		$.ajax({
			type:"get",
			url:"/grad/sdjf/getSdjfListByUserId.do?user_id="+id+"&period="+period,
			success:function(data){
				console.info(data);
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html += "<tr id='"+item.id+"' onclick='ck(\""+item.id+"\")'>";
					html += "<td style='width: 20%;'>"+getPeriod(item.period)+"</td>";
					html += "<td style='width:10%'>" +item.water_price+"</td>";
					html += "<td style='width:10%'>" +item.electricity +"</td>";
					html += "<td style='width:10%'>" +item.state_name +"</td>";
					html += "<td style='width:10%'>" +getBTDate(item.jf_time) +"</td>";

					if(item.state == 1){
						html +="<td style='width: 40%;text-align: center;'><button type='button' class='blue' onclick='sc("+item.id+")'>删除</button></td>";
					}else{
						html +="<td style='width: 40%;text-align: center;'><button type='button' class='blue' onclick='xg("+item.id+")'>修改</button><button type='button' class='blue' onclick='sc("+item.id+")'>删除</button></td>";
					}
					
					html += "</tr>";
				})
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		//日期控件初始化
		$('#datetimepicker').datepicker({ 
			changeMonth: true, 
			changeYear: true, 
			showButtonPanel: true, 
			dateFormat: 'yy-MM', 
			onClose: function(dateText, inst) { 
				var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val(); 
				var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val(); 
				$(this).datepicker('setDate', new Date(year, month, 1)); 
				var period = $("#datetimepicker").val()+"-01";
				var selectValue = $("#accountSelect").val();
				query(selectValue,period);
			} 
			});  
		var date = new Date();
		var now = getPeriod(date.getTime());
		$("#datetimepicker").val(now);
		
		initAccountSelect();
		$("#accountSelect").on('change',function(){
			var period = $("#datetimepicker").val()+"-01";
			query($(this).val(),period);
		})
		//query();
	})
	
	function initAccountSelect(){
		$.ajax({
			type:"get",
			url:"/grad/user/getUserList.do",
			success:function(data){
				console.info(data)
				var html = "";
				$("#accountSelect").html("");
				$.each(data,function(index,item){
					html +="<option value='"+item.id+"'>"+item.acc+"</option>"
				})
				$("#accountSelect").append(html);
				var selectValue=$("#accountSelect").val();
				var period = $("#datetimepicker").val()+"-01";
				query(selectValue,period);
			}
		})
		
		
	}
	
	function ck(id){
		var selectValue=$("#accountSelect").val();
		openWindow(800,500,"/grad/sdjf/toAddSdjfUI.do?flag=3&id="+id,'查看水电费');
	}
	
	function xg(id){
		openWindow(800,500,"/grad/sdjf/toAddSdjfUI.do?flag=2&id="+id,'修改水电费');
		event.stopPropagation();  
	}
	
	function add(){
		var selectValue=$("#accountSelect").val();
		openWindow(800,500,"/grad/sdjf/toAddSdjfUI.do?flag=1",'新增水电费');
	}
	
	function sc(id){
		$.ajax({//////////////////////////////////////////////////////////////
			type:"get",
			url:"/grad/sdjf/deleteSdjf.do?id="+id,
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
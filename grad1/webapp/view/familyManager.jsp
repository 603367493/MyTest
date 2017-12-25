<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭成员管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
<!-- 家庭成员管理 -->
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看家庭成员信息</h1>
			<h2>check family info</h2>
		</hgroup>
		<aside >
		
		<button class="red" style="font-size:12px;" onclick="add()">新增成员</button>
		
		</aside>
	</header>
	<div style="padding:20px">
		<label>请选择对应的账号</label>
		<select id="accountSelect">
			
		</select>
	</div>
	
	<table id="myTable" border="0" width="100" class="myTable">
		<thead>
			<tr>
				<td style="width: 30%;">与户主关系</td>
				<td style="width: 20%;">姓名</td>
				<td style="width: 20%;">联系方式</td>
				<td style="width: 20%;">操作</td>
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
	function query(id){
		$.ajax({
			type:"get",
			url:"/grad/account/getFamilyById?user_id="+id,
			success:function(data){
				console.info(data);
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html += "<tr id='"+item.id+"' onclick='ck(\""+item.id+"\")'>";
					html += "<td style='width: 10%;'>"+item.relation_name+"</td>";
					html += "<td style='width:30%'>" +item.name+"</td>";
					html += "<td style='width:20%'>" +item.phone +"</td>";
					html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='xg("+item.id+")'>修改</button><button type='button' class='blue' onclick='sc("+item.id+")'>删除</button></td>";
					html += "</tr>";
				})
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		initAccountSelect();
		$("#accountSelect").on('change',function(){
			query($(this).val());
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
				query(selectValue);
			}
		})
		
		
	}
	
	function ck(id){
		var selectValue=$("#accountSelect").val();
		openWindow(800,500,"/grad/account/toAddFamilyUI.do?flag=3&id="+id+"&user_id="+selectValue,'查看家庭成员');
	}
	
	function xg(id){
		var selectValue=$("#accountSelect").val();
		openWindow(800,500,"/grad/account/toAddFamilyUI.do?flag=2&id="+id+"&user_id="+selectValue,'修改家庭成员');
		event.stopPropagation();  
	}
	
	function add(){
		var selectValue=$("#accountSelect").val();
		openWindow(800,500,"/grad/account/toAddFamilyUI.do?flag=1&user_id="+selectValue,'新增家庭成员');
	}
	function sc(id){
		$.ajax({
			type:"get",
			url:"/grad/account/deleteFamily.do?id="+id,
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
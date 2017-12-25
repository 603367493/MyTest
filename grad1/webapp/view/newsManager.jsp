<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>公告栏管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
	<section class="widget">

	<header>
		<span class="icon">&#128196;</span>
		<hgroup>
			<h1>查看公告栏信息</h1>
			<h2>check news info</h2>
		</hgroup>
		<aside id="addAside">
			
				<button onclick="add()"style="font-size:12px;" class="red" >新增公告</button
			
		</aside>
	</header>

	</div> 
	
	<table id="myTable" border="0" width="100" class="myTable">
		<thead id="thead">
			<tr>
				<td style="width: 10%;text-align: center;">标题</td>
				<td style="width: 20%;text-align: center;">发布时间</td>
				<td style="width: 20%;text-align: center;">发布人</td>
				<td style="width: 30%;text-align: center;">操作</td>
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
			url:"/grad/news/getNews.do",
			success:function(data){
				var html = "";
				$.each(data,function(index,item){
					html += "<tr id='"+item.id+"' onclick=ck(\""+item.id+"\") >";
					html += "<td style='width: 10%;'>"+item.title+"</td>";
					html += "<td style='width:20%'>" +getBTDate(item.publish_time) +"</td>";
					html += "<td style='width:20%'>" +item.publish_name +"</td>";
					html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='xg("+item.id+")'>修改</button><button type='button' class='blue' onclick='sc("+item.id+")'>移进回收站</button></td>";
				})
				$("#tbody").html("");
				$("#tbody").append(html);
			}
		})
	}
	
	
	function queryHSZ(){
		$.ajax({
			type:"get",
			url:"/grad/news/getHSZNews.do",
			success:function(data){
				var html = "";
				$.each(data,function(index,item){
					html += "<tr id='"+item.id+"' onclick=ck(\""+item.id+"\") >";
					html += "<td style='width: 10%;'>"+item.title+"</td>";
					html += "<td style='width:20%'>" +getBTDate(item.publish_time) +"</td>";
					html += "<td style='width:20%'>" +item.publish_name +"</td>";
					html +="<td style='width: 20%;text-align: center;'><button type='button' class='blue' onclick='move("+item.id+")'>移出回收站</button></td>";
				})
				$("#tbody").html("");
				$("#tbody").append(html);
			}
		})
	}
	
	$(function(){
		console.info("${isHSZ}")
		if("${isHSZ}" == "0"){
			//新增按钮显示
			$("#addAside").css("display","block");
			query();
		}else{
			//新增按钮隐藏
			$("#addAside").css("display","none");
			queryHSZ();
		}	
		
	})
	
	function ck(id){
		openWindow(800,500,"/grad/news/toAddNewUI.do?flag=3&id="+id,'查看公告');
	}
	
	function xg(id){
		openWindow(800,500,"/grad/news/toAddNewUI.do?flag=2&id="+id,'修改公告');
		event.stopPropagation();  
	}
	
	function add(){
		openWindow(800,500,"/grad/news/toAddNewUI.do?flag=1",'新增公告信息');
	}
	function sc(id){
		$.ajax({
			type:"get",
			url:"/grad/news/deleteNews.do?id="+id,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					$("#tbody").find("#"+id).remove();
				}
			}
		})
		event.stopPropagation();  
	}
	
	function move(id){
		$.ajax({
			type:"get",
			url:"/grad/news/unDeleteNews.do?id="+id,
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
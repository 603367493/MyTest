<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
<body>
	
			
			
	<section class="widget" >
				<header>
					<span class="icon">&#128196</span>
					<hgroup>
						<h1>个人账号</h1>
						<h2>account</h2>
					</hgroup>
					  <aside>
						<button onclick="add()"style="font-size:12px;" class="red" >新增用户</button>
						</aside>
				    </header>
				<table id="myTable" border="0" width="100">
					<thead>
						<tr>
							<th style="width: 10%;">姓名</th>
							<th style="width: 10%;">地址</th>
							<th style="width: 10%;">账号</th>
							<th style="width: 10%;">联系方式</th>
							<th style="width: 10%;">车牌号</th>
							<th style="width: 10%;">账户余额</th>
							<th style="width: 60%;">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
			
					</tbody>
				</table>
			</section>
</section>
		
</body>
</html>
<script>
	function query(){
		$.ajax({
			type:"get",
			url:"/grad/user/getUserList.do",
			success:function(data){
				$("#tbody").html("");
				var html = "";
				$.each(data,function(index,item){
					html +="<tr id='"+item.id+"' onclick=\"ck('"+item.id+"')\">";
					html +="<td style='width: 10%;text-align: center;'>"+item.name+"</td>";
					html +="<td style='width: 10%;text-align: center;'>"+item.address+"</td>";
					html +="<td style='width: 10%;text-align: center;'>"+item.acc+"</td>";
					html +="<td style='width: 10%;text-align: center;'>"+item.phone+"</td>";
					html +="<td style='width: 10%;text-align: center;'>"+item.license_name+"</td>";
					html +="<td style='width: 10%;text-align: center;'>"+item.price+"</td>";
					html +="<td style='width: 60%;text-align: center;'><button type='button' class='blue' onclick='xg("+item.id+")'>修改</button><button type='button' class='blue' onclick='sc("+item.id+")'>删除</button><button type='button' class='blue' onclick='addLicense("+item.id+")'>添加车牌号</button><button type='button' class='blue' onclick='addPrice("+item.id+")'>账号充值</button></td>";
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
		openWindow(800,500,"/grad/user/toAddUserUI.do?flag=2&id="+id,'修改用户信息');
		event.stopPropagation();  
	}
	
	function ck(id){
		openWindow(800,500,"/grad/user/toAddUserUI.do?flag=3&id="+id,'查看用户信息');
	}
	
	function add(){
		openWindow(800,500,"/grad/user/toAddUserUI.do?flag=1",'新增用户');
		event.stopPropagation();  
	}
	
	function sc(id){
		$.ajax({
			type:"get",
			url:"/grad/user/deleteUser.do?id="+id,
			success:function(data){
				alert(data.msg);
				if(data.flag == true){
					$("#tbody").find("#"+id).remove();
				}
			}
		})
		event.stopPropagation();  
	}
	
	function addLicense(id){
		openWindow(800,500,"/grad/license/toAddLicenseUI.do?user_id="+id,'新增车牌号');
		event.stopPropagation();  
	}
	
	function addPrice(id){
		openWindow(800,500,"/grad/user/toAddPriceUI.do?user_id="+id,'账户充值');
		event.stopPropagation();  
	}	
</script>
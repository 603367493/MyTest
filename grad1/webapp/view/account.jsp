<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人账号</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
<link rel="stylesheet" href="../layui/css/layui.css">
</head>
<script src="${jsPath}/jquery-3.2.1.js"></script>
<script src="${jsPath}/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath}/common.js"></script>
<script  src="../layui/layui.js"></script>
<body >
		<!-- 个人账号  -->	
			<section class="widget small" >
				<header>
					<span class="icon">&#128196;</span>
					<hgroup>
						<h1>家庭成员</h1>
						<h2>family</h2>
					</hgroup>
				</header>
				<table id="myTable" border="0" width="100">
					<thead>
						<tr class="odd">
							<td style="width: 30%;">与户主关系</td>
							<td style="width: 30%;">姓名</td>
							<td style="width: 20%;">联系方式</td>
						</tr>
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
				</div>
			</section>
			
			
	<section class="widget small" >
				<header>
					<span class="icon">&#128196;</span>
					<hgroup>
						<h1>个人账号</h1>
						<h2>account</h2>
					</hgroup>
					<aside onmouseenter="showMenu(this,'.settings-dd')" onmouseleave="showMenu(this,'.settings-dd')">
						 <button class="green" onclick="upload()">上传头像</button> 
						<button class="red" onclick="modify()">修改密码</button>
					</aside>
				</header>
				<table id="myTable" border="0" width="100">
					<thead>
						 <tr class="odd">
							<td >姓名</td>
							<td >地址</td>
							<td >账号</td>
							<td >联系方式</td>
						</tr>
					</thead>
					<!-- 个人数据 -->
					<tbody>
						<tr class="odd">
							<td>${sessionScope.user.name}</td>
							<td>${sessionScope.user.address}</td>
							<td>${sessionScope.user.acc}</td>
							<td>${sessionScope.user.phone}</td>
						</tr>	
					</tbody>
				</table>
				</div>
			</section>
</body>
</html>
<script>
//通过json获取家属属性
	$(function(){
		$.ajax({
			type:"get",
			url:"/grad/account/getFamilyList.do",
			success:function(data){
				console.info(data);
				var html = "";
				//遍历输出
				$.each(data,function(index,item){
					html += "<tr>";
					html += "<td style='width:10%;'>"+item.relation_name+"</td>";
					html += "<td style='width:30%'>" +item.name+"</td>";
					html += "<td style='width:20%'>" +item.phone +"</td>";
					html += "</tr>";
				})
				//追加内容
				$("#tbody").append(html);
				
			}
		})
	});
	//打开窗口
	function modify(){
		openWindow(800,500,"/grad/modifyPwd/toModifyUI.do",'修改账号');
	}
	
 	function upload(){
		openWindow(800,500,"/grad/upload/toUploadUI.do",'上传图片');
	} 
</script>
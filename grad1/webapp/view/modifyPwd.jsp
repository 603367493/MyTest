<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<link rel="stylesheet" href="${cssPath }/style.css" />

</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>

<body>
	 <section style="margin-top:80px">
	<h1>
		<strong class="navbar-brand">修改密码</strong>
	</h1>
		<input value="" type="password" placeholder="请输入新密码"  id="new_pwd_1"/ style="width:50%">
		<input value="" type="password" placeholder="请再次输入新密码"  id="new_pwd_2" style="width:50%" />
		<button class="blue" onclick="modify()">保存</button>
	</section> 
</body>
</html>
<script>
	function modify(){
		var new_pwd_1 = $("#new_pwd_1").val();
		var new_pwd_2 = $("#new_pwd_2").val();
		if(new_pwd_1 != new_pwd_2){
			alert("两次密码不一样，请重新输入");
			return;
		}
		
		$.post("/grad/user/updatePwd.do",{
			user_id:"${sessionScope.user.id}",pwd:new_pwd_1
		},function(data){
	
			if(data.flag == true){
				alert(data.msg);
				parent.$("#closeImg").click();
			}else{
				alert(data.msg);
			}
		})
 	}
</script>

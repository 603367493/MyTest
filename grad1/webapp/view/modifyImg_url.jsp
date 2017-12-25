<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>上传头像</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<body>
	<form action="upload/upload.action" method="post" enctype="multipart/form-data">
		用户名：<input type="text" name="username">
		头像：<input type="file" name="uploadFile">
		<input type="submit" value="上传"/>
	</form>
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
			console.info(data)
			if(data.flag == "true"){
				alert(data.msg);
			}else{
				alert(data.msg);
			}
		})
 	}
</script>
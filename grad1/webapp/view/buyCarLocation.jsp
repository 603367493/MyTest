<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购买车位</title>
<link rel="stylesheet" href="${cssPath }/style.css" />
</head>
<script src="${jsPath }/jquery-3.2.1.js"></script>
<script src="${jsPath }/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${jsPath }/common.js"></script>
<body>
	<div style="width:100%;text-align:center;margin-top:20px;">
		<div class="jfType" style="padding:10px">
			<label>请选择缴费类型</label>
		</div>
		<div style="padding:10px">
			<label>数量</label><input type="text" id="num" style="width:20%"/>
		</div>
		<div style="padding:10px">
			<label>总共</label>￥<span id="all"></span>
		</div>
		<div id="license">
			<label>请选择你的车牌号</label>
			<select id="licenseSelect"></select>
		</div>
		<button type='button' class='blue' onclick='buy()' style="margin-top:20px">确定购买</button>
	</div>
	
</body>
</html>
<script>
	function buy(){
		 var jfCode = $("#select50000003").val();//类型
		var userId = "${sessionScope.user.id}";//id
		var carId = "${carId}";//车位id
		var licenseId = $("#licenseSelect").val();
		var num = $("#num").val();
		var all = $("#all").text();
		if(licenseId == "" || licenseId == undefined){
			alert("请选择车牌号，若没有车牌号，请联系管理员添加");
			return ;
		}
		
		if(jfCode == "" || jfCode == undefined){
			alert("缴费类型不能为空");
			return ;
		}
		$.ajax({
			url:"/grad/carLocation/buy.do",
			type:"get",
			data:{
				allPrice:all,
				num:num,
				jfCode:jfCode,
				userId:userId,
				carId:carId,
				licenseId:licenseId
			},
			async:true,
			success:function(data){
				if(data.flag == true){
					console.info(parent.parent.$("#finallyPrice"))
					parent.parent.$("#finallyPrice").text(formatPrice(data.finallyPrice));
				}
				alert(data.msg);
				parent.query();
				parent.$("#closeImg").click();
			}
		}) 
	}
	
	$(function(){
		getData('.jfType',50000003);
		$.ajax({
			type:"get",
			url:"/grad/license/getLicenseList.do",
			success:function(data){
				$("#licenseSelect").html("");
				var html = "";
				$.each(data,function(index,item){
					html +="<option value="+item.id+">"+item.license+"</option>";
				})
				
				$("#licenseSelect").append(html);
			}
		})
		
		$("#num").on('change',function(){
			var num = $(this).val();
			var priceVal = $("#select50000003").find("option:checked").text();//50/日
			var index = priceVal.indexOf('/');//找出/的索引
			var price = priceVal.substring(0,index-1);//得出50
			var all = parseInt(num) * parseInt(price);
			$("#all").text(all);
			
		})
	})
</script>
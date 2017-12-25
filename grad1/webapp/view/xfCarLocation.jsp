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
<input type="hidden" value="${id }" id="car_location_id"/>
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
		<button type='button' class='blue' onclick='xf()' style="margin-top:20px">确定续费</button>
	</div>
	
</body>
</html>
<script>
	function xf(){
		 var jfCode = $("#select50000003").val();
		//var userId = "${sessionScope.user.id}";
		var carId = $("#car_location_id").val();
		var num = $("#num").val();
		var allPrice = $("#all").text();
		 $.ajax({
			url:"/grad/carLocation/xf.do",
			type:"get",
			async:true,
			data:{
				num:num,
				allPrice:allPrice,
				jfCode:jfCode,
				car_location_id:carId,
				userable_time:"${userable_time}"
			},
			success:function(data){
				$("#closeImg").click();
				alert(data.msg);
				if(data.flag == true){
					parent.parent.$("#finallyPrice").text(formatPrice(data.finallyPrice));
					parent.query();
					parent.$("#closeImg").click();
				}
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
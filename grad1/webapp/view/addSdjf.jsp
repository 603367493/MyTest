<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增水电费</title>
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
<script src="${jsPath }/common.js"></script>
<script src="${jsPath }/jquery-ui.js"></script>
<script type="text/javascript" src="${jsPath }/jquery.ui.datepicker-zh-CN.js"></script>
<body>
<!-- 新增水电费 -->
	<input type="hidden" id="id" value="${sdjfVO.id }"/>
	<input type="hidden" id="period" value="${ sdjfVO.period }"/>

	<section class="widget" style="min-height:300px">
		<header>
			<span class="icon">&#128196;</span>
			<hgroup>
				<h1>水电费</h1>
				<h2>sd</h2>
			</hgroup>
		</header>
		<div class="content">
			<div class="field-wrap" >
				<label>请选择账号</label>
				<select id="accountSelect" value="${ sdjfVO.user_name }"></select>
				<label>请选择期间</label><input type="text" name="datetimepicker" class="it date-pick" id="datetimepicker" readonly = "readonly" style="width:30%" value=""/>
			</div>
			<div class="field-wrap">
				<label>水费</label><input type="text"  placeholder="水费" id="water_price" value="${sdjfVO.water_price }"/>
			</div>
			<div class="field-wrap">
				<label>电费</label><input type="text"  placeholder="电费" id="electricity" value="${ sdjfVO.electricity }"/>
			</div>
			<button type="button" class="green"  onclick="add()" id="save">保存</button> 
			
		</div>
	</section>
</body>
</html>
<script>
	$(function(){
		//用户账号列表初始化
		initAccountSelect();
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
			} 
		}); 
		if($("#period").val() !=""){
			$("#datetimepicker").val(getPeriod($("#period").val()));
		}
		//查看情况下的初始化，将保存按钮屏蔽
		if("${flag}" == "3"){
			$("#save").css("display","none");
			$("#water_price").attr("readonly",true);
			$("#electricity").attr("readonly",true);
			$("#accountSelect").attr("disabled","disabled");
			$("#datetimepicker").attr("disabled","disabled");

		}
		//修改的情况
		else if("${flag}" == "2"){
			$("#accountSelect").attr("disabled","disabled");
			$("#datetimepicker").attr("disabled","disabled");
		}
	})

	
	
	function add(){	
		var param = {};
		//水费
		var water_price = $("#water_price").val();
		//电费
		var electricity = $("#electricity").val();
		var user_id = $("#accountSelect").val();
		var id = $("#id").val() == ""?-1: $("#id").val();
		var period = $("#datetimepicker").val();
		console.info(period);
		if(period == ""){
			alert("期间不能为空");
			return ;
		}
		param.water_price = water_price;
		param.electricity = electricity;
		param.user_id = user_id;
		param.id = id;
		param.period = period + "-01";
		console.info(param);
		 $.ajax({ 
	        type:"POST", 
	        url:"/grad/sdjf/addSdjf.do", 
	        dataType:"json",      
	        contentType:"application/json",               
	       	data:JSON.stringify(param), 
	        success:function(data){ 
	            alert(data.msg);
	             if(data.flag == true){
	            	var user_id = parent.$("#accountSelect").val();
	            	var period = parent.$("#datetimepicker").val();
	            	period = period +"-01";
					parent.query(user_id,period);
					parent.$("#closeImg").click();
				} 
	        } 
	    }); 
	}
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
</script>
function showMenu(item, ulClass) {
	$(item).find(ulClass).slideToggle("fast");
}

function closeAlert() {
	$(".green").css("display", "none")
}
//宽 高 连接 题目
function openWindow(width,height,src,title){
	var innerHeight = document.body.clientHeight ;
	var innerWidth = document.body.clientWidth ;
	var wid = (innerWidth-width)/2;
	var hei = (innerHeight - height)/2;
	var div = "<div class='iframeDiv' " +
			"style='left:"+wid+"px; " +
			"top:"+(hei+50)+"px; " +
			"width:"+width+"px;" +
			" height:"+height+"px;" +
		    "position:absolute;" +
		    "z-index:999;" +
		    "' class='iframeClass'>"+
			"<div class='titleDiv'>" +
			"<span style='line-height: 50px;'>"
			+title+"</span>" +
			"<button class='green' style='float: right;' onclick='closeWindow()' id='closeImg'>关闭</button>" +
			"</div>" +
			"<iframe src="+src+" id='secondIframe' style='width:"+width+"px; " +
					"height:"+height+"px;'></ifame>" +
					"</div>";
	$("body").append(div);
}

function closeWindow(){
	$("body").find(".iframeDiv").remove();
}

function getData(item,dictionary_id,defalutValue,isOnlyRead,change){
	this.change = change;
	this.isOnlyRead = isOnlyRead == undefined?false:isOnlyRead;
	console.info(this.isOnlyRead)
	$.ajax({
		url:"/grad/dicionary/getContentById?id=" + dictionary_id,
		type:"get",
		async:true,
		success:function(data){
			var select = "<select id='select"+dictionary_id+"' >";
			var option = "";
			$.each(data,function(index,item){
				option += "<option value='"+item.code+"'>"+item.content+"</option>";
			})
			select +=option;
			select +="</select>";
			$(item).append(select);
			$("#select"+dictionary_id).change(function(){
				console.info('a')
				if(typeof(change) == "function" || change !=undefined){
					change();
				}
			})
			
			$("#select"+dictionary_id).find("option[value='"+defalutValue+"']").attr("selected",true);
			if(isOnlyRead == true){
				$("#select"+dictionary_id).attr("disabled","disabled")
			}else{
				$("#select"+dictionary_id).attr("disabled",false)
			}
		}
	})
}

function getBTDate(str){  
	if(str == null){
		return "";
	}
    var oDate = new Date(str),  
    oYear = oDate.getFullYear(),  
    oMonth = oDate.getMonth()+1,  
    oDay = oDate.getDate(),  
    oHour = oDate.getHours(),  
    oMin = oDate.getMinutes(),  
    oSen = oDate.getSeconds(),  
    oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
    return oTime;  
};  
//补0操作  
function getzf(num){  
    if(parseInt(num) < 10){  
        num = '0'+num;  
    }  
    return num;  
}  

function getPeriod(str){

	if(str == null){
		return "";
	}
	var oDate = new Date(str),  
    oYear = oDate.getFullYear(),  
    oMonth = oDate.getMonth()+1,  
    oDay = oDate.getDate(),  
    oHour = oDate.getHours(),  
    oMin = oDate.getMinutes(),  
    oSen = oDate.getSeconds(),  
    oTime = oYear +'-'+ getzf(oMonth);
	return oTime;  
}

function formatPrice(price){
	return parseFloat(price).toFixed(2);
}

function page(pageDataNum){
	console.info("pageing")
	var trs = $("#tbody").find("tr");
	var html = "";
	for(var i = 0;i < pageDataNum;i++){
		html += $(trs[i]).html();
	}
	$("#tbody").html("");
	$("#tbody").append(html);
	$("#page").paging({
		pageNo:0,
		totalSize: trs.length,
		totalPage:parseInt(trs.length/pageDataNum),
		callback: function(num) {
			var pageHtml = "";
			var num = num-1;
			for(var i = num;i < num+pageDataNum;i++){
				pageHtml += $(trs[i]).html();
			}
			$("#tbody").html("");
			$("#tbody").append(pageHtml);
		}
	})
}

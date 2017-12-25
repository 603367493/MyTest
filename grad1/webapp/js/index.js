var newsAry = [];
$(function() {
	var root = '/grad';
	$.ajax({
		type: "get",
		async: true,
		url: root+"/news/getNews.do",
		success: function(data) {
			newsAry = data;
			var html ="";
			$.each(data,function(index,item){
				var unixTimestamp = new Date(item.publish_time) ;
				commonTime = unixTimestamp.toLocaleString();
				html +="<tr onclick='showContent("+item.id+")' >";
				html +="<td style='width: 50%;text-align: center;'>"+(index+1)+"</td>";
				html +="<td style='width: 30%;text-align: left;'>"+item.title+"</td>";
				html +="<td style='width: 20%;text-align: right;'>"+commonTime+"</td>"
				html +="</tr>";
			});
			console.info(html);
			
			$("#tbody").append(html);
		}
	})

});

//日期转换格式
Date.prototype.toLocaleString = function() {
    return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
};

//展示新闻
function showContent(id){
	$("#flot-example-2").html("");
	if(newsAry != []){
		$.each(newsAry,function(index,item){
			if(item.id == id){
				var html = "";
				html +="<span >"+item.content+"</span>";
				$("#flot-example-2").append(html);
			}
		})
		

	}
}

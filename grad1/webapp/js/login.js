$(function() {
	console.info("aaaaaaaaaaaa");
	
});



function login(){
	console.info("bbbbbbbbbbbbbbb");
	var acc = $("#acc").val();
	var pwd = $("#pwd").val();
	$.post("/grad/user/login.do",{
		acc:acc,pwd:pwd
	},function(data){
		if(data.flag == "true"){
			window.location.href="/grad/home/toHomeUI.do";
		}else{
			alert(data.msg);
		}
	})
}



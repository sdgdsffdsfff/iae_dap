//登录
function login() {
	$("#error").html("");
	var user_name = $("#user_name").val();
	var user_password = $("#user_password").val();
	if(user_name==null||user_name==""){
		$("#error").html("用户名不能为空");
		return false;
	}
	if(user_password==null||user_password==""){
		$("#error").html("密码不能为空");
		return false;
	}
	var params = {
		"user_name" : user_name,
		"user_password" : user_password
	};
	$.ajax( {
		type : "POST",
		url : basePath+"cntv/getLoginInfo.json",
		data : params,
		dataType : "text",
		success : function(json) {
			if (json == null || json == "") {
				$("#error").html("用户名或密码不正确");
			} else {
				var obj = $.parseJSON(json);
				if(obj.flag=="0"){
					window.location.href = basePath+"adminMain.jsp";
				}else if(obj.flag=="1"){
					window.location.href = basePath+"userMain.jsp";
				}else{
					window.location.href = basePath+"login.jsp";
				}
			}
		},
		error : function(json) {
			return false;
		}
	});
}
//其他页面验证登录
function checkLogin(){
	$.ajax( {
		type : "POST",
		url : basePath+"cntv/getCheckLogin.json",
		async:false,
		dataType : "text",
		success : function(json) {
				if(json==null||json==""){
					parent.window.location.href = basePath+"login.jsp";
				}
		},
		error : function(json) {
			parent.window.location.href = basePath+"login.jsp";
			return false;
		}
	});
}
//退出登录后刷新管理员页面判断
function checkAdmin(flag){
	if(flag=="1"){
		window.location.href = basePath+"userMain.jsp";
	}
}
//退出登录后刷新普通用户页面判断
function checkUser(flag){
	if(flag=="0"){
		window.location.href = basePath+"adminMain.jsp";
	}
}
//退出管理员重新登录普通用户后判断
function checkFlag(flag){
	if(flag=="1"){
		top.document.location.href = basePath+"userMain.jsp";
	}
}

//登录页面验证用户是否已经登录
function checkLoginInfo(){
	$.ajax( {
		type : "POST",
		url : basePath+"cntv/getCheckLogin.json",
		dataType : "text",
		success : function(json) {
			var obj = $.parseJSON(json);
			if(obj.flag=="0"){
				window.location.href = basePath+"adminMain.jsp";
			}else if(obj.flag=="1"){
				window.location.href = basePath+"userMain.jsp";
			}
		}
	});
}
//退出登录
function loginOut(){
	if (confirm("确认退出登录？")) {
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getLoginOut.json",
			dataType : "text",
			success : function(json) {
				var obj = $.parseJSON(json);
				if(obj.message=="OK"){
					window.location.href='login.jsp';
				}else{
					return false;
				}
			},
			error : function(json) {
				return false;
			}
		});
	}

		
}
//var c_width = 1000;
//var c_height = 700;

//打开弹出层
function colorBox(url,c_width,c_height){
	$.fn.colorbox({iframe:true,innerWidth:c_width, innerHeight:c_height, href:url,overlayClose:false,onClosed:function (){}});
}
//关闭弹出层
function closeColorBox(getdata){
	$.fn.colorbox.close();
	if(getdata!="-1"){
		mainframe.window.runGetData(getdata);
	}
}

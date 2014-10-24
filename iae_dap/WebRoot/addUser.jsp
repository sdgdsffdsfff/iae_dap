<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>adduser</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var power = "";
	checkLogin();
	</script>
</head>
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<div class="adduser">
		<form action="">
		<table class="user_table">
			<tr>
				<td width="52" align="left"><span>用户名</span></td>
				<td><input type="text" maxlength=16 id="uname" name="uname" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" /><span id = "user_error"></span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>密码</span></td>
				<td><input type="password" maxlength=16 id="psw" name="psd"/><span id = "password_error"></span></td>
			</tr>
			<tr>
				<td width="100" align="left"><span>再次输入密码</span></td>
				<td><input type="password" maxlength=16 id="psw1" name="psd1"/><span id = "password_error1"></span></td>
			</tr>
			<!--<tr>
				<td width="52" align="left"><span>权限</span></td>
				<td><input type="radio" value="管理员" id="quanxian" name="quanxian" class="radio_input" /><span class="radio_text">管理员</span><input type="radio" value="普通用户"id="quanxian" name="quanxian" class="radio_input" checked="checked"/><span class="radio_text">普通用户</span></td>
			</tr>
			--><tr>
				<td width="52" align="left"><span>状态</span></td>
				<td><input type="radio" value="正常" id="status" name="status" class="radio_input" checked="checked"/><span class="radio_text">正 常</span><input type="radio" value="已删除" id="status" name="status" class="radio_input"/><span class="radio_text">已删除</span></td>
			</tr>
		</table>
		<div class="btn_box">
			<input type="button" class="btn" value="确定" onclick="add();"/><input type="button" class="btn" value="返回" id="fanhui" onclick="close();"/>
		</div>
		</form>
	</div>

	<script type="text/javascript">
	function add(){
		$("#user_error").html("");
		$("#password_error").html("");
		$("#password_error1").html("");
		var flag = $("input[name='quanxian']:checked").val()=="管理员"?"0":"1";
		var status = $("input[name='status']:checked").val()=="正常"?"0":"1";
		var uname = $("#uname").val();
		var psw = $("#psw").val();
		var psw1 = $("#psw1").val();
		if(uname==""||uname==null){
			$("#user_error").html("用户名不可以为空");
			return false;
		}
		if(uname.length<6||uname.length>16){
			$("#user_error").html("用户名至少6位，最多16位");
			return false;
		}
		if(psw==""||psw==null){
			$("#password_error").html("密码不可以为空");
			return false;
		}
		if(psw.length<6||psw.length>16){
			$("#password_error").html("密码至少6位，最多16位");
			return false;
		}
		if(psw1==""||psw1==null){
			$("#password_error1").html("重复密码不可以为空");
			return false;
		}
		if(psw!=psw1){
			$("#password_error1").html("两次密码输入的不一致");
			return false;
		}
		var params = {
			"user_name" : uname,
			"user_password" : psw,
			"flag" : flag,
			"status" : status
		};
		$.ajax( {
			type : "POST",
			url:basePath+"cntv/getManageUser.json?type=search&checkUser=true&user_name="+uname,
			dataType : "json",
			success : function(json) {
				var obj = json.userInfo;
				if(obj.length<1){
					$.ajax( {
						type : "POST",
						url : basePath+"cntv/getManageUser.json?type=add",
						data : params,
						dataType : "text",
						success : function(json) {
							if(json!=null){
								//window.open('userManager.jsp',"_self");
								window.open('success.jsp',"_self");
							}
						},
						error : function(json) {
							return false;
						}
					});
				}else{
					$("#user_error").html("用户名已经存在");
				}
			},
			error : function(json) {
				return false;
			}
		});
		
	}
	$("#fanhui").click(function (){
		window.open('userManager.jsp',"_self");
	})
	</script>
</body>
</html>

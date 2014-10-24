<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    
    <title>My JSP 'modifyPassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var pk = ${userInfo.pk};
	var power = "";
	checkLogin();
	</script>
  </head>
  
 <body>
  
   <div id ="formArea" class="adduser" style="margin-left:200px;padding-top: 20px;">
		<form action="">
		<table class="user_table">
			<tr>
				<td width="52" align="left"><span>用户名</span></td>
				<td><span id="uname" name="uname">${userInfo.user_name}</span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>旧密码</span></td>
				<td><input type="password" maxlength=16 id="old_psw" name="old_psd"/><span id = "old_password_error"></span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>新密码</span></td>
				<td><input type="password" maxlength=16 id="new_psw" name="new_psd"/><span id = "new_password_error"></span></td>
			</tr>
			<tr>
				<td width="100" align="left"><span>再次输入新密码</span></td>
				<td><input type="password" maxlength=16 id="new_psw1" name="new_psd1"/><span id = "new_password_error1"></span></td>
			</tr>
		</table>
		<br/>
		<div class="btn_box">
			<input type="button" class="btn" value="确定" onclick="add();"/><input type="button" class="btn" value="返回" id="fanhui" onclick="window.parent.closeColorBox('-1');"/>
		</div>
		<div style="padding-top:25px;color:#db0272;text-align:center;width:200px;font-size:14px;">
    		<span id ="success"></span>
    	</div>
		</form>
	</div>
	<div id="tishi" style="width: 120px; margin-left: 300px;  margin-top: 100px; line-height: 50px;font-size:20px; color:#db0272;display:none;">
	<center>密码修改成功
		<div	style="width: 150px;" >
			<input type="button" class="btn" id= "queding" value="确定" style="width: 116px; display: block; background:#db0272;
margin-right: 16px; height: 27px; float: left; border: none; font-size: 16px; color: #fff;
font-weight: bold; cursor: pointer;">
		</div></center>
	</div>
	
	<script type="text/javascript">
	function add(){
		$("#old_password_error").html("");
		$("#new_password_error").html("");
		$("#new_password_error1").html("");
		var old_psw = $("#old_psw").val();
		var new_psw = $("#new_psw").val();
		var new_psw1 = $("#new_psw1").val();
		if(old_psw==""||old_psw==null){
			$("#old_password_error").html("旧密码不可以为空");
			return false;
		}
		if(new_psw==""||new_psw==null){
			$("#new_password_error").html("新密码不可以为空");
			return false;
		}
		if(new_psw.length<6||new_psw.length>16){
			$("#new_password_error").html("新密码至少6位，最多16位");
			return false;
		}
		if(new_psw!=new_psw1){
			$("#new_password_error1").html("两次密码输入的不一致");
			return false;
		}
		var params = {
			"old_password" : old_psw,
			"new_password" : new_psw
		};
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getModifyPassword.json?pk="+pk,
			data : params,
			dataType : "text",
			success : function(json) {
				var obj = $.parseJSON(json);
				if(obj.message=="error"){
					$("#old_password_error").html("旧密码错误");
				}else{
					//$("#success").html("密码修改成功!");
					//window.parent.closeColorBox('-1');
					$("#formArea").hide();
					$("#tishi").show();
					//window.parent.colorBox("modifySuccess.jsp",700,300);
				}
			},
			error : function(json) {
				return false;
			}
		});
		
	}
	$("#queding").click(function (){
			
			window.parent.closeColorBox('-1');
		})
	</script>
  </body>
</html>

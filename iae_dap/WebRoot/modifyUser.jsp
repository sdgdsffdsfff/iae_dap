<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String pk  =  request.getParameter("pk");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>modifyuser</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var pk = '<%=pk %>'
	var power = "";
	checkLogin();
	var userFlag = ${userInfo.flag};
	checkFlag(userFlag);
	</script>
</head>
<body>
	<div class="adduser">
		<form action="">
		<table class="user_table">
			<tr>
				<td width="52" align="left"><span>用户名</span></td>
				<td><span id="uname" name="uname"></span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>权限</span></td>
				<td><input type="radio" value="管理员" id="gly" name="quanxian" class="radio_input"/><span class="radio_text">管理员</span><input type="radio" value="普通用户"id="ptyh" name="quanxian" class="radio_input"/><span class="radio_text">普通用户</span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>状态</span></td>
				<td><input type="radio" value="正常" id="zhengchang" name="status" class="radio_input"/><span class="radio_text">正 常</span><input type="radio" value="已删除" id="shanchu" name="status" class="radio_input"/><span class="radio_text">已删除</span></td>
			</tr>
		</table>
		<div class="btn_box">
			<a href="#"><input type="button" class="btn" value="确定" onclick="modify();"/></a><a href="###"><input type="button" class="btn" value="返回" id="fanhui"/></a>
		</div>
		</form>
	</div>
	
	
	<script type="text/javascript">
	$(document).ready(function() {
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getManageUser.json?type=search&pk="+pk,
			dataType : "text",
			success : function(json) {
				obj = $.parseJSON(json);
				for(i=0;i<obj.userInfo.length;i++){
					$("#uname").html(obj.userInfo[i].user_name);
					if(obj.userInfo[i].flag=="0"){
						$("input[name=quanxian][value='管理员']").attr("checked","checked");
						
					}else if(obj.userInfo[i].flag=="1"){
						$("input[name=quanxian][value='普通用户']").attr("checked","checked");
					}
					if(obj.userInfo[i].status=="0"){
						$("input[name=status][value='正常']").attr("checked",true);
					}else if(obj.userInfo[i].status=="1"){
						$("input[name=status][value='已删除']").attr("checked",true);
					}
				}
			},
			error : function(json) {
				return false;
			}
		});
	}); 
	function modify(){
		var flag = $("input[name='quanxian']:checked").val()=="管理员"?"0":"1";
		var status = $("input[name='status']:checked").val()=="正常"?"0":"1";
		var params = {
			"flag" : flag,
			"status" : status
		};
		
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getManageUser.json?type=update&pk="+pk,
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
	}
	$("#fanhui").click(function (){
		window.open('userManager.jsp',"_self");
	})
	</script>
</body>
</html>

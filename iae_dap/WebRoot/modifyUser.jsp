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
				<td width="52" align="left"><span>�û���</span></td>
				<td><span id="uname" name="uname"></span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>Ȩ��</span></td>
				<td><input type="radio" value="����Ա" id="gly" name="quanxian" class="radio_input"/><span class="radio_text">����Ա</span><input type="radio" value="��ͨ�û�"id="ptyh" name="quanxian" class="radio_input"/><span class="radio_text">��ͨ�û�</span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>״̬</span></td>
				<td><input type="radio" value="����" id="zhengchang" name="status" class="radio_input"/><span class="radio_text">�� ��</span><input type="radio" value="��ɾ��" id="shanchu" name="status" class="radio_input"/><span class="radio_text">��ɾ��</span></td>
			</tr>
		</table>
		<div class="btn_box">
			<a href="#"><input type="button" class="btn" value="ȷ��" onclick="modify();"/></a><a href="###"><input type="button" class="btn" value="����" id="fanhui"/></a>
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
						$("input[name=quanxian][value='����Ա']").attr("checked","checked");
						
					}else if(obj.userInfo[i].flag=="1"){
						$("input[name=quanxian][value='��ͨ�û�']").attr("checked","checked");
					}
					if(obj.userInfo[i].status=="0"){
						$("input[name=status][value='����']").attr("checked",true);
					}else if(obj.userInfo[i].status=="1"){
						$("input[name=status][value='��ɾ��']").attr("checked",true);
					}
				}
			},
			error : function(json) {
				return false;
			}
		});
	}); 
	function modify(){
		var flag = $("input[name='quanxian']:checked").val()=="����Ա"?"0":"1";
		var status = $("input[name='status']:checked").val()=="����"?"0":"1";
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

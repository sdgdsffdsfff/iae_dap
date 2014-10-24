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
				<td width="52" align="left"><span>�û���</span></td>
				<td><input type="text" maxlength=16 id="uname" name="uname" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" /><span id = "user_error"></span></td>
			</tr>
			<tr>
				<td width="52" align="left"><span>����</span></td>
				<td><input type="password" maxlength=16 id="psw" name="psd"/><span id = "password_error"></span></td>
			</tr>
			<tr>
				<td width="100" align="left"><span>�ٴ���������</span></td>
				<td><input type="password" maxlength=16 id="psw1" name="psd1"/><span id = "password_error1"></span></td>
			</tr>
			<!--<tr>
				<td width="52" align="left"><span>Ȩ��</span></td>
				<td><input type="radio" value="����Ա" id="quanxian" name="quanxian" class="radio_input" /><span class="radio_text">����Ա</span><input type="radio" value="��ͨ�û�"id="quanxian" name="quanxian" class="radio_input" checked="checked"/><span class="radio_text">��ͨ�û�</span></td>
			</tr>
			--><tr>
				<td width="52" align="left"><span>״̬</span></td>
				<td><input type="radio" value="����" id="status" name="status" class="radio_input" checked="checked"/><span class="radio_text">�� ��</span><input type="radio" value="��ɾ��" id="status" name="status" class="radio_input"/><span class="radio_text">��ɾ��</span></td>
			</tr>
		</table>
		<div class="btn_box">
			<input type="button" class="btn" value="ȷ��" onclick="add();"/><input type="button" class="btn" value="����" id="fanhui" onclick="close();"/>
		</div>
		</form>
	</div>

	<script type="text/javascript">
	function add(){
		$("#user_error").html("");
		$("#password_error").html("");
		$("#password_error1").html("");
		var flag = $("input[name='quanxian']:checked").val()=="����Ա"?"0":"1";
		var status = $("input[name='status']:checked").val()=="����"?"0":"1";
		var uname = $("#uname").val();
		var psw = $("#psw").val();
		var psw1 = $("#psw1").val();
		if(uname==""||uname==null){
			$("#user_error").html("�û���������Ϊ��");
			return false;
		}
		if(uname.length<6||uname.length>16){
			$("#user_error").html("�û�������6λ�����16λ");
			return false;
		}
		if(psw==""||psw==null){
			$("#password_error").html("���벻����Ϊ��");
			return false;
		}
		if(psw.length<6||psw.length>16){
			$("#password_error").html("��������6λ�����16λ");
			return false;
		}
		if(psw1==""||psw1==null){
			$("#password_error1").html("�ظ����벻����Ϊ��");
			return false;
		}
		if(psw!=psw1){
			$("#password_error1").html("������������Ĳ�һ��");
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
					$("#user_error").html("�û����Ѿ�����");
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

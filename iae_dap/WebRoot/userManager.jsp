<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

<head>

	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>UserManager</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var power = "";
	checkLogin();
	var userFlag = ${userInfo.flag};
	checkFlag(userFlag);
	</script>
</head>
<base target="_self" />
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<div class="contain" style="width: 950px;">
		<div class="user_contain" style="width: 830px;">
			<div class="search_box">
				<span class="search_text">�û���&nbsp;</span>
				<span class="select_date"><input type="text" id="user_name" class="inner_text_input user_input" maxlength=16 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></span>
				<span class="search_text" style="display:none;" id="success">�������óɹ�</span>
				<div class="sear_set"><a href="###" onclick="addUser();">�����û�</a></div>
				<div class="sear_set"><a href="###" onclick="searchUser();">��ѯ</a></div>
			</div>
			<div id ="dataArea">
				<table class="user" ellspacing="0" cellpadding="0" border="0" id="userTable">
					<tbody>
						<tr class="user_header"><td class="u_index"><span>���</span></td><td class="u_name"><span>�û���</span></td><td class="u_name"><span>Ȩ��</span></td><td class="u_status"><span>״̬</span></td><td class="u_option"><span>����</span></td></tr>
					</tbody>
				</table>
			</div>
			<div id ="tishi" style="display:none;" >û�в鵽��������������</div>
		</div>
	</div>

	<script type="text/javascript" src="js/usermanage.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			loadUserData();
		});
	</script>
</body>
</html>
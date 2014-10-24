<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var pk = ${userInfo.pk};
	var power = "";
	checkLogin();
	var userFlag = ${userInfo.flag};
	checkFlag(userFlag);
	</script>

  </head>
  
  <body>
    <center>
			<div
				style="width: 120px;   margin-top: 100px; line-height: 50px; color:#db0272;">密码修改成功
				<div
					style="width: 150px;" >
					<input type="button" class="btn" id= "queding" value="确定" style="width: 116px; display: block; background:#db0272;
		margin-right: 16px; height: 27px; float: left; border: none; font-size: 16px; color: #fff;
		font-weight: bold; cursor: pointer;">
				</div>
			</div>
			
		</center>
	<script type="text/javascript">
		$("#queding").click(function (){
			
			window.parent.closeColorBox('-1');
		})
	</script>
  </body>
</html>

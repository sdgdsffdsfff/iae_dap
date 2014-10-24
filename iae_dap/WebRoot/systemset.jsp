<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>SystemSet</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<div class="contain">
		<div class="sys_contain">
			<div >
				
				

			<div class="quzhong" id="load" >
				
			</div>
			<div class="quzhong" id="systemInfo" >
				
				<div style="float:none;height:100px;">
				<h2>用户质量权重</h2>
				<div style="margin-top:20px;">
				开机时长:<input style="width:40px;margin-right:40px;" type ="text"  value="0.316"  />
				播放次数:<input style="width:40px;margin-right:40px;" type ="text"  value="0.323"  />
				开机时刻:<input style="width:40px;margin-right:40px;" type ="text"  value="0"  />
				开机时刻sin:<input style="width:40px;margin-right:40px;" type ="text"  value="0.361"  />
				
				</div>
				</div>
				
				
				<div style="float:none;height:100px;">
				<h2 >用户时长权重</h2>
				<div style="margin-top:20px;">
				超长用户:<input  style="width:40px;margin-right:40px;" type ="text"  value="0.95"  />
				潜在用户:<input  style="width:40px;margin-right:40px;" type ="text"  value="0.75"  />
				短时用户:<input  style="width:40px;margin-right:40px;" type ="text"  value="0.25"  />
				</div>
				</div>
				
				
				<div style="float:none;height:100px;">
				<h2>用户质量比例阈值</h2>
				<div style="margin-top:20px;">
				<span style="clear:both;">高质量用户:</span><input  style="width:40px;" type ="text"  value="5"  /><span style="clear:both;margin-right:40px;">%</span>
				<span style="clear:both;">潜力用户:</span><input  style="width:40px;" type ="text"  value="10"  /><span style="clear:both;margin-right:40px;">%</span>
				<span style="clear:both;">普通用户:</span><input  style="width:40px;" type ="text"  value="35"  /><span style="clear:both;margin-right:40px;">%</span>
				<span style="clear:both;">低质量用户:</span><input  style="width:40px;" type ="text"  value="50"  /><span style="clear:both;margin-right:40px;">%</span>
				</div>
				</div>
				
				
				<div class="sys_set" style="position:relative;left:35%;top:100px;"><a href="#" onclick="update();">保存修改</a></div>
			</div>
		</div>
	</div>
	</div>
	
</body>
</html>

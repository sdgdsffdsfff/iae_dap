<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>shishijiankong</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/date.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var power = "";
	checkLogin();
	var currDD = "";
	var currSS = "";
	var currSC = "";
	var currLL = "";
	</script>
</head>
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<input type="hidden" id="user_name" value="${userInfo.user_name}"/>
	<div class="vspace" style="height:28px;"></div>
	<div class="csmB_ind001">
		<div id="chart_box_01" ><div style="padding-top:50px;padding-left:200px;"><img width="70" height="70" src="style/img/loadingNew.gif"/></div></div>
		<div class="channel_set"><a href="###" id="box01" onclick="openChnnl('5','5');">频道设置</a></div>
		<div id="chart_box_02"><div style="padding-top:50px;padding-left:200px;"><img width="70" height="70" src="style/img/loadingNew.gif"/></div></div>
		<div class="channel_set r-t"><a href="###" id="box02" onclick="openChnnl('6','5');">频道设置</a></div>
		<div id="chart_box_03"><div style="padding-top:50px;padding-left:200px;"><img width="70" height="70" src="style/img/loadingNew.gif"/></div></div>
		<div class="channel_set l-b"><a href="###" id="box03" onclick="openChnnl('7','5');">频道设置</a></div>
		<div id="chart_box_04"><div style="padding-top:50px;padding-left:200px;"><img width="70" height="70" src="style/img/loadingNew.gif"/></div></div>
		<div class="channel_set r-b"><a href="###" id="box04" onclick="openChnnl('8','5');">频道设置</a></div>
	</div>
	<script type="text/javascript" src="js/shishijiankong.js"></script>
	<script type="text/javascript">
	//打开频道设置页面
	function openChnnl(type,getdata){
		window.parent.colorBox('pindaoshezhi.jsp?type='+type+'&getdata='+getdata,1000,700);
	}
	function runGetData(getdata){
		if(getdata=="5"){
			loadDataRTDD(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=5&initial=5&searchSource=shishiDD');
			loadDataRTSS(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=6&initial=5&searchSource=shishiSS');
			loadDataRTSC(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=7&initial=5&searchSource=shishiSC');
			loadDataRTLL(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=8&initial=5&searchSource=shishiLL');
		}
	}
	$(document).ready(function(){
		loadDataRTDD(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=5&initial=5&searchSource=shishiDD');
		loadDataRTSS(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=6&initial=5&searchSource=shishiSS');
		loadDataRTSC(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=7&initial=5&searchSource=shishiSC');
		loadDataRTLL(basePath+'cntv/getShiShiJianKong.json?user_name=${userInfo.user_name}&type=8&initial=5&searchSource=shishiLL');
	});
	</script>
</body>
</html>

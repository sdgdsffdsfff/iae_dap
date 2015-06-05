<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>歌华智能引擎数据分析系统</title>
	<link rel="stylesheet" type="text/css" href="style/login.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
	var basePath = '<%=basePath %>';
	checkLoginInfo();
	</script>
</head>
<body>
		<div id="bg" style="position:absolute; left:0px; top:0px; z-index:-999">
			<img src="image/bgbgbg.jpg"/>
		</div>
		<!--<div style="opacity: 0.4;"><img src="image/dalogo.png" alt="" /></div>-->
		<div class="bg_center_v">
	        <div class="logo_1110_131012" style="position:relative;left:60%;">
	             <img src="image/1.png" width="426" height="242"> 
	        </div>
	        <div class="title_1110_131012">
	        	<!--<h1>央视-索福瑞IPTV收视分析系统</h1>-->
	        	<!-- <img src="style/img/654321.png" width="380" height="28"/> -->
	        </div>
	        <div class="yuanlogo_1110_131012">
	        	<img src="style/img/12345.png" width="510" height="485" />
	        </div>
        	<div class="login_131012">
        		<form action="">
        		<table class="login_table">
        			<tr>
        				<td align="center" width="27"><img src="image/user.png" height="18" width="18"/></td>
        				<td width="60" align="middle"><span>username</span></td>
        				<td><input id="user_name" type="text" maxlength=16 onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></td>
        			</tr>
        			<tr class="spe_tr"></tr>
        			<tr>
        				<td align="center" width="27"><img src="image/password.png" height="18" width="14"></td>
        				<td width="60" align="middle"><span>password</span></td>
        				<td><input type="password" id="user_password"  maxlength=16 value=""/></td>
        			</tr>
        		</table>
        		<div class="btn_box">
        			<a href="#"><input type="button" class="btn" value="登录" onclick="login()"/></a><a href="#"><input type="reset" class="btn" value="重置"/></a>
        		</div>
        		</form>
        	</div>
		</div>
    	<div class="bottom_t">
    		<img src="style/img/slogn.png" height="16" width="580"/>
    	</div>
    	<!--<div class="spe_logo"><img src="image/dalogo.png" height="200" width="200"/></div>-->
    	<!--<div class="bottom_b">
			<img src="style/img/_bg_bottom.png" width="3200" height="27"/>
		</div>-->
		<script type="text/javascript">
		document.onkeydown = function (e) { 
			var theEvent = window.event || e; 
			var code = theEvent.keyCode || theEvent.which; 
			if (code == 13) { 
				login(); 
			} 
		} 
		 window.onresize = window.onload = function(){
		     var w,h
		     if(!!(window.attachEvent && !window.opera))
		     {
		      h = document.documentElement.clientHeight;
		      w = document.documentElement.clientWidth;
		     }else{
		      h = window.innerHeight;
		      w = window.innerWidth;
		     }
		    var bgImg = document.getElementById('bg').getElementsByTagName('img')[0];
		    bgImg.width = (w);
		    bgImg.height = (h);  
		    $(".bg_center_v").height(h-47);
		    $(".bottom_l img").width(w-1000);
		   }   
		   
		
		</script>
</body>
</html>

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
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<link type="text/css" rel="stylesheet" href="style/colorBox/colorbox.css" />
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script src="js/jquery.colorbox-min.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var power = "";
	//checkLogin();
	var userFlag = ${userInfo.flag};
	checkAdmin(userFlag);
	</script>
	
</head>
<body style="background-color: rgb(52,145,206);">
	<div class="yuanlogo_1110_131012">
    	<img src="style/img/12345.png" width="510" height="485" />
    </div>
    <div class="vspace" style="height:30px;"></div>
	<div id="top_bar">
		<div class="top_bar_owner">
			 <div class="logo"> <img src="image/1small.png" alt="logo" width="199" height="39"/> </div> 
			<div class="title"><h3>歌华智能引擎数据分析系统</h3></div>
			<div class="login" style="display:none"></div>
			<div class="logout"><span class="name_box">欢迎你&nbsp;&nbsp;&nbsp;<span id="cookie_user_name" style="float: right;"><a style="text-decoration:none;">${userInfo.user_name}</a></span></span><span class="out_box" style="margin-right: 10px;"><a href="#" onclick="modifyPassword();">修改密码</a></span><span class="out_box"><a href="#" onclick="loginOut();">退出</a></span></div>
		</div>
	</div>
	<div id="page_body">
		<div class="vspace" style="height:30px;"></div>
		<div id="tab_container">
			<ul class="tablist" style="padding-left: 0px;">
				<!-- <li class=" border_left_none cur"><a href="#">实时监测</a></li> -->
				<li><a href="#">业务量统计</a></li>
				<li><a href="#">KPI查询</a></li>
				<li><a href="#">用户画像分析</a></li>
				
				<li><a href="#">系统设置</a></li>
				<li><a href="#">用户管理</a></li>
			</ul>
		</div>
		<div class="vspace" style="height:40px;"></div>
		<div id="frame_container" style="width:1000px;height:1030px; ">
	      <div class="iframe_div"><iframe name="mainframe" src="daodalv.jsp" frameborder="0" width="1000px" height="1030px"></iframe></div>
	    </div>
	</div>
	<div class="bottom_t">
		<img src="style/img/slogn.png" height="16" width="580"/>
	</div>
	<script type="text/javascript">
		//var iframe_arr = ['<div class="iframe_div focus"><iframe name="mainframe" src="shishijiankong.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="daodalv.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="shoushilv.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="yonghuhuaxiang.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="systemset.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe id="yonghuguanli" name="mainframe" src="userManager.jsp" frameborder="0" width="1000px" height="600px" style="overflow-y:scroll; overflow-x:hidden"></iframe></div>'];
		
		var iframe_arr = ['<div class="iframe_div"><iframe name="mainframe" src="daodalv.jsp" frameborder="0" width="1000px" height="1030px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="shoushilv.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="yonghuhuaxiang.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe name="mainframe" src="systemset.jsp" frameborder="0" width="1000px" height="600px"></iframe></div>','<div class="iframe_div"><iframe id="yonghuguanli" name="mainframe" src="userManager.jsp" frameborder="0" width="1000px" height="600px" style="overflow-y:scroll; overflow-x:hidden"></iframe></div>'];
		$("#tab_container li").click(function(){
			var num = $(this).index();
			$(this).addClass("cur").siblings("li").removeClass("cur");
			$("#frame_container").html(iframe_arr[num]);
		});
		function modifyPassword(){
			window.parent.colorBox("modifyPassword.jsp",700,300);
			//alert("2223"+$(window.parent.document).find('#uname').html());
			//var k = window.showModalDialog('modifyPassword.jsp',window,"dialogWidth=1000px;dialogHeight=700px");
		}
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Shichangfene</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/getdatashoushi.js"></script>
	<script type="text/javascript" src="js/date.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var param = '';
	var area = "";
	checkLogin();
	</script>
</head>
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<input type="hidden" id="user_name" value="${userInfo.user_name}"/>
	<input type="hidden" id="type" value="3"/>
	<div class="contain">
		<div class="l_contain" id = "tab_change">
			<ul class="tab_change">
				<li class="cur"><a href="#">按天查询</a></li>
				<li><a href="#">按时段查询</a></li>
			</ul>
		</div>
		<div class="r_contain">
			<div class="block" style="display:block">
				<div class="csmB_ind008">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date"><input id="bgDate_d" class="inner_text_input" type="text" readonly="readonly" onclick="WdatePicker({el:'bgDate_d',vel:'bgDate'})"/><input type="hidden" id="bgDate" value="2014-10-22"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgDate_d',vel:'bgDate'})"></a></span>
						<span class="search_text" style="margin-left:10px;">区域&nbsp;</span>
						<span class="select_date"><span class="inner_text" id="area">全省</span><a href!="#" class="search_xiala" id="select_allpro"></a></span>
						<div id="selectAllpro" style="display:none;">
							<ul>
								<li>全省</li>
							</ul>
						</div>
						<span class="alertMes al_1"  id="error1"></span>
						<div class="sear_set"><a href!="###" onclick="getData(0,'11')">查询</a></div>
						<div class="chan_set"><a href!="###" onclick="openChnnl('3','0');">频道设置</a></div>
						<input type="hidden" id="box07" value=""/>
					</div>
					<div class="zhezhao"></div>
					<div id="chart_box_11"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
				<script type="text/javascript">
					$("#select_allpro").click(function (e) {
						$("#error1").html("");
						$("#error2").html("");
						$("#selectAllpro").css("display","block");
						e.stopPropagation();
						$(document).click(function(){
							$("#selectAllpro").css("display","none");
						});
					});
					$("#selectAllpro li").click(function () {
						$("#area").html($(this).text());
						$("#selectAllpro").css("display","none");
					});
				</script>
			</div>
			<div class="block" style="display:none">
				<div class="csmB_ind009">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date" style="width:139px;"><input id="bgTime_d" class="inner_text_input spe_iti" type="text" readonly="readonly" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="bgTime" value="2014-10-22 18:21"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date"style="width:139px;"><input class="inner_text_input spe_iti" id="endTime_d" type="text" readonly="readonly" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="endTime" value="2014-10-22 18:30"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="alertMes al_1" style="display:none" id="alertDate3"></span><span class="alertMes al_1"  id="error2"></span>
						<div class="sear_set"><a href!="###" onclick="getData(1,'12')">查询</a></div>
						<div class="chan_set"><a href!="###" onclick="openChnnl('3','1');">频道设置</a></div>
						<input type="hidden" id="box08" value=""/>
					</div>
					<div class="zhezhao"></div>
					<div id="chart_box_12"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/shichangfene.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="js/getdatashichang.js"></script>
	<script type="text/javascript">
	//打开频道设置页面
	function openChnnl(type,getdata){
		window.parent.colorBox('pindaoshezhi.jsp?type='+type+'&getdata='+getdata,1000,700);
	}
	function runGetData(getdata){
			if(getdata=="0"){
				getData(0,'11');
			}else if(getdata=="1"){
				getData(1,'12');
			}
	}
		$(document).ready(function(){
			//初始加载默认数据
			var curr_riqi =CurentTime('riqi');
			var curr =CurentTime('wanzheng');
			var pre =PreTime('wanzheng',15);
			$("#bgDate_d").val(curr_riqi);
			$("#bgDate").val(curr_riqi);
			$("#bgTime_d").val(pre);
			$("#bgTime").val(pre);
			$("#endTime_d").val(curr);
			$("#endTime").val(curr);
			$("#error1").html("");
			$("#error2").html("");
			loadDataSCD(basePath+'cntv/getSearchDataByDay.json?user_name=${userInfo.user_name}&type=3&bgDate='+curr_riqi+'&area=全省&searchSource=shichangDay');
			$("#tab_change li").click(function () {
				var idx = $(this).index();
				if(idx == 0){
					getData(0,'11');
				}else if(idx == 1){
					getData(1,'12');
				}
			})
		});
		
	</script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>央视 - 索福瑞IPTV收视分析系统</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/highcharts.js" ></script>
	<script type="text/javascript" src="js/exporting.js" ></script>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/date.js"></script>
	<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var param = '';
	var channel = "";
	checkLogin();
	</script>

</head>
<body style="background-color: rgb(228,247,254);height:1000px;">
	<input type="hidden" id="user_name" value="${userInfo.user_name}"/>
	<input type="hidden" id="type" value="1"/>
	<div class="contain">
		<div class="l_contain" id ="tab_change">
			<ul class="tab_change">
				<li class="cur"><a href="#">用户数据量统计</a></li>
				<li ><a href="#" >内容数据量统计</a></li>
				<li><a href="#">回看量</a></li>
				<li><a href="#">点播量</a></li>
			</ul>
		</div>
		<div class="r_contain">
			<div class="block" style="display:block">
				<div class="csmB_ind002">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date"><input id="bgDate_d" type="text" class="inner_text_input" readonly="readonly" onclick="WdatePicker({el:'bgDate_d',vel:'bgDate'})"/><input type="hidden" id="bgDate" onclick="WdatePicker({el:'bgDate_d',vel:'bgDate'})"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgDate_d',vel:'bgDate'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date"><input class="inner_text_input" type="text" id="endDate_d" readonly="readonly" onclick="WdatePicker({el:'endDate_d',vel:'endDate',minDate:'#F{$dp.$D(\'bgDate\',{d:1})}'})"><input type="hidden" id="endDate" /><a href="#" class="search_xiala" onclick="WdatePicker({el:'endDate_d',vel:'endDate',minDate:'#F{$dp.$D(\'bgDate\',{d:1})}'})"></a></span>
						<span class="alertMes al_1" style="display:none" id="alertDate1"></span><span class="alertMes al_1"  id="error1"></span>
						
						<div class="sear_set"><a href!="###" onclick="getData(0,'05')">查询</a></div>
						
						<input type="hidden" id="box01" value=""/>
					</div>
					<div class="zhezhao" ><iframe id="dap_iframe" style="width:850px;height:950px;border:none;position:relative;right:24px;top:20px;" src="./index.jsp"></iframe></div>
					<div id="chart_box_05"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
				<script type="text/javascript">
					$("#bgHour").click(function (e) {
						$("#error1").html("");
						$("#error2").html("");
						$("#error3").html("");
						$("#bghour").css("display","block");
						e.stopPropagation();
						$(document).click(function(){
							$("#bghour").css("display","none");
						});
					});
					$("#bghour li").click(function(){
						var liText = $(this).text();
						$("#bgHour").html(liText);
						$("#bghour").css("display","none");
						
					});
					$("#endHour").click(function (e) {
						$("#error1").html("");
						$("#error2").html("");
						$("#error3").html("");
						$("#endhour").css("display","block");
						e.stopPropagation();
						$(document).click(function(){
							$("#endhour").css("display","none");
						});
					});
					$("#endhour li").click(function(){
						var liText = $(this).text();
						$("#endHour").html(liText);
						$("#endhour").css("display","none");
					});
				</script>
			</div>
			<div class="block" style="display:none">
				<div class="csmB_ind003">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date" style="width:139px;"><input id="bgTime_d" type="text" class="inner_text_input spe_iti" readonly="readonly" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="bgTime" value="2014-10-22 18:21"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date" style="width:139px;"><input class="inner_text_input spe_iti" id="endTime_d" type="text" readonly="readonly" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="endTime" value="2014-10-22 18:30"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="alertMes al_2" style="display:none" id="alertDate2"></span><span class="alertMes al_1"  id="error2"></span>
					
						
						
						<div class="sear_set"><a href!="#" onclick="getData(1,'06')">查询</a></div>
						<!--<div class="chan_set"><a href!="#" onclick="window.open('pindaoshezhi.html');">频道设置</a></div>
						<input type="hidden" id="box02" value=""/>-->
					</div>
					<div class="zhezhao"><iframe id="dap_iframe2" style="width:850px;height:480px;border:none;position:relative;right:24px;top:20px;" src="./contentcount.jsp"></iframe></div>
					<div id="chart_box_06"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
				<script type="text/javascript">
					$("#select_pro").click(function (e) {
						$("#error1").html("");
						$("#error2").html("");
						$("#error3").html("");
						$("#selectPro").css("display","block");
						$("#selectPro").empty();
						$("#selectPro").append("<ul></ul>");
						for(i=0;i<channel.channelList.length;i++){
							$("#selectPro ul").append("<li>" + channel.channelList[i].name + "</li>")
						}
						e.stopPropagation();
						$(document).click(function(){
							$("#selectPro").css("display","none");
						});
						$("#selectPro li").click(function () {
							var text = $(this).text();
							text = text.length>5?text.substr(0,4)+"...":text;
							$("#provice").html(text);
							$("#selectPro").css("display","none");
						});
					});
					
				</script>
			</div>
			<div class="block"  style="display:none">
				<div class="csmB_ind004">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date"><input id="bgDate1_d" class="inner_text_input" type="text" readonly="readonly" onclick="WdatePicker({el:'bgDate1_d',vel:'bgDate1'})"/><input type="hidden" id="bgDate1" value="2014-10-22"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgDate1_d',vel:'bgDate1'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date"><input id="endDate1_d" class="inner_text_input" type="text" readonly="readonly" onclick="WdatePicker({el:'endDate1_d',vel:'endDate1',minDate:'#F{$dp.$D(\'bgDate1\',{d:1})}'})"/><input type="hidden" id="endDate1" value="2014-10-23"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'endDate1_d',vel:'endDate1',minDate:'#F{$dp.$D(\'bgDate1\',{d:1})}'})"></a></span>
						<span class="alertMes al_1" style="display:none" id="alertDate3"></span><span class="alertMes al_1"  id="error3"></span>
						<div class="sear_set"><a href!="###" onclick="getData(2,'07')">查询</a></div>
						
						<input type="hidden" id="box03" value=""/>
					</div>
					<div class="zhezhao"><iframe id="dap_iframe3" style="width:850px;height:480px;border:none;position:relative;right:24px;top:20px;" src="./vodcount.jsp"></iframe></div>
					<div id="chart_box_07"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
			</div>
			
			<div class="block"  style="display:none">
				<div class="csmB_ind004">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date"><input id="bgDate1_d" class="inner_text_input" type="text" readonly="readonly" onclick="WdatePicker({el:'bgDate1_d',vel:'bgDate1'})"/><input type="hidden" id="bgDate1" value="2014-10-22"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgDate1_d',vel:'bgDate1'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date"><input id="endDate1_d" class="inner_text_input" type="text" readonly="readonly" onclick="WdatePicker({el:'endDate1_d',vel:'endDate1',minDate:'#F{$dp.$D(\'bgDate1\',{d:1})}'})"/><input type="hidden" id="endDate1" value="2014-10-23"/><a href!="#" class="search_xiala" onclick="WdatePicker({el:'endDate1_d',vel:'endDate1',minDate:'#F{$dp.$D(\'bgDate1\',{d:1})}'})"></a></span>
						<span class="alertMes al_1" style="display:none" id="alertDate3"></span><span class="alertMes al_1"  id="error3"></span>
						<div class="sear_set"><a href!="###" onclick="getData(2,'07')">查询</a></div>
						
						<input type="hidden" id="box03" value=""/>
					</div>
					<div class="zhezhao"><iframe id="dap_iframe4" style="width:850px;height:480px;border:none;position:relative;right:24px;top:20px;" src="./playcount.jsp"></iframe></div>
					<div id="chart_box_07"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/daodalv.js" ></script>
	<script type="text/javascript" src="js/util.js" ></script>
	<script type="text/javascript" src="js/getdatadaoda.js" ></script>
	<script type="text/javascript">
	//打开频道设置页面
	function openChnnl(type,getdata){
		window.parent.colorBox('pindaoshezhi.jsp?type='+type+'&getdata='+getdata,1000,700);
		//var k = window.showModalDialog('pindaoshezhi.jsp?type='+type,window,"dialogWidth=1000px;dialogHeight=700px");
		/*if(k.type== "OK")//传递回的type为ok的时候才刷新页面。 
		{ 
			if(getdata=="0"){
				getData(0);
			}else if(getdata=="2"){
				getData(2);
			}
		} */
	}
	function runGetData(getdata){
			if(getdata=="0"){
				getData(0,'05')
			}else if(getdata=="2"){
				getData(2,'07')
			}
	}
	$(document).ready(function() {
		
		//初始加载默认数据
		//var curr_shijian =CurentTime('shijian');
		//var pre_shijian =PreTime('shijian',60);
		/* var curr_riqi =CurentTime('riqi');
		var pre_riqi =PreTime('riqi',1440);
		var curr =CurentTime('wanzheng');
		var pre =PreTime('wanzheng',15);
		$("#bgDate_d").val(pre_riqi);
		$("#bgDate").val(pre_riqi);
		$("#endDate_d").val(curr_riqi);
		$("#endDate").val(curr_riqi);
		$("#bgTime_d").val(pre);
		$("#bgTime").val(pre);
		$("#endTime_d").val(curr);
		$("#endTime").val(curr);
		$("#bgDate1_d").val(pre_riqi);
		$("#bgDate1").val(pre_riqi);
		$("#endDate1_d").val(curr_riqi);
		$("#endDate1").val(curr_riqi);
		$("#error1").html("");
		$("#error2").html("");
		$("#error3").html("");
		loadDataDDD(basePath+'cntv/getSearchDataByDay.json?user_name=${userInfo.user_name}&type=1&bgDate='+pre_riqi+'&endDate='+curr_riqi+'&bgHour=19:00&endHour=20:00&searchSource=DaodaDay');
		//初始加载频道列表
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getChannelRelation.json",
			dataType : "text",
			success : function(json) {
				channel = $.parseJSON(json);
			},
			error : function(json) {
				return false;
			} 
		});*/
		$("#tab_change li").click(function () {
			var idx = $(this).index();
			if(idx == 0){
				//getData(0,'05');
				//$('#dap_iframe').attr("src","./index.jsp");
			}else if(idx == 1){
				//getData(1,'06')
				//$('#dap_iframe').attr("src","./index.jsp");
			}else if(idx == 2){
				//getData(2,'07');
				//$('#dap_iframe').attr("src","./index.jsp");
			}
		})
	});
	</script>
</body>
</html>

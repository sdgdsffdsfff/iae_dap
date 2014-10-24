<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>Liuruliuchu</title>
	<link rel="stylesheet" type="text/css" href="style/style.css">
	<script type="text/javascript" src="js/jQuery1.7.2.js"></script>
	<script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script>
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
<body style="background:url('image/bg_sub1.jpg') 0 0 no-repeat transparent;">
	<input type="hidden" id="user_name" value="${userInfo.user_name}"/>
	<input type="hidden" id="type" value="4"/>
	<div class="contain">
		<div class="l_contain" style="border-right:none;">
		</div>
		<div class="r_contain">
			<div class="block" style="display:block">
				<div class="csmB_ind010">
					<div class="search_box">
						<span class="search_text">开始时间&nbsp;</span>
						<span class="select_date" style="width:139px;"><input id="bgTime_d" class="inner_text_input spe_iti" type="text" readonly="readonly" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="bgTime" value="2014-10-22 18:21" /><a href!="#" class="search_xiala" onclick="WdatePicker({el:'bgTime_d',vel:'bgTime',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="search_text">结束时间&nbsp;</span>
						<span class="select_date" style="width:139px;"><input class="inner_text_input spe_iti" id="endTime_d" type="text" readonly="readonly" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"/><input type="hidden" id="endTime" value="2014-10-22 18:30" /><a href!="#" class="search_xiala" onclick="WdatePicker({el:'endTime_d',vel:'endTime',minDate:'#F{$dp.$D(\'bgTime\',{m:1})}',dateFmt:'yyyy-MM-dd HH:mm'})"></a></span>
						<span class="alertMes" style="display:none" id="alertDate2"></span><span class="alertMes al_1"  id="error1"></span>
						<span class="search_text" style="margin-left:10px;">频道&nbsp;</span>
						<span class="select_date"><span class="inner_text" id="prov">湖南卫视</span><a href="#" class="search_xiala" id="select_prov"></a></span>
						<div id="selectProv" style="display: none;">
							<ul>
								<li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li><li>湖南卫视</li><li>湖南经视</li>
							</ul>
						</div>
						<div class="sear_set"><a href!="#" onclick="getData('13')">查询</a></div>
					</div>
					<div class="zhezhao"></div>
					<div id="chart_box_13"><div class="dengdai"><img src="style/img/loadingNew.gif"/></div></div>
				</div>
				<script type="text/javascript">
					$("#select_prov").click(function (e) {
						$("#error1").html("");
						$("#selectProv").css("display","block");
						$("#selectProv").empty();
						$("#selectProv").append("<ul></ul>");
						for(i=0;i<channel.channelList.length;i++){
							$("#selectProv ul").append("<li>" + channel.channelList[i].name + "</li>")
						}
						e.stopPropagation();
						$(document).click(function(){
							$("#selectProv").css("display","none");
						});
						$("#selectProv li").click(function () {
							var text = $(this).text();
							text = text.length>5?text.substr(0,4)+"...":text;
							$("#prov").html(text);
							$("#selectProv").css("display","none");
						});
					});
				</script>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/liuruliuchu.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="js/getdataliuruliuchu.js"></script>
	<script type="text/javascript">
	//打开频道设置页面
	function openChnnl(type,getdata){
		window.parent.colorBox('pindaoshezhi.jsp?type='+type+'&getdata='+getdata,1000,700);
	}
	$(document).ready(function(){
		//初始加载默认数据
		var curr =CurentTime('wanzheng');
		var pre =PreTime('wanzheng',15);
		$("#bgTime_d").val(pre);
		$("#bgTime").val(pre);
		$("#endTime_d").val(curr);
		$("#endTime").val(curr);
		$("#error1").html("");
		loadDataLL(basePath+'cntv/getSearchDataInfo.json?user_name=${userInfo.user_name}&type=4&bgDate='+pre+'&endDate='+curr+'&chnnlName=湖南卫视&searchSource=liuruliuchu');
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
		});
	});
	</script>
</body>
</html>

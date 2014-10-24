<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String type = request.getParameter("type");
String getdata = request.getParameter("getdata");

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style/pindaoshezhi.css" rel="stylesheet" type="text/css" />
<title>频道设置</title>
  <script type="text/javascript" src="js/jQuery1.7.2.js"></script>
  <script type="text/javascript" src="js/login.js"></script>
<script  type="text/javascript">
	var basePath = '<%=basePath %>';
	var param = '';
	checkLogin();
	</script>
</head>

<body>
	<input type="hidden" id="user_name" value="${userInfo.user_name}"/>
	<input type="hidden" id="type" value="<%=type%>"/>
	<input type="hidden" id="getdata" value="<%=getdata%>"/>
	<div class="column_wrapper">
        <div class="shipin_ind01">
            <span class="pindao">频道设置</span><span class="all"><input type="checkbox" name="checkbox" id="allpindao" onclick="allSelect(this);"><em>所有频道</em></span>
        </div>
    </div>
    <div class="column_wrapper" id="all">
        <div class="shipin_ind02">
	        <div class="vspace" style="height:7px;"></div>
	        <div class="title">
	            <input type="checkbox" class="shengneicb" id="allshjshx" onchange="partSelect('allshjshx','shjshx');"><span>省级上星</span>
	        </div>
            <div class="list" id="shjshx">
                <ul>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="hnwsh_shjshx" value="hnwsh_shjshx" /><span>湖南卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="qhwsh_shjshx" value="qhwsh_shjshx"/><span>青海卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="jykt_shjshx" value="jykt_shjshx"/><span>金鹰卡通</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="jswsh_shjshx" value="jswsh_shjshx"/><span>江苏卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="zhjwsh_shjshx" value="zhjwsh_shjshx"><span>浙江卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="anhwsh_shjshx" value="anhwsh_shjshx"><span>安徽卫视</span></li>
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" id="bjwsh_shjshx" value="bjwsh_shjshx"><span>北京卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="tjwsh_shjshx" value="tjwsh_shjshx"><span>天津卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="dfwsh_shjshx" value="dfwsh_shjshx"><span>东方卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="jxwsh_shjshx" value="jxwsh_shjshx"><span>江西卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="shzhwsh_shjshx" value="shzhwsh_shjshx"><span>深圳卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="lnwsh_shjshx" value="lnwsh_shjshx"><span>辽宁卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="dnwsh_shjshx" value="dnwsh_shjshx"><span>东南卫视</span></li>
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" value="lywsh_shjshx" id="lywsh_shjshx"><span>旅游卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="gdwsh_shjshx" value="gdwsh_shjshx"><span>广东卫视</span></li> 
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="hbwsh_shjshx" value="hbwsh_shjshx"><span>湖北卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="gxwsh_shjshx" value="gxwsh_shjshx"><span>广西卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="shdwsh_shjshx" value="shdwsh_shjshx"><span>山东卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="schwsh_shjshx" value="schwsh_shjshx"><span>四川卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="chqwsh_shjshx" value="chqwsh_shjshx"><span>重庆卫视</span></li>  
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" value="ynwsh_shjshx" id="ynwsh_shjshx"><span>云南卫视</span></li> 
                     <li><input type="checkbox" name="shjshx" class="shengneicb" id="henwsh_shjshx" value="henwsh_shjshx"><span>河南卫视</span></li> 
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="hljwsh_shjshx" value="hljwsh_shjshx"><span>黑龙江卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="gzhwsh_shjshx" value="gzhwsh_shjshx"><span>贵州卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="hebwsh_shjshx" value="hebwsh_shjshx"><span>河北卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="jlwsh_shjshx" value="jlwsh_shjshx"><span>吉林卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="shxwsh_shjshx" value="shxwsh_shjshx"><span>陕西卫视</span></li>  
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" value="shxiwsh_shjshx" id="shxiwsh_shjshx"><span>山西卫视</span></li>
                      <li><input type="checkbox" name="shjshx" class="shengneicb" id="nmgwsh_shjshx" value="nmgwsh_shjshx"><span>内蒙古卫视</span></li> 
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="nxwsh_shjshx" value="nxwsh_shjshx"><span>宁夏卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="xzwsh_shjshx" value="xzwsh_shjshx"><span>西藏卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="xjwsh_shjshx" value="xjwsh_shjshx"><span>新疆卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="gswsh_shjshx" value="gswsh_shjshx"><span>甘肃卫视</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="kkwsh_shjshx" value="kkwsh_shjshx"><span>卡酷卫视</span></li>  
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" value="hnwshhd_shjshx" id="hnwshhd_shjshx"><span>湖南卫视高清</span></li>
                      <li><input type="checkbox" name="shjshx" class="shengneicb" id="jswshhd_shjshx" value="jswshhd_shjshx"><span>江苏卫视高清</span></li> 
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="zhjwshhd_shjshx" value="zhjwshhd_shjshx"><span>浙江卫视高清</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="bjwshhd_shjshx" value="bjwshhd_shjshx"><span>北京卫视高清</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="tjwshhd_shjshx" value="tjwshhd_shjshx"><span>天津卫视高清</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="dfwshhd_shjshx" value="dfwshhd_shjshx"><span>东方卫视高清</span></li>
                    <li><input type="checkbox" name="shjshx" class="shengneicb" id="shzhwshhd_shjshx" value="shzhwshhd_shjshx"><span>深圳卫视高清</span></li>  
                    <li class="last"><input type="checkbox" name="shjshx" class="shengneicb" value="hbwshhd_shjshx" id="hbwshhd_shjshx"><span>湖北卫视高清</span></li> 
                     <li><input type="checkbox" name="shjshx" class="shengneicb" id="hljwshhd_shjshx" value="hljwshhd_shjshx"><span>黑龙江卫视高清</span></li>      
                </ul>
                <div class="clear"></div>
            </div>
	     </div>
	     <div class="shipin_ind02">
	        <div class="vspace" style="height:7px;"></div>
	        <div class="title">
	            <input type="checkbox" class="shengneicb" id="allshjfshx" onchange="partSelect('allshjfshx','shjfshx');"><span>省级非上星</span>
	        </div>
            <div class="list" id="shjfshx">
                <ul>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hngj_shjfshx" value="hngj_shjfshx" /><span>湖南国际</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hnjj_shjfshx" value="hnjj_shjfshx"/><span>湖南经视</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hndsh_shjfshx" value="hndsh_shjfshx"/><span>湖南都市</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hnyl_shjfshx" value="hnyl_shjfshx"/><span>湖南娱乐</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="xxdy_shjfshx" value="xxdy_shjfshx"><span>潇湘电影</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hndshj_shjfshx" value="hndshj_shjfshx"><span>湖南电视剧</span></li>
                    <li class="last"><input type="checkbox" name="shjfshx" class="shengneicb" id="hngg_shjfshx" value="hngg_shjfshx"><span>湖南公共</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="jyjsh_shjfshx" value="jyjsh_shjfshx"><span>金鹰纪实</span></li>
                    <li><input type="checkbox" name="shjfshx" class="shengneicb" id="hnjshhd_shjfshx" value="hnjshhd_shjfshx"><span>湖南经视高清</span></li>
                </ul>
                <div class="clear"></div>
            </div>
	     </div>
	     <div class="shipin_ind02">
	        <div class="vspace" style="height:7px;"></div>
	        <div class="title">
	            <input type="checkbox" class="shengneicb" id="allzhyj" onchange="partSelect('allzhyj','zhyj');"><span>中央级</span>
	        </div>
            <div class="list" id="zhyj">
                <ul>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv1_zhyj" value="cctv1_zhyj" /><span>CCTV-1</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv2_zhyj" value="cctv2_zhyj"/><span>CCTV-2</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv3_zhyj" value="cctv3_zhyj"/><span>CCTV-3</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv4_zhyj" value="cctv4_zhyj"/><span>CCTV-4</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv5_zhyj" value="cctv5_zhyj"><span>CCTV-5</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv6_zhyj" value="cctv6_zhyj"><span>CCTV-6</span></li>
                    <li class="last"><input type="checkbox" name="zhyj" class="shengneicb" id="cctv7_zhyj" value="cctv7_zhyj"><span>CCTV-7</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv8_zhyj" value="cctv8_zhyj"><span>CCTV-8</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv9_zhyj" value="cctv9_zhyj"><span>CCTV-9</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv10_zhyj" value="cctv10_zhyj"><span>CCTV-10</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv11_zhyj" value="cctv11_zhyj"><span>CCTV-11</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv12_zhyj" value="cctv12_zhyj"><span>CCTV-12</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctvxw_zhyj" value="cctvxw_zhyj"><span>CCTV新闻</span></li>
                    <li class="last"><input type="checkbox" name="zhyj" class="shengneicb" value="cctvsher_zhyj" id="cctvsher_zhyj"><span>CCTV少儿</span></li>
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctvyy_zhyj" value="cctvyy_zhyj"><span>CCTV音乐</span></li>   
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctvnews_zhyj" value="cctvnews_zhyj"><span>CCTVNEWS</span></li>  
                    <li><input type="checkbox" name="zhyj" class="shengneicb" id="cctv1hd_zhyj" value="cctv1hd_zhyj"><span>CCTV-1高清</span></li>  
                </ul>
                <div class="clear"></div>
            </div>
	     </div>
	     <div class="shipin_ind02">
	        <div class="vspace" style="height:7px;"></div>
	        <div class="title">
	            <input type="checkbox" name="checkbox" class="shengneicb" id="allqt" onchange="partSelect('allqt','qt');" ><span>其他</span>
	        </div>
	        <div class="list" id="qt">
	            <ul>
	               <li><input type="checkbox" name="qt" class="shengneicb" id="klg_qt" value="klg_qt"><span>快乐购</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="klg2_qt" value="klg2_qt"><span>快乐购2台</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="xfjl_qt" value="xfjl_qt"><span>先锋记录</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="xfpy_qt" value="xfpy_qt"><span>先锋乒羽</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="dshzhn_qt" value="dshzhn_qt"><span>电视指南</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="ychh_qt" value="ychh_qt"><span>演唱会</span></li>
                    <li class="last"><input type="checkbox" name="qt" class="shengneicb" id="jkshh_qt" value="jkshh_qt"><span>健康生活</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" value="sherdh_qt" id="sherdh_qt"><span>少儿动画</span></li>
                   <li><input type="checkbox" name="qt" class="shengneicb" id="bjjt_qt" value="bjjt_qt"><span>百家讲坛</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="djsch_qt" value="djsch_qt"><span>顶级赛场</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="mlshsh_qt" value="mlshsh_qt"><span>魅力时尚</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="jddy_qt" value="jddy_qt"><span>经典电影</span></li>
                    <li><input type="checkbox" name="qt" class="shengneicb" id="rbjch_qt" value="rbjch_qt"><span>热播剧场</span></li>
                    <li class="last"><input type="checkbox" name="qt" class="shengneicb" id="cctvhd_qt" value="cctvhd_qt"><span>CCTV高清</span></li>
	            </ul>
	            <div class="clear"></div>
	        </div>
        </div>
        <div class="shipin_ind02">
	        <div class="vspace" style="height:7px;"></div>
	        <div class="title">
	            <input type="checkbox" name="checkbox" class="shengneicb" id="allqtff" onchange="partSelect('allqtff','qtff');" ><span>其他（付费）</span>
	        </div>
	        <div class="list" id="qtff">
	            <ul>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="klchd_qtff" value="klchd_qtff"><span>快乐垂钓</span></li>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="golfwq_qtff" value="golfwq_qtff"><span>高尔夫网球</span></li>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="tywq_qtff" value="tywq_qtff"><span>天元围棋</span></li>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="jslzh_qtff" value="jslzh_qtff"><span>江苏靓妆</span></li>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="sihdy_qtff" value="sihdy_qtff"><span>四海钓鱼</span></li>
	                <li><input type="checkbox" name="qtff" class="shengneicb" id="golfpd_qtff" value="golfpd_qtff"><span>高尔夫频道</span></li>
	            </ul>
	            <div class="clear"></div>
	        </div>
        </div>
    </div>
    
    <div class="buttons">
    	<input type="button" name="保存" class="baocun"  value="保存"  onclick="safeChannel();">
        <input type="button" name="取消" class="quxiao"  value="取消"  onclick="window.parent.closeColorBox('-1');">
    </div>
    <div style="padding-top:25px;color:#585858;text-align:center;">
    	<span id ="error"></span>
    </div>
    <div style="padding-top:2px;color:#585858;text-align:center;">
    	<span id ="tishi1"></span>
    	<span id ="tishi2"></span>
    	<span id ="tishi3"></span>
    </div>
  
    <script type="text/javascript">
		 var user =  $("#user_name").val();
		 var type =  $("#type").val();
		 var getdata =  $("#getdata").val();
		 if(type=='5'||type=='6'||type=='7'||type=='8'){
		 	$("#tishi1").html("友情提示：为了保证实时监控页面的实时有效：");
		 	$("#tishi2").html("1.尽量不要频繁修改频道设置");
		 	$("#tishi3").html("2.最多设置6个频道(流入流出最多3个)");
		 }
    	$(document).ready(
      	  setChannel(user,type)
        );
        function setChannel (user,type) {
            // 将json数据中为1的频道，显示为选中状态
            $.getJSON(basePath+"cntv/getChannelInfo.json?user_name="+user+"&type="+type,function(data){
                for(i=0;i<data.shjshx.length;i++){
                     $.each(data.shjshx[i], function(k, v) {
                        if(v== '1'){
                            document.getElementById(k).checked=true
                            }
                    });
                }
                for(i=0;i<data.shjfshx.length;i++){
                     $.each(data.shjfshx[i], function(k, v) {
                        if(v== '1'){
                            document.getElementById(k).checked=true
                            }
                    });
                }
                 for(i=0;i<data.zhyj.length;i++){
                     $.each(data.zhyj[i], function(k, v) {
                        if(v== '1'){
                            document.getElementById(k).checked=true
                            }
                    });
                }
                 for(i=0;i<data.qt.length;i++){
                     $.each(data.qt[i], function(k, v) {
                        if(v== '1'){
                            document.getElementById(k).checked=true
                            }
                    });
                }
                 for(i=0;i<data.qtff.length;i++){
                     $.each(data.qtff[i], function(k, v) {
                        if(v== '1'){
                            document.getElementById(k).checked=true
                            }
                    });
                }
            });
           	
        }
        
        //各组频道的全选效果
       function partSelect(id,name){

			 var allchecked=$('#'+id).is(':checked');
			 var onechecked=$('input[name="'+name+'"]');
			 allchecked?onechecked.attr('checked',true):onechecked.attr('checked',false);
       }
       //所有频道的全选效果
       function allSelect () {
           if($(arguments[0]).get(0).checked==true){
            $('#all input').each(function(){
                $(this).attr("checked",true);
            });
                
        }else{
            $('#all input').each(function(){
                 $(this).attr("checked",false);
            });  
            }
       }
       
       
       
       //保存频道设置
	function safeChannel(){
		$("#error").html("");
		var shjshx_num = $('input[name="shjshx"]:checked').length;
		var shjfshx_num = $('input[name="shjfshx"]:checked').length;
		var zhyj_num = $('input[name="zhyj"]:checked').length;
		var qt_num = $('input[name="qt"]:checked').length;
		var qtff_num = $('input[name="qtff"]:checked').length;
		if((shjshx_num+shjfshx_num+zhyj_num+qt_num+qtff_num)<1){
				$("#error").html("至少设置1个频道");
				return false;
		}
		if(type=='5'||type=='6'||type=='7'){
			if((shjshx_num+shjfshx_num+zhyj_num+qt_num+qtff_num)>6){
				$("#error").html("实时监控的到达率、收视率、市场份额分别最多设置6个频道");
				return false;
			}
		}
		if(type=='8'){
			if((shjshx_num+shjfshx_num+zhyj_num+qt_num+qtff_num)>3){
				$("#error").html("实时监控的流入流出最多设置3个频道");
				return false;
			}
		}
		
		var param = '{"user_name":"'+$("#user_name").val()+'","type":"'+$("#type").val()+'"';
		$('input[name="shjshx"]:checked').each(function(){
			var chnl=$(this).val();
			param += ',"'+chnl+'":"1"';
		});
		$('input[name="shjfshx"]:checked').each(function(){
			var chnl=$(this).val();
			param += ',"'+chnl+'":"1"';
		});
		$('input[name="zhyj"]:checked').each(function(){
			var chnl=$(this).val();
			param += ',"'+chnl+'":"1"';
		});
		$('input[name="qt"]:checked').each(function(){
			var chnl=$(this).val();
			param += ',"'+chnl+'":"1"';
		});
		$('input[name="qtff"]:checked').each(function(){
			var chnl=$(this).val();
			param += ',"'+chnl+'":"1"';
		});
		param += '}';
		var params = {
			"param" : param
		};
		$.ajax( {
			type : "POST",
			url : basePath+"cntv/getSafeChannel.json",
			data : params,
			dataType : "text",
			success : function(json) {
				//var obj = $.parseJSON(json);
				if(json!=null&&json!=""){
					window.parent.closeColorBox(getdata);
				}
			},
			error : function(json) {
				$("#error").html("保存失败");
				return false;
			}
		});
	}
    </script>
</body>
</html>



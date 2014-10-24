//查询方法，idx的值代表不同的查询方式；0 按天查询；1 按时段查询； 2 按地区查询
function getData (idx,id) {
	// body...
	var user = $("#user_name").val();
	var bd = $("#bgDate").attr("value");
	var ed = $("#endDate").attr("value");
	var bh = $("#bgHour").text();
	var eh = $("#endHour").text();
	var bt = $("#bgTime").attr("value");
	var et = $("#endTime").attr("value");
	var bd1 = $("#bgDate1").attr("value");
	var ed1 = $("#endDate1").attr("value");
	var pro = $("#provice").text();
	var _bd = bd.split("-");
	var _ed = ed.split("-");
	var _bd1 = bd1.split("-");
	var _ed1 = ed1.split("-");
	$("#error1").html("");
	$("#error2").html("");
	$("#error3").html("");
	switch(idx){
		case 0:{
			
			var a = timeBetween(bd,ed);
			if(bd==null||bd==""||ed==null||ed==""){
				$("#alertDate1").html("开始时间或结束时间不可以为空");
				$("#alertDate1").show();
				setTimeout(function(){$("#alertDate1").hide();},5000);
				return;
			}else if(bd>ed){
				$("#alertDate1").html("开始时间不能大于结束时间");
				$("#alertDate1").show();
				setTimeout(function(){$("#alertDate1").hide();},5000);
				return;
			}else if(a>864000000){
				$("#alertDate1").html("开始时间和结束时间必须间隔10天以内");
				$("#alertDate1").show();
				setTimeout(function(){$("#alertDate1").hide();},5000);
				return;
			}else{
				$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
				var url = basePath+'cntv/getSearchDataByDay.json?user_name='+user+'&type=1&bgDate='+bd+'&endDate='+ed+'&bgHour='+bh+'&endHour='+eh+'&searchSource=DaodaDay';
				loadDataDDD(url);
			}
			break;
		}
		case 1:{
			var a = timeBetween(bt,et);
			if(bt==null||bt==""||et==null||et==""){
				$("#alertDate2").html("开始时间或结束时间不可以为空");
				$("#alertDate2").show();
				setTimeout(function(){$("#alertDate2").hide();},5000);
				return;
			}else if(bt>et){
				$("#alertDate2").html("开始时间不能大于结束时间");
				$("#alertDate2").show();
				setTimeout(function(){$("#alertDate2").hide();},5000);
				return;
			}else if(a>21600000){
				$("#alertDate2").html("开始时间和结束时间必须间隔6小时内");
				$("#alertDate2").show();
				setTimeout(function(){$("#alertDate2").hide();},5000);
				return;
			}else{
				$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
				var url = basePath+'cntv/getSearchDataInfo.json?user_name='+user+'&type=1&bgDate='+bt+'&endDate='+et+'&chnnlName='+pro+'&searchSource=DaodaTime';
				loadDataDDT(url);
			}
			break;
		}
		case 2:{
			var a = timeBetween(bd1,ed1);
			if(bd1==null||bd1==""||ed1==null||ed1==""){
				$("#alertDate3").html("开始时间或结束时间不可以为空");
				$("#alertDate3").show();
				setTimeout(function(){$("#alertDate3").hide();},5000);
				return;
			}else if(bd1>ed1){
				$("#alertDate3").html("开始时间不能大于结束时间");
				$("#alertDate3").show();
				setTimeout(function(){$("#alertDate3").hide();},5000);
				return;
			}else if(a>432000000){
				$("#alertDate3").html("开始时间和结束时间必须间隔5天以内");
				$("#alertDate3").show();
				setTimeout(function(){$("#alertDate3").hide();},5000);
				return;
			}else{
				$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
				var url = basePath+'cntv/getSearchDataByArea.json?user_name='+user+'&type=1&bgDate='+bd1+'&endDate='+ed1+'&searchSource=DaodaArea';
				loadDataDDA(url);
			}
			break;
		}
	}
	
}
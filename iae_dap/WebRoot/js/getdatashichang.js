//查询方法，idx的值代表不同的查询方式；0 按天查询；1 按时段查询;
function getData (idx,id) {
	var user = $("#user_name").val();
	// body...
	var bd = $("#bgDate").attr("value");
	var bt = $("#bgTime").attr("value");
	var et = $("#endTime").attr("value");
	var pro = $("#area").text();
	$("#error1").html("");
	$("#error2").html("");
	var a = timeBetween(bt,et);
	if(bt==null||bt==""||et==null||et==""){
		$("#alertDate3").html("开始时间或结束时间不可以为空");
		$("#alertDate3").show();
		setTimeout(function(){$("#alertDate3").hide();},5000);
		return;
	}else if(bt>et){
		$("#alertDate3").html("开始时间不能大于结束时间");
		$("#alertDate3").show();
		setTimeout(function(){$("#alertDate3").hide();},5000);
		return;
	}else if(a>21600000){
		$("#alertDate3").html("开始时间和结束时间必须间隔6小时内");
		$("#alertDate3").show();
		setTimeout(function(){$("#alertDate3").hide();},5000);
		return;
	}else{
		switch(idx){
			case 0:{
				$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
				var url = basePath+'cntv/getSearchDataByDay.json?user_name='+user+'&type=3&bgDate='+bd+'&area='+pro+'&searchSource=shichangDay';
				loadDataSCD(url);
				break;
			}
			case 1:{
				$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
				var url = basePath+'cntv/getSearchDataInfo.json?user_name='+user+'&type=3&bgDate='+bt+'&endDate='+et+'&searchSource=shichangTime';
				loadDataSCT(url);			
				break;
			}
		}
	}
}
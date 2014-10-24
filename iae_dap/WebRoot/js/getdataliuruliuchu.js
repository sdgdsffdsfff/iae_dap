function getData (id) {
	var user = $("#user_name").val();
	var bt = $("#bgTime").attr("value");
	var et = $("#endTime").attr("value");
	var pro = $("#prov").text();
	var a = timeBetween(bt,et);
	$("#error1").html("");
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
	}else if(a>900000){
		$("#alertDate2").html("开始时间和结束时间必须间隔15分钟内");
		$("#alertDate2").show();
		setTimeout(function(){$("#alertDate2").hide();},5000);
		return;
	}else{
		$("#chart_box_"+id).html('<div class="dengdai"><img src="style/img/loadingNew.gif"/></div>');
		var url = basePath+'cntv/getSearchDataInfo.jsonp?callback=?&user_name='+user+'&type=4&bgDate='+bt+'&endDate='+et+'&chnnlName='+pro+'&searchSource=liuruliuchu';
		loadDataLL(url);
	}	
}
//生成图表公共的参数
Highcharts.setOptions({
    global: {
        useUTC: false
    },
    credits: {
        enabled: false
    },
    title: {
    	text: null
    }
});
//按天查询读接口获取数据,查询的时候当满足条件时，getdatashichang.js中的按天查询调用此方法
function loadDataSCD(url){
    $.getJSON(url,function(data){
        var name_arr = [];
        name_arr = data.shichangDay.name;
        if(name_arr=="false"){
        	$("#chart_box_11").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
        var shuju_arr = [];
        shuju_arr = data.shichangDay.data;
        var data_arr = [];
        for (var i = 0; i < data.shichangDay.name.length; i++) {
            data_arr.push([name_arr[i],parseFloat(shuju_arr[i])]);
        }
        shichangDay(data_arr);
    });
}
//按时段查询读接口获取数据,查询的时候当满足条件时，getdatashichang.js中的按天查询调用此方法
function loadDataSCT (url) {
    // body...
    $.getJSON(url,function(data){
        var name_arr = [];
        name_arr = data.shichangTime.name;
        if(name_arr=="false"){
        	$("#chart_box_12").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
        var shuju_arr = [];
        shuju_arr = data.shichangTime.data;
        var data_arr = [];
        for (var i = 0; i < data.shichangTime.name.length; i++) {
            data_arr.push([name_arr[i],parseFloat(shuju_arr[i])]);
        }
        shichangTime(data_arr);
    });
}
//按天查询图表生成方法
function shichangDay (arr) {
	if(arr.length<=0){
		$("#error1").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_11").html('');
		return false;
	}
	// body...
    var optionDayShi = {
        chart: {
            renderTo: 'chart_box_11',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            marginTop: 80
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        exporting: {
            enabled: false
        },
        series: []
    };
	var series = {
			data: []
		};
	series.name = '市场份额';
	series.type = 'pie';
	series.data = arr;
	optionDayShi.series.push(series);
	var chartTime = new Highcharts.Chart(optionDayShi);
}
//按时段查询图表生成方法
function shichangTime (arr) {
	if(arr.length<=0){
		$("#error2").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_12").html('');
		return false;
	}
    // body...
    var optionTimeShi = {
        chart: {
            renderTo: 'chart_box_12',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            marginTop: 80,
            width: 850
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        exporting: {
            enabled: false
        },
        series: []
    };
    var series = {
            data: []
        };
    series.name = '市场份额';
    series.type = 'pie';
    series.data = arr;
    optionTimeShi.series.push(series);
    var chartTime = new Highcharts.Chart(optionTimeShi);

}
//三种查询方式切换
$(".l_contain .tab_change li").click(function () {
	// body...
	var num = $(this).index();
	$(this).addClass("cur").siblings().removeClass("cur");
	$(".r_contain div.block").eq(num).show().siblings(".block").hide();
});
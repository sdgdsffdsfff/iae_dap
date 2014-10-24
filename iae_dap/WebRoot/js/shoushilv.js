//按天查询读接口获取数据,查询的时候当满足条件时，getdatashoushi.js中的按天查询调用此方法
function loadDataSSD (url) {
	// body...
	$.getJSON(url,function(data){
		var name_arr = [];
		name_arr=data.ShoushiDay.name;
		if(name_arr=="false"){
        	$("#chart_box_08").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
		var data_arr_0;
		data_arr_0=data.ShoushiDay.data;
		var date_arr = getDateArrByDay(data.ShoushiDay.beginShijian, data.ShoushiDay.endShijian);
		shoushilvDay(name_arr,data_arr_0,date_arr);
	});
}
//按时段查询读接口获取数据,查询的时候当满足条件时，getdatashoushi.js中的按时段查询调用此方法
function loadDataSST (url) {
	// body...
	$.getJSON(url,function(data){
		var name=data.ShoushiTime.name;
		var data_arr_1 =[];
		data_arr_1=data.ShoushiTime.data;
		var cat_arr = [];
		cat_arr=data.ShoushiTime.categories;
		shoushilvTime(name,data_arr_1,cat_arr);
	});
}
//按地区查询读接口获取数据,查询的时候当满足条件时，getdatashoushi.js中的按地区查询调用此方法
function loadDataSSA (url) {
	// body...
	$.getJSON(url,function(data){
		var name_arr = [];
		name_arr=data.ShoushiArea.name;
		if(name_arr=="false"){
        	$("#chart_box_10").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
		var data_arr_2;
		data_arr_2=data.ShoushiArea.data;
		var area_arr = [];
		area_arr = data.ShoushiArea.area;
		shoushilvArea(name_arr,data_arr_2,area_arr);
		$(".highcharts-legend .highcharts-legend-item rect").attr('height','14');
	});
}
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
//按天查询图表生成方法
function shoushilvDay(name_arr,data_arr,cat_arr){
	if(name_arr.length<=0){
		$("#error1").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_08").html('');
		return false;
	}
	// body...
	var optionsDayShou = {
		chart: {
	        renderTo: 'chart_box_08',
	        defaultSeriesType: 'line',
	        marginTop: 80
	    },
        xAxis: {
	        categories: []
	    },
	    yAxis: {
	        title: {
	            text: ''
	        },
	        min: 0
	    },
	    exporting: {
            enabled: false
        },
	    series: []
	};
	//压入横轴名称
	for (var j = 0; j < cat_arr.length; j++) {
		optionsDayShou.xAxis.categories.push(cat_arr[j]);
	};
	for(var k=0;k<name_arr.length;k++){
   	 //压入数据
		var series = {
		    data: []
		};
		series.name = name_arr[k];
		for(var i=0;i<data_arr[k].length;i++)
			series.data.push(parseFloat(data_arr[k][i]));
		optionsDayShou.series.push(series);
	}
    // Create the chart
    var chartDay = new Highcharts.Chart(optionsDayShou);
}
function shoushilvTime ( name, data_arr, cat_arr){
	if(data_arr.length<=0){
		$("#error2").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_09").html('');
		return false;
	}
	// body...
	var optionsTimeShou = {
		chart: {
	        renderTo: 'chart_box_09',
	        defaultSeriesType: 'line',
	        width: 850,
	        marginTop: 80
	    },
	    xAxis: {
	        categories: []
	    },
	    yAxis: {
	        title: {
	            text: ''
	        },
	        min: 0
	    },
	    plotOptions: {
            line: {
                 marker:{
                    enabled: false,
                    states: {
                       hover: {
                             enabled: true,
                            symbol: 'circle',
                            radius: 5,
                          lineWidth: 1
                       }
                    }
                }
          }
        },
        tooltip:{
        	formatter: function() {  
                    return '<b>'+ this.series.name +'</b><br/>'+  
                    this.x +': '+ Highcharts.numberFormat(this.y, 4);  
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
	series.name = name;
	for (var j = 0; j < cat_arr.length; j++)
		optionsTimeShou.xAxis.categories.push(cat_arr[j]);
	for (var i = 0; i < data_arr.length; i++)
		series.data.push(parseFloat(data_arr[i]));
	optionsTimeShou.series.push(series);
	var chartTime = new Highcharts.Chart(optionsTimeShou);
}
function shoushilvArea (name_arr, data_arr,area_arr){
	if(data_arr.length<=0){
		$("#error3").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_10").html('');
		return false;
	}
	// body...
	var optionsAreaShou = {
		chart: {
	        renderTo: 'chart_box_10',
	        defaultSeriesType: 'column',
	        width: 850,
	        marginTop: 80
	    },
	    xAxis: {
	        categories: []
	    },
	    yAxis: {
	        title: {
	            text: ''
	        },
	        min: 0
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    exporting: {
            enabled: false
        },
	    series: []
	};
	//压入横轴名称
	for (var j = 0; j < area_arr.length; j++) {
		optionsAreaShou.xAxis.categories.push(area_arr[j]);
	};
	for(var k=0;k<name_arr.length;k++){
   	 //压入数据
		var series = {
		    data: []
		};
		series.name = name_arr[k];
		for(var i=0;i<data_arr[k].length;i++)
			series.data.push(parseFloat(data_arr[k][i]));
		optionsAreaShou.series.push(series);
	}
    // Create the chart
    var chartArea = new Highcharts.Chart(optionsAreaShou);
}
$(".l_contain .tab_change li").click(function () {
	// body...
	var num = $(this).index();
	$(this).addClass("cur").siblings().removeClass("cur");
	$(".r_contain div.block").eq(num).show().siblings(".block").hide();
});
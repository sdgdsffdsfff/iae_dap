//按天查询读接口获取数据,查询的时候当满足条件时，getdatadaoda.js中的按天查询调用此方法
function loadDataDDD (url) {
	// body...
	$.getJSON(url,function(data){
		var name_arr = [];
		name_arr=data.DaodaDay.name;
		if(name_arr=="false"){
        	$("#chart_box_05").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
		var data_arr_0;
		data_arr_0=data.DaodaDay.data;
		var date_arr = getDateArrByDay(data.DaodaDay.beginShijian, data.DaodaDay.endShijian);
		daodalvDay(name_arr,data_arr_0,date_arr);
	});
}
//按时段查询读接口获取数据,查询的时候当满足条件时，getdatadaoda.js中的按时段查询调用此方法
function loadDataDDT (url) {
	// body...
	$.getJSON(url,function(data){
		var name=data.DaodaTime.name;
		var data_arr_1 =[];
		data_arr_1=data.DaodaTime.data;
		var cat_arr = [];
		cat_arr=data.DaodaTime.categories;
		daodalvTime(name,data_arr_1,cat_arr);
	});
}
//按地区查询读接口获取数据,查询的时候当满足条件时，getdatadaoda.js中的按地区查询调用此方法
function loadDataDDA (url) {
	// body...
	$.getJSON(url,function(data){
		var name_arr = [];
		name_arr=data.DaodaArea.name;
		if(name_arr=="false"){
        	$("#chart_box_07").html('<div class="dengdai">请选择频道设置...</div>');
        	return false;
        }
		var data_arr_2;
		data_arr_2=data.DaodaArea.data;
		var cat_arr1 = [];
		cat_arr1=data.DaodaArea.area;
		daodalvArea(name_arr,data_arr_2,cat_arr1);
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
function daodalvDay ( name_arr, data_arr, cat_arr) {
	if(name_arr.length<=0){
		$("#error1").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_05").html('');
		return false;
	}
	
	// body...
	var optionsDay = {
		chart: {
	        renderTo: 'chart_box_05',
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
	for (j = 0; j < cat_arr.length; j++) {
		optionsDay.xAxis.categories.push(cat_arr[j]);
	};
	for(k=0;k<name_arr.length;k++){	
   	 //压入数据
		var series = {
		    data: []
		};
		series.name = name_arr[k];
		for(i=0;i<data_arr[k].length;i++)
			series.data.push(parseFloat(data_arr[k][i]));
		optionsDay.series.push(series);
	}
    // Create the chart
    var chartDay = new Highcharts.Chart(optionsDay);
}
//按时段查询图表生成方法
function daodalvTime ( name, data_arr, cat_arr){
	if(data_arr.length<=0){
		$("#error2").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_06").html('');
		return false;
	}
	var optionsTime = {
		chart: {
	        renderTo: 'chart_box_06',
	        defaultSeriesType: 'line',
	        marginTop: 80,
	        width: 850
	    },
	    xAxis: {
	        categories: [],
	        tickPixelInterval: 10
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
		optionsTime.xAxis.categories.push(cat_arr[j]);
	for (var i = 0; i < data_arr.length; i++ )
		series.data.push(parseFloat(data_arr[i]));
	optionsTime.series.push(series);
	var chartTime = new Highcharts.Chart(optionsTime);
}
//按地区查询图表生成方法
function daodalvArea ( name_arr, data_arr,area_arr){
	if(data_arr.length<=0){
		$("#error3").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_07").html('');
		return false;
	}
	// body...
	var optionsArea = {
		chart: {
	        renderTo: 'chart_box_07',
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
	for (j = 0; j < area_arr.length; j++)
		optionsArea.xAxis.categories.push(area_arr[j]);
	for(k=0;k<name_arr.length;k++){	
   	 //压入数据
		var series = {
		    data: []
		};
		series.name = name_arr[k];
		for(i=0;i<data_arr[k].length;i++){
			series.data.push(parseFloat(data_arr[k][i]));
		}
		optionsArea.series.push(series);
	}
    // Create the chart
    var chartArea = new Highcharts.Chart(optionsArea);
}
//三种查询方式切换
$(".l_contain .tab_change li").click(function () {
	// body...
	var num = $(this).index();
	$(this).addClass("cur").siblings().removeClass("cur");
	$(".r_contain div.block").eq(num).show().siblings(".block").hide();
});

//读接口获取数据,查询的时候当满足条件时，getdataliuruliuchu.js中的查询调用此方法
function loadDataLL(url){
	$.getJSON(url,function(data){
			var first_arr = data.liuruliuchu.first;
			var second_arr = data.liuruliuchu.second;
			var third_arr = data.liuruliuchu.third;
			var data_arr = data.liuruliuchu.data;
			var cat_arr = data.liuruliuchu.categories;
			liuruliuchu(first_arr, second_arr, third_arr, data_arr, cat_arr);
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
    },
    
});
//流入流出图表生成方法
function liuruliuchu (first_arr, second_arr, third_arr, data_arr, cat_arr) {
	if(cat_arr.length<=0){
		$("#error1").html("该条件下没有查到任何数据，请重新选择查询条件");
		$("#chart_box_13").html('');
		return false;
	}
	// body...
	var name = ['流入','流出'];
	var optionsDay = {
		chart: {
	        renderTo: 'chart_box_13',
	        defaultSeriesType: 'line',
	        marginTop: 80,
	        width: 750
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
	        series: {
	            color: '#FF0000'
	        }
	    },
	    tooltip: {
	    	formatter: function(){
	    		return '<b>'+ this.series.name +'</b><br/>'+  
                    this.x +': '+ Highcharts.numberFormat(this.y, 4)+'<br/>第一名：'+
                    this.point.first +'<br/>第二名：'+this.point.second+'<br/>第三名：'+this.point.third;
	    	}
	    },
	    exporting: {
            enabled: false
        },
	    series: []
	};
	//压入横轴名称
	for (var j = 0; j < cat_arr.length; j++) {
		optionsDay.xAxis.categories.push(cat_arr[j]);
	};
	for(var k=0;k<2;k++){	
   	 //压入数据
		var series = {
		    data: []
		};
		if (k%2 == 1) {
			series.dashStyle = 'Dot';
		};
		series.name = name[k];
		for(var i=0;i<data_arr[k].length;i++)
			series.data.push({
				y: parseFloat(data_arr[k][i]),
				first: first_arr[i][k],
				second: second_arr[i][k],
				third: third_arr[i][k]
			});
		optionsDay.series.push(series);
	}
    // Create the chart
    var chartDay = new Highcharts.Chart(optionsDay);
}
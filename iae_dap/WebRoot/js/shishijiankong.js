//生成图表的公共参数
Highcharts.setOptions({
    global: {
        useUTC: false
    },
    credits: {
        enabled: false
    },
    yAxis: {
        title: {
            text: ''
        },
        tickPixelInterval: 36,
        min: 0,
    },
    tooltip: {
        formatter: function() {
            return this.x +'<br/><b>'+ this.series.name +'</b>:'+
            Highcharts.numberFormat(this.y, 4);
        }
    },
    plotOptions: {
        spline: {
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
    legend: {
        enabled: false
    },
    exporting: {
        enabled: false
    }
});
//变量定义
var chartDD;
var chartSS;
var chartSC;
var chartLL;
var user_name = $("#user_name").val();
//动态请求接口读取数据
function requestDataDD () {
    // body...
    var series = chartDD.series;
    var formatCurr = new Date(currDD.replace(/-/g,"/"));
    var newdate = "";
    setInterval(function() {
    	formatCurr = new Date(currDD.replace(/-/g,"/"));
        newdate = new Date(formatCurr.getTime() + 60000); 
        var hourandmin = getTime(newdate,"shijian");
        var riqi = getTime(newdate,"riqi");
        var url = basePath+'cntv/getShiShiJianKong.json?user_name='+user_name+'&type=5&';
        url += 'clientDate='+riqi+'%20'+hourandmin+'&initial=0&searchSource=shishiDD';
        
        $.getJSON(url,function(data){
            try{
            	var addflag=0
                for( j = 0 ; j<series.length; j++){
                	if(addflag==0){
                		chartDD.xAxis[0].categories.push(hourandmin);
                		addflag=1;
                	}
                	series[j].addPoint(parseFloat(data.shishiDD.data[j][0]), true, true);
                }
                currDD=data.shishiDD.endShijian;
                addflag=0;
            }catch(err){
                addflag=0;
                console.log("该点无数据");
            }
            
        });
    }, 30000);
}
function requestDataSS(){
	var series = chartSS.series;
    var formatCurr = new Date(currSS.replace(/-/g,"/"));
    var newdate = "";
    setInterval(function() {
    	formatCurr = new Date(currSS.replace(/-/g,"/"));
        newdate = new Date(formatCurr.getTime() + 60000); 
        var hourandmin = getTime(newdate,"shijian");
        var riqi = getTime(newdate,"riqi");
        var url = basePath+'cntv/getShiShiJianKong.json?user_name='+user_name+'&type=6&';
        url += 'clientDate='+riqi+'%20'+hourandmin+'&initial=0&searchSource=shishiSS';
        $.getJSON(url,function(data){
            try{
            	var addflag=0
                for( j = 0 ; j<series.length; j++){
	                if(addflag==0){
	                	chartSS.xAxis[0].categories.push(hourandmin);
                		addflag=1;
                	}
                	series[j].addPoint(parseFloat(data.shishiSS.data[j][0]), true, true);
                }
                currSS=data.shishiSS.endShijian;
                addflag=0;
            }catch(err){
            	addflag=0;
                console.log("该点无数据");
            }
        });
    }, 30000);
}
function requestDataSC(){
	var series = chartSC.series;
    var formatCurr = new Date(currSC.replace(/-/g,"/"));
    var newdate = "";
    setInterval(function() {
    	formatCurr = new Date(currSC.replace(/-/g,"/"));
        newdate = new Date(formatCurr.getTime() + 60000); 
        var hourandmin = getTime(newdate,"shijian");
        var riqi = getTime(newdate,"riqi");
        var url = basePath+'cntv/getShiShiJianKong.json?user_name='+user_name+'&type=7&';
        url += 'clientDate='+riqi+'%20'+hourandmin+'&initial=0&searchSource=shishiSC';
        $.getJSON(url,function(data){
            try{
            	var addflag=0
                for( j = 0 ; j<series.length; j++){
                	if(addflag==0){
                		chartSC.xAxis[0].categories.push(hourandmin);
                		addflag=1;
                	}
                	series[j].addPoint(parseFloat(data.shishiSC.data[j][0]), true, true);
                }
                currSC=data.shishiSC.endShijian;
                addflag=0;
            }catch(err){
            	addflag=0;
                console.log("该点无数据");
            }
        });
    }, 30000);
}
function requestDataLL(){
	var series = chartLL.series;
    var formatCurr = new Date(currLL.replace(/-/g,"/"));
    var newdate = "";
    setInterval(function() {
    	formatCurr = new Date(currLL.replace(/-/g,"/"));
        newdate = new Date(formatCurr.getTime() + 60000); 
        var hourandmin = getTime(newdate,"shijian");
        var riqi = getTime(newdate,"riqi");
        var url = basePath+'cntv/getShiShiJianKong.json?user_name='+user_name+'&type=8&';
        url += 'clientDate='+riqi+'%20'+hourandmin+'&initial=0&searchSource=shishiLL';
        $.getJSON(url,function(data){
        	try{
        		var addflag=0
	            var m = 0;
	            var n = 0;
                for( j = 0 ; j<series.length; j++){
                	if(addflag==0){
                		chartLL.xAxis[0].categories.push(hourandmin);
                		addflag=1;
                	}
                    if (j%2 == 0) {
                        series[j].addPoint(parseFloat(data.shishiLL.data_liuru[m][0]), true, true);
                        m++;
                    }else{
                        series[j].addPoint(parseFloat(data.shishiLL.data_liuchu[n][0]), true, true);
                        n++;
                    }
                } 
                currLL=data.shishiLL.endShijian;
                addflag=0;
        	 }catch(err){
             	addflag=0;
                console.log("该点无数据");
             }
        });
    }, 30000);
}
//页面初始化请求接口读取数据
function loadDataRTDD(url){
    $.getJSON(url,function(data){
    	
        var name_arr = data.shishiDD.name;
        if(name_arr=="false"){
        	$("#chart_box_01").html('<div style="padding-top:50px;padding-left:200px;">请选择频道设置...</div>');
        	return false;
        }
        var data_arr = data.shishiDD.data;
        var cat_arr = [];
        currDD = data.shishiDD.endShijian;
        for (var i = 0; i < data.shishiDD.record_time.length; i++) {
            cat_arr.push(data.shishiDD.record_time[i].split(" ")[1]);
        }
        realTimeDD(name_arr, data_arr, cat_arr);
    });
}
function loadDataRTSS(url){
    $.getJSON(url,function(data){
        var name_arr = data.shishiSS.name;
        if(name_arr=="false"){
        	$("#chart_box_02").html('<div style="padding-top:50px;padding-left:200px;">请选择频道设置...</div>');
        	return false;
        }
        var data_arr = data.shishiSS.data;
        var cat_arr = [];
        currSS = data.shishiSS.endShijian;
        for (var i = 0; i < data.shishiSS.record_time.length; i++) {
            cat_arr.push(data.shishiSS.record_time[i].split(" ")[1]);
        }
        realTimeSS(name_arr, data_arr, cat_arr);
    });
}
function loadDataRTSC(url){
    $.getJSON(url,function(data){
        var name_arr = data.shishiSC.name;
        if(name_arr=="false"){
        	$("#chart_box_03").html('<div style="padding-top:50px;padding-left:200px;">请选择频道设置...</div>');
        	return false;
        }
        var data_arr = data.shishiSC.data;
        var cat_arr = [];
        currSC = data.shishiSC.endShijian;
        for (var i = 0; i < data.shishiSC.record_time.length; i++) {
            cat_arr.push(data.shishiSC.record_time[i].split(" ")[1]);
        }
        realTimeSC(name_arr, data_arr, cat_arr);
    });
}
function loadDataRTLL(url){
    $.getJSON(url,function(data){
        var name_arr = data.shishiLL.name;
        if(name_arr=="false"){
        	$("#chart_box_04").html('<div style="padding-top:50px;padding-left:200px;">请选择频道设置...</div>');
        	return false;
        }
        var data_arr_lr = data.shishiLL.data_liuru;
        var data_arr_lc = data.shishiLL.data_liuchu;
        var cat_arr = [];
        currLL = data.shishiLL.endShijian;
        for (var i = 0; i < data.shishiLL.record_time.length/2; i++) {
            cat_arr.push(data.shishiLL.record_time[i].split(" ")[1]);
        }
        realTimeLL(name_arr, data_arr_lr, data_arr_lc, cat_arr);
    });
}
function realTimeDD(name_arr, data_arr, cat_arr){
    var optionsdd = {
        chart: {
            renderTo:'chart_box_01',
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginLeft: 50,
            height: 230,
        },
        xAxis: {
            categories:[],
            tickPixelInterval: 200,
            labels: {
                style: {
                    color: '#585858',
                    fontSize: '12px'
                }
            }
        },
        title: {
            text: '到达率（'+name_arr.length+'个频道'+cat_arr.length+'分钟收视）',
            align: 'left',
            x: 75,
            margin: 43,
            style: {
                color:'#585858',
                fontSize:'12px'
            }
        },
        series: []
    };
    for (j = 0; j < cat_arr.length; j++) {
        optionsdd.xAxis.categories.push(cat_arr[j]);
    }
    var time = (new Date()).getTime()-540000;
    for(k=0;k<name_arr.length;k++){ 
     //压入数据
        var series = {
            data: []
        };
        series.name = name_arr[k];
        for(i=0;i<data_arr[k].length;i++)
            series.data.push(parseFloat(data_arr[k][i]));
        optionsdd.series.push(series);
    }
    chartDD = new Highcharts.Chart(optionsdd);
    requestDataDD();
}
function realTimeSS(name_arr, data_arr, cat_arr){
    var optionsss = {
        chart: {
            renderTo:'chart_box_02',
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginLeft: 50,
            height: 230,
        },
        xAxis: {
            categories:[],
            tickPixelInterval: 200,
            labels: {
                style: {
                    color: '#585858',
                    fontSize: '12px'
                }
            }
        },
        title: {
            text: '收视率（'+name_arr.length+'个频道'+cat_arr.length+'分钟收视）',
            align: 'left',
            x: 75,
            margin: 43,
            style: {
                color:'#585858',
                fontSize:'12px'
            }
        },
        series: []
    };
    for (j = 0; j < cat_arr.length; j++) {
        optionsss.xAxis.categories.push(cat_arr[j]);
    }
    for(k=0;k<name_arr.length;k++){ 
     //压入数据
        var series = {
            data: []
        };
        series.name = name_arr[k];
        for(i=0;i<data_arr[k].length;i++)
            series.data.push(parseFloat(data_arr[k][i]));
        optionsss.series.push(series);
    }
    chartSS = new Highcharts.Chart(optionsss);
    requestDataSS();
}
function realTimeSC(name_arr, data_arr, cat_arr){
    var optionssc = {
        chart: {
            renderTo:'chart_box_03',
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginLeft: 50,
            height: 230,
        },
        xAxis: {
            categories:[],
            tickPixelInterval: 200,
            labels: {
                style: {
                    color: '#585858',
                    fontSize: '12px'
                }
            }
        },
        title: {
            text: '市场份额（'+name_arr.length+'个频道'+cat_arr.length+'分钟收视）',
            align: 'left',
            x: 75,
            margin: 43,
            style: {
                color:'#585858',
                fontSize:'12px'
            }
        },
        series: []
    };
    for (j = 0; j < cat_arr.length; j++) {
        optionssc.xAxis.categories.push(cat_arr[j]);
    }
    for(k=0;k<name_arr.length;k++){ 
     //压入数据
        var series = {
            data: []
        };
        series.name = name_arr[k];
        for(i=0;i<data_arr[k].length;i++)
            series.data.push(parseFloat(data_arr[k][i]));
        optionssc.series.push(series);
    }
    chartSC = new Highcharts.Chart(optionssc);
    requestDataSC();
}
//流入流出率（12个频道分钟收视）
function realTimeLL(name_arr, data_arr_lr, data_arr_lc, cat_arr){
    var optionsll = {
        chart: {
            renderTo:'chart_box_04',
            type: 'spline',
            animation: Highcharts.svg, // don't animate in old IE
            marginLeft: 50,
            height: 230,
        },
        xAxis: {
            categories:[],
            tickPixelInterval: 200,
            labels: {
                style: {
                    color: '#585858',
                    fontSize: '12px'
                }
            }
        },
        title: {
            text: '流入流出（'+name_arr.length+'个频道'+cat_arr.length+'分钟收视）',
            align: 'left',
            x: 75,
            margin: 43,
            style: {
                color:'#585858',
                fontSize:'12px'
            }
        },
        series: []
    };
    for (j = 0; j < cat_arr.length; j++) {
        optionsll.xAxis.categories.push(cat_arr[j]);
    }
    var type_arr = ["流入","流出"];
    for(k=0;k<name_arr.length;k++){ 
        for (l = 0; l < type_arr.length; l++) {
            var series = {
                data: []
            };
            series.name = name_arr[k]+'-'+type_arr[l];
            if (type_arr[l] == '流入') {
            	
                for(i=0;i<data_arr_lr[k].length;i++)
                    series.data.push(parseFloat(data_arr_lr[k][i]));
            };
            if (type_arr[l] == '流出') {
            	series.dashStyle = 'Dot';
                for(i=0;i<data_arr_lc[k].length;i++)
                    series.data.push(parseFloat(data_arr_lc[k][i]));
            };
            optionsll.series.push(series);
        }  
    }
    chartLL = new Highcharts.Chart(optionsll);
    requestDataLL();
}
//获取时间
function getTime(date,type)
{ 

	var now=new Date(date);//定义一个新时间
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
      
     var mm = now.getMinutes();  //分
     var clock ="";
   if(type=="wanzheng"){
	   clock = year + "-";
	    if(month < 10)
	        clock += "0";
	    clock += month + "-";
	    if(day < 10)
	        clock += "0";
	    clock += day + " ";
	    if(hh < 10)
	        clock += "0";
	    clock += hh + ":";
	    if (mm < 10) clock += '0'; 
	    clock += mm; 
   }else if(type=="riqi"){
	   clock = year + "-";
	    if(month < 10)
	        clock += "0";
	    clock += month + "-";
	    if(day < 10)
	        clock += "0";
	    clock += day;
   }else if(type=="shijian"){
	    if(hh < 10)
	        clock += "0";
	    clock += hh + ":";
	    if (mm < 10) clock += '0'; 
	    clock += mm; 
   } 
   	
    return(clock);
} 
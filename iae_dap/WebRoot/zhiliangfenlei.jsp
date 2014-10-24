<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./dap/jquery-1.8.3.min.js"></script>
  <script type="text/javascript" src="./dap/highcharts.js"></script>
  <script type="text/javascript" src="./dap/exporting.js"></script>

  </head>
  	 <script type="text/javascript">
  	 var data1;
  	 var start = '';
  	 var end = '';
  	$(function () {
  		$('#container').highcharts({
  	        chart: {
  	            plotBackgroundColor: null,
  	            plotBorderWidth: null,
  	            plotShadow: false
  	        },
  	        title: {
  	            text: '用户质量分类'
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
  	        series: [{
  	            type: 'pie',
  	            name: '所占比例',
  	            data: [
  	               
  	                {
  	                    name: 'high quality user',
  	                    y: 15,
  	                    sliced: true,
  	                    selected: true
  	                },
  	                ['potential user',   25],
  	                ['normal user',     40],
  	                ['low quality user',   20]
  	            ]
  	        }]
  	    });
  		
  	  
  	});
  	
  	function getData(start,end){
  		 $.ajax({
             type: "GET",
             url: "/iae_dap/getDailyUserCountData.json?start="+start+"&end="+end,
             success: function(data){
            	var json = eval("(" + data + ")");
            	 data1 = eval("["+json.data+"]");
               /*          data1 = eval(data.data);
                        alert(data1);
                        date = data[1];
                        alert(date); */
                        $('#container').highcharts({
                  	        chart: {
                  	            zoomType: 'x',
                  	            spacingRight: 20
                  	        },
                  	        title: {
                  	            text: '活跃用户统计'
                  	        },
                  	        subtitle: {
                  	            text: document.ontouchstart === undefined ?
                  	                'Tips:框选放大该部分' :
                  	                'Pinch the chart to zoom in'
                  	        },
                  	        xAxis: {
                  	            type: 'datetime',
                  	            maxZoom: 14 * 24 * 3600000, // fourteen days
                  	            title: {
                  	                text: '日期'
                  	            }
                  	        },
                  	        yAxis: {
                  	            title: {
                  	                text: '用户数'
                  	            }
                  	        },
                  	        tooltip: {
                  	            shared: true
                  	        },
                  	        legend: {
                  	            enabled: false
                  	        },
                  	        plotOptions: {
                  	            area: {
                  	                fillColor: {
                  	                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1},
                  	                    stops: [
                  	                        [0, Highcharts.getOptions().colors[0]],
                  	                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                  	                    ]
                  	                },
                  	                lineWidth: 1,
                  	                marker: {
                  	                    enabled: false
                  	                },
                  	                shadow: false,
                  	                states: {
                  	                    hover: {
                  	                        lineWidth: 1
                  	                    }
                  	                },
                  	                threshold: null
                  	            }
                  	        },

                  	        series: [{
                  	           
                  	            name: '日活跃用户',
                  	            pointInterval: 24 * 3600 * 1000,
                  	            pointStart: json.date,
                  	            data: data1
                  	        }]
                  	    });
                      }
         });
  	}
  </script>
  <body>
    <div id="container" style="min-width: 310px;width:800px; height: 400px; margin: 0 auto"></div>
  	

	<div id="w" class="easyui-window" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:500px;height:200px;padding:10px;">
		The window content.
	</div>
  
  </body>
</html>

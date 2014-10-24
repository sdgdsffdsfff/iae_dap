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
  	            zoomType: 'x',
  	            spacingRight: 20
  	        },
  	        title: {
  	            text: '回看量统计'
  	        },
  	        
  	        xAxis: {
  	            type: 'datetime',
  	            maxZoom: 14 * 24 * 3600000, // fourteen days
  	            title: {
  	                text: null
  	            }
  	        },
  	        yAxis: {
  	            title: {
  	                text: '回看量'
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
  	            type: 'area',
  	            name: '回看量',
  	            pointInterval: 24 * 3600 * 1000,
  	            pointStart: Date.UTC(2006, 0, 01),
  	            data: [
  	                 84460,  84435,  84244,  81451,     84138,  82654,     82258,  82332,     82233,  82558,
  	                 82863,  82278,  82456,  82292,     82329,  82349,     82245,  82265,     82261,  82269,
  	                 82273,  82244,  82424,  81272,     82139,  82146,     81624,  82224,     82269,  82629,
  	                 82269,  82258,  82247,  82286,     82289,  83216,     82232,  82333,     82352,  83257,
  	                 83525,  83254,  84203,  84203,     84206,  84023,     83926,  82418,     82409,  83284,
  	                 83826,  82372,  83229,  84246,  83289,  84223,  84223,  84223,  84235,  84222,
  	                 82238,  83373,  83316,  83303,     83303,  83302,     83369,  84233,  83385,  83334,
  	                 84031,  84302,  83381,  83531,     83134,  82373,     82133,  82037,     82307,  82315,
  	                 82342,  83273,  83301,  83346,     83132,  83132,     83132,  83306,     83327,  82382,
  	                 82334,  82355,  82356,  83273,  83209,  81531,  83149,  83213,  82373,  83273
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
  </body>
</html>

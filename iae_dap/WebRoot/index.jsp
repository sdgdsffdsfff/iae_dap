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
	<link href="DataTables-1.10.3/media/css/jquery.dataTables.css" rel="stylesheet"
	type="text/css" />
	<link href="DataTables-1.10.3/extensions/TableTools/css/dataTables.tableTools.css" rel="stylesheet"
	type="text/css" />
<script src="DataTables-1.10.3/media/js/jquery.dataTables.js" type="text/javascript"></script>
<script src="DataTables-1.10.3/extensions/TableTools/js/dataTables.tableTools.js" type="text/javascript"></script>
  </head>
  	 <script type="text/javascript">
  	 var data1;
  	 var start = '';
  	 var end = '';
  	$(function () {
  		
  		getData(start,end);
  		$('#example').dataTable( {
  	        "ajax": "/iae_dap/getDailyUserTableData.json",
  	        dom: 'T<"clear">lfrtip',
  	      "paging":   10,
  	    "bLengthChange": false, 
  	    	searching: false,
  	        tableTools: {
  	            "aButtons": [
  	                
  	                "csv",
  	                "xls",
  	                {
  	                    "sExtends": "pdf",
  	                    "sPdfOrientation": "landscape",
  	                    "sPdfMessage": "Your custom message would go here."
  	                }
  	               
  	            ]
  	        },
  	      "language": {
              "lengthMenu": "每页 _MENU_ 条记录",
              "zeroRecords": "没有找到记录",
              "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
              "infoEmpty": "无记录",
              "infoFiltered": "(从 _MAX_ 条记录过滤)"
          }
  	    } );
  	  
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
                  	            pointStart: json.date+24*60*60*1000,
                  	            data: data1
                  	        }]
                  	    });
                      }
         });
  	}
  </script>
  
  
  <body>
    <div id="container" style="min-width: 310px;width:800px; height: 400px; margin: 0 auto"></div>
  <br/>
  <table id="example" class="display" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th>Date</th>
            <th>Count</th>
          <!--   <th>Office</th>
            <th>Extn.</th>
            <th>Start date</th>
            <th>Salary</th> -->
        </tr>
    </thead>
 
  <!--   <tfoot>
        <tr>
            <th>Name</th>
            <th>Position</th>
            <th>Office</th>
            <th>Extn.</th>
            <th>Start date</th>
            <th>Salary</th>
        </tr>
    </tfoot> -->
</table>
  
  </body>
</html>

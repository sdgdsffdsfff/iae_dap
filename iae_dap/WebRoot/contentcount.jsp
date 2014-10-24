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
  	            text: '����������ͳ��'
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
  	                text: '����������'
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
  	            name: '����������',
  	            pointInterval: 24 * 3600 * 1000,
  	            pointStart: Date.UTC(2006, 0, 01),
  	            data: [
  	                 8446,  8445,  8444,  8451,     8418,  8264,     8258,  8232,     8233,  8258,
  	                 8283,  8278,  8256,  8292,     8239,  8239,     8245,  8265,     8261,  8269,
  	                 8273,  8244,  8244,  8172,     8139,  8146,     8164,  8224,     8269,  8269,
  	                 8269,  8258,  8247,  8286,     8289,  8316,     832,  8333,     8352,  8357,
  	                 8355,  8354,  8403,  8403,     8406,  8403,     8396,  8418,     8409,  8384,
  	                 8386,  8372,  839,  8446,  8389,  8423,  8423,  8423,  8435,  8422,
  	                 838,  8373,  8316,  8303,     8303,  8302,     8369,  8423,  8385,  84,
  	                 8401,  8402,  8381,  8351,     8314,  8273,     8213,  8207,     8207,  8215,
  	                 8242,  8273,  8301,  8346,     8312,  8312,     8312,  8306,     8327,  8282,
  	                 824,  8255,  8256,  8273,  8209,  8151,  8149,  8213,  8273,  8273,
  	                 8261,  8252,  824,  8262,  8258,  8261,  826,  8199,  8153,  8097,
  	                 8101,  8119,  8107,  8105,     8084,  8069,     8047,  8023,     7965,  7919,
  	                 7921,  7922,  7934,  7918,     7915,  787,  7861,  7861,  7853,  7867,
  	                 7827,  7834,  7766,  7751,  7739,  7767,  7802,  7788,  7828,  7816,
  	                 7829,  783,  7829,  7781,  7811,  7831,  7826,  7855,  7855,  7845,
  	                 7798,  7777,  7822,  7785,  7744,  7743,  7726,  7766,  7806,  785,
  	                 7907,  7912,  7913,  7931,  7952,  7951,  7928,  791,  7913,  7912,
  	                 7941,  7953,  7921,  7919,  7968,  7999,  7999,  7974,  7942,  796,
  	                 7969,  7862,  7821,  7821,  7821,  7811,  7833,  7849,  7819,  7809,
  	                 7809,  7827,  7848,  785,  7873,  7894,  7907,  7909,  7947,  7987,
  	                 799,  7927,  79,  7878,  7878,  7907,  7922,  7937,  786,  787,
  	                 7838,  7838,  7837,  7836,  7806,  7825,  7798,  777,  777,  7772,
  	                 7793,  7788,  7785,  7832,  7865,  7865,  7853,  7847,  7809,  778,
  	                 7799,  78,  7801,  7765,  7785,  7811,  782,  7835,  7845,  7844,
  	                 782,  7811,  7795,  7794,  7806,  7794,  7794,  7778,  7793,  7808,
  	                 7824,  787,  7894,  7893,  7882,  7871,  7882,  7871,  7878,  79,
  	                 7901,  7898,  7879,  7886,  7858,  7814,  7825,  7826,  7826,  786,
  	                 7878,  7868,  7883,  7893,  7892,  7876,  785,  787,  7873,  7901,
  	                 7936,  7939,  7938,  7956,  7975,  7978,  7972,  7995,  7995,  7994,
  	                 7976,  7977,  796,  7922,  7928,  7929,  7948,  797,  7953,  7907,
  	                 7872,  7852,  7852,  786,  7862,  7836,  7837,  784,  7867,  7867,
  	                 7869,  7837,  7827,  7825,  7779,  7791,  779,  7787,  78,  7807,
  	                 7803,  7817,  7799,  7799,  7795,  7801,  7765,  7725,  7683,  7641,
  	                 7639,  7616,  7608,  759,  7582,  7539,  75,  75,  7507,  7505,
  	                 7516,  7522,  7531,  7577,  7577,  7582,  755,  7542,  7576,  7616,
  	                 7648,  7648,  7641,  7614,  757,  7587,  7588,  762,  762,  7617,
  	                 7618,  7615,  7612,  7596,  758,  758,  758,  7547,  7549,  7613,
  	                 7655,  7693,  7694,  7688,  7678,  7708,  7727,  7749,  7741,  7741,
  	                 7732,  7727,  7737,  7724,  7712,  772,  7721,  7717,  7704,  769,
  	                 7711,  774,  7745,  7745,  774,  7716,  7713,  7678,  7688,  7718,
  	                 7718,  7728,  7729,  7698,  7685,  7681,  769,  769,  7698,  7699,
  	                 7651,  7613,  7616,  7614,  7614,  7607,  7602,  7611,  7622,  7615,
  	                 7598,  7598,  7592,  7573,  7566,  7567,  7591,  7582,  7585,  7613,
  	                 7631,  7615,  76,  7613,  7627,  7627,  7608,  7583,  7575,  7562,
  	                 752,  7512,  7512,  7517,  752,  7511,  748,  7509,  7531,  7531,
  	                 7527,  7498,  7493,  7504,  75,  7491,  7491,  7485,  7484,  7492,
  	                 7471,  7459,  7477,  7477,  7483,  7458,  7448,  743,  7399,  7395,
  	                 7395,  7378,  7382,  7362,  7355,  7348,  7361,  7361,  7365,  7362,
  	                 7331,  7339,  7344,  7327,  7327,  7336,  7333,  7359,  7359,  7372,
  	                 736,  736,  735,  7365,  7384,  7395,  7413,  7397,  7396,  7385,
  	                 7378,  7366,  74,  7411,  7406,  7405,  7414,  7431,  7431,  7438,
  	                 7443,  7443,  7443,  7434,  7429,  7442,  744,  7439,  7437,  7437,
  	                 7429,  7403,  7399,  7418,  7468,  748,  748,  749,  7494,  7522,
  	                 7515,  7502,  7472,  7472,  7462,  7455,  7449,  7467,  7458,  7427,
  	                 7427,  743,  7429,  744,  743,  7422,  7388,  7388,  7369,  7345,
  	                 7345,  7345,  7352,  7341,  7341,  734,  7324,  7272,  7264,  7255,
  	                 7258,  7258,  7256,  7257,  7247,  7243,  7244,  7235,  7235,  7235,
  	                 7235,  7262,  7288,  7301,  7337,  7337,  7324,  7297,  7317,  7315,
  	                 7288,  7263,  7263,  7242,  7253,  7264,  727,  7312,  7305,  7305,
  	                 7318,  7358,  7409,  7454,  7437,  7424,  7424,  7415,  7419,  7414,
  	                 7377,  7355,  7315,  7315,  732,  7332,  7346,  7328,  7323,  734,
  	                 734,  7336,  7351,  7346,  7321,  7294,  7266,  7266,  7254,  7242,
  	                 7213,  7197,  7209,  721,  721,  721,  7209,  7159,  7133,  7105,
  	                 7099,  7099,  7093,  7093,  7076,  707,  7049,  7012,  7011,  7019,
  	                 7046,  7063,  7089,  7077,  7077,  7077,  7091,  7118,  7079,  7053,
  	                 705,  7055,  7055,  7045,  7051,  7051,  7017,  7,  6995,  6994,
  	                 7014,  7036,  7021,  7002,  6967,  695,  695,  6939,  694,  6922,
  	                 6919,  6914,  6894,  6891,  6904,  689,  6834,  6823,  6807,  6815,
  	                 6815,  6847,  6859,  6822,  6827,  6837,  6823,  6822,  6822,  6792,
  	                 6746,  6735,  6731,  6742,  6744,  6739,  6731,  6761,  6761,  6785,
  	                 6818,  6836,  6823,  6805,  6793,  6849,  6833,  6825,  6825,  6816,
  	                 6799,  6813,  6809,  6868,  6933,  6933,  6945,  6944,  6946,  6964,
  	                 6965,  6956,  6956,  695,  6948,  6928,  6887,  6824,  6794,  6794,
  	                 6803,  6855,  6824,  6791,  6783,  6785,  6785,  6797,  68,  6803,
  	                 6805,  676,  677,  677,  6736,  6726,  6764,  6821,  6831,  6842,
  	                 6842,  6887,  6903,  6848,  6824,  6788,  6814,  6814,  6797,  6769,
  	                 6765,  6733,  6729,  6758,  6758,  675,  678,  6833,  6856,  6903,
  	                 6896,  6896,  6882,  6879,  6862,  6852,  6823,  6813,  6813,  6822,
  	                 6802,  6802,  6784,  6748,  6747,  6747,  6748,  6733,  665,  6611,
  	                 6583,  659,  659,  6581,  6578,  6574,  6532,  6502,  6514,  6514,
  	                 6507,  651,  6489,  6424,  6406,  6382,  6382,  6341,  6344,  6378,
  	                 6439,  6478,  6481,  6481,  6494,  6438,  6377,  6329,  6336,  6333,
  	                 6333,  633,  6371,  6403,  6396,  6364,  6356,  6356,  6368,  6357,
  	                 6354,  632,  6332,  6328,  6331,  6342,  6321,  6302,  6278,  6308,
  	                 6324,  6324,  6307,  6277,  6269,  6335,  6392,  64,  6401,  6396,
  	                 6407,  6423,  6429,  6472,  6485,  6486,  6467,  6444,  6467,  6509,
  	                 6478,  6461,  6461,  6468,  6449,  647,  6461,  6452,  6422,  6422,
  	                 6425,  6414,  6366,  6346,  635,  6346,  6346,  6343,  6346,  6379,
  	                 6416,  6442,  6431,  6431,  6435,  644,  6473,  6469,  6386,  6356,
  	                 634,  6346,  643,  6452,  6467,  6506,  6504,  6503,  6481,  6451,
  	                 645,  6441,  6414,  6409,  6409,  6428,  6431,  6418,  6371,  6349,
  	                 6333,  6334,  6338,  6342,  632,  6318,  637,  6368,  6368,  6383,
  	                 6371,  6371,  6355,  632,  6277,  6276,  6291,  6274,  6293,  6311,
  	                 631,  6312,  6312,  6304,  6294,  6348,  6378,  6368,  6368,  6368,
  	                 636,  637,  6418,  6411,  6435,  6427,  6427,  6419,  6446,  6468,
  	                 6487,  6594,  6666,  6666,  6678,  6712,  6705,  6718,  6784,  6811,
  	                 6811,  6794,  6804,  6781,  6756,  6735,  6763,  6762,  6777,  6815,
  	                 6802,  678,  6796,  6817,  6817,  6832,  6877,  6912,  6914,  7009,
  	                 7012,  701,  7005,  7076,  7087,  717,  7105,  7031,  7029,  7006,
  	                 7035,  7045,  6956,  6988,  6915,  6914,  6859,  6778,  6815,  6815,
  	                 6843,  6846,  6846,  6923,  6997,  7098,  7188,  7232,  7262,  7266,
  	                 7359,  7368,  7337,  7317,  7387,  7467,  7461,  7366,  7319,  7361,
  	                 7437,  7432,  7461,  7461,  7454,  7549,  7742,  7801,  7903,  7876,
  	                 7928,  7991,  8007,  7823,  7661,  785,  7863,  7862,  7821,  7858,
  	                 7731,  7779,  7844,  7866,  7864,  7788,  7875,  7971,  8004,  7857,
  	                 7932,  7938,  7927,  7918,  7919,  7989,  7988,  7949,  7948,  7882,
  	                 7745,  771,  775,  7791,  7882,  7882,  7899,  7905,  7889,  7879,
  	                 7855,  7866,  7865,  7795,  7758,  7717,  761,  7497,  7471,  7473,
  	                 7407,  7288,  7074,  6927,  7083,  7191,  719,  7153,  7156,  7158,
  	                 714,  7119,  7129,  7129,  7049,  7095
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
                  	            text: '��Ծ�û�ͳ��'
                  	        },
                  	        subtitle: {
                  	            text: document.ontouchstart === undefined ?
                  	                'Tips:��ѡ�Ŵ�ò���' :
                  	                'Pinch the chart to zoom in'
                  	        },
                  	        xAxis: {
                  	            type: 'datetime',
                  	            maxZoom: 14 * 24 * 3600000, // fourteen days
                  	            title: {
                  	                text: '����'
                  	            }
                  	        },
                  	        yAxis: {
                  	            title: {
                  	                text: '�û���'
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
                  	           
                  	            name: '�ջ�Ծ�û�',
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
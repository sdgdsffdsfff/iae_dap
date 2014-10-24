//获取N分钟后的时间点
function AfterTime(type,fen)
    { 
    
        var nt = new Date();
       	var t=new Date();//你已知的时间
		var t_s=t.getTime();//转化为时间戳毫秒数
		var now=new Date();//定义一个新时间
		now.setTime(t_s+1000*60*fen);//设置新时间比旧时间多一分钟
		
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

//获取N分钟后的时间点
function PreTime(type,fen)
    { 
    
        var nt = new Date();
       	var t=new Date();//你已知的时间
		var t_s=t.getTime();//转化为时间戳毫秒数
		var now=new Date();//定义一个新时间
		now.setTime(t_s-1000*60*fen);//设置新时间比旧时间多一分钟
		
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
//获取当前时间点
function CurentTime(type)
{ 

	var now=new Date();//定义一个新时间
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
//计算两个时间的间隔
function timeBetween (timeOne, timeTwo) {
	// body...
	var str1 = timeOne.replace(/-/g,"/");
	var date1 = new Date(str1);
	var time1 = date1.getTime();
	var str2 = timeTwo.replace(/-/g,"/");
	var date2 = new Date(str2);
	var time2 = date2.getTime();
	return (parseInt(time2)-parseInt(time1));
}
//根据开始日期和结束日期，获取日期间隔，生成一个日期的数组
function getDateArrByDay (bgDate,endDate) {
    // body...
    var bd = bgDate.split("-");
    var ed = endDate.split("-");
    var day_arr = [];
    for (var i = 0; i <= (ed[2]-bd[2]); i++) {
        day_arr.push(bd[1]+"-"+(parseInt(bd[2])+i));
    };
    return day_arr;
}
//获取时间间隔，生成一个时间的数组
function getDateArrByTime (bgTime, endTime) {
		// body...
		var str1 = bgTime.replace(/-/g,"/");
		var date1 = new Date(str1);
		var time1 = date1.getTime();
		var str2 = endTime.replace(/-/g,"/");
		var date2 = new Date(str2);
		var time2 = date2.getTime();
		var time_arr = [];
		for (var i = 0; i <= ((time2-time1)/1000/60); i++) {			
			var newDate = new Date(time1+i*60000);
			var newHour = newDate.getHours();
			var newMinute = newDate.getMinutes();
			if(newHour<10){
				newHour = "0"+newHour;
			}
			if(newMinute<10){
				newMinute = "0"+newMinute;
			}
			time_arr.push(newHour+":"+newMinute);
		};
		return time_arr;
	}
//获取五天前的日期
function getFiveDay(){
	var myDate = new Date();
	var haomiao = myDate.getTime();
	var fiveBeforeHaomiao = haomiao - 345600000;
	var newDate = new Date(fiveBeforeHaomiao);
	var fbMonth = newDate.getMonth()+1;
	var fbDate = newDate.getDate();
	var fbYear = newDate.getFullYear();
	return (fbYear+"-"+fbMonth+"-"+fbDate);
}
//获取12小时前的时间
function getTwelveHour () {
	// body...
	var myDate = new Date();
	var haomiao = myDate.getTime();
	var twelveBeforeHaomiao = haomiao - 39600000;
	var newDate = new Date(fiveBeforeHaomiao);
	var fbMonth = newDate.getMonth()+1;
	var fbDate = newDate.getDate();
	var fbYear = newDate.getFullYear();
	var fbHour = newDate.getHours();
	var fbMinute = newDate.getMinutes();
	return (fbYear+"-"+fbMonth+"-"+fbDate+" "+fbHour+":"+fbMinute);
}
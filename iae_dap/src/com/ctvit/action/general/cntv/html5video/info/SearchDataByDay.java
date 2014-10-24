package com.ctvit.action.general.cntv.html5video.info;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ctvit.action.base.ActionSupport;
import com.ctvit.converter.Converter;
import com.ctvit.converter.ConverterContext;
import com.ctvit.converter.ConverterJson;
import com.ctvit.converter.ConverterJsonp;
import com.ctvit.dao.base.rowmapper.DataToolsMapRowMapper;
import com.ctvit.dbutils.C3P0Utils;
import com.ctvit.dbutils.ExecuteSql;
import com.ctvit.domain.iptv.User;

/**
 * 查询数据--按天查询
 * @日期 2013-10-22
 */
public class SearchDataByDay  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(SearchDataByDay.class);
	private Properties pros = null;

	public SearchDataByDay() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String user_name=(String)getParamName("user_name");//用户名称
		String type=(String)getParamName("type");//页面类型1:到达率
		String bgDate=(String)getParamName("bgDate");//开始时间
		String endDate=(String)getParamName("endDate");//结束时间
		String bgHour=(String)getParamName("bgHour");//开始时间段
		String endHour=(String)getParamName("endHour");//结束时间段
		String searchSource=(String)getParamName("searchSource");//查询来源
		String area=(String)getParamName("area");//地区--市场份额
		Map param=new HashMap();
		param.put("user_name", user_name);
		param.put("type", type);
		param.put("bgDate", bgDate);
		param.put("endDate", endDate);
		param.put("bgHour", bgHour);
		param.put("endHour", endHour);
		param.put("searchSource", searchSource);
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		Map result = new HashMap();
		//http://localhost:8082/iptv/cntv/getSearchDataByDay.json?user_name=admin&type=1&bgDate=2014-10-22&endDate=2014-10-23&bgHour=18:21&endHour=18:30&searchSource=DaodaDay
		try{ 
			String channelSql = createQuerySql(param);
			String checkedChannel = excuteSql.executeSqlAsHashMapClobGetTure(channelSql);
			if(checkedChannel!=null&&!"".equals(checkedChannel)){
				checkedChannel = checkedChannel.substring(0,checkedChannel.length()-1);
				param.put("checkedChannel", checkedChannel);
				if(param.get("type").equals("3")){
					if(SystemUtils.OS_NAME.contains("Windows")){
						param.put("area", new String(area.getBytes("ISO-8859-1"),"UTF-8"));							
					}else{
						param.put("area", area);						
					}
					String dataSql = createQuerySqlDataToShiChang(param);
					Map map = excuteSql.executeSqlAsHashMapClobGetDayAndShouShi(dataSql,param);
					result.put(param.get("searchSource"), map);
				}else {
					String dataSql = createQuerySqlData(param);
					Map map = excuteSql.executeSqlAsHashMapClobGetDay(dataSql,param);
					result.put(param.get("searchSource"), map);
				}
				
			}else{
				Map map = new HashMap();
				map.put("name", "false");
				result.put(param.get("searchSource"), map);//没有进行频道设置时，返回false
			}
			Converter<Map> converterJsonpStrategy=null;
			if(dataType.equals("jsonp")){
				converterJsonpStrategy=new ConverterJsonp<Map>();			
			}else if(dataType.equals("json")){
				converterJsonpStrategy=new ConverterJson<Map>();			
			}
			ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
			String runConverter = context.runConverter(result);
			build(runConverter);
			log.info("数据："+runConverter);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		return null;
	}
	
	/**
	 * 根据被选中的频道标识，查询符合条件的数据--到达率、收视率
	 * @param param
	 * @return
	 */
	public static String createQuerySqlData(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("select * from (SELECT ");
		if(param.get("type").equals("1")){
			excuteSql.append("sum(i.arrive_rating) as data ,");
		}else if (param.get("type").equals("2")){
			excuteSql.append("sum(i.audi_rating) as data ,");
		}
		excuteSql.append("(select r.name from iptv_channel_relation r where r.pk = i.channelid ) as channelName,DATE_FORMAT(i.record_time,'%Y-%m-%d')as date_time FROM iptv_rating i where DATE_FORMAT(i.record_time,'%Y-%m-%d') between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' and ") 
		.append(" DATE_FORMAT(i.record_time,'%H:%i') between '"+param.get("bgHour")+"' and '"+param.get("endHour")+"' and i.channelid in (select r.pk from iptv_channel_relation r where r.logo in ("+param.get("checkedChannel")+"))  and i.area_flag ='0' ")
		.append(" group by channelName,date_time) z order by z.channelName desc,z.date_time");
		return excuteSql.toString();
	}
	/**
	 * 市场份额--按天查询
	 * @param param
	 * @return
	 */
	public static String createQuerySqlDataToShiChang(Map param) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = (String)param.get("bgDate");
		Calendar c_curr = Calendar.getInstance();
		try {
			c_curr.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c_curr.add(Calendar.DATE, 1);
		String str_curr = date + " 02:00";
		String str_next = sdf.format(c_curr.getTime()) + " 02:00";
		
		
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("select * from (SELECT sum(i.audi_share)as data, ")
		.append("(select r.name from iptv_channel_relation r where r.pk = i.channelid ) as channelName FROM iptv_rating i where i.record_time between '"+str_curr+"' and '"+str_next+"' ") 
		.append(" and i.channelid in (select r.pk from iptv_channel_relation r where r.logo in ("+param.get("checkedChannel")+"))  and i.area ='"+param.get("area")+"' ")
		.append(" group by i.channelid ")
		.append(" union all ")
		.append("  SELECT sum(i.audi_share)as audi_share, ")
		.append(" '其他频道' as channelName FROM iptv_rating i where i.record_time between '"+str_curr+"' and '"+str_next+"' ") 
		.append(" and i.channelid in (select r.pk from iptv_channel_relation r where r.logo not in ("+param.get("checkedChannel")+"))  and i.area ='"+param.get("area")+"' ")
		.append("  group by channelName")
		.append(" ) z order by z.channelName desc");
		return excuteSql.toString();
	}
	
	/**
	 * 查询被选中的频道信息
	 * @param param
	 * @return
	 */
	public static String createQuerySql(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT * FROM iptv_channel c where  c.user_name = '"+param.get("user_name")+"' and c.type ='"+param.get("type")+"'");
		return excuteSql.toString();
	}
	

}

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
 * 实时监控
 * @日期 2013-10-22
 */
public class ShiShiJianKong  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(ShiShiJianKong.class);
	private Properties pros = null;

	public ShiShiJianKong() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String user_name=(String)getParamName("user_name");//用户名称
		String type=(String)getParamName("type");//页面类型1:到达率
		String clientDate=(String)getParamName("clientDate");//客户端时间
		String searchSource=(String)getParamName("searchSource");//查询来源
		String initial=(String)getParamName("initial");//初始加载几分钟的数据
		Map param=new HashMap();
		param.put("user_name", user_name);
		param.put("type", type);
		param.put("clientDate", clientDate);
		param.put("searchSource", searchSource);
		param.put("initial", initial);
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		Map result = new HashMap();
		//http://localhost:8082/iptv/cntv/getSearchDataByDay.json?user_name=admin&type=1&bgDate=2014-10-22&endDate=2014-10-23&bgHour=18:21&endHour=18:30&searchSource=DaodaDay
		try{ 
			//获取频道设置
			String channelSql = createQuerySql(param);
			String checkedChannel = excuteSql.executeSqlAsHashMapClobGetTure(channelSql);
			if(checkedChannel!=null&&!"".equals(checkedChannel)){
				checkedChannel = checkedChannel.substring(0,checkedChannel.length()-1);
				param.put("checkedChannel", checkedChannel);
				//获取最新入库时间
				String newDateSql = createQuerySqlByNewDate(param);
				String endDate =  excuteSql.executeSqlAsHashMapClobByNewDate(newDateSql);
				param.put("endDate", endDate);
				//把入库时间当作 时间段的结束时间，然后通过算法得到开始时间
				String bgDate = getBeginDate(param);
				if(bgDate!=null&&!"".equals(bgDate)){
					param.put("bgDate", bgDate);
					String dataSql = createQuerySqlData(param);
					//System.out.println(dataSql);
					if (param.get("type").equals("8")){
						Map map = excuteSql.executeSqlAsHashMapClobShiShiLL(dataSql,param);
						result.put(param.get("searchSource"), map);
					}else{
						Map map = excuteSql.executeSqlAsHashMapClobShiShi(dataSql,param);
						result.put(param.get("searchSource"), map);
					}
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
	 * 根据被选中的频道标识，查询符合条件的数据
	 * @param param
	 * @return
	 */
	public static String createQuerySqlData(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("select * from (SELECT ");
		if(param.get("type").equals("5")){
			excuteSql.append("i.arrive_rating as data ,");
		}else if (param.get("type").equals("6")){
			excuteSql.append("i.audi_rating as data ,");
		}else if (param.get("type").equals("7")){
			excuteSql.append("i.audi_share as data ,");
		}else if (param.get("type").equals("8")){
			excuteSql.append("i.inflow_rating as data_liuru , i.outflow_rating as data_liuchu,");
		}
		excuteSql.append("(select r.name from iptv_channel_relation r where r.pk = i.channelid ) as channelName,DATE_FORMAT(i.record_time,'%Y-%m-%d %H:%i')as date_time FROM iptv_rating i where ")
		.append(" i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' ")
		.append("  and i.channelid in (select r.pk from iptv_channel_relation r where r.logo in ("+param.get("checkedChannel")+"))  and i.area_flag ='0' ")
		.append(") z order by z.channelName desc,z.date_time");
		return excuteSql.toString();
	}
	
	/**
	 * 根据被选中的频道标识，查询符合条件的数据--加上频道为空的处理(弊端查询速度太慢)
	 * @param param
	 * @return
	 */
	public static String createQuerySqlDataNew(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("select *")
		.append("	  from (select r.name as channelName,")
		.append("	               r.logo,")
		.append("	               DATE_FORMAT(i.record_time, '%Y-%m-%d %H:%i') as date_time,");
		if(param.get("type").equals("5")){
			excuteSql.append("i.arrive_rating as data ");
		}else if (param.get("type").equals("6")){
			excuteSql.append("i.audi_rating as data ");
		}else if (param.get("type").equals("7")){
			excuteSql.append("i.audi_share as data ");
		}else if (param.get("type").equals("8")){
			excuteSql.append("i.inflow_rating as data_liuru , i.outflow_rating as data_liuchu");
		}
		excuteSql.append("	          from iptv_channel_relation r")
		.append("	          left join iptv_rating i on r.pk = i.channelid")
		.append("	                                 and i.record_time between")
		.append("	                                     '"+param.get("bgDate")+"' and")
		.append("	                                     '"+param.get("endDate")+"'")
		.append("	                                 and i.area_flag = '0') z")
		.append("	 where z.logo in ("+param.get("checkedChannel")+")")
		.append("	        order by z.channelName desc,z.date_time");
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
	/**
	 * 查询最新入库时间
	 * @param param
	 * @return
	 */
	public static String createQuerySqlByNewDate(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT DATE_FORMAT(i.record_time, '%Y-%m-%d %H:%i') as record_time FROM iptv_date i where  pk=1");
		return excuteSql.toString();
	}
	
	public static String getBeginDate(Map param)throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String initial = param.get("initial").toString();
		String bgDate ="";
		String endDate = param.get("endDate").toString();
		//System.out.println("endDate  "+endDate);
		Calendar endDate_c = Calendar.getInstance();
		endDate_c.setTime(sdf.parse(endDate));
		if("0".equals(initial)){
			String clientDate = param.get("clientDate").toString();
			//System.out.println("clientDate  "+clientDate);
			Calendar clientDate_c = Calendar.getInstance();
			clientDate_c.setTime(sdf.parse(clientDate));
			if(clientDate_c.before(endDate_c)||clientDate_c.equals(endDate_c)){
				bgDate=clientDate;
				//System.out.println("bgDate===="+bgDate);
			}
		}else{
			endDate_c.add(Calendar.MINUTE, -Integer.parseInt(initial));
			bgDate = sdf.format(endDate_c.getTime());
			//System.out.println("bgDate####"+bgDate);
		}
		return bgDate;
	}
	
}

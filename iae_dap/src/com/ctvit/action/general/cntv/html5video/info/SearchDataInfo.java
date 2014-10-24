package com.ctvit.action.general.cntv.html5video.info;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
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
 * 查询数据--按时段查询
 * @日期 2013-10-22
 */
public class SearchDataInfo  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(SearchDataInfo.class);
	private Properties pros = null;

	public SearchDataInfo() throws Exception {
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
		String chnnlName=(String)getParamName("chnnlName");//频道名称
		String searchSource=(String)getParamName("searchSource");//查询来源
		Map param=new HashMap();
		param.put("user_name", user_name);
		param.put("type", type);
		param.put("bgDate", bgDate);
		param.put("endDate", endDate);
		param.put("searchSource", searchSource);
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		Map result = new HashMap();
		try{ 
			if(param.get("type").equals("3")){//市场份额
				String channelSql = createQuerySql(param);
				String checkedChannel = excuteSql.executeSqlAsHashMapClobGetTure(channelSql);
				if(checkedChannel!=null&&!"".equals(checkedChannel)){
					checkedChannel = checkedChannel.substring(0,checkedChannel.length()-1);
					param.put("checkedChannel", checkedChannel);
					String dataSql = createQuerySqlDataToShiChang(param);
					Map map = excuteSql.executeSqlAsHashMapClobGetDayAndShouShi(dataSql,param);
					result.put(param.get("searchSource"), map);
				}else{
					result.put(param.get("searchSource"), "false");//没有进行频道设置时，返回false
				}
			}else if(param.get("type").equals("4")){//流入流出
				//Window 测试下需要转码，Linux环境不需要转换。故做判断操作系统类型
				if(SystemUtils.OS_NAME.contains("Windows")){
					param.put("chnnlName", new String(chnnlName.getBytes("ISO-8859-1"),"UTF-8"));							
				}else{
					param.put("chnnlName", chnnlName);						
				}
				String dataSql = createQuerySqlDataToFlow(param);
				Map map = excuteSql.executeSqlAsHashMapClobToFlow(dataSql,param);
				result.put(param.get("searchSource"), map);
			}else{//到达率、收视率
				//Window 测试下需要转码，Linux环境不需要转换。故做判断操作系统类型
				if(SystemUtils.OS_NAME.contains("Windows")){
					param.put("chnnlName", new String(chnnlName.getBytes("ISO-8859-1"),"UTF-8"));							
				}else{
					param.put("chnnlName", chnnlName);						
				}
				String dataSql = createQuerySqlData(param);
				Map map = excuteSql.executeSqlAsHashMapClobGetMinute(dataSql,param);
				result.put(param.get("searchSource"), map);
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
		excuteSql
		.append("SELECT DATE_FORMAT(i.record_time,'%H:%i')as record_time,");
		if(param.get("type").equals("1")){
			excuteSql.append("i.arrive_rating ");
		}else if (param.get("type").equals("2")){
			excuteSql.append("i.audi_rating ");
		}
		excuteSql.append(" FROM iptv_rating i where i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' and ")
		.append(" i.channelid in (select r.pk from iptv_channel_relation r where r.name in ('"+param.get("chnnlName")+"')) and area_flag='0' ");
		return excuteSql.toString();
	}
	/**
	 * 市场份额--按时段查询
	 * @param param
	 * @return
	 */
	public static String createQuerySqlDataToShiChang(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("select * from (SELECT sum(i.audi_share)as data, ");
		excuteSql.append("(select r.name from iptv_channel_relation r where r.pk = i.channelid ) as channelName FROM iptv_rating i where i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' ") 
		.append(" and i.channelid in (select r.pk from iptv_channel_relation r where r.logo in ("+param.get("checkedChannel")+"))  and i.area_flag='0' ")
		.append(" group by i.channelid ")
		.append(" union all ")
		.append("  SELECT sum(i.audi_share)as audi_share, ")
		.append(" '其他频道' as channelName FROM iptv_rating i where i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' ") 
		.append(" and i.channelid in (select r.pk from iptv_channel_relation r where r.logo not in ("+param.get("checkedChannel")+"))  and i.area_flag='0' ")
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
	/**
	 * 流入流出
	 * @param param
	 * @return
	 */
	//http://10.65.8.112:8082/iptv/cntv/getSearchDataInfo.jsonp?callback=?&user_name=admin&type=4&bgDate=2014-10-22%2018:21&endDate=2014-10-23%2018:30&chnnlName=湖南国际&searchSource=liuruliuchu
	public static String createQuerySqlDataToFlow(Map param) {
		
		StringBuilder excuteSql = new StringBuilder();
		excuteSql.append("SELECT DATE_FORMAT(t.record_time, '%H:%i') as record_time,")
		.append("       t.inflow_rating,")
		.append("       t.outflow_rating,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where  i.record_time = t.record_time")
		.append("         order by inflow_rating desc limit 0, 1)) as inflow_first,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where i.record_time = t.record_time")
		.append("         order by inflow_rating desc limit 1, 1)) as inflow_second,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where i.record_time = t.record_time")
		.append("         order by inflow_rating desc limit 2, 1)) as inflow_third,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where i.record_time = t.record_time")
		.append("         order by outflow_rating desc limit 0, 1)) as outflow_first,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where i.record_time = t.record_time")
		.append("         order by outflow_rating desc limit 1, 1)) as outflow_second,")
		.append("       (select c.name from iptv_channel_relation c where c.pk = (select i.channelid")
		.append("          from iptv_rating i")
		.append("         where i.record_time = t.record_time")
		.append("         order by outflow_rating desc limit 2, 1)) as outflow_third")
		.append("  FROM iptv_rating t")
		.append(" where t.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"'")
		.append("   and t.channelid in")
		.append("       (select r.pk from iptv_channel_relation r where r.name in ('"+param.get("chnnlName")+"'))")
		.append("   and t.area_flag = '0' order by t.record_time");
		return excuteSql.toString();
	}
	
	
}

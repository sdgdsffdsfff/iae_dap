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
 * 查询数据--按地区查询
 * @日期 2013-10-22
 */
public class SearchDataByArea  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(SearchDataByArea.class);
	private Properties pros = null;

	public SearchDataByArea() throws Exception {
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
		//http://localhost:8082/iptv/cntv/getSearchDataByArea.jsonp?callback=?&user_name=admin&type=1&bgDate=2014-10-22&endDate=2014-10-23&searchSource=DaodaArea
		try{ 
			String channelSql = createQuerySql(param);
			String checkedChannel = excuteSql.executeSqlAsHashMapClobGetTure(channelSql);
			if(checkedChannel!=null&&!"".equals(checkedChannel)){
				checkedChannel = checkedChannel.substring(0,checkedChannel.length()-1);
				param.put("checkedChannel", checkedChannel);
				String dataSql = createQuerySqlData(param);
				Map map = excuteSql.executeSqlAsHashMapClobGetArea(dataSql,param);
				result.put(param.get("searchSource"), map);
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
			log.error("获取数据失败");
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
			excuteSql.append("sum(i.arrive_rating)as data ,");
		}else if (param.get("type").equals("2")){
			excuteSql.append("sum(i.audi_rating)as data ,");
		}
		excuteSql.append("(select r.name from iptv_channel_relation r where r.pk = i.channelid ) as channelName,i.area FROM iptv_rating i where i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' and ") 
		.append(" i.channelid in (select r.pk from iptv_channel_relation r where r.logo in ("+param.get("checkedChannel")+")) group by i.channelid ,i.area ")
		.append(" union all ")
		.append(" SELECT ");
		if(param.get("type").equals("1")){
			excuteSql.append("sum(i.arrive_rating)as data ,");
		}else if (param.get("type").equals("2")){
			excuteSql.append("sum(i.audi_rating)as data ,");
		}
		excuteSql.append(" '其他'as channelName,i.area FROM iptv_rating i where i.record_time between '"+param.get("bgDate")+"' and '"+param.get("endDate")+"' and  ")
		.append(" i.channelid in (select r.pk from iptv_channel_relation r where r.logo not in ("+param.get("checkedChannel")+")) group by i.area ) z order by z.channelName desc,z.area");
		
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

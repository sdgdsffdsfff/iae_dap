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
 * 系统设置信息
 * @日期 2013-10-22
 */
public class SystemInfo  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(SystemInfo.class);
	private Properties pros = null;

	public SystemInfo() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String date=(String)getParamName("date");
		String  systemSql= "";
		if(date!=null&&!"".endsWith(date)){
			Map param=new HashMap();
			param.put("date", date);
			systemSql= createQuerySql(param);
		}else{
			systemSql= createQuerySqlToNew();
		}
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		Map map = new HashMap();
		try{ 
			List<Map> Map_list = excuteSql.executeSqlAsHashMapClob(systemSql);
			if(Map_list.size()>=1){
				map.put("systemInfo", Map_list);
				Converter<Map> converterJsonpStrategy=null;
				if(dataType.equals("jsonp")){
					converterJsonpStrategy=new ConverterJsonp<Map>();			
				}else if(dataType.equals("json")){
					converterJsonpStrategy=new ConverterJson<Map>();			
				}
				ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
				String runConverter = context.runConverter(map);
				build(runConverter);
				log.info("获取系统设置信息:"+runConverter);
			}else{
				log.info("获取系统设置信息失败");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		return null;
	}
	
	/**
	 * 查询频道信息
	 * @param param
	 * @return
	 */
	public static String createQuerySql(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT * FROM iptv_setting i where  i.record_time = '"+param.get("date")+"' ");
		return excuteSql.toString();
	}
	
	/**
	 * 查询频道信息--最新保存的系统设置
	 * @param param
	 * @return
	 */
	public static String createQuerySqlToNew() {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT * FROM iptv_setting i where i.flag = '0' ");
		return excuteSql.toString();
	}
}

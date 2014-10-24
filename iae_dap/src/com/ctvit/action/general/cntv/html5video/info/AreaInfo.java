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
 * 地域信息
 * @日期 2013-10-22
 */
public class AreaInfo  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(AreaInfo.class);
	private Properties pros = null;

	public AreaInfo() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String  chnnlSql= createQuerySql();
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		Map result = new HashMap();
		try{ 
			List<Map> Map_list = excuteSql.executeSqlAsHashMapClob(chnnlSql);
			if(Map_list.size()>=1){
				result.put("areaList", Map_list);
				Converter<Map> converterJsonpStrategy=null;
				if(dataType.equals("jsonp")){
					converterJsonpStrategy=new ConverterJsonp<Map>();			
				}else if(dataType.equals("json")){
					converterJsonpStrategy=new ConverterJson<Map>();			
				}
				ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
				String runConverter = context.runConverter(result);
				build(runConverter);
				log.info("获取地域列表信息:"+runConverter);
			}else{
				log.info("获取地域列表失败");
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
	public static String createQuerySql() {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT distinct i.area FROM iptv_rating i order by i.area ");
		return excuteSql.toString();
	}
}

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

import org.apache.commons.lang.StringEscapeUtils;
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
 * 登录
 * @日期 2013-10-22
 */
public class LoginInfo  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(LoginInfo.class);
	private Properties pros = null;

	public LoginInfo() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String user_name=StringEscapeUtils.escapeSql((String)getParamName("user_name"));
		String user_password=StringEscapeUtils.escapeSql((String)getParamName("user_password"));
		Map param=new HashMap();
		param.put("user_name", user_name);
		param.put("user_password", user_password);
		String userSql = createQuerySql(param);
		Connection con = null;
		con = C3P0Utils.getConnection();
		ExecuteSql excuteSql = new ExecuteSql(con);
		try{ 
			List<Map> Map_list = excuteSql.executeSqlAsHashMapClob(userSql);
			if(Map_list.size()>=1){
				//HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = getHttpServletRequest().getSession();
				session.setAttribute("userInfo", Map_list.get(0));
				Converter<Map> converterJsonpStrategy=null;
				if(dataType.equals("jsonp")){
					converterJsonpStrategy=new ConverterJsonp<Map>();			
				}else if(dataType.equals("json")){
					converterJsonpStrategy=new ConverterJson<Map>();			
				}
				ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
				String runConverter = context.runConverter(Map_list.get(0));
				build(runConverter);
				log.info("登录信息:"+runConverter);
			}else{
				log.info("登录失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		return null;
	}
	
	/**
	 * 查询用户信息
	 * @param param
	 * @return
	 */
	public static String createQuerySql(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("SELECT * FROM iptv_user i where i.status='0' and i.user_name = '"+param.get("user_name")+"' and i.user_password ='"+param.get("user_password")+"'");
		return excuteSql.toString();
	}
}

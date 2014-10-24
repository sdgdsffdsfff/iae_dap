package com.ctvit.action.general.cntv.html5video.info;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
 * 密码修改
 * @日期 2013-10-22
 */
public class ModifyPassword  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(ModifyPassword.class);
	private Properties pros = null;

	public ModifyPassword() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String pk = (String)getParamName("pk");
		String old_password=StringEscapeUtils.escapeSql((String)getParamName("old_password"));
		String new_password=StringEscapeUtils.escapeSql((String)getParamName("new_password"));
		Map param=new HashMap();
		param.put("pk", pk);
		param.put("old_password", old_password);
		param.put("new_password", new_password);
		Map result =new HashMap();
		HttpSession session = getHttpServletRequest().getSession();
		Map map_user = (Map)session.getAttribute("userInfo");
		String user = (String)map_user.get("user_name");
		Connection conn = null;
		Statement stmt = null;
		conn = C3P0Utils.getConnection();
		String userSql = "";
		try{ 
			userSql = createQuerySql(param);
			ExecuteSql excuteSql = new ExecuteSql(conn);
			List<Map> Map_list = excuteSql.executeSqlAsHashMapClob(userSql);
			result.put("userInfo", Map_list);
			if(param.get("old_password").equals(Map_list.get(0).get("user_password"))){
				stmt = conn.createStatement();
				userSql = createQuerySqlUpdate(param);
				stmt.executeUpdate(userSql);
				result.put("message", "success");
				log.info(user+"密码修改成功");
			}else{
				result.put("message", "error");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt!=null){stmt.close();}
			C3P0Utils.closeConnection();
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
		.append("SELECT i.user_password FROM iptv_user i where i.pk = "+param.get("pk"));
		return excuteSql.toString();
	}
	/**
	 * 修改用户
	 * @param param
	 * @return
	 */
	public static String createQuerySqlUpdate(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("update iptv_user set user_password='"+param.get("new_password")+"' where pk = "+param.get("pk")+"");
		return excuteSql.toString();
	}
}

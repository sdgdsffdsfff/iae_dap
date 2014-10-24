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
 * 密码重置
 * @日期 2013-10-22
 */
public class ResetPassword  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(ResetPassword.class);
	private Properties pros = null;

	public ResetPassword() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String pk = (String)getParamName("pk");
		Map param=new HashMap();
		param.put("pk", pk);
		Map result =new HashMap();
		HttpSession session = getHttpServletRequest().getSession();
		Map map_user = (Map)session.getAttribute("userInfo");
		String user = (String)map_user.get("user_name");
		Connection conn = null;
		Statement stmt = null;
		conn = C3P0Utils.getConnection();
		String userSql = "";
		try{ 
			stmt = conn.createStatement();
			userSql = createQuerySqlUpdate(param);
			stmt.executeUpdate(userSql);
			result.put("message", "success");
			log.info(user+"密码重置成功");
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
	 * 修改用户
	 * @param param
	 * @return
	 */
	public static String createQuerySqlUpdate(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("update iptv_user set user_password='123456' where pk = "+param.get("pk")+"");
		return excuteSql.toString();
	}
}

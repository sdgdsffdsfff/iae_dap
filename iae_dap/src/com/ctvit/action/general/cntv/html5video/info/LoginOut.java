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
 * 退出登录
 * @日期 2013-10-22
 */
public class LoginOut  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(LoginOut.class);
	private Properties pros = null;

	public LoginOut() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		HttpSession session = getHttpServletRequest().getSession();
		Map result = new HashMap();
		session.removeAttribute("userInfo"); 
		session.invalidate();
		result.put("message", "OK");
		log.info("用户退出成功");
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
}

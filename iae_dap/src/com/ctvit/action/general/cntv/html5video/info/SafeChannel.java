package com.ctvit.action.general.cntv.html5video.info;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 * 保存频道设置
 * @日期 2013-10-22
 */
public class SafeChannel  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(SafeChannel.class);
	private Properties pros = null;

	public SafeChannel() throws Exception {
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String  param=(String)getParamName("param");
		JSONObject  jasonObject = JSONObject.fromObject(param);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = C3P0Utils.getConnection();
			stmt = conn.createStatement();
			StringBuffer sbSql = new StringBuffer();
			StringBuffer sbColumn = new StringBuffer();
			StringBuffer sbValue = new StringBuffer();
			StringBuffer pk = new StringBuffer();
			Iterator  keyIter = jasonObject.keys();
			String key;
			Object value;
			while( keyIter.hasNext())
			{
				key = (String)keyIter.next();
				value = jasonObject.get(key);
				if(!key.equals("user_name")&&!key.equals("type")){
				}else{
					pk.append(jasonObject.get(key));
				}
				sbColumn.append(","+key);
				sbValue.append(",'"+value+"'");
			}
			sbSql.append("replace into iptv_channel(pk").append(sbColumn).append(") values('").append(pk).append("'").append(sbValue).append(")");
			stmt.executeQuery(sbSql.toString());
			Map map = new HashMap();
			map.put("message", "success");
			Converter<Map> converterJsonpStrategy=null;
			if(dataType.equals("jsonp")){
				converterJsonpStrategy=new ConverterJsonp<Map>();			
			}else if(dataType.equals("json")){
				converterJsonpStrategy=new ConverterJson<Map>();			
			}
			ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
			String runConverter = context.runConverter(map);
			build(runConverter);
			log.info("保存频道设置成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("保存频道设置失败", e);
			e.printStackTrace();
		}finally{
			stmt.close();
			C3P0Utils.closeConnection();
		}
		return null;
	}
	
}

package com.ctvit.action.general.cntv.html5video.info;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
 * 系统设置信息
 * @日期 2013-10-22
 */
public class UpdateSystem  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(UpdateSystem.class);
	private Properties pros = null;

	public UpdateSystem() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String  param=(String)getParamName("param");
		JSONObject  jasonObject = JSONObject.fromObject(param);
		 //取查询参数中的systemInfo,是个json数组
		JSONArray jsons = jasonObject.getJSONArray("systemInfo");
		int jsonLength = jsons.size();
		Map map = new HashMap();
		HttpSession session = getHttpServletRequest().getSession();
		Map map_user = (Map)session.getAttribute("userInfo");
		//验证当前用户是否是管理员
		if("0".equals(map_user.get("flag"))){
			Connection conn = null;
			Statement ps = null;
			SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
			try {
				conn = C3P0Utils.getConnection();
				conn.setAutoCommit(false);
				ps = conn.createStatement();
				//flag=0代表最新保存 flag=1代表历史数据;   先把之前为0的更新成历史数据，然后在进行批量插入最新的数据
				String update_sql="update iptv_setting set flag ='1' where flag='0'";
				ps.addBatch(update_sql);
				for (int i = 0; i < jsonLength; i++) {
					JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
					String area = StringEscapeUtils.escapeSql(tempJson.getString("area"));
					String arrive = StringEscapeUtils.escapeSql(tempJson.getString("arrive"));
					String audi = StringEscapeUtils.escapeSql(tempJson.getString("audi"));
					String population = StringEscapeUtils.escapeSql(tempJson.getString("population"));
					String date = sdm.format(new Date());
					String sql = "replace into iptv_setting(pk,area,flag,arrive,audi,population,record_time) values('"+area+date+"','"+area+"','0','"+arrive+"','"+audi+"','"+population+"','"+date+"');";
					ps.addBatch(sql);
				}
				int[] rows=ps.executeBatch();
				conn.commit();
				if(rows.length>0){
					map.put("message", "设置成功");
					log.info("设置当前权重成功");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("设置当前权重失败", e);
				e.printStackTrace();
			}finally{
				ps.close();
				C3P0Utils.closeConnection();
			}
		}else{
			map.put("message", "您没有权限设置");
		}
		Converter<Map> converterJsonpStrategy=null;
		if(dataType.equals("jsonp")){
			converterJsonpStrategy=new ConverterJsonp<Map>();			
		}else if(dataType.equals("json")){
			converterJsonpStrategy=new ConverterJson<Map>();			
		}
		ConverterContext<Map> context=new ConverterContext<Map>(converterJsonpStrategy);
		String runConverter = context.runConverter(map);
		build(runConverter);
		return null;
	}
	
}

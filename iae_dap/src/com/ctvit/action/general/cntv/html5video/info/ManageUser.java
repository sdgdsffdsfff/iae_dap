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
 * 用户管理
 * @日期 2013-10-22
 */
public class ManageUser  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(ManageUser.class);
	private Properties pros = null;

	public ManageUser() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String callbackFunction = (String)getParamName("callback");
		String type = (String)getParamName("type");
		String status = (String)getParamName("status");
		String flag = (String)getParamName("flag");
		String pk = (String)getParamName("pk");
		String user_name=StringEscapeUtils.escapeSql((String)getParamName("user_name"));
		String user_password=StringEscapeUtils.escapeSql((String)getParamName("user_password"));
		String checkUser = (String)getParamName("checkUser");//用于添加用户时精确查询用户是否存在
		Map param=new HashMap();
		param.put("type", type);
		param.put("pk", pk);
		param.put("flag", flag);
		param.put("status", status);
		param.put("user_name", user_name);
		param.put("user_password", user_password);
		param.put("checkUser", checkUser);
		Map result =new HashMap();
		HttpSession session = getHttpServletRequest().getSession();
		Map map_user = (Map)session.getAttribute("userInfo");
		//验证当前用户是否是管理员
		if("0".equals(map_user.get("flag"))){
			String user = (String)map_user.get("user_name");
			Connection conn = null;
			Statement stmt = null;
			conn = C3P0Utils.getConnection();
			String userSql = "";
			try{ 
				if(type.equals("search")){
					userSql = createQuerySql(param);
					ExecuteSql excuteSql = new ExecuteSql(conn);
					List<Map> Map_list = excuteSql.executeSqlAsHashMapClob(userSql);
					result.put("userInfo", Map_list);
				}else if(type.equals("add")){
					stmt = conn.createStatement();
					userSql = createQuerySqlAdd(param);
					stmt.executeUpdate(userSql);
					result.put("message", "add success");
					log.info(user+"添加成功");
				}else if(type.equals("update")){
					stmt = conn.createStatement();
					userSql = createQuerySqlUpdate(param);
					stmt.executeUpdate(userSql);
					result.put("message", "update success");
					log.info(user+"修改成功");
				}else if(type.equals("delete")){
					stmt = conn.createStatement();
					userSql = createQuerySqlDelete(param);
					stmt.executeUpdate(userSql);
					if("1".equals(param.get("status"))){
						result.put("message", "删除成功");
						log.info(user+"删除成功");
					}else{
						result.put("message", "恢复成功");
						log.info(user+"恢复成功");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(stmt!=null){stmt.close();}
				C3P0Utils.closeConnection();
			}
		}else{
			result.put("message", "您没有用户管理权限");
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
		.append("SELECT i.pk,i.user_name,i.flag,i.status FROM iptv_user i ");
		if(param.get("user_name")!=null&&!"".equals(param.get("user_name"))){
			if(param.get("checkUser")!=null&&!"".equals(param.get("checkUser"))){
				excuteSql.append("where i.user_name = '"+param.get("user_name")+"'");
			}else{
				excuteSql.append("where i.user_name like '%"+param.get("user_name")+"%'");
			}
		}
		if(param.get("pk")!=null&&!"".equals(param.get("pk"))){
			excuteSql.append("where i.pk = "+param.get("pk")+"");
		}
		excuteSql.append(" order by i.flag,i.pk ");
		excuteSql.append(" limit 10 ");
		return excuteSql.toString();
	}
	/**
	 * 添加用户
	 * @param param
	 * @return
	 */
	public static String createQuerySqlAdd(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("insert into iptv_user  (user_name,user_password,flag,status) values ('"+param.get("user_name")+"','"+param.get("user_password")+"','"+param.get("flag")+"','"+param.get("status")+"')");
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
		.append("update iptv_user set flag='"+param.get("flag")+"',status='"+param.get("status")+"' where pk = "+param.get("pk")+"");
		return excuteSql.toString();
	}
	/**
	 * 删除用户、恢复用户
	 * @param param
	 * @return
	 */
	public static String createQuerySqlDelete(Map param) {
		StringBuilder excuteSql = new StringBuilder();
		excuteSql
		.append("update iptv_user set status='"+param.get("status")+"' where pk = "+param.get("pk")+"");
		return excuteSql.toString();
	}
}

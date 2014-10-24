package com.ctvit.action.dap;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletOutputStream;




import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.ctvit.action.base.ActionSupport;
import com.ctvit.dbutils.C3P0Utils;
import com.ctvit.dbutils.ExecuteSql;


/**
 * 频道设置信息
 * @日期 2013-10-22
 */
public class UserTimeData  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(UserTimeData.class);
	private Properties pros = null;
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd");
	public UserTimeData() throws Exception {
		//pros = ReadProperties.getInstance().readProperties("DangYangWang_SiteMap.properties");	
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Connection con = null;
		try {
			con = C3P0Utils.getConnection();
			ExecuteSql excuteSql = new ExecuteSql(con);
			String sql = "select * from userdailycount order by date asc";
			ResultSet rs = excuteSql.executeSql(sql);
			
			this.getHttpServletResponse().setContentType("text;html;charset=utf-8");
			  PrintWriter out = this.getHttpServletResponse().getWriter();
			  JSONObject json = new JSONObject();
			  SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
			  List<String> list = new ArrayList<String>();
				list.add("TigN测试");
				list.add("System Architect");
				list.add("Edinburgh");
				list.add("5421");
				list.add("2011-04-25");
				list.add("$320,800");
				List<String> list2 = new ArrayList<String>();
				list2.add(listToString(list));
				list.clear();
				list.add("Tiger Nixon2");
				list.add("System Architect2");
				list.add("Edinburgh2");
				list.add("54212");
				list.add("2011-04-252");
				list.add("$320,8002");
				list2.add(listToString(list));
				String result = listToResult(list2);
			  out.print(result);
			  out.flush();
			  out.close();
			return null;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		return null;
		
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Tiger Nixon");
		list.add("System Architect");
		list.add("Edinburgh");
		list.add("5421");
		list.add("2011-04-25");
		list.add("$320,800");
		List<String> list2 = new ArrayList<String>();
		list2.add(listToString(list));
		list.clear();
		list.add("Tiger Nixon2");
		list.add("System Architect2");
		list.add("Edinburgh2");
		list.add("54212");
		list.add("2011-04-252");
		list.add("$320,8002");
		list2.add(list.toString());
		list2.add(listToString(list));
		System.out.println(listToResult(list2));
		
	}
	
	public static String listToString(List<String> list){
		String result = "[";
		for(String str :list){
			result = result +"\""+str+"\",";
		}
		result = result.substring(0, result.length()-1);
		result +="]";
		
		
		return result;
	}
	
	public static String listToResult(List<String> list){
		String result = "{ \"data\" :"+list.toString()+"}";
		
		
		return result;
	}
	
	
}

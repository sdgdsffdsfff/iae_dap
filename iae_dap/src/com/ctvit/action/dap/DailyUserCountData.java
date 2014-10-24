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
public class DailyUserCountData  extends ActionSupport {
	private static final long serialVersionUID = 4369073531652262056L;
	private static final Logger log = Logger.getLogger(DailyUserCountData.class);
	private Properties pros = null;
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd");
	public DailyUserCountData() throws Exception {
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
			String date = "";
			String nowDate = "";
			List<String> list = new ArrayList<String>();
			if(rs.next()){
				date = rs.getString(2);
				nowDate = DailyUserCountData.nextDay(rs.getString(2));
				list.add(rs.getString(3));
			}while(rs.next()){
				while(!rs.getString(2).equals(nowDate)){
					
					nowDate = DailyUserCountData.nextDay(nowDate);
					list.add("0");
				}
				list.add(rs.getString(3));
				nowDate = DailyUserCountData.nextDay(nowDate);
			}
			System.out.println(list);
			System.out.println(date);
			this.getHttpServletResponse().setContentType("text;html;charset=utf-8");
			  ServletOutputStream out = this.getHttpServletResponse().getOutputStream();
			  JSONObject json = new JSONObject();
			  SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd");
			  long time = format.parse(date).getTime();
			  json.put("data", list);
			  json.put("date", time);
			  out.print(json.toString());
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
		Connection con = null;
		try {
			con = C3P0Utils.getConnection();
			ExecuteSql excuteSql = new ExecuteSql(con);
			String sql = "select * from userdailycount order by date asc";
			ResultSet rs = excuteSql.executeSql(sql);
			String date = "";
			String nowDate = "";
			List<String> list = new ArrayList<String>();
			if(rs.next()){
				date = rs.getString(2);
				nowDate = DailyUserCountData.nextDay(rs.getString(2));
				list.add(rs.getString(3));
			}while(rs.next()){
				while(!rs.getString(2).equals(nowDate)){
					
					nowDate = DailyUserCountData.nextDay(nowDate);
					list.add("0");
				}
				list.add(rs.getString(3));
				nowDate = DailyUserCountData.nextDay(nowDate);
			}
			date = date.replaceAll("-", ",");
			System.out.println(list);
			System.out.println(date);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			C3P0Utils.closeConnection();
		}
		
	}
	
	public static String nextDay(String nowDate) throws ParseException{
		 Date now = format.parse(nowDate);
		 Date next = new Date(now.getTime()+24*60*60*1000);
		 return format.format(next);
		 }
	
	public String tableData() throws Exception {
		Connection con = null;
		try {
			con = C3P0Utils.getConnection();
			String type = this.getHttpServletRequest().getParameter("type");
			String value = this.getHttpServletRequest().getParameter("value");
			ExecuteSql excuteSql = new ExecuteSql(con);
			String sql = "select * from userdailycount order by date asc";

			
			ResultSet rs = excuteSql.executeSql(sql);
			String date = "";
			String nowDate = "";
			List<String> list = new ArrayList<String>();
			List<String> tmp = new ArrayList<String>();
			if(rs.next()){
				date = rs.getString(2);
				nowDate = DailyUserCountData.nextDay(rs.getString(2));
				tmp.add( rs.getString(2));
				tmp.add( rs.getString(3));
				list.add(UserTimeData.listToString(tmp));
				tmp.clear();
			}while(rs.next()){
				while(!rs.getString(2).equals(nowDate)){
					
					nowDate = DailyUserCountData.nextDay(nowDate);
					tmp.add( nowDate);
					tmp.add("0");
					list.add(UserTimeData.listToString(tmp));
					tmp.clear();
				}
				tmp.add( nowDate);
				tmp.add(rs.getString(3));
				list.add(UserTimeData.listToString(tmp));
				tmp.clear();
				nowDate = DailyUserCountData.nextDay(nowDate);
			}
			System.out.println(UserTimeData.listToResult(list));
			this.getHttpServletResponse().setContentType("text;html;charset=utf-8");
			  PrintWriter out = this.getHttpServletResponse().getWriter();
			  out.print(UserTimeData.listToResult(list));
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
}

package com.ctvit.dbutils;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 执行SQL语句返回不同格式的List
 * 
 * @throws SQLException
 */
public class ExecuteSql {
	private java.sql.Connection conn;

	public ExecuteSql(java.sql.Connection conn) {
		this.conn = conn;
	}

	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象
	 * 
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public List executeSqlAsHashMap(String sql) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		List list = new ArrayList();
		try{
		if (conn == null)
			return null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsm = rs.getMetaData();
		int count = rsm.getColumnCount();
		while (rs.next()) {
			HashMap map = new HashMap();
			for (int i = 0; i < count; i++) {
				String columnName = rsm.getColumnName((i + 1));
				int sqlType = rsm.getColumnType(i + 1);
				Object sqlView = rs.getString(columnName);
				if (Types.VARCHAR == sqlType && null != sqlView) {
					String str = sqlView.toString().trim();
					map.put(columnName.toLowerCase(), new String(str.getBytes("ISO-8859-1"),
							"gbk"));
				} else {
					if (sqlView != null
							&& sqlView.getClass() == java.lang.String.class) {
						String str = sqlView.toString();
						str = new String(str.getBytes("ISO-8859-1"), "gbk");
						map.put(columnName.toLowerCase(), str);
					} else {
						map.put(columnName.toLowerCase(), sqlView);
					}
				}
			}
			list.add(map);
		}
		}catch (Exception e) {
			
		}finally{
			rs.close();
			stmt.close();
		}
		
		return list;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public List executeSqlAsHashMapClob(String sql) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		List list = new ArrayList();
		try{
		if (conn == null)
			return null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		ResultSetMetaData rsm = rs.getMetaData();
		int count = rsm.getColumnCount();
		while (rs.next()) {
			HashMap map = new HashMap();
			for (int i = 0; i < count; i++) {
				String columnName = rsm.getColumnName((i + 1));
				int sqlType = rsm.getColumnType(i + 1);
				Object sqlView = rs.getString(columnName);
				if (Types.VARCHAR == sqlType && null != sqlView) {
					String str = sqlView.toString().trim();
					map.put(columnName.toLowerCase(), str);
				} else {
					if (sqlView != null
							&& sqlView.getClass() == java.lang.String.class) {
						String str = sqlView.toString();
						map.put(columnName.toLowerCase(), str);
					} else if(sqlView == null){
						map.put(columnName.toLowerCase(), "");
					}else {
						map.put(columnName.toLowerCase(), sqlView);
					}
				}
			}
			list.add(map);
		}
		}catch (Exception e) {
			
		}finally{
			rs.close();
			stmt.close();
		}
		
		return list;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于频道设置查询
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public List executeSqlAsHashMapClobToChnnl(String sql) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		List list = new ArrayList();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			while (rs.next()) {
				HashMap map = new HashMap();
				List zhzh_list = new ArrayList();
				List shn_list = new ArrayList();
				List ff_list = new ArrayList();
				List ysh_list = new ArrayList();
				List wsh_list = new ArrayList();
				for (int i = 0; i < count; i++) {
					String columnName = rsm.getColumnName((i + 1));
					int sqlType = rsm.getColumnType(i + 1);
					Object sqlView = rs.getString(columnName);
					Object columnValue = "";
					if (Types.VARCHAR == sqlType && null != sqlView) {
						columnValue = sqlView.toString().trim();
					} else {
						if (sqlView != null
								&& sqlView.getClass() == java.lang.String.class) {
							columnValue = sqlView.toString();
						} else if (sqlView == null) {
							columnValue = "";
						} else {
							columnValue = sqlView;
						}
					}
					if (columnName.indexOf("_shjshx") >= 0) {
						Map zh_map = new HashMap();
						zh_map.put(columnName.toLowerCase(), columnValue);
						zhzh_list.add(zh_map);
						map.put("shjshx", zhzh_list);
					} else if (columnName.indexOf("_shjfshx") >= 0) {
						Map shn_map = new HashMap();
						shn_map.put(columnName.toLowerCase(), columnValue);
						shn_list.add(shn_map);
						map.put("shjfshx", shn_list);
					} else if (columnName.indexOf("_zhyj") >= 0) {
						Map ff_map = new HashMap();
						ff_map.put(columnName.toLowerCase(), columnValue);
						ff_list.add(ff_map);
						map.put("zhyj", ff_list);
					} else if (columnName.indexOf("_qt") >= 0&&columnName.indexOf("_qtff") <0) {
						Map ysh_map = new HashMap();
						ysh_map.put(columnName.toLowerCase(), columnValue);
						ysh_list.add(ysh_map);
						map.put("qt", ysh_list);
					} else if (columnName.indexOf("_qtff") >= 0) {
						Map wsh_map = new HashMap();
						wsh_map.put(columnName.toLowerCase(), columnValue);
						wsh_list.add(wsh_map);
						map.put("qtff", wsh_list);
					}else {
						map.put(columnName.toLowerCase(), columnValue);
					}

				}
				list.add(map);
			}
		} catch (Exception e) {

		} finally {
			rs.close();
			stmt.close();
		}

		return list;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于查询数据(获取值为1的列名)
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public String executeSqlAsHashMapClobGetTure(String sql) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		StringBuffer stb = new StringBuffer();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			while (rs.next()) {
				for (int i = 0; i < count; i++) {
					String columnName = rsm.getColumnName((i + 1));
					int sqlType = rsm.getColumnType(i + 1);
					Object sqlView = rs.getString(columnName);
					Object columnValue = "";
					if (Types.VARCHAR == sqlType && null != sqlView) {
						columnValue = sqlView.toString().trim();
					} else {
						if (sqlView != null
								&& sqlView.getClass() == java.lang.String.class) {
							columnValue = sqlView.toString();
						} else if (sqlView == null) {
							columnValue = "";
						} else {
							columnValue = sqlView;
						}
					}
					if (columnName.indexOf("_shjshx") >= 0 || columnName.indexOf("_shjfshx") >= 0|| columnName.indexOf("_zhyj") >= 0|| columnName.indexOf("_qt") >= 0|| columnName.indexOf("_qtff") >= 0) {
						if(columnValue.equals("1")){
							stb.append("'"+columnName.toLowerCase()+"',");
						}
					}
				}
			}
		} catch (Exception e) {

		} finally {
			rs.close();
			stmt.close();
		}

		return stb.toString();
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于查询最新入库时间
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public String executeSqlAsHashMapClobByNewDate(String sql) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		StringBuffer stb = new StringBuffer();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			while (rs.next()) {
				stb.append(rs.getString("record_time"));
			}
		} catch (Exception e) {

		} finally {
			rs.close();
			stmt.close();
		}

		return stb.toString();
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于查询每分钟的数据--按时段查询
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobGetMinute(String sql,Map searchmap) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			List time_list = new ArrayList();
			List data_list = new ArrayList();
			while (rs.next()) {
				for (int i = 0; i < count; i++) {
					String columnName = rsm.getColumnName((i + 1));
					int sqlType = rsm.getColumnType(i + 1);
					Object sqlView = rs.getString(columnName);
					Object columnValue = "";
					if (Types.VARCHAR == sqlType && null != sqlView) {
						columnValue = sqlView.toString().trim();
					} else {
						if (sqlView != null
								&& sqlView.getClass() == java.lang.String.class) {
							columnValue = sqlView.toString();
						} else if (sqlView == null) {
							columnValue = "";
						} else {
							columnValue = sqlView;
						}
					}
					if (columnName.indexOf("record_time") >= 0) {
						time_list.add((String)columnValue);
					} else if (columnName.indexOf("arrive_rating") >= 0||columnName.indexOf("audi_rating") >= 0||columnName.indexOf("audi_share") >= 0) {
						data_list.add(columnValue);
					}
				}
			}
			map.put("categories", time_list);
			map.put("data", data_list);
			map.put("name", searchmap.get("chnnlName"));
			map.put("beginTime", searchmap.get("bgDate"));
			map.put("endTime", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于查询每个地区的数据
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobGetArea(String sql,Map searchmap) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			List chnl_list = new ArrayList();
			List area_list = new ArrayList();
			List data_list = new ArrayList();
			List data_item = null;
			Set chnl_set = new HashSet();
			Set area_set = new HashSet();
			
			while (rs.next()) {
				String chnl = rs.getString("channelName");
				String area = rs.getString("area");
				String data = rs.getString("data");
				if(chnl_set.add(chnl)){
					data_item = new ArrayList();
					data_list.add(data_item);
					chnl_list.add(chnl);
				}
				if(area_set.add(area)){
					area_list.add(area);
				}
				data_item.add(data);
			}
			map.put("data", data_list);
			map.put("name", chnl_list);
			map.put("area", area_list);
			map.put("beginShijian", searchmap.get("bgDate"));
			map.put("endShijian", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	private String[] arrTool(String[] temp) {
		int len = 0;
		if(temp!=null){
			len = temp.length;
		}
		for (int i = 0; i < len; i++) {
			if (temp[i] == null) {
				temp[i] = "0.0000";
			}
		}
		return temp;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于按天查询
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobGetDay(String sql,Map searchmap) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			List chnl_list = new ArrayList();
			List record_time_list = new ArrayList();
			List data_list = new ArrayList();
			String[] data_item = null;
			Set chnl_set = new HashSet();
			Set chnl_date_set = new HashSet();
			Set record_time_set = new HashSet();
			
			String bgDate = searchmap.get("bgDate").toString();
			String endDate = searchmap.get("endDate").toString();
			
			Calendar b_c = Calendar.getInstance();
			b_c.setTime(sdf.parse(bgDate));
			Calendar e_c = Calendar.getInstance();
			e_c.setTime(sdf.parse(endDate));
			
			long date_count = (sdf.parse(endDate).getTime() - sdf.parse(bgDate).getTime())/ 1000 / 60 / 60 / 24;
			int day_count = Integer.parseInt(date_count + "");
			
			while (rs.next()) {
				String channelName = rs.getString("channelName");
				String data = rs.getString("data");
				String date_time = rs.getString("date_time");
				record_time_list = new ArrayList();
				if(chnl_set.add(channelName)){
					chnl_list.add(channelName);
					if(data_item!=null){
						
						data_list.add(arrTool(data_item));
					}
					data_item = new String[day_count+1];
				}
				for (int i = 0; i <=day_count; i++) {
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(bgDate));
					c.add(Calendar.DATE, i);
					String record_time = sdf.format(c.getTime());
					record_time_list.add(record_time);
					if(date_time.equals(record_time)){
						data_item[i] = data;
					}
				}
			}
			data_list.add(arrTool(data_item));
			map.put("data", data_list);
			map.put("name", chnl_list);
			map.put("record_time", record_time_list);
			map.put("beginShijian", searchmap.get("bgDate"));
			map.put("endShijian", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于实时监控--到达率、收视率、市场份额
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobShiShi(String sql,Map searchmap) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			List chnl_list = new ArrayList();
			List record_time_list = new ArrayList();
			List<String[]> data_list = new ArrayList<String[]> ();
			String[] data_item = null;
			Set chnl_set = new HashSet();
			Set chnl_date_set = new HashSet();
			Set record_time_set = new HashSet();
		
			String bgDate = searchmap.get("bgDate").toString();
			String endDate = searchmap.get("endDate").toString();
			
			Calendar b_c = Calendar.getInstance();
			b_c.setTime(sdf.parse(bgDate));
			Calendar e_c = Calendar.getInstance();
			e_c.setTime(sdf.parse(endDate));
			
			long date_count = (sdf.parse(endDate).getTime() - sdf.parse(bgDate).getTime())/ 1000 / 60;
			int day_count = Integer.parseInt(date_count + "");
			
			while (rs.next()) {
				String channelName = rs.getString("channelName");
				String data = rs.getString("data");
				String date_time = rs.getString("date_time")==null?"":rs.getString("date_time");
				record_time_list = new ArrayList();
				if(chnl_set.add(channelName)){
					chnl_list.add(channelName);
					if(data_item!=null){
						data_list.add(arrTool(data_item));
					}
					data_item = new String[day_count+1];
				}
				for (int i = 0; i <=day_count; i++) {
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(bgDate));
					c.add(Calendar.MINUTE, i);
					String record_time = sdf.format(c.getTime());
					record_time_list.add(record_time);
					if(date_time.equals(record_time)){
						data_item[i] = data;
					}
				}
			}
			data_list.add(arrTool(data_item));
//			List<String> chn_lis = new ArrayList<String>();
//			chn_lis.add("湖南卫视");
//			chn_lis.add("CCTV-1");
//			List bugList =new ArrayList();
//			for(int i=0;i<chn_lis.size();i++){
//				boolean  flag = true;
//				for(int j=0;j<chnl_list.size();j++){
//					
//					if(chn_lis.get(i).equals(chnl_list.get(j))){
//						flag=false;
//						continue;
//					}
//					if(flag){
//						bugList.add(i)	;
//						
//					}else{
//						flag = true;
//					}
//				}				
//			}
//			String[] empityData11=new String[data_list.get(0).length];
//			for(int q=0;q<data_list.get(0).length;q++){
//				empityData11[q]="0.0000";
//			}
//			List<String[]> data_listNew = new ArrayList<String[]> ();
//			for(int w=0;w<bugList.size();w++){
//				data_list.add((Integer)bugList.get(w), empityData11);
//			}
			map.put("data", data_list);
			map.put("name", chnl_list);
			map.put("record_time", record_time_list);
			map.put("beginShijian", searchmap.get("bgDate"));
			map.put("endShijian", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于实时监控--流入流出
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobShiShiLL(String sql,Map searchmap) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			List chnl_list = new ArrayList();
			List record_time_list = new ArrayList();
			List data_liuru_list = new ArrayList();
			List data_liuchu_list = new ArrayList();
			String[] data_liuru_item = null;
			String[] data_liuchu_item = null;
			Set chnl_set = new HashSet();
			Set chnl_date_set = new HashSet();
			Set record_time_set = new HashSet();
			
			String bgDate = searchmap.get("bgDate").toString();
			String endDate = searchmap.get("endDate").toString();
			
			Calendar b_c = Calendar.getInstance();
			b_c.setTime(sdf.parse(bgDate));
			Calendar e_c = Calendar.getInstance();
			e_c.setTime(sdf.parse(endDate));
			
			long date_count = (sdf.parse(endDate).getTime() - sdf.parse(bgDate).getTime())/ 1000 / 60;
			int day_count = Integer.parseInt(date_count + "");
			
			while (rs.next()) {
				String channelName = rs.getString("channelName");
				String data_liuru = rs.getString("data_liuru");
				String data_liuchu = rs.getString("data_liuchu");
				String date_time = rs.getString("date_time")==null?"":rs.getString("date_time");
				record_time_list = new ArrayList();
				if(chnl_set.add(channelName)){
					chnl_list.add(channelName);
					if(data_liuru_item!=null){
						data_liuru_list.add(arrTool(data_liuru_item));
					}
					if(data_liuchu_item!=null){
						data_liuchu_list.add(arrTool(data_liuchu_item));
					}
					data_liuru_item = new String[day_count+1];
					data_liuchu_item = new String[day_count+1];
				}
				for (int i = 0; i <=day_count; i++) {
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(bgDate));
					c.add(Calendar.MINUTE, i);
					String record_time = sdf.format(c.getTime());
					record_time_list.add(record_time);
					if(date_time.equals(record_time)){
						data_liuru_item[i] = data_liuru;
					}
				}
				for (int i = 0; i <=day_count; i++) {
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(bgDate));
					c.add(Calendar.MINUTE, i);
					String record_time = sdf.format(c.getTime());
					record_time_list.add(record_time);
					if(date_time.equals(record_time)){
						data_liuchu_item[i] = data_liuchu;
					}
				}
			}
			data_liuru_list.add(arrTool(data_liuru_item));
			data_liuchu_list.add(arrTool(data_liuchu_item));
			map.put("data_liuru", data_liuru_list);
			map.put("data_liuchu", data_liuchu_list);
			map.put("name", chnl_list);
			map.put("record_time", record_time_list);
			map.put("beginShijian", searchmap.get("bgDate"));
			map.put("endShijian", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于按时段查询---流入流出
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobToFlow(String sql,Map searchmap) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			
			String[] type_arr = new String[] { "流入", "流出" };
			List categories_list = new ArrayList();
			
			List first_list = new ArrayList();
			List second_list = new ArrayList();
			List third_list = new ArrayList();
			
			List inflow_list = new ArrayList();
			List outflow_list = new ArrayList();
			
			while (rs.next()) {
				String record_time = rs.getString("record_time");
				String inflow_rating = rs.getString("inflow_rating");
				String outflow_rating = rs.getString("outflow_rating");
				String inflow_first = rs.getString("inflow_first")==null?"":rs.getString("inflow_first");
				String inflow_second = rs.getString("inflow_second")==null?"":rs.getString("inflow_second");
				String inflow_third = rs.getString("inflow_third")==null?"":rs.getString("inflow_third");
				String outflow_first = rs.getString("outflow_first")==null?"":rs.getString("outflow_first");
				String outflow_second = rs.getString("outflow_second")==null?"":rs.getString("outflow_second");
				String outflow_third = rs.getString("outflow_third")==null?"":rs.getString("outflow_third");
				
				inflow_list.add(inflow_rating);
				outflow_list.add(outflow_rating);
				categories_list.add(record_time);
				
				String[] first_arr = new String[] { inflow_first, outflow_first };
				String[] second_arr = new String[] { inflow_second, outflow_second };
				String[] third_arr = new String[] { inflow_third, outflow_third };
				
				first_list.add(first_arr);
				second_list.add(second_arr);
				third_list.add(third_arr);
				
			}
			List[] data_list = new List[] { inflow_list, outflow_list };
			
			
			map.put("name", searchmap.get("chnnlName"));
			map.put("type", type_arr);

			map.put("data", data_list);
			map.put("categories",categories_list);
			map.put("first", first_list);
			map.put("second", second_list);
			map.put("third", third_list);
			
			map.put("beginShijian", searchmap.get("bgDate"));
			map.put("endShijian", searchmap.get("endDate"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个ArrayList对象，对象中的每一个元素是一个HashMap对象-----去掉转码
	 * 用于按天查询--收视份额
	 * @param sql
	 *            String 查询语句
	 * @return ArrayList 结果集
	 * @throws Exception
	 */
	public Map executeSqlAsHashMapClobGetDayAndShouShi(String sql,Map searchmap) throws Exception {
		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		HashMap map = new HashMap();
		try {
			if (conn == null)
				return null;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData rsm = rs.getMetaData();
			int count = rsm.getColumnCount();
			List chnl_list = new ArrayList();
			List data_list = new ArrayList();
			while (rs.next()) {
				String chnl = rs.getString("channelName");
				String data = rs.getString("data");
				chnl_list.add(chnl);
				data_list.add(data);
			}
			map.put("name", chnl_list);
			map.put("data", data_list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	
	/**
	 * 查询数据 返回的是一个Vector对象，对象中的每一个元素是一个HashMap对象
	 * 
	 * @param sql
	 *            String
	 * @return Vector
	 * @throws Exception
	 */
	public Vector queryVectorAsHashMap(String sql) throws Exception {
		return null;
	}

	/**
	 * 查询数据 返回的是一个List对象，对象中的每一个元素是一个Vector对象
	 * 
	 * @param sql
	 *            String
	 * @return Vector
	 * @throws Exception
	 */
	public List queryListAsVector(String sql) throws Exception {
		return null;
	}

	/**
	 * 查询数据 返回的是一个Vector对象，对象中的每一个元素是一个Vector对象
	 * 
	 * @param sql
	 *            String
	 * @return Vector
	 * @throws Exception
	 */
	public Vector queryVectorAsVector(String sql) throws Exception {
		return null;
	}

	public java.sql.ResultSet executeSql(String sql) throws SQLException {

		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		if (conn == null)
			return null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		// stmt.close();
		return rs;

	}
	public String executeSql2(String sql)	throws SQLException {

		java.sql.Statement stmt = null;
		java.sql.ResultSet rs = null;
		if(conn==null) return null;
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
		stmt.close();
		return null;

	}

	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static Map getFirstMapFromDB(PreparedStatement ps,
			String sourceCharset, String targetCharset) throws SQLException {
		ResultSet rs = null;
		Map map = null;
		try {
			rs = ps.executeQuery();
			if (rs != null) {
				ResultSetMetaData metaDate = rs.getMetaData();
				int columnCount = metaDate.getColumnCount();
				if (rs.next()) {
					map = new HashMap();
					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						if (obj instanceof String) {
							obj = new String(((String) obj)
									.getBytes(sourceCharset), targetCharset);
						}
						map.put(metaDate.getColumnName(i).toLowerCase(), obj);
					}
					;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return map;
	}

	public static Map getFirstMapFromDB(PreparedStatement ps)
			throws SQLException {
		ResultSet rs = null;
		Map map = null;
		try {
			rs = ps.executeQuery();
			if (rs != null) {
				ResultSetMetaData metaDate = rs.getMetaData();
				int columnCount = metaDate.getColumnCount();
				if (rs.next()) {
					map = new HashMap();
					for (int i = 1; i <= columnCount; i++) {
						map.put(metaDate.getColumnName(i).toLowerCase(), rs
								.getObject(i));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		return map;
	}
	
	
	
	public static Map[] getMapsFromDB(PreparedStatement ps) throws SQLException {
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			rs = ps.executeQuery();
			if (rs != null) {
				ResultSetMetaData metaDate = rs.getMetaData();
				int columnCount = metaDate.getColumnCount();
				while (rs.next()) {
					Map map = new HashMap();
					for (int i = 1; i <= columnCount; i++) {
						map.put(metaDate.getColumnName(i).toLowerCase(), rs
								.getObject(i));
					}
					list.add(map);
				}
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		Map[] maps = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			maps[i] = (Map) list.get(i);
		}
		return maps;
	}

	public static Map[] getMapsFromDB(PreparedStatement ps,
			String sourceCharset, String targetCharset) throws SQLException {
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			rs = ps.executeQuery();
			if (rs != null) {
				ResultSetMetaData metaDate = rs.getMetaData();
				int columnCount = metaDate.getColumnCount();
				while (rs.next()) {
					Map map = new HashMap();
					for (int i = 1; i <= columnCount; i++) {
						Object obj = rs.getObject(i);
						if (obj instanceof String) {
							obj = new String(((String) obj)
									.getBytes(sourceCharset), targetCharset);
						}
						map.put(metaDate.getColumnName(i).toLowerCase(), obj);
					}
					list.add(map);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
		Map[] maps = new Map[list.size()];
		for (int i = 0; i < list.size(); i++) {
			maps[i] = (Map) list.get(i);
		}
		return maps;
	}
}

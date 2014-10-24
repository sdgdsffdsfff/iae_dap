package com.ctvit.converter;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonHandle {
	private static Map jsonMap = null;
	private static Map newJsonMap = null;
	static{
		InputStream is = JsonHandle.class.getClassLoader().getResourceAsStream("cntvlanmu_json20130416.js");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String jsonString = "";
		JSONObject jsonObject = null;
		try {
			String line = null;
			while((line = br.readLine()) != null){
				jsonString += line;
			}
			jsonString = jsonString.substring(1);
			jsonMap = jsonObject = JSONObject.fromObject(jsonString);
			
		} catch (IOException e) {
			System.out.println("com.ctvit.converter.JsonHandle is error......");
		}
	}
	private JsonHandle(){}
	
	public static Map queryById(String id){
		if(jsonMap == null || id == null || "".equals(id)) return null;
		Map map = null;
		try{
			JSONArray list = (JSONArray)jsonMap.get("list");
			Object[] arrayObject = list.toArray();
			for(Object o : arrayObject){
				Map m = (Map)o;
				if(id.equals(m.get("video_album_id"))){
					map = m;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	public static Map queryAll(){
		if(jsonMap == null) return null;
		Map map = null;
		List<Map> New_list = new ArrayList<Map>();
		Map entMap = new HashMap();
		try{
			JSONArray list = (JSONArray)jsonMap.get("list");
			Object[] arrayObject = list.toArray();
			for(Object o : arrayObject){
				Map m = (Map)o;
					map = m;
					New_list.add(map);
					map=null;
					
			}
			entMap.put("total", New_list.size());
			entMap.put("list", New_list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return entMap;
	}
	
	/**
	 * 
	 * @param 根据参数返回单挑数据（重构数据）
	 * @return
	 */
	public static List<Map> queryInfoOne(String id){
		if(jsonMap == null || id == null || "".equals(id)) return null;
		List<Map> result = new ArrayList<Map>();
		Map map = new HashMap();
		Map end_map = new HashMap();
		try{
			JSONArray list = (JSONArray)jsonMap.get("list");
			Object[] arrayObject = list.toArray();
			for(Object o : arrayObject){
				Map m = (Map)o;
				if(id.equals(m.get("video_album_id"))){
					Map new_map=new HashMap();
					String itf=(String)m.get("interface");
					map = getOneVideo(itf);
					new_map.put("name",m.get("name"));
					new_map.put("video_album_id",m.get("video_album_id"));
					new_map.put("video_album_pic",m.get("video_photo"));
					if(itf.indexOf("getEntVideoListInfo")!=-1){
						new_map.put("new_video_name",map.get("video_name"));
						new_map.put("new_video_time",map.get("add_time"));
						new_map.put("new_video_pic",map.get("video_photo_url"));
						new_map.put("new_video_pid",map.get("video_id"));
					}else{
						new_map.put("new_video_name",map.get("title"));
						new_map.put("new_video_time",map.get("timestamp"));
						new_map.put("new_video_pic",map.get("image"));
						new_map.put("new_video_pid",map.get("pid"));
						
					}
					result.add(new_map);
					new_map =null;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param 根据参数返回所有数据（重构数据）
	 * @return
	 */
	public static List<Map> queryInfoAll(){
		if(jsonMap == null) return null;
		List<Map> result = new ArrayList<Map>();
		Map map = new HashMap();
		Map end_map = new HashMap();
		try{
			JSONArray list = (JSONArray)jsonMap.get("list");
			Object[] arrayObject = list.toArray();
			for(Object o : arrayObject){
				Map m = (Map)o;
				Map new_map=new HashMap();
				String itf=(String)m.get("interface");
				map = getOneVideo(itf);
				new_map.put("name",m.get("name"));
				new_map.put("video_album_id",m.get("video_album_id"));
				new_map.put("video_album_pic",m.get("video_photo"));
				if(itf.indexOf("getEntVideoListInfo")!=-1){
					new_map.put("new_video_name",map.get("video_name"));
					new_map.put("new_video_time",map.get("add_time"));
					new_map.put("new_video_pic",map.get("video_photo_url"));
					new_map.put("new_video_pid",map.get("video_id"));
				}else{
					new_map.put("new_video_name",map.get("title"));
					new_map.put("new_video_time",map.get("timestamp"));
					new_map.put("new_video_pic",map.get("image"));
					new_map.put("new_video_pid",map.get("pid"));
					
				}
				result.add(new_map);
				new_map =null;
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param 根据接口地址返回最新一条视频
	 * @return
	 */
	public static Map getOneVideo(String interfaceStr) {
		Map map = null;
		try {
			String responseJson = DownLoadXml(interfaceStr);
			JSONObject jsonObject = null;
			jsonObject = JSONObject.fromObject(responseJson);
			JSONArray list = (JSONArray) jsonObject.get("list");
			Object[] arrayObject = list.toArray();
			System.out.println(arrayObject.length);
			for (Object o : arrayObject) {
				Map m = (Map) o;
				if ("1".equals(m.get("num"))) {
					map = m;
					break;
				}
				if ("1".equals(m.get("num").toString())) {
					map = m;
					break;
				}
				
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @param 访问接口地址，返回页面json数据
	 * @return
	 */
	private static String DownLoadXml(String urlStr) {
		URL url = null;
		HttpURLConnection httpConn = null;
		InputStream in = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String responseJson =null;
		try {
			if(urlStr!=null&&!"".equals(urlStr)){
				if(urlStr.indexOf("hot.app.cntv.cn")>=0){
					urlStr=urlStr.replaceAll("hot\\.app\\.cntv\\.cn","10\\.9\\.4\\.59\\:7001"); 
				}else{
					urlStr=urlStr.replaceAll("115\\.182\\.9\\.109","10\\.9\\.4\\.59\\:7001"); 
				}
				
				if(urlStr.indexOf("pageSize=500")>=0){
					urlStr=urlStr.replaceAll("pageSize=500","pageSize=1"); 
				}
				System.out.println("urlStr:"+urlStr);
			}
			url = new URL(urlStr);
			httpConn = (HttpURLConnection) url.openConnection();
			HttpURLConnection.setFollowRedirects(true);
			httpConn.setRequestMethod("POST");

			in = httpConn.getInputStream();
			br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			for (String str = br.readLine(); str != null; str = br.readLine()) {
				sb.append(str);
			}
			responseJson = sb.toString();
			if(urlStr.indexOf("jsonp")!=-1){
				responseJson=responseJson.substring(0,responseJson.length()-1);
				responseJson=responseJson.replaceAll("test\\(",""); 
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				httpConn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return responseJson;
	}
	
	
	public static void main(String[] args) throws Exception{
		
		String urlStr = "http://hot.app.cntv.cn";
		if(urlStr.indexOf("hot.app.cntv.cn")>=0){
			urlStr=urlStr.replaceAll("http\\://hot\\.app\\.cntv\\.cn","10\\.9\\.4\\.59\\:7001"); 
		}else{
			urlStr = "esle";
		}
		
		
		//System.out.println(queryInfoOne("C10334"));
		System.out.println(urlStr);
	}
}

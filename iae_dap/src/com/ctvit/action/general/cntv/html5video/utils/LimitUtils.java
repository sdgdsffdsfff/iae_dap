package com.ctvit.action.general.cntv.html5video.utils;

import java.util.HashMap;
import java.util.Map;

public class LimitUtils {
	/**
	 * 获取开始和结束索引,默认取十条
	 * @param currentPage
	 * @return
	 */
	public static Map<String,Integer> getStartAndEndIndex(String currentPage){
		Map entiry=new HashMap();
		if(currentPage==null||"".equals(currentPage.trim())){
			currentPage="1";
		}
		Integer page = Integer.valueOf(currentPage);
		Integer startRecord=(page-1)*12+1;
		Integer endRecord=page*12;
		entiry.put("startRecord", startRecord);
		entiry.put("endRecord", endRecord);
		return entiry;
	}
	/**
	 * 获取开始和结束索引
	 * @param currentPage
	 * @return
	 */
	public static Map<String,Integer> getStartAndEndIndex(String currentPage,String rows){
		Map entiry=new HashMap();
		if(currentPage==null||"".equals(currentPage.trim())){
			currentPage="1";
		}
		if(rows==null||"".equals(rows.trim())){
			rows="12";
		}
		Integer page = Integer.valueOf(currentPage);
		Integer row=Integer.valueOf(rows);
		if(row>500){
			row=500;
		}
		Integer startRecord=(page-1)*row+1;
		Integer endRecord=page*row;
		entiry.put("startRecord", startRecord);
		entiry.put("endRecord", endRecord);
		return entiry;
	}
}

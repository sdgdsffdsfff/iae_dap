package com.ctvit.action.general.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.ctvit.action.base.ActionSupport;
import com.ctvit.converter.Converter;
import com.ctvit.converter.ConverterContext;
import com.ctvit.converter.ConverterJson;

public class TestJson extends ActionSupport  {
	private static final long serialVersionUID = 9164110008127050433L;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String  paramName =(String)getParamName("name");
		System.out.println("name:"+paramName);
		String[] paramNames = (String[])getParamNames("name");
		for(String param:paramNames){
			System.out.println("@name:"+param);
		}
		System.out.println("--------------------------------------------");
		Map map=new HashMap();
		map.put("key", "key_TestJson");
		map.put("value", "value_TestJson");
		List<Map> videoList=new ArrayList<Map>();
		Map map_=new HashMap();
		map_.put("mk", "mv");
		videoList.add(map_);
		map.put("videoList", videoList);
		
		
		Converter<Map> converterJsonStrategy = new ConverterJson<Map>();
		ConverterContext<Map> context = new ConverterContext<Map>(converterJsonStrategy);
		String responseData = context.runConverter(map);
		
		build(responseData);
		return null;
	}



}

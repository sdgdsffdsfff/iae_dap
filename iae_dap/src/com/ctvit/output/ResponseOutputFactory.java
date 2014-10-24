/**
 * 构造输出工厂
 */
package com.ctvit.output;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class ResponseOutputFactory {
	private static Logger logger = Logger.getLogger(ResponseOutputFactory.class);
	public HttpServletResponse httpServletResponse=null;
	
	public ResponseOutputFactory(HttpServletResponse httpServletResponse) {
		super();
		this.httpServletResponse = httpServletResponse;
	}

	/*
	 * 创建输出对象
	 */
	public ResponseOutput createOutputFactory(String objectType){
		ResponseOutput responseOutput=null;
		if(objectType.equalsIgnoreCase("xml")){
			responseOutput=new ResponseXmlOutput(httpServletResponse);
		}else if(objectType.equalsIgnoreCase("json")){
			responseOutput=new ResponseJsonOutput(httpServletResponse);
		}else if(objectType.equalsIgnoreCase("jsonp")){
			responseOutput=new ResponseJsonpOutput(httpServletResponse);

		}
		return responseOutput;
	}
	
	
	
}

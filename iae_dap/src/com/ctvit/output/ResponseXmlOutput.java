/**
 * 
 */
package com.ctvit.output;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class ResponseXmlOutput extends ResponseOutput {
	private static Logger logger = Logger.getLogger(ResponseXmlOutput.class);

	/**
	 * @param httpServletResponse
	 */
	public ResponseXmlOutput(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 响应XML
	 * @throws IOException 
	 */
	@Override
	public void responseResult(String response) throws IOException {
		logger.debug("Response数据格式为XML");		
		httpServletResponse.setContentType("text/xml;charset=utf-8");    
		httpServletResponse.getWriter().write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"); 
		httpServletResponse.getWriter().write(response); 
		httpServletResponse.flushBuffer();
		
	}



}

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
public class ResponseJsonOutput extends ResponseOutput {
	private static Logger logger = Logger.getLogger(ResponseJsonOutput.class);

	/**
	 * @param httpServletResponse
	 */
	public ResponseJsonOutput(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 响应Json
	 */
	@Override
	public void responseResult(String response) throws IOException {
		logger.debug("Response数据格式为JSON");
		httpServletResponse.setContentType("application/json;charset=UTF-8");    
		httpServletResponse.getWriter().write(response); 
		httpServletResponse.flushBuffer();
	}

}

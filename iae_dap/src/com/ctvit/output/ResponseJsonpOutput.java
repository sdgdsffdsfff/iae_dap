/**
 * 
 */
package com.ctvit.output;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-12-25
 */
public class ResponseJsonpOutput extends ResponseOutput {
	private static Logger logger = Logger.getLogger(ResponseJsonpOutput.class);

	/**
	 * @param httpServletResponse
	 */
	public ResponseJsonpOutput(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 响应Json
	 */
	@Override
	public void responseResult(String response) throws IOException {
		logger.debug("Response数据格式为JSONP");
		httpServletResponse.setContentType("application/javascript;charset=UTF-8");    
		httpServletResponse.getWriter().write(response); 
		httpServletResponse.flushBuffer();
	}

}

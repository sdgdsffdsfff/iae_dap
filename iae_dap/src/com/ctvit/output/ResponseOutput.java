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
public abstract class ResponseOutput {
	private static Logger logger = Logger.getLogger(ResponseOutput.class);
	public HttpServletResponse httpServletResponse=null;
	

	public ResponseOutput(HttpServletResponse httpServletResponse) {
		super();
		this.httpServletResponse = httpServletResponse;
	}


	public abstract void responseResult(String response) throws IOException;

	
}

/**
 * 
 */
package com.ctvit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-9
 */
public class EncodingFilter implements Filter {
	  protected String encoding = null; 
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */

	public void destroy() {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if((request.getCharacterEncoding()==null)){
            if(encoding!=null){
            	request.setCharacterEncoding(encoding); 
            	response.setCharacterEncoding(encoding);
            }

        }



        chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig filterconfig) throws ServletException {
		this.encoding = filterconfig.getInitParameter("encoding");
	}

}

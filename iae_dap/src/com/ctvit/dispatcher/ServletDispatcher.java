/**
 * 
 */
package com.ctvit.dispatcher;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ctvit.config.analyzeConfig.InitConfig;
import com.ctvit.config.bean.ActionBean;
import com.ctvit.dbutils.C3P0Utils;
import com.ctvit.exception.DataToolsException;

/**
 * Servlet核心控制器
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-4
 */
public class ServletDispatcher extends HttpServlet {
	
	private static Logger logger  =  Logger.getLogger(ServletDispatcher.class );
	
	private static final long serialVersionUID = 1L;
	/**
	 * 默认构造器
	 */
	public ServletDispatcher(){
		try {
			//DBCPUtils.getDataSource();
			C3P0Utils.getDataSource();
			logger.debug("DBCP数据源初始化。");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {

		super.init();
	}

	/**
	 * 客户端doGet请求
	 */
	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		this.doPost(httpServletRequest, httpServletResponse);
	}

	/**
	 * 客户端doPost请求
	 */
	@Override
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
		logger.debug("ServletDispatcher接收用户请求");
		 DataToolsContext dataToolsContext=null;
		dataToolsContext=DataToolsContext.getInstance();
		dataToolsContext.setHttpServletRequest(httpServletRequest);
		dataToolsContext.setHttpServletResponse(httpServletResponse);
		System.out.println("servlet object is begining...............thread info="+Thread.currentThread().getId());
		
		Map<String, ActionBean> actionBeans = InitConfig.getInstance().getActionBeans();
		dataToolsContext.setActionBeans(actionBeans);
		
		this.getFormMap(httpServletRequest,dataToolsContext);
		this.getAboutRequest(httpServletRequest,dataToolsContext);
		try {
			
			logger.debug("执行ActionManager处理类");
			ActionManager newInstance = ActionManager.class.newInstance();
			newInstance.process(dataToolsContext);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("执行ActionManager处理类异常");
			throw new ServletException(e.getMessage());

		} 
		
	}
	/**
	 * 获取与请求相关的参数
	 * @param httpServletRequest
	 * @return
	 */
	private  void getAboutRequest(HttpServletRequest httpServletRequest,DataToolsContext dataToolsContext){
		String path = httpServletRequest.getContextPath();
		Map aboutRequest=new HashMap();
		String remoteAddr = getRemoteAddress(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();
		String requestQueryParams = httpServletRequest.getQueryString();
		logger.info("请求的IP地址:"+remoteAddr);
		logger.info("请求的URI:"+requestURI);
		logger.info("请求的参数:"+requestQueryParams);
		dataToolsContext.setRequestQueryParams(requestQueryParams);
		if(remoteAddr!=null&&(requestURI.endsWith(".xml")||requestURI.endsWith(".json")||requestURI.endsWith(".jsonp"))){
			if(requestURI.endsWith("xml")){
				requestURI=requestURI.substring(0, requestURI.length()-4).replace(path, "");
			}else if(requestURI.endsWith(".json")){
				requestURI=requestURI.substring(0, requestURI.length()-5).replace(path, "");
			}else if(requestURI.endsWith(".jsonp")){
				requestURI=requestURI.substring(0, requestURI.length()-6).replace(path, "");
			}
			
			dataToolsContext.setRequestUri(requestURI);
		}
		dataToolsContext.setRemoteAddr(remoteAddr);		

	}
	
	
	/**
	 * 获取请求参数的Map集合
	 * @param httpServletRequest
	 * @return
	 */
	private void getFormMap(HttpServletRequest httpServletRequest,DataToolsContext dataToolsContext){
		Map parameterMap = httpServletRequest.getParameterMap();
		dataToolsContext.setFormMap(parameterMap);
		Set<Map.Entry> entrys=parameterMap.entrySet();
		String params="";
		for(Entry entry:entrys){
			params+=entry.getKey()+"="+Arrays.toString((Object[]) entry.getValue())+"\t";
		}
		logger.debug("请求的参数:"+params);
	}
	/**
	 * 获取远程客户端的IP地址
	 * @param httpServletRequest
	 * @return
	 */
	private String getRemoteAddress(HttpServletRequest request){
		 String ip = request.getHeader("x-forwarded-for");      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getHeader("WL-Proxy-Client-IP");      
	       }      
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	           ip = request.getRemoteAddr();      
	       }      
	       return ip;  
	}
	
}

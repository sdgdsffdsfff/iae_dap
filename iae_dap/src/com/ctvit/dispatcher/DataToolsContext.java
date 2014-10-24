/**
 * 
 */
package com.ctvit.dispatcher;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ctvit.config.bean.ActionBean;

/**
 * DataToolsContext是DataTools上下文工具类
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public class DataToolsContext {
	private static Logger logger = Logger.getLogger(DataToolsContext.class);
	//本地线程变量
	private static final ThreadLocal<DataToolsContext> instance=new ThreadLocal<DataToolsContext>();
	public static DataToolsContext getInstance(){
		DataToolsContext dataToolsContext = instance.get();
		if(dataToolsContext==null){
			dataToolsContext=new DataToolsContext();
			instance.set(dataToolsContext);
		}
		return dataToolsContext;
	}
	
	private DataToolsContext() {
		super();
	}
	
	/**
	 * 请求的参数
	 */
	
	private  String requestQueryParams;
	/**
	 * 访问IP
	 */
	private  String remoteAddr;
	/**
	 * 访问地址
	 */
	private  String requestUri;
	/**
	 * 配置文件IP
	 */
	private  String configIp;
	/**
	 * Form请求的表单
	 */
	private Map<String, Object[]> formMap;
	/**
	 * 配置文件ActionBean
	 */
	private Map<String, ActionBean> actionBeans;
	
	/**
	 * servlet的HttpServletRequest请求
	 */
	private HttpServletRequest httpServletRequest;
	/**
	 * servlet的httpServletResponse响应
	 */
	private HttpServletResponse httpServletResponse;
	/**
	 * @return the httpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}
	/**
	 * @param httpServletRequest the httpServletRequest to set
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	/**
	 * @return the httpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}
	/**
	 * @param httpServletResponse the httpServletResponse to set
	 */
	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	/**
	 * @return the remoteAddr
	 */
	public String getRemoteAddr() {
		return remoteAddr;
	}
	/**
	 * @param remoteAddr the remoteAddr to set
	 */
	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}
	/**
	 * @return the requestUri
	 */
	public String getRequestUri() {
		return requestUri;
	}
	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	/**
	 * @return the configIp
	 */
	public String getConfigIp() {
		return configIp;
	}
	/**
	 * @param configIp the configIp to set
	 */
	public void setConfigIp(String configIp) {
		this.configIp = configIp;
	}

	/**
	 * @return the actionBeans
	 */
	public Map<String, ActionBean> getActionBeans() {
		return actionBeans;
	}
	/**
	 * @param actionBeans the actionBeans to set
	 */
	public void setActionBeans(Map<String, ActionBean> actionBeans) {
		this.actionBeans = actionBeans;
	}

	/**
	 * 根据path寻找对应的Action处理类
	 * @param path
	 * @return
	 */
	public  ActionBean  getActionBean(String path){
		return  actionBeans.get(path);
	}
	/**
	 * @return the formMap
	 */
	public Map<String, Object[]> getFormMap() {
		return formMap;
	}
	/**
	 * @param formMap the formMap to set
	 */
	public void setFormMap(Map<String, Object[]> formMap) {
		this.formMap = formMap;
	}

	public String getRequestQueryParams() {
		return requestQueryParams;
	}

	public void setRequestQueryParams(String requestQueryParams) {
		this.requestQueryParams = requestQueryParams;
	}

	

}

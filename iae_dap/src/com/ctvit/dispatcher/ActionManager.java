/**
 * Actiong请求处理器
 */
package com.ctvit.dispatcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ctvit.action.base.ActionSupport;
import com.ctvit.config.bean.ActionBean;
import com.ctvit.dbutils.C3P0Utils;
import com.ctvit.exception.DataToolsException;
import com.ctvit.proxy.ActionProxy;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class ActionManager {
	private static Logger logger = Logger.getLogger(ActionManager.class);
	//private DataToolsContext dataToolsContext;
	
	/**
	 * @return the dataToolsContext
	 */
//	public DataToolsContext getDataToolsContext() {
//		return dataToolsContext;
//	}
//
//	/**
//	 * @param dataToolsContext the dataToolsContext to set
//	 */
//	public void setDataToolsContext(DataToolsContext dataToolsContext) {
//		this.dataToolsContext = dataToolsContext;
//	}

	/**
	 * Action处理器
	 * @throws DataToolsException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings({ "unchecked" })
	public void process(DataToolsContext dataToolsContext)  throws DataToolsException, InstantiationException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
		dataToolsContext=DataToolsContext.getInstance();
		logger.debug("ActionManager控制器执行");
		ActionBean actionBean = dataToolsContext.getActionBean(dataToolsContext.getRequestUri());
		if(actionBean==null){			
			System.out.println("====="+dataToolsContext.getRequestUri());
			throw new DataToolsException("请求的路径未在配置文件中配置:"+dataToolsContext.getRequestUri());
		}
		String classType = actionBean.getClassType();
		String classMethod = actionBean.getMethod();
		dataToolsContext.setConfigIp(actionBean.getIp());
		Class clazz = getClass(classType);
		Object newInstance = clazz.newInstance();
		ActionSupport proxyInstance =(ActionSupport) new ActionProxy(dataToolsContext).getProxyInstance(newInstance);
		
		if(!ActionSupport.class.isInstance(proxyInstance)){			
			throw new DataToolsException("类没有继续ActionSupport");
		}
		//请求的数据类型
		Object requestDataType = null;
		if(dataToolsContext.getHttpServletRequest().getRequestURI().endsWith(".json")){
			requestDataType="json";
		}else if(dataToolsContext.getHttpServletRequest().getRequestURI().endsWith(".xml")){
			requestDataType="xml";
		}else if(dataToolsContext.getHttpServletRequest().getRequestURI().endsWith(".jsonp")){
			requestDataType="jsonp";
		}
		BeanUtils.setProperty(proxyInstance, "dataType",requestDataType );
		BeanUtils.setProperty(proxyInstance, "dataToolsContext", dataToolsContext);
		BeanUtils.setProperty(proxyInstance, "jdbcTemplate", new NamedParameterJdbcTemplate(C3P0Utils.getDataSource()));

		logger.debug("配置的Action是:"+classType+",访问的方法是:"+classMethod);
		if(classMethod==null){
			classMethod="execute";
		}
		Method method = clazz.getMethod(classMethod, null);
		Object result = method.invoke(proxyInstance, null);

	}

	/**
	 * 获取配置文件的Class类型
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class getClass(String className) throws DataToolsException  {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("配置文件中配置类的加载异常："+className);
			throw new DataToolsException("配置文件中配置类的加载异常："+className);
		}
	}
	
//	/**
//	 * 获取请求参数的数据类型
//	 */
//	public Object getRequestDataType(){
//		String result=null;
//		Map<String, Object[]> formMap = dataToolsContext.getFormMap();
//		if(formMap!=null){
//			Object[] objects = formMap.get("dt");
//			if(objects!=null&&objects.length>=1){
//				return objects[0];
//			}
//		}
//		return "json";
//	}

	
	

}

/**
 * Action代理类
 */
package com.ctvit.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.log4j.Logger;

import com.ctvit.config.bean.ActionBean;
import com.ctvit.dispatcher.DataToolsContext;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class ActionProxy implements MethodInterceptor {
	private static Logger logger  =  Logger.getLogger(ActionProxy.class );
	private Object target;
	private DataToolsContext dataToolsContext;
	
	
	

	public ActionProxy(DataToolsContext dataToolsContext) {
		super();
		this.dataToolsContext = dataToolsContext;
	}


	/**
	 * 获取代理实例
	 * @param target
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  Object getProxyInstance(Object target){
		Class consrClass[]={DataToolsContext.class};
		Object consObject[]={dataToolsContext};
		this.target=target;
        Enhancer enhancer = new Enhancer();  
        enhancer.setSuperclass(this.target.getClass());  
        enhancer.setCallback(this);  
        logger.debug("实例化Action代理类。");
		return enhancer.create();
	}

	

	public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		Object resultObject=null;
		//对IP进行鉴权
		Boolean auth = auth();
		
		if(auth){
			logger.debug("该客户端请求通过鉴权");
			resultObject=methodProxy.invokeSuper(object,args);			
		}else{
			logger.debug("该客户端请求未通过鉴权");			
		}
		return resultObject;
	}
	/**
	 * 对IP地址进行鉴权
	 */
	public Boolean auth(){
		String remoteAddr = dataToolsContext.getRemoteAddr();
		ActionBean actionBean = dataToolsContext.getActionBean(dataToolsContext.getRequestUri());
		if(actionBean==null){
			return false;
		}else{
			String configIP=actionBean.getIp();
			if(configIP==null){
				return true;
			}else{
				if(configIP.contains(remoteAddr)){
					return true;
				}else{
					return false;					
				}
			}
		}
	}
	
	
	

}

/**
 * 转换成jsonp策略
 */
package com.ctvit.converter;

import org.apache.log4j.Logger;

import com.ctvit.dispatcher.DataToolsContext;
import com.ctvit.exception.DataToolsException;

import net.sf.json.JSONObject;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-12-25
 */
public   class ConverterJsonp <T> extends Converter<T>{
	private static Logger logger  =  Logger.getLogger(ConverterJsonp.class );
	/**
	 * 将对象转换一个JSON对象
	 */
	@Override
	public String conver(T instance) throws DataToolsException {
		logger.debug("执行ConverterJson数据转换器,转换为JSONP数据格式");
		JSONObject fromObject;
		Object callbackFunction="";
		String outputJsonp="";
		try {
			fromObject = JSONObject.fromObject(instance);
			this.set(fromObject,instance);
			Object[] resultObjects = DataToolsContext.getInstance().getFormMap().get("callback");
			if (resultObjects != null && resultObjects.length >= 1) {
				callbackFunction=resultObjects[0];
			}else{
				throw new DataToolsException("您当前讲求数据格式是jsonp类型，请传入callback参数!");
			}
			outputJsonp=callbackFunction+"("+fromObject.toString()+")";
			return outputJsonp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataToolsException("应用程序转换为JSONP数据格式异常");
		}
	}

	/**
	 * 设置JSOn格式标签
	 * @param fromObject
	 * @param instance
	 */
	protected void set(JSONObject fromObject, T instance) {
		
	}


	
	

}

/**
 * 
 */
package com.ctvit.converter;

import org.apache.log4j.Logger;

import com.ctvit.exception.DataToolsException;

import net.sf.json.JSONObject;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public   class ConverterJson <T> extends Converter<T>{
	private static Logger logger  =  Logger.getLogger(ConverterJson.class );
	/**
	 * 将对象转换一个JSON对象
	 */
	@Override
	public String conver(T instance) throws DataToolsException {
		logger.debug("执行ConverterJson数据转换器,转换为JSON数据格式");
		JSONObject fromObject;
		try {
			fromObject = JSONObject.fromObject(instance);
			this.set(fromObject,instance);
			return fromObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataToolsException("应用程序转换为JSON数据格式异常");
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

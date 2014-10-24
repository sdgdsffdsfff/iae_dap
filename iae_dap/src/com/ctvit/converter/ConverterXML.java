/**
 * 
 */
package com.ctvit.converter;

import org.apache.log4j.Logger;

import com.ctvit.exception.DataToolsException;
import com.thoughtworks.xstream.XStream;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public   class ConverterXML<T> extends Converter<T> {
	private static Logger logger  =  Logger.getLogger(ConverterXML.class );
	/**
	 * 将对象转换一个XML对象
	 */
	@Override
	public String conver(T instance)  throws DataToolsException {
		try {
			logger.debug("执行ConverterXML数据转换器,转换为XML数据格式");
			XStream xStream = new XStream();
			this.set(xStream,instance);
			
			return xStream.toXML(instance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataToolsException("应用程序转换为XML数据格式异常");
		}
	}

	/**
	 * 设置XML的标签的别名
	 * @param stream
	 * @param instance
	 */
	protected  void set(XStream stream, T instance){};
	

}

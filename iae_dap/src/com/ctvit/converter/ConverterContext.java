/**
 * 
 */
package com.ctvit.converter;

import org.apache.log4j.Logger;

import com.ctvit.exception.DataToolsException;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public class ConverterContext<T> {
	private static Logger logger  =  Logger.getLogger(ConverterContext.class );
	private Converter<T> converterStrategy;

	public ConverterContext(Converter<T> converterStrategy) {
		super();
		this.converterStrategy = converterStrategy;
	}
	/**
	 * 运行转换器
	 * @param instance
	 * @return
	 * @throws DataToolsException 
	 */
	public  String runConverter(T instance ) throws DataToolsException{
		logger.debug("执行数据转换器");
		String conver = converterStrategy.conver(instance);
		return conver;
	}
	
	
}

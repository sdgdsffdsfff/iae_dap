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
public abstract class Converter<T> {
	private static Logger logger  =  Logger.getLogger(Converter.class );
	public abstract String conver(T instance) throws DataToolsException;
}
/*
 *这个RowMapper可将字符Bean中结果集为NULL的自动转换成空字符串。Bean其它类型不做设置,为默认。 
 */
package com.ctvit.dao.base.rowmapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-21
 */
public class DataToolsRowMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(DataToolsRowMapper.class);
	private Map<String, Class> fieldNameAndType = null;
	
	private Class clazz;

	/**
	 * DataToolsRowMapper有参构造器
	 * 
	 * @param clazz
	 *            class类型
	 */
	public DataToolsRowMapper(Class clazz) {
		this.clazz = clazz;
		fieldNameAndType = new HashMap<String, Class>();
	}


	public Object mapRow(ResultSet resultSet, int recore) throws SQLException {
		getFieldNameAndType();
		Object resultObject=null;
		try {
			resultObject = ConstructorUtils.invokeConstructor(clazz, null);

			Set<String> keys = fieldNameAndType.keySet();
			for (String key : keys) {
				Class fieldType = fieldNameAndType.get(key);
				Object dbFieldValue = null;
				try {
					dbFieldValue = resultSet.getObject(key);
				} catch (Exception e) {
					logger.error("DataToolsRowMapper对Bean处理时异常!"+clazz.getName()+"中存在"+key+"属性，与数据库字段中字段无法映射。");
				}
				if (String.class.getName().equalsIgnoreCase(fieldType.getName())) {
					if (dbFieldValue == null) {
						dbFieldValue = "";
					}
					PropertyUtils.setProperty(resultObject, key, dbFieldValue);
				} else {
					PropertyUtils.setProperty(resultObject, key, dbFieldValue);
				}
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * 获取Bean的属性名和类型
	 */
	public void getFieldNameAndType() {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			Class type = field.getType();
			fieldNameAndType.put(name, type);
		}
	}

}

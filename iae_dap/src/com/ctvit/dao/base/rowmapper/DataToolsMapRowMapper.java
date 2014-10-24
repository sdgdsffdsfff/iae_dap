/*
 *这个RowMapper可将字符Map中结果集为NULL的自动转换成空字符串。Map其它类型不做设置,为默认。 
 */
package com.ctvit.dao.base.rowmapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 * 这个RowMapper可将字符Map中结果集为NULL的自动转换成空字符串。Map其它类型不做设置,为默认.
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-6-4
 */
public class DataToolsMapRowMapper implements RowMapper {
	private static Logger logger = Logger.getLogger(DataToolsMapRowMapper.class);

	
	public DataToolsMapRowMapper() {
		
	}

	public Object mapRow(ResultSet resultSet, int recore) throws SQLException {
		try {
			Map<String, Object> entity = new LinkedHashMap<String, Object>();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
			String columnName = "";
			for (int i = 1; i <= numberOfColumns; i++) {
				String columnClassName = rsmd.getColumnClassName(i);
				columnName = rsmd.getColumnName(i);
				Object colunmValue = resultSet.getObject(i);
				Class forName = Class.forName(columnClassName);
				if (colunmValue == null && forName.equals(String.class)) {
					colunmValue = "";
				}
				columnName = columnName.toLowerCase();
				entity.put(columnName, colunmValue);
			}
			return entity;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}
	}

}

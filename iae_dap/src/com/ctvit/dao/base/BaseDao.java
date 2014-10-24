/**
 * 
 */
package com.ctvit.dao.base;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.ctvit.exception.DataToolsException;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-10
 */
public class BaseDao{
	protected NamedParameterJdbcTemplate jdbcTemplate = null;
	

	/**
	 * @return the jdbcTemplate
	 */
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		
		return jdbcTemplate;
	}

	/**
	 * @param jdbcTemplate the jdbcTemplate to set
	 */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 根据条件查询，返回一个对象
	 * @param <T> 类型
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return 查询对象
	 */
	@SuppressWarnings("unchecked")
	public<T> T queryForObject(String sql,Map paramMap,Class<T> beanClass)throws DataToolsException {
		return (T) jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper(beanClass));
	};
	/**
	 * 根据条件查询，返回一个Map(key为字段名，value为字段值)
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return Map对象
	 */
	public Map queryForMap(String sql,Map paramMap)throws DataToolsException {
		return jdbcTemplate.queryForMap(sql, paramMap);
	}
	
	/**
	 * 根据条件查询，返回一个List集合(集合对象为Map)
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return
	 */
	public List<Map> queryForList(String sql,Map paramMap)throws DataToolsException{
		return jdbcTemplate.queryForList(sql, paramMap);
	}
	/**
	 * 根据条件查询，返回一个List集合
	 * @param <T> 类型
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return 查询对象
	 */
	public <T> List<T> query(String sql,Map paramMap,Class<T> beanClass)throws DataToolsException{
		return jdbcTemplate.query(sql, paramMap,new BeanPropertyRowMapper(beanClass));
	}
	/**
	 * 更新对象
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return 受影响记录数
	 */
	public Integer update(String sql,Map paramMap)throws DataToolsException{
		return jdbcTemplate.update(sql, paramMap);
	}
	
	/**
	 * 根据条件查询,返回符合条件的个数
	 * @param sql sql语句
	 * @param paramMap 参数
	 * @return 符合条件的个数
	 */
	public Integer queryForInt(String sql,Map paramMap)throws DataToolsException{
		return jdbcTemplate.queryForInt(sql,paramMap);
	}
	
}

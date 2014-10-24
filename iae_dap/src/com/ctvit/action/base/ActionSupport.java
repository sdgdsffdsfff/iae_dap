/**
 * 所有Action继承这个公共类
 */
package com.ctvit.action.base;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ctvit.config.bean.ActionBean;
import com.ctvit.config.bean.ActionResult;
import com.ctvit.dao.base.BaseDao;
import com.ctvit.dispatcher.DataToolsContext;
import com.ctvit.exception.DataToolsException;
import com.ctvit.output.ResponseOutput;
import com.ctvit.output.ResponseOutputFactory;

/**
 * Action的基类
 * 
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public class ActionSupport extends BaseDao implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ActionSupport.class);
	private DataToolsContext dataToolsContext = null;
	private HttpServletRequest httpServletRequest = null;
	private HttpServletResponse httpServletResponse = null;

	/**
	 * 数据类型
	 */
	public String dataType;

	/**
	 * @return the dataType
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 *            the dataType to set
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the httpServletRequest
	 */
	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	/**
	 * @param httpServletRequest
	 *            the httpServletRequest to set
	 */
	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	/**
	 * @return the httpServletResponse
	 */
	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	/**
	 * @param httpServletResponse
	 *            the httpServletResponse to set
	 */
	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	/**
	 * @return the dataToolsContext
	 */
	public DataToolsContext getDataToolsContext() {
		return dataToolsContext;
	}

	/**
	 * @param dataToolsContext
	 *            the dataToolsContext to set
	 */
	public void setDataToolsContext(DataToolsContext dataToolsContext) {
		this.dataToolsContext = dataToolsContext;
		this.httpServletRequest = dataToolsContext.getHttpServletRequest();
		this.httpServletResponse = dataToolsContext.getHttpServletResponse();
	}

	/**
	 * 通过参数名取得参数多个值
	 * 
	 * @param paramNames
	 *            参数名
	 * @return
	 */
	public Object[] getParamNames(String paramNames) {
		Object[] resultObjects = dataToolsContext.getFormMap().get(paramNames);
		logger.debug("通过名称" + paramNames + "获取参数的多个值" + resultObjects);
		return resultObjects;
	}

	/**
	 * 通过参数名取得参数值
	 */
	public Object getParamName(String paramName) {
		Object[] resultObjects = dataToolsContext.getFormMap().get(paramName);
		if (resultObjects != null && resultObjects.length >= 1) {
			return resultObjects[0];
		}
		logger.debug("通过名称" + paramName + "获取参数的多个值" + resultObjects);
		return null;
	}

	/**
	 * 默认重写的方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {

		return null;
	};

	protected String validate() throws Exception {
		return null;
	};

	/**
	 * 构建响应结果
	 * 
	 * @param data
	 * @throws DataToolsException 
	 * @throws Exception
	 */
	public void build(String responseData) throws DataToolsException {
		logger.debug("构建数据响应结果开始");

		ActionBean actionBean = dataToolsContext.getActionBean(dataToolsContext.getRequestUri());
		ActionResult result = actionBean.getResult();
		try {
			ResponseOutput createOutputFactory = new ResponseOutputFactory(httpServletResponse).createOutputFactory(this.dataType);
			logger.debug("返回数据类型:" + this.dataType);
			createOutputFactory.responseResult(responseData);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DataToolsException("构建响应结果异常");
		}
		logger.debug("构建数据响应结果结束");

	}

}

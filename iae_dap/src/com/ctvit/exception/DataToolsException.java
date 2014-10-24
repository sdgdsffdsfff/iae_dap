/**
 * 
 */
package com.ctvit.exception;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class DataToolsException extends Exception {
	
	private static final long serialVersionUID = 894212156114136121L;
	private Integer status;
	private String message;
	
	public DataToolsException(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}


	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}



	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}



	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}



	/**
	 * @param string
	 */
	public DataToolsException(String message) {
		super(message);
	}


}

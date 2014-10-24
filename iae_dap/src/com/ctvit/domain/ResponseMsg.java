/**
 * 响应信息
 */
package com.ctvit.domain;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-8
 */
public class ResponseMsg {
	/**
	 * 状态码
	 */
	private String status;
	
	/**
	 * 信息
	 */
	private String message;



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

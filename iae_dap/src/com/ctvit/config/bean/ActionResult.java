/**
 * 
 */
package com.ctvit.config.bean;

import java.io.Serializable;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-9
 */
public class ActionResult  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String type;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public ActionResult(String type) {
		super();
		this.type = type;
	}

	public ActionResult() {
		super();
	}
	
}

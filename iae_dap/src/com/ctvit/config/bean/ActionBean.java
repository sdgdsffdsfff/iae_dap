/**
 * 
 */
package com.ctvit.config.bean;

import java.io.Serializable;


/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public class ActionBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String classType;
	private String path;
	private String method;
	private String ip;
	private ActionResult result;
	
	/**
	 * @return the result
	 */
	public ActionResult getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(ActionResult result) {
		this.result = result;
	}
	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the classType
	 */
	public String getClassType() {
		return classType;
	}
	/**
	 * @param classType the classType to set
	 */
	public void setClassType(String classType) {
		this.classType = classType;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	public ActionBean() {
		super();
	}
	public ActionBean(String classType, String ip, String method, String name, String path) {
		super();
		this.classType = classType;
		this.ip = ip;
		this.method = method;
		this.name = name;
		this.path = path;
	}
	public ActionBean(String classType, String ip, String method, String name, String path, ActionResult result) {
		super();
		this.classType = classType;
		this.ip = ip;
		this.method = method;
		this.name = name;
		this.path = path;
		this.result = result;
	}
	
	
}

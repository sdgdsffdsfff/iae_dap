/**
 * 读取dataToolConfig.xml配置文件
 */
package com.ctvit.config.analyzeConfig;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ctvit.config.bean.ActionBean;
import com.ctvit.config.bean.ActionResult;
import com.ctvit.config.bean.ActionsBean;
import com.ctvit.config.bean.ImportBean;
import com.thoughtworks.xstream.XStream;

/**
 * @作者 guoxiaojie@ctvit.com.cn
 * @日期 2012-5-7
 */
public class InitConfig {
	private static Logger logger = Logger.getLogger(InitConfig.class);
	private static List<ActionBean> actions = null;
	private static ActionsBean actionsBean = null;
	private static Map<String, ActionBean> pathsMap = new HashMap<String, ActionBean>();
	private static InitConfig instance = new InitConfig();
	private  final String defaultXML = "/dataToolConfig.xml";

	private InitConfig() {
		ActionsBean actionsBeanTemp = readDataToolConfigXML(defaultXML);
		ArrayList<ActionBean> actionsTemp = actionsBeanTemp.getActions();
		ArrayList<ImportBean> importsXml = actionsBeanTemp.getImports();
		addActionPathMap(actionsBeanTemp);
		if (importsXml != null) {
			for (ImportBean import_ : importsXml) {
				String file = import_.getFile();
				actionsBeanTemp = readDataToolConfigXML(file);
				addActionPathMap(actionsBeanTemp);
			}
		}
	}

	/**
	 * 根据path寻找对应的Action处理类
	 * 
	 * @param path
	 * @return
	 */
	public ActionBean getActionBean(String path) {
		logger.debug("根据" + path + "寻找对应的ActionBean处理类");
		return pathsMap.get(path);
	}

	/**
	 *获取实例
	 * 
	 * @return
	 */
	public static InitConfig getInstance() {

		return instance;
	}

	/**
	 * 获取所有配置文件对象
	 * 
	 * @return
	 */
	public Map<String, ActionBean> getActionBeans() {

		return pathsMap;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param xmlFile
	 * @return
	 */
	private ActionsBean readDataToolConfigXML(String xmlFile) {
		InputStream xmlConfig = InitConfig.class.getResourceAsStream(xmlFile);
		XStream xStream = new XStream();
		xStream.alias("actions", ActionsBean.class);
		xStream.alias("action", ActionBean.class);
		xStream.alias("import", ImportBean.class);
		xStream.aliasField("class-type", ActionBean.class, "classType");

		xStream.useAttributeFor("name", String.class);
		xStream.useAttributeFor("classType", String.class);
		xStream.useAttributeFor("path", String.class);
		xStream.useAttributeFor("method", String.class);
		xStream.useAttributeFor("ip", String.class);
		xStream.useAttributeFor(ActionResult.class, "type");
		xStream.useAttributeFor("file", String.class);

		xStream.addImplicitCollection(ActionsBean.class, "actions");
		actionsBean = (ActionsBean) xStream.fromXML(xmlConfig);
		return actionsBean;
	}

	/**
	 * 添加请求访问对应具体的Action
	 */
	private void addActionPathMap(ActionsBean actionsBean) {
		ArrayList<ActionBean> actions = actionsBean.getActions();
		if (actions != null) {
			for (ActionBean action : actions) {
				pathsMap.put(action.getPath(), action);
			}
		}
	}

}

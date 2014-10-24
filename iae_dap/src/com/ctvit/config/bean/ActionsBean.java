package com.ctvit.config.bean;

import java.util.ArrayList;

public class ActionsBean {
	private ArrayList< ActionBean> actions=new ArrayList<ActionBean>();
	private ArrayList<ImportBean> imports=new ArrayList<ImportBean>();

	public ArrayList<ActionBean> getActions() {
		return actions;
	}
	public void setActions(ArrayList<ActionBean> actions) {
		this.actions = actions;
	}
	public ArrayList<ImportBean> getImports() {
		return imports;
	}
	public void setImports(ArrayList<ImportBean> imports) {
		this.imports = imports;
	}
	
}

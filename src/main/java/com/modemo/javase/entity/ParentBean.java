package com.modemo.javase.entity;

import java.util.List;

public class ParentBean extends BaseBean{
	
	private static final long serialVersionUID = -3427144409583903449L;
	
	private List<ChildBean> childrens;

	public List<ChildBean> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<ChildBean> childrens) {
		this.childrens = childrens;
	}
	
	
}

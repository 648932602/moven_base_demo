package com.modemo.javase.entity.family;

import java.util.List;

import com.modemo.javase.entity.BaseBean;

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

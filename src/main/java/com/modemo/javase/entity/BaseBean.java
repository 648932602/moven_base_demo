package com.modemo.javase.entity;

import java.io.Serializable;

public class BaseBean implements Cloneable, Serializable{
	
	private static final long serialVersionUID = 9195518941167232244L;

	private Integer id;
	
	private String name;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

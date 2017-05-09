package com.modemo.javase.base;

public class StringDemo {
	public static void main(String[] args) {
//		System.out.println("1".equals(null));
		String[] ids = "1".split(",");
		for (String id : ids) {
			System.out.println(id);
		}
	}
}

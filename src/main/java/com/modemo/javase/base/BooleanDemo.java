package com.modemo.javase.base;

public class BooleanDemo {
	public static void main(String[] args) {
//		Boolean enable = Boolean.parseBoolean("1");
		Boolean enable = new Boolean("1");
//		Boolean disable = Boolean.parseBoolean("0");
		Boolean disable = new Boolean("0");
		System.out.println(enable);
		System.out.println(disable);
	}
}

package com.modemo.javase.base;

import com.modemo.javase.entity.BaseBean;

public class EqualDemo {
	public static void main(String[] args) {
//		BaseBean b = new BaseBean();
		String b = null;
		if("" == null) {
			System.out.println(1);
		}
		if(b != null) {
			System.out.println(2);
		}
	}
}

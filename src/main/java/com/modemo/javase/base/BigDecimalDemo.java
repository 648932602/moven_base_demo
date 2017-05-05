package com.modemo.javase.base;

import java.math.BigDecimal;

public class BigDecimalDemo {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(39.948937);
		BigDecimal b = new BigDecimal(116.484331);
		System.out.println("a = "+a+", b = "+b);
		BigDecimal c = a;
		a = b;
		b = c;
		System.out.println("a = "+a+", b = "+b);
	}
}

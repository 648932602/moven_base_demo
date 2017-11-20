package com.modemo.javase.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalDemo {
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(39.959415);
		BigDecimal b = new BigDecimal(116.486727);
//		System.out.println("a = "+a+", b = "+b);
//		BigDecimal c = a;
//		a = b;
//		b = c;
//		System.out.println("a = "+a+", b = "+b);
//		getDistence(a, b);
		System.out.println(getString());
	}
	public static void getDistence(BigDecimal latitude, BigDecimal longitude){
		 // 地球半径千米
        double r = 6371;
        // 1千米距离
        double dis = 2;
        // 计算角度
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos((latitude.multiply(new BigDecimal(Math.PI))).intValue() / 180));
        // 角度转为弧度
        dlng = dlng * 180 / Math.PI;
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        DecimalFormat df = new DecimalFormat("####0.0000");
        BigDecimal minlat = latitude.subtract(new BigDecimal(df.format(dlat)).abs());
        BigDecimal maxlat = latitude.add(new BigDecimal(df.format(dlat)).abs());
        BigDecimal minlng = longitude.subtract(new BigDecimal(df.format(dlng)).abs());
        BigDecimal maxlng = longitude.add(new BigDecimal(df.format(dlng)).abs());
        System.out.println("minlat = "+minlat);
        System.out.println("maxlat = "+maxlat);
        System.out.println("minlng = "+minlng);
        System.out.println("maxlng = "+maxlng);
	}
	
	public static String getString() {
		BigDecimal floatValue = new BigDecimal("20000000000000000000");
		System.out.println(floatValue);
		return floatValue.toString();
	}
}

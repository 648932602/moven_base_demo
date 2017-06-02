package com.modemo.javase.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxDemo {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("[a-zA-Z]{2}");
		String t = "aabbcc";
		Matcher m = p.matcher(t);
//		while (m.find()) {
//			System.out.println("s = " + m.start() + ",e = " + m.end());
//		}
		m.find();
		System.out.println("s = " + m.start() + ",e = " + m.end());
		System.out.println(t.substring(m.end()));
	}
}

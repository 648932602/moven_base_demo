package com.modemo.javase.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegxDemo {
	public static void main(String[] args) {
//		Pattern p = Pattern.compile("^[a-zA-Z]+");
//		Pattern p = Pattern.compile("^(0\\d{2,3}-\\d{7,8}(-\\d{3,5}){0,1})|((\\+86)?(1[35847]\\d{9}))$");
		Pattern p = Pattern.compile("^([\\d|-]+)$");
//		String t = "400-807-3636";
		String t = "15151515151";
		Matcher m = p.matcher(t);
//		while (m.find()) {
//			System.out.println("s = " + m.start() + ",e = " + m.end());
//		}
//		if(m.find()){
//			System.out.println("s = " + m.start() + ",e = " + m.end());
//			System.out.println(t.substring(m.end()));
//		} else {
//			System.out.println(t);
//		}
		if(m.matches()){
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}

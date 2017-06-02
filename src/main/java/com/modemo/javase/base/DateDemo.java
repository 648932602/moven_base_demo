package com.modemo.javase.base;

import java.util.Date;

import com.modemo.javase.util.DateUtil;

public class DateDemo {
	public static void main(String[] args) {
//		long time = 1492592681000l;
//		Date createDate = new Date(time);
//		System.out.println(createDate);
		
//		Date timeDate = DateUtil.parseStringToDate("2017-05-24 10:30:00", DateUtil.C_HHMM);
//		System.out.println(timeDate);
//		String timeDateStr = DateUtil.parseDateToString(timeDate, DateUtil.C_HHMM);
//		System.out.println(timeDateStr);
		Date now = DateUtil.getCurrentDate();
		System.out.println(now);
	}
}

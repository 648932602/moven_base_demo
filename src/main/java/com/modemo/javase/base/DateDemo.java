package com.modemo.javase.base;

import java.util.Calendar;
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
		Date later = new Date(117,5,29);
		System.out.println("now = "+now.getTime());
		System.out.println("later = "+later.getTime());
		boolean after = now.after(later);
		System.out.println(after);
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		System.out.println(now.getMonth()+1);
		System.out.println(now.getDate());
	}
}

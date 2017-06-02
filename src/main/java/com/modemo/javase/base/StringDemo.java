package com.modemo.javase.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDemo {
	private static Pattern prefixRegx = Pattern.compile("^[a-zA-Z]+");// 判断工位编号连续前缀

	public static void main(String[] args) {
		// System.out.println("1".equals(null));
		// String[] ids = "1".split(",");
		// for (String id : ids) {
		// System.out.println(id);
		// }
//		String testSubString = "aabbcc";
//		testSubString = testSubString.substring(0);
//		System.out.println(testSubString);
		boolean isContinue = isContinue("1001", "1002");
		System.out.println(isContinue);
	}

	private static boolean isContinue(String a, String b) {
		boolean isContinue = false;
		String aSuffix;
		String bSuffix;
		// 1.判断前缀是否一致
		Matcher am = prefixRegx.matcher(a);
		Matcher bm = prefixRegx.matcher(b);
		boolean aIsLetter = am.find();
		boolean bIsLetter = bm.find();
		if (aIsLetter == bIsLetter) {
			aSuffix = a;
			bSuffix = b;
			// 2.判断是否以字母开头
			if (true == aIsLetter) {
				// 3.字母部分是否一致
				String aPrefix = a.substring(am.start(), am.end());
				String bPrefix = b.substring(bm.start(), bm.end());
				if (aPrefix.equals(bPrefix)) {
					// 4.获取纯数字部分
					aSuffix = a.substring(am.end());
					bSuffix = b.substring(bm.end());
				}
			}
			// 5.数字部分是否连续
			try {
				int aInt = Integer.valueOf(aSuffix);
				int bInt = Integer.valueOf(bSuffix);

				if (Math.abs(bInt - aInt) == 1) {
					isContinue = true;
				}
			} catch (Exception e) {
			}
		}
		return isContinue;
	}
}

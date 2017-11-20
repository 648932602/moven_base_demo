package com.modemo.javase.base;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.moven.common.utils.MoneyUtil;

public class StringDemo {
	// 判断工位编号连续前缀
	private static Pattern prefixRegx = Pattern.compile("^[a-zA-Z]+");
	// 信息模板
	private static String infoTemp = "%s月%s日  %s  预约%s个工位";
	// 格式模板
	private static String styleTemp = "{0}月{1}日  {2}  预约{3}个工位";

	public static void main(String[] args) {
		BigDecimal money = new BigDecimal("150015641575.23");
		System.out.println(MoneyUtil.getMoneyCDesc(money));
	}
	private static Boolean testR() {
		System.out.println("r");
		return null;
	}
	
	private static Integer testD() {
		testR();
		System.out.println("d");
		return null;
	}
	
	private static void testE() {
		String settingStr = "{\"wsbtnEnabled\":\"false\",\"wsradio\":\"btnCity\"}";
		JSONObject setting = JSON.parseObject(settingStr);
		System.out.println(setting.getString("wsradio"));
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
	
	private static String replace() {
		String path = "10_";
		path = StringUtils.replace(path, "_", "\\_");
//		path = path.replace("_", "\\_");
		return path;
	}
	
	private static String nullToJson() {
		String path = "";
		JSONObject o = JSON.parseObject(path);
		System.out.println("o = "+o);
		return path;
	}
	
}

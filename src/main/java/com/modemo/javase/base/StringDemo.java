package com.modemo.javase.base;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class StringDemo {
	// 判断工位编号连续前缀
	private static Pattern prefixRegx = Pattern.compile("^[a-zA-Z]+");
	// 信息模板
	private static String infoTemp = "%s月%s日  %s  预约%s个工位";
	// 格式模板
	private static String styleTemp = "{0}月{1}日  {2}  预约{3}个工位";

	public static void main(String[] args) {
		// String maxSerialNumber = "12345678900000001";
		// Integer nextSerialNumber =
		// Integer.parseInt(maxSerialNumber.substring(maxSerialNumber.length() - 8)) +
		// 1;
		// String nextSNStr = String.valueOf(nextSerialNumber);
		// int currentLength = nextSNStr.length();
		// for (int i = 8; i > currentLength; i--) {
		// nextSNStr = "0" + nextSNStr;
		// }
		// System.out.println(nextSNStr);
		// String a = "12345678987654321";
		// System.out.println(a.substring(a.length()-6));
		// System.out.println(MessageFormat.format(styleTemp, 1,2,3,4));
		// String a = "a";
		// System.out.println("a1 = "+a);
		// testR(a);
		// System.out.println("a2 = "+a);
		// getDPI();
//		System.out.println(new String("") == null);
		getFontNames();
	}

	private static void getFontNames() {
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for (int i = 0; i < fonts.length; i++) {
			System.out.println(fonts[i]);
		}
	}

	private static void getDPI() {
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int height = (int) screensize.getHeight();
		// 屏幕的物理大小还需要知道屏幕的dpi 意思是说一英寸多少个象素
		int dpi = Toolkit.getDefaultToolkit().getScreenResolution();
		System.out.println("dpi = " + dpi);
	}

	private static void testR(String r) {
		System.out.println("r1 = " + r);
		r = r + "b";
		System.out.println("r2 = " + r);
	}

	private static Integer testD() {
		// testR();
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
		// path = path.replace("_", "\\_");
		return path;
	}

	private static String nullToJson() {
		String path = "";
		JSONObject o = JSON.parseObject(path);
		System.out.println("o = " + o);
		return path;
	}

}

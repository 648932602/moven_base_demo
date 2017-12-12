package com.modemo.javase.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HtmlDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder path = new StringBuilder();
		path.append("//FileDemo");// 保存生成Html文件的目录
		File file1;
		String str = null;
		Date todaytime = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat ttime = new SimpleDateFormat("yyyyMMddhhMMSS");
		String name = ttime.format(todaytime);// 生成当前时间作为文件名
		String folder = date.format(todaytime);// 生成当前日期作为文件夹名
		File file = new File(path.toString());
		File[] files = file.listFiles();// 得到根目录下的所有文件名目录
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {// 判断是否为目录
				if (files[i].getName().equals(folder)) { 
					str = files[i].getName();
				}
			}
		}

		if (str == null) {
			file1 = new File(path.append("\\" + folder + "\\").toString());
			file1.mkdir();// 如果str等于空.则在根目录下创建folder目录.
			System.out.println("1:" + path);
		} else {
			path.append("\\" + str + "\\");// 如果str不为空,则用原目录
			System.out.println("2:" + path);
		}

		String content = "this is my fisrt Html page.这是我第一个html页面<br>呵呵.......史来右.在灰在此呵呵.......史来右.在灰在此呵呵.......史来 右.在灰在此呵呵.......史来右.在灰在此呵呵.......史来右.在灰在此";
		File file2 = new File(path.append(name + ".html").toString());
		System.out.println(path);
		StringBuilder sb = new StringBuilder();
		try {
			file2.createNewFile();// 创建文件
			sb.append("<html><head><title>Html Test</title></head><body>");
			sb.append("<div align='center'>");
			sb.append(content);
			sb.append("</div>");
			sb.append("</body></html>");
			PrintStream printStream = new PrintStream(new FileOutputStream(file2));
			printStream.println(sb.toString());// 将字符串写入文件
			String newPath = path.toString().replace("\\", "/");
			System.out.println(newPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
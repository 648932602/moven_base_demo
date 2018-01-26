package com.modemo.javase.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FreemarkerDemo {
	public static void main(String[] args) throws Exception {
//		getFile("ftl/demo.ftl");
		getFile("file:/workspace/krspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/webapps/krspace-erp-web/WEB-INF/lib/krspace-framework-1.9.2-pdf-SNAPSHOT.jar!ftl/stationContract.ftl");
	}

	private static void getFile(String fileName) {
		ClassLoader classLoader = FreemarkerDemo.class.getClassLoader();
		/**
		 * getResource()方法会去classpath下找这个文件，获取到url resource, 得到这个资源后，调用url.getFile获取到 文件
		 * 的绝对路径
		 */
		URL url = classLoader.getResource(fileName);
		/**
		 * url.getFile() 得到这个文件的绝对路径
		 */
		System.out.println(url.getFile().getBytes().toString());
		File file = new File(url.getFile());
		InputStream input = null;
		try {
			input = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuffer buffer = new StringBuffer();
		byte[] bytes = new byte[1024];
		try {
			for (int n; (n = input.read(bytes)) != -1;) {
				buffer.append(new String(bytes, 0, n, "utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(buffer.toString());
	}

}

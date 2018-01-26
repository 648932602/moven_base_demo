package com.modemo.javase.base;

import java.awt.GraphicsEnvironment;

public class FontDemo {
	public static void main(String[] args) {
		getFontNames();
	}
	private static void getFontNames() {
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for (int i = 0; i < fonts.length; i++) {
			System.out.println(fonts[i]);
		}
	}
}

package com.modemo.javase.base;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetDemo {
	public static void main(String[] args) {
		List<Integer> ids = Collections.emptyList();
		Set<Integer> idSet = new HashSet<Integer>(ids);
	}
}

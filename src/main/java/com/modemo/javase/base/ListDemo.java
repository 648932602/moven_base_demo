package com.modemo.javase.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListDemo {
	public static void main(String[] args) {
//		List<Integer> ids1 = new ArrayList<Integer>(10);
//		ids1.add(1);
//		ids1.add(1);
//		ids1.add(2);
//		ids1.add(3);
//		List<Integer> ids2 = new ArrayList<Integer>(10);
//		ids2.add(5);
//		ids2.add(6);
//		ids2.add(7);
//		ids2.add(8);
//		Set<Integer> idSet = new HashSet<Integer>();
//		idSet.addAll(ids1);
//		idSet.addAll(ids2);
//		for (Integer id : idSet) {
//			System.out.println(id);
//		}
		
		List<Integer> ids3 = Collections.emptyList();
		for (Integer id : ids3) {
			System.out.println(id);
		}
	}
}

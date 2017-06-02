package com.modemo.javase.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.modemo.javase.entity.ChildBean;
import com.modemo.javase.entity.ParentBean;

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
		
//		List<Integer> ids3 = Collections.emptyList();
//		for (Integer id : ids3) {
//			System.out.println(id);
//		}
		List<ParentBean> parents = new ArrayList<ParentBean>(3);
		ParentBean p1 = new ParentBean();
		List<ChildBean> children1 = new ArrayList<ChildBean>(3);
		p1.setChildrens(children1);
		ParentBean p2 = new ParentBean();
		List<ChildBean> children2 = new ArrayList<ChildBean>(3);
		p2.setChildrens(children2);
		ParentBean p3 = new ParentBean();
		List<ChildBean> children3 = new ArrayList<ChildBean>(3);
		p3.setChildrens(children3);
		parents.add(p1);
		parents.add(p2);
		parents.add(p3);
		
		ChildBean c11 = new ChildBean();
		c11.setId(11);
		c11.setName("11");
		ChildBean c12 = new ChildBean();
		c12.setId(12);
		c12.setName("12");
		ChildBean c13 = new ChildBean();
		c13.setId(13);
		c13.setName("13");
		
		children1.add(c11);
		children1.add(c12);
		children1.add(c13);
		
	}
}

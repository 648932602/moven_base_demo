package com.modemo.javase.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.modemo.javase.entity.family.ChildBean;
import com.modemo.javase.entity.family.ParentBean;

public class ListDemo {
	public static void main(String[] args) {
		List<Integer> ids1 = new ArrayList<Integer>(10);
		ids1.add(1);
		ids1.add(2);
		ids1.add(3);
		ids1.add(4);
		ids1.add(5);
		ids1.add(6);
//		System.out.println(ids1.contains(null));
//		ids1.add(null);
//		ids1.add(null);
//		ids1.add(null);
		List<Integer> ids2 = new ArrayList<Integer>(10);
		ids2.add(5);
		ids2.add(6);
		ids2.add(7);
		ids2.add(8);
		ids1.retainAll(ids2);
		System.out.println(ids1);
//		Set<Integer> idSet = new HashSet<Integer>();
//		idSet.addAll(ids1);
//		idSet.addAll(ids2);
//		for (Integer id : ids1) {
////			System.out.println(ids1.indexOf(id));
//			System.out.println(id.toString());
//		}
//		System.out.println("倒序");
//		Collections.reverse(ids1);
//		for (Integer id : ids1) {
////			System.out.println(ids1.indexOf(id));
//			System.out.println(id.toString());
//		}
		
//		List<Integer> ids3 = Collections.emptyList();
//		for (Integer id : ids3) {
//			System.out.println(id);
//		}
//		List<ParentBean> parents = new ArrayList<ParentBean>(3);
//		ParentBean p1 = new ParentBean();
//		List<ChildBean> children1 = new ArrayList<ChildBean>(3);
//		p1.setChildrens(children1);
//		ParentBean p2 = new ParentBean();
//		List<ChildBean> children2 = new ArrayList<ChildBean>(3);
//		p2.setChildrens(children2);
//		ParentBean p3 = new ParentBean();
//		List<ChildBean> children3 = new ArrayList<ChildBean>(3);
//		p3.setChildrens(children3);
//		parents.add(p1);
//		parents.add(p2);
//		parents.add(p3);
//		
//		ChildBean c11 = new ChildBean();
//		c11.setId(11);
//		ChildBean c12 = new ChildBean();
//		c12.setId(12);
//		ChildBean c13 = new ChildBean();
//		c13.setId(13);
//		
//		children1.add(c11);
//		children1.add(c12);
//		children1.add(c13);
//		removeAll();
	}
	
	private static void removeAll() {
		List<ChildBean> children1 = new ArrayList<ChildBean>(3);
		List<ChildBean> children2 = new ArrayList<ChildBean>(3);
		
		ChildBean c11 = new ChildBean();
		c11.setId(11);
		ChildBean c12 = new ChildBean();
		c12.setId(12);
		ChildBean c13 = new ChildBean();
		c13.setId(13);
		
		children1.add(c11);
		children1.add(c12);
		children1.add(c13);
		
		System.out.println("------before------");
		for (ChildBean childBean : children1) {
			if(12 == childBean.getId()) {
				childBean.setName("12");
			}
			System.out.println("---index = "+children1.indexOf(childBean)+"---");
			System.out.println("name = "+childBean.getName());
			System.out.println("--------------");
		}
		children2.add(c12);
		children1.removeAll(children2);
		System.out.println("------after-------");
		for (ChildBean childBean : children1) {
			System.out.println("---index = "+children1.indexOf(childBean)+"---");
			System.out.println("name = "+childBean.getName());
			System.out.println("--------------");
		}
	}
}

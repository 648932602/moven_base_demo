package com.modemo.javase.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.modemo.javase.entity.tree.TreeNode;

public class MapDemo {
	private static final Map<Integer, TreeNode> tree = new HashMap<Integer, TreeNode>(10);
//	private static final Map<String, Integer> tree = new HashMap<String, Integer>(10);
	private static List<TreeNode> nodes = new ArrayList<TreeNode>(10);

	static {
		for (int i = 1; i <= 10; i++) {
			TreeNode node = new TreeNode();
			node.setId(i);
			node.setName("node_"+i);
			int pid = i%4+1;
			node.setPid(pid);
			nodes.add(node);
		}
		for (int i = 1; i <= 10; i++) {
			TreeNode node = new TreeNode();
			node.setId(i);
			node.setName("node_"+i);
			int pid = i%4+1;
			node.setPid(pid);
			tree.put(i, node);
		}
	}
	
	public static void main(String[] args) {
//		System.out.println("-S--"+System.currentTimeMillis()+"---");
//		buildTree();
//		for (TreeNode node : nodes) {
//			System.out.println(node.toString());
//		}
//		System.out.println("-E--"+System.currentTimeMillis()+"---");
//		putIfAbsent();
//		List<TreeNode> tempNode = new ArrayList<TreeNode>(1);
//		tempNode.add(get(1));
//		TreeNode node = tempNode.get(0);
//		System.out.println(get(1));
//		tree.remove(1);
//		System.out.println(node);
//		System.out.println(get(1));
//		System.out.println(node.getName());
		
//		Map<Integer, String> linkMap = Maps.newLinkedHashMapWithExpectedSize(5);
//		linkMap.put(1, "1");
//		linkMap.put(2, "2");
//		linkMap.put(3, "3");
//		linkMap.put(4, "4");
//		linkMap.put(5, "5");
//		
//		for (String value : linkMap.values()) {
//			System.out.println(value);
//		}
//		pubSub();
//		getBytes();
		putAllForUpdate();
	}
	
	public static void pubSub() {
		Map<String, Object> pMap = new HashMap<String, Object>(2);
		Map<String, Object> subMap = new HashMap<String, Object>(2);
		subMap.put("name", "子集合");
		pMap.put("sub", subMap);
		pMap.put("sub.id", 1);
		System.out.println(subMap.get("id"));
	}
	
	public static TreeNode get(Integer key) {
		TreeNode temp = tree.get(key);
//		System.out.println(temp);
		return temp;
	}
	
	public static void buildTree() {
		for(int i = nodes.size(),j = 0; i != j ; j = i, i = nodes.size()) {
			System.out.println("size = "+nodes.size()+",i = "+i+",j = "+j);
			Map<Integer, TreeNode> tempNodeMap = getNodeMapBy(nodes);
			Map<Integer, List<TreeNode>> tempSubMap = getSubListGroupByPid(nodes);
			nodes = mergeNodeList(combTree(tempNodeMap, tempSubMap));
			System.out.println("size = "+nodes.size()+",i = "+i+",j = "+j);
		}
	}

	public static List<TreeNode> mergeNodeList(Map<Integer, TreeNode> nodes){
		if(MapUtils.isEmpty(nodes)) {
			return Collections.emptyList();
		}
		List<TreeNode> nodeList = new ArrayList<TreeNode>(nodes.size());
		for (TreeNode node : nodes.values()) {
			nodeList.add(node);
		}
		return nodeList;
	}
	
	public static Map<Integer, TreeNode> getNodeMapBy(List<TreeNode> nodes){
		if(CollectionUtils.isEmpty(nodes)) {
			return Collections.emptyMap();
		}
		Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>(nodes.size());
		for (TreeNode node : nodes) {
			nodeMap.put(node.getId(), node);
		}
		return nodeMap;
	}
	
	public static Map<Integer, List<TreeNode>> getSubListGroupByPid(List<TreeNode> nodes){
		if(CollectionUtils.isEmpty(nodes)) {
			return Collections.emptyMap();
		}
		Map<Integer, List<TreeNode>> subListMap = new HashMap<Integer, List<TreeNode>>(nodes.size());
		for (TreeNode node : nodes) {
			List<TreeNode> subs = subListMap.get(node.getPid());
			if(null == subs) {
				subs = new ArrayList<TreeNode>(nodes.size());
				subListMap.put(node.getPid(), subs);
			}
			subs.add(node);
		}
		return subListMap;
	}
	
	public static Map<Integer, TreeNode> combTree(Map<Integer, TreeNode> treeNodes, Map<Integer, List<TreeNode>> subMap){
		if(MapUtils.isEmpty(subMap)) {
			return treeNodes;
		}
		Map<Integer, TreeNode> nodeMap = new HashMap<Integer, TreeNode>(nodes.size());
		for (Entry<Integer, List<TreeNode>> node : subMap.entrySet()) {
			TreeNode realNode = treeNodes.get(node.getKey());
			if(null == realNode) {
				continue;
			}
			realNode.setNodes(node.getValue());
			nodeMap.put(node.getKey(), realNode);
		}
		return nodeMap;
	}
	
	public static void putIfAbsent() {
		Map<Integer, String> putMap = new HashMap<Integer, String>();
		for(int i = 0; i < 10; i++){
			String a = putMap.putIfAbsent(i%4, "absent"+i);
			if(null == a) {
				a = putMap.get(i%4);
			}
			System.out.println(a);
		}
	}
	
	public static void getBytes() {
//		File file = new File(WkhtmlToPDFUtil.DEST_HTML);
//		try {
//			byte[] bytes = IOUtils.toByteArray(file.toURI());
//			System.out.println(bytes);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public static void putAllForUpdate() {
		Map<String, Integer> a = new HashMap<String, Integer>(5);
		a.put("1", 1);
		a.put("2", 1);
		a.put("3", 1);
		a.put("4", 1);
		Map<String, Integer> b = new HashMap<String, Integer>(5);
		b.put("1", 1);
		b.put("2", 2);
		b.put("3", 3);
		b.put("4", 4);
		a.putAll(b);
		for (Map.Entry<String, Integer> entry : a.entrySet()) {
			System.out.println("K - "+entry.getKey()+", V - "+entry.getValue());
		}
	}
}

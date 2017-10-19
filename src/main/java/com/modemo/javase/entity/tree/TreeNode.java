package com.modemo.javase.entity.tree;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.modemo.javase.entity.BaseBean;

public class TreeNode extends BaseBean {
	
	private static final long serialVersionUID = 5993002130076812836L;
	
	private Integer pid;
	
	private List<TreeNode> nodes;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public List<TreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeNode> nodes) {
		this.nodes = nodes;
	}

//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append("-START---ID->").append(this.getId()).append("-----\r\n");
//		sb.append("name:").append(this.getName()).append("\r\n");
//		sb.append("pid:").append(this.getPid()).append("\r\n");
//		sb.append("nodes.size->").append("\r\n");
//		if(CollectionUtils.isNotEmpty(nodes)) {
//			sb.append(nodes.size()).append("\r\n");
//		}
//		sb.append("-END---ID->").append(this.getId()).append("-----\r\n");
//		return sb.toString();
//	}

}

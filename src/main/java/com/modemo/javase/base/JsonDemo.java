package com.modemo.javase.base;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class JsonDemo {
	public static void main(String[] args) {
//		String result1 = JSONObject.toJSONString(1);
//		JSONObject object = JSON.parseObject(result1);
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("a", new String("atest"));
		objMap.put("b", new Integer(1));
		objMap.put("c", new Double(2.0));
		System.out.println(JSON.toJSONString(objMap));
	}
}

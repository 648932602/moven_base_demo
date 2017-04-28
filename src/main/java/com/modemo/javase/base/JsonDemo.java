package com.modemo.javase.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonDemo {
	public static void main(String[] args) {
		String result1 = JSONObject.toJSONString(1);
		JSONObject object = JSON.parseObject(result1);
	}
}

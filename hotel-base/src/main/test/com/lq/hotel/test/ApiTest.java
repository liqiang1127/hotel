package com.lq.hotel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.webUtils.HttpHelper;

public class ApiTest {
	@Test
	public void test(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("coop_id", 1);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/api!getKeyContentByCoop.action",jsonStr);
		System.out.println(json);
	}
}

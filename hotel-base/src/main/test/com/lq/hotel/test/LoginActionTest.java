package com.lq.hotel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.webUtils.HttpHelper;

public class LoginActionTest {
	@Test
	public void login(){
		//前面封装成list 后面用Integer[]直接取可以的
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("staffId","00101001" );
		map.put("password", "000000");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/login!login.action",jsonStr);
		System.out.println(json);
	}
}

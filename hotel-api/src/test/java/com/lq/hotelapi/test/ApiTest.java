package com.lq.hotelapi.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.webUtils.HttpHelper;
import com.lq.webUtils.PasswordEncoder;

public class ApiTest {
	@Test
	public void queryHotelList(){
		Map<String,Object> reqMap = new HashMap<>();
		Map<String,Object> data = new HashMap<>();
		data.put("name", "");
		reqMap.put("data", data);
		reqMap.put("coop_id", 1);
		Long timestamp =  new Date().getTime();
		reqMap.put("timestamp", timestamp);
		String key = "+NqvhdpHSwujzc0oBVfURw==";
		List<String> keys = new ArrayList<>(data.keySet());
		Collections.sort(keys);
		String datastr = "";
		for (String string : keys) {
			datastr+=(String)data.get(string);
		}
		datastr = datastr+ key+timestamp;//该字符串用于签名
		String sign = PasswordEncoder.passwordEncode(datastr);//md5加密 base64明文传输
		reqMap.put("sign", sign);
		String jsonStr = JSON.toJSONString(reqMap);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8080/hotel-api/router!queryHotelList.action",jsonStr);
		System.out.println(json);
	}
}

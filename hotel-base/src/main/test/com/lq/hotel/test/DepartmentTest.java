package com.lq.hotel.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.entity.Department;
import com.lq.webUtils.HttpHelper;

public class DepartmentTest {
	@Test
	public void tsetDeleteDe(){
		//前面封装成list 后面用Integer[]直接取可以的
		Map<String,Object> map = new HashMap<String, Object>();
		List<Integer> ids = new ArrayList<>();
		ids.add(1);
		ids.add(2);
		map.put("ids", ids);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/department!deleteByIDs.action",jsonStr);
		System.out.println(json);
	}
	
	//不会报错 会null
	@Test
	public void tset2(){
		String json = "{\"qq\":\"123\"}";
		System.out.println(json);
		Department de = JSON.parseObject(json, Department.class);
		System.out.println(de.getName());
	}	
}

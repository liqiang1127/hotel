package com.lq.hotel.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.CooperationBean;
import com.lq.webUtils.HttpHelper;

public class CooperActionTest {
	@Test
	public void addCooeration(){
		Map<String,Object> map = new HashMap<String, Object>();
		CooperationBean cb = new CooperationBean();
		cb.setName("测闪闪闪");
		cb.setRemark("x11111111");
		cb.setState("0");
		cb.setCreateDate(new Date());
		map.put("cooperation", cb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/cooperation!addCooperation.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void editCooperation(){
		Map<String,Object> map = new HashMap<String, Object>();
		CooperationBean cb = new CooperationBean();
		cb.setId(2);
		cb.setName("测试合作1测试合修改修改修改");
		cb.setRemark("x1111吾问无为谓11");
		cb.setState("1");
		cb.setCreateDate(new Date());
		map.put("cooperation", cb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/cooperation!editCooperation.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deteteCooperation(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/cooperation!deteteCooperation.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void getCooperation(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/cooperation!getCooperation.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryCooperations(){
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("name", "测测试");
//		map.put("state", "0");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/cooperation!queryCooperation.action",jsonStr);
		System.out.println(json);
	}
}

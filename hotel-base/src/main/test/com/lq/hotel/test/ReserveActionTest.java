package com.lq.hotel.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.bean.ReserveOrderBean;
import com.lq.webUtils.HttpHelper;

public class ReserveActionTest {
	//提供bean对象 以类名作为key
		@Test
		public void addReserve(){
			//必选参数 name 可选remark
			Map<String,Object> map = new HashMap<String, Object>();
			ReserveOrderBean rob= new  ReserveOrderBean();
			rob.setCreateTime(new Date());
			rob.setResName("李强啊啊啊");
			rob.setPreNumber(2);
			rob.setResMobile("16656295808");
			map.put("reserveOrder", rob);
			map.put("hotel_id", 1);
			map.put("roomKind_id", 2);
			map.put("employee_id", 1);
			String jsonStr = JSON.toJSONString(map);
			System.out.println(jsonStr);
			String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/reserve!addReserve.action",jsonStr);
			System.out.println(json);
		}
		
		@Test
		public void getReserve(){
			//必选参数 name 可选remark
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", 1);
			String jsonStr = JSON.toJSONString(map);
			System.out.println(jsonStr);
			String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/reserve!getReserve.action",jsonStr);
			System.out.println(json);
		}
		
		@Test
		public void queryReserve(){
			//必选参数 name 可选remark
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("resMobile", "13095489456");
//			map.put("state", "0");
			String jsonStr = JSON.toJSONString(map);
			System.out.println(jsonStr);
			String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/reserve!queryReserve.action",jsonStr);
			System.out.println(json);
		}
		@Test
		public void cancelReserve(){
			//必选参数 name 可选remark
			Map<String,Object> map = new HashMap<String, Object>();
//			map.put("resMobile", "16656295808");
			map.put("id", "1");
			String jsonStr = JSON.toJSONString(map);
			System.out.println(jsonStr);
			String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/reserve!cancelReserve.action",jsonStr);
			System.out.println(json);
		}
		
}

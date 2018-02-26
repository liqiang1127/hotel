package com.lq.hotel.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.BusinessFlowBean;
import com.lq.hotel.bean.CheckinOrderBean;
import com.lq.hotel.bean.GuestBean;
import com.lq.hotel.entity.BusinessFlow;
import com.lq.hotel.entity.CheckinOrder;
import com.lq.webUtils.HttpHelper;

public class ServiceActionTest {
	@Test
	public void checkInFromRes(){
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("resMobile", "16656295808");
//		map.put("state", "0");
		List<GuestBean> guests = new ArrayList<>();
		GuestBean gb1 = new GuestBean();
		gb1.setName("一号");
		GuestBean gb2 = new GuestBean();
		gb2.setName("二号");
		guests.add(gb1);
		guests.add(gb2);
		map.put("guests", guests);
		map.put("reserveOrder_id", 1);
		map.put("employee_id", 1);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/service!checkInFromRes.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void checkIn(){
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("resMobile", "16656295808");
//		map.put("state", "0");
		List<GuestBean> guests = new ArrayList<>();
		GuestBean gb1 = new GuestBean();
		gb1.setName("san号");
		GuestBean gb2 = new GuestBean();
		gb2.setName("si号");
		guests.add(gb1);
		guests.add(gb2);
		map.put("guests", guests);
		CheckinOrderBean cob = new CheckinOrderBean();
		cob.setRoom_id(2);
		cob.setInDate(new Date());
		cob.setCreateTime(new Date());
		cob.setPaidMoney(new BigDecimal("300.0"));
		map.put("checkinOrder", cob);
		map.put("employee_id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/service!checkIn.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void addService(){
		Map<String,Object> map = new HashMap<String, Object>();
		BusinessFlowBean bfb = new BusinessFlowBean();
		bfb.setEmployee_id(1);
		bfb.setRemark("asdsadads1");
		bfb.setCost(new BigDecimal(200));
		bfb.setCheckinOrder_id(3);
		bfb.setServiceKind_id(2);
		map.put("businessFlow",bfb );
		String jsonStr = JSON.toJSONString(map);

		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/service!addService.action",jsonStr);
		System.out.println(json);
	}
	@Test
	public void queryCheckin(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("room_id", 1);
		String jsonStr = JSON.toJSONString(map);

		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/service!queryCheckin.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void checkout(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		String jsonStr = JSON.toJSONString(map);

		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/service!checkout.action",jsonStr);
		System.out.println(json);
	}
}

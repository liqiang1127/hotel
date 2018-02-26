package com.lq.hotel.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.RoomBean;
import com.lq.hotel.bean.RoomKindBean;
import com.lq.hotel.entity.ServiceKind;
import com.lq.webUtils.HttpHelper;

public class ManageActionTest {
	@Test
	public void addRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
		RoomKindBean rkb = new RoomKindBean();
		rkb.setName("测试间");
		rkb.setBedNumber(1);
		rkb.setDiscount(new BigDecimal(0.68));
		rkb.setMinHours(5);
		rkb.setPerHourPrice(new BigDecimal(100.0));
		rkb.setPrePrice(new BigDecimal(200.0));
		rkb.setPriceOneNight(new BigDecimal(350.0));
		rkb.setState("1");
		map.put("roomKind", rkb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!addRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deleteRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!deleteRoomKind.action",jsonStr);
		System.out.println(json);
	}
	@Test
	public void editeRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
		RoomKindBean rkb = new RoomKindBean();
		rkb.setId(3);
		rkb.setName("闪闪闪");
		rkb.setBedNumber(2);
		rkb.setDiscount(new BigDecimal(0.68));
		rkb.setMinHours(5);
		rkb.setPerHourPrice(new BigDecimal(100.0));
		rkb.setPrePrice(new BigDecimal(200.0));
		rkb.setPriceOneNight(new BigDecimal(350.0));
		rkb.setState("1");
		map.put("roomKind", rkb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!editRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void getRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!getRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("name", "间");
//		map.put("bedNumber", "1");
		map.put("state", "1");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!queryRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void addRoom(){
		Map<String,Object> map = new HashMap<String, Object>();
		RoomBean rb = new RoomBean();
		rb.setNo("302");
		rb.setArea("北");
		rb.setTelphone("110");
		rb.setFloor("3");
		rb.setHotel_id(1);//重要必须要有
		rb.setRoomKind_id(2);//重要必须要有
		map.put("room", rb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!addRoom.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void getRoom(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",1 );
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!getRoom.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void editRoom(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",1);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!getRoom.action",jsonStr);
		System.out.println(json);
		Map<String, Object> JSONMap = JSON.parseObject(json);
		RoomBean rb = JSON.parseObject(JSONMap.get("item").toString(), RoomBean.class);
		map.clear();
		rb.setNo("2221");
		map.put("room", rb);
		jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		 json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!editRoom.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryRoom(){
		Map<String,Object> map = new HashMap<String, Object>();
//		String area = JSONMap.get("area")==null?"":JSONMap.get("area").toString();
//		String floor = JSONMap.get("floor")==null?"":JSONMap.get("floor").toString();
//		String no = JSONMap.get("no")==null?"":JSONMap.get("no").toString();
//		String available = JSONMap.get("available")==null?"":JSONMap.get("available").toString();
//		String roomKind_id = JSONMap.get("roomKind_id")==null?"":JSONMap.get("roomKind_id").toString();
//		map.put("area","1" );
		map.put("floor", "2");
		map.put("no", "2221");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!queryRoom.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deleteRoom(){
		Map<String,Object> map = new HashMap<String, Object>();
//		String area = JSONMap.get("area")==null?"":JSONMap.get("area").toString();
//		String floor = JSONMap.get("floor")==null?"":JSONMap.get("floor").toString();
//		String no = JSONMap.get("no")==null?"":JSONMap.get("no").toString();
//		String available = JSONMap.get("available")==null?"":JSONMap.get("available").toString();
//		String roomKind_id = JSONMap.get("roomKind_id")==null?"":JSONMap.get("roomKind_id").toString();
		map.put("id", "2");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!deleteRoom.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void addServiceKind(){
		Map<String,Object> map = new HashMap<String, Object>();
		ServiceKind sk = new ServiceKind();
		sk.setName("标准早餐");
		sk.setRemark("鸡蛋饼");
		sk.setCost(new BigDecimal("100.0"));
		map.put("serviceKind", sk);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!addServiceKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deleteServiceKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!deleteServiceKind.action",jsonStr);
		System.out.println(json);
	}
	@Test
	public void queryServiceKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("name", "11");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!queryServiceKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryHotelRoomKinds(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("hotel_id", "1");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!queryHotelRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void editHotelRoomKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("hotel_id", "1");
		map.put("roomKind_id", "2");
		map.put("op", "2");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!editHotelRoomKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryHotelServiceKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("hotel_id", "1");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!queryHotelServiceKind.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void editHotelServiceKind(){
		Map<String,Object> map = new HashMap<String, Object>();
//		ServiceKind sk = new ServiceKind();
//		sk.setName("加床");
//		sk.setRemark("增加床铺");
//		sk.setCost(new BigDecimal("100.0"));
		map.put("hotel_id", "1");
		map.put("serviceKind_id", "2");
		map.put("op", "2");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/manage!editHotelServiceKind.action",jsonStr);
		System.out.println(json);
	}
}

package com.lq.hotel.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.HotelBean;
import com.lq.webUtils.HttpHelper;

public class BranchActionTest {
	/*		private Integer id;
	private String location;// (浙江省,杭州市,滨江区,下沙六号大街二十五号路口)省市区以英文逗号分隔
	private String coordinate; // 坐标 可用于调度百度地图api 106.581515,29.615467 x,y
								// 英文,分割
	private String state;// 状态 1正常营业 2尚未营业 3停业整顿 4其他
	private String star;// 星级
	private Date createDate;// 插入这条记录的日期
	private Date bussinessDate;// 开始营业的日期
	private String remark;// 描述
*/	
	//新增hotel
	@Test
	public void addHotel() throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date busDate = df.parse("2017-07-13");
		HotelBean hb = new HotelBean();
		hb.setLocation("浙江.杭州.下沙222");
		hb.setCoordinate("123,236.3");
		hb.setCreateDate(new Date());
		hb.setStar("5");
		hb.setState("1");
		hb.setBussinessDate(busDate);
		map.put("hotel", hb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!addHotel.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void getHotel(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!getHotel.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deleteHotel(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!deleteHotel.action",jsonStr);
		System.out.println(json);
	}
	
	//edit id是前台传过来的 如果id不存在会报系统错误
	@Test
	public void editHotel() throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date busDate = df.parse("2017-07-13");
		HotelBean hb = new HotelBean();
		hb.setId(3);
		hb.setLocation("浙江.杭州.下沙222");
		hb.setCoordinate("123,236.3");
		hb.setCreateDate(new Date());
		hb.setStar("5");
		hb.setState("1");
		hb.setBussinessDate(busDate);
		hb.setName("理工对面的");
		map.put("hotel", hb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!editHotel.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryHotels() throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("location", "浙江");
//		map.put("state", "1");
//		map.put("star", "5");
		map.put("star", "一星级");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!queryHotel.action",jsonStr);
		System.out.println(json);
	}
	
}

package com.lq.hotel.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.bean.EmployeeBean;
import com.lq.webUtils.HttpHelper;

public class PersonnelActionTest {

	
	//提供bean对象 以类名作为key
	@Test
	public void addDepartment(){
		//必选参数 name 可选remark
		Map<String,Object> map = new HashMap<String, Object>();
		DepartmentBean db = new DepartmentBean();
		db.setName("水水水水部");
		map.put("department", db);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!addDepartment.action",jsonStr);
		System.out.println(json);
	}
	
	// 按id删除 部门只能一个个删除
	@Test
	public void deleteDepartment(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 3);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!deleteDepartment.action",jsonStr);
		System.out.println(json);
	}
	
	//提供id 获取某一个的信息
	@Test
	public void getDepartment(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!getDepartment.action",jsonStr);
		System.out.println(json);
	}
	
	//提供bean对象 以类名作为key
	@Test
	public void editDepartment(){
		Map<String,Object> map = new HashMap<String, Object>();
		DepartmentBean db = new DepartmentBean();
		db.setId(4);
		db.setName("测试部");
		db.setRemark("22222222222222");
		map.put("department", db);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!editDepartment.action",jsonStr);
		System.out.println(json);
	}
	
	//query一般都是提供非id的字段 组成语句
	@Test
	public void queryDepartments(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "部");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!queryDepartment.action",jsonStr);
		System.out.println(json);
	}
	//==========================================================================================
	@Test
	public void addEmployee() throws ParseException{
		Map<String,Object> map = new HashMap<String, Object>();
		EmployeeBean eb = new EmployeeBean();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = df.parse("2017-02-13");
		//到前端 用modelDriven自定封装
		Integer department_id = 1;
		Integer hotel_id = 1;
		eb.setName("李强");
		eb.setIDCardNo("340702199311272514");
		eb.setMobile("15656295808");
		eb.setPassword("123456");
		eb.setRole("副董事长");
		eb.setState("0");
		String department_str = String.format("%02d", department_id);
		String hotel_str = String.format("%03d", hotel_id);
		eb.setStaffId(hotel_str+department_str);
		eb.setDepartment_id(department_id);
		eb.setHotel_id(hotel_id);
		eb.setWorkDate(new Date());
		map.put("employee", eb);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!addEmployee.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void deteleEmployee(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!deteleEmployee.action",jsonStr);
		System.out.println(json);
	}

	@Test
	public void getEmployee(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", 2);
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!getEmployee.action",jsonStr);
		System.out.println(json);
	}
	
	@Test
	public void queryEmployee(){
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("name","李");
		map.put("hotel_id", "1");
		String jsonStr = JSON.toJSONString(map);
		System.out.println(jsonStr);
		String json =  HttpHelper.sendHttpJsonRequest("http://localhost:8280/hotel-base/personnel!queryEmployee.action",jsonStr);
		System.out.println(json);
	}
}

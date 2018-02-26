package com.lq.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EmployeeBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 自增id
	private String name;// 姓名
	private String password;// 密码
	private String staffId;// 工号
	private String IDCardNo;// 身份证号码
	private String state;// 状态
	private String role;// 角色
	private String mobile;// 手机号码
	private Date workDate;// 入职日期
	
	private String hotelStr;
	private String departmentStr;

	//以下属性需要特殊处理
	private Integer department_id;// 部门
	private Integer hotel_id;// 酒店

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getIDCardNo() {
		return IDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		IDCardNo = iDCardNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getHotel_id() {
		return hotel_id;
	}

	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	
	

	public String getHotelStr() {
		return hotelStr;
	}

	public void setHotelStr(String hotelStr) {
		this.hotelStr = hotelStr;
	}

	public String getDepartmentStr() {
		return departmentStr;
	}

	public void setDepartmentStr(String departmentStr) {
		this.departmentStr = departmentStr;
	}

	public static Map<String, String> statesMap() {
		Map<String, String> map = new HashMap<>();
		map.put("0", "在职");
		map.put("1", "休假");
		map.put("2", "离职");
		return map;
	}

	public static Map<String, String> rolesMap() {
		Map<String, String> map = new HashMap<>();
		map.put("0", "普通员工");
		map.put("1", "部门经理");
		map.put("2", "分店经理");
		map.put("3", "地区经理");
		map.put("4", "总经理");
		map.put("5", "董事长");
		return map;
	}
}

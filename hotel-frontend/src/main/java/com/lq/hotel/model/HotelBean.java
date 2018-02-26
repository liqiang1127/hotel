package com.lq.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HotelBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String location;// (浙江省,杭州市,滨江区,下沙六号大街二十五号路口)省市区以英文逗号分隔
	private String coordinate; // 坐标 可用于调度百度地图api 106.581515,29.615467 x,y
								// 英文,分割
	private String state;// 状态 1正常营业 2尚未营业 3停业整顿 4其他
	private String star;// 星级
	private Date createDate;// 插入这条记录的日期
	private Date bussinessDate;// 开始营业的日期
	private String remark;// 描述
	private String x;
	private String y;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getBussinessDate() {
		return bussinessDate;
	}
	public void setBussinessDate(Date bussinessDate) {
		this.bussinessDate = bussinessDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public static Map<String, String> statesMap() {
		Map<String, String> map = new HashMap<>();
		map.put("0", "建设");
		map.put("1", "营业");
		map.put("2", "停业");
		return map;
	}
	
	public static Map<String, String> starsMap() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "一星级");
		map.put("2", "二星级");
		map.put("3", "三星级");
		map.put("4", "四星级");
		map.put("5", "五星级");
		return map;
	}
	
	
}

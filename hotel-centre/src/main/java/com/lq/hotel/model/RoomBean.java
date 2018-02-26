package com.lq.hotel.model;


public class RoomBean {
	private Integer id; // 客房编号
	private String area; // 朝向朝南朝北 1 north 2 south
	private String floor; // 所属楼层
	private String no; // 房间号
	private String telphone; // 分机电话
	private String state; // 客房状态
	private String available; // 是否可用
	private String picture; // 房间图片 url
	
	public static final int STATE_VACANT = 0; // 空闲状态
	public static final int STATE_RESERVED = 1; // 预定状态
	public static final int STATE_RENTED = 2; // 租用状态
	public static final int STATE_CHECKOUT = 3; // 结账状态
	public static final int STATE_CLEANED = 4; // 清洁状态
	public static final int STATE_BLOCKED = 5; // 锁房状态
	// 关联属性
	private Integer hotel_id; // 房间所属酒店
	private Integer roomKind_id; // 房间类别
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Integer getRoomKind_id() {
		return roomKind_id;
	}
	public void setRoomKind_id(Integer roomKind_id) {
		this.roomKind_id = roomKind_id;
	}
}

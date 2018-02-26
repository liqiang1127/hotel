package com.lq.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Room 客房
 * 
 * @author liqiang
 *
 */

@Entity
@Table(name = "t_room", schema = "hotel")
public class Room {
	private Integer id; // 客房编号
	private String area; // 朝向朝南朝北 1 north 2 south
	private String floor; // 所属楼层
	private String no; // 房间号
	private String telphone; // 分机电话
	private String state; // 客房状态
	private String available; // 是否可用
	private String picture; // 房间图片 url
	// 关联属性
	private Hotel hotel; // 房间所属酒店
	private RoomKind roomKind; // 房间类别

	public static final String[] STATES = { "空闲", "预定", "租用", "结帐", "清洁", "锁房" };// 客房状态数组
	public static final int STATE_VACANT = 0; // 空闲状态
	public static final int STATE_RESERVED = 1; // 预定状态
	public static final int STATE_RENTED = 2; // 租用状态
	public static final int STATE_CHECKOUT = 3; // 结账状态
	public static final int STATE_CLEANED = 4; // 清洁状态
	public static final int STATE_BLOCKED = 5; // 锁房状态

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column
	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	@Column
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	@Column
	public String getPicture() {
		return picture;
	}

	@Column
	public void setPicture(String picture) {
		this.picture = picture;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "hotel_id")
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "roomKind_id")
	public RoomKind getRoomKind() {
		return roomKind;
	}

	public void setRoomKind(RoomKind roomKind) {
		this.roomKind = roomKind;
	}
}

package com.lq.hotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_guest", schema = "hotel")
public class Guest {// 住客只做少量登记
	private Integer id; // 客人编号
	private String name; // 客人姓名
	private String IDCardNo; // 客人证件号码
	private String mobile; // 手机
	private String gender; // 性别
	private String state;//状态 0离店 1活跃 在住店状态
	private Date createTime; // 资料创建时间


	private Hotel hotel; // 客人入住的酒店
	private Room room; // 入住的房间
	private CheckinOrder checkInOrder;//关联的CheckinOrder
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="hotel_id")
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="room_id")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = true)
	public String getIDCardNo() {
		return IDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		IDCardNo = iDCardNo;
	}

	@Column(nullable = true)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="checkInOrder_id")
	public CheckinOrder getCheckInOrder() {
		return checkInOrder;
	}

	public void setCheckInOrder(CheckinOrder checkInOrder) {
		this.checkInOrder = checkInOrder;
	}
	
}

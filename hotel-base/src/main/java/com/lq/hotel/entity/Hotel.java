package com.lq.hotel.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_hotel", schema = "hotel")
public class Hotel {
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

	// 以下是关联属性
	private List<ServiceKind> serviceKinds;
	private List<RoomKind> roomKinds;
	private Key key;

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	@Column
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column
	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getBussinessDate() {
		return bussinessDate;
	}

	public void setBussinessDate(Date bussinessDate) {
		this.bussinessDate = bussinessDate;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "key_id", nullable = true)
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column
	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "Hotel_RoomKind", joinColumns = @JoinColumn(name = "Hotel_id"), inverseJoinColumns = @JoinColumn(name = "RoomKind_id"))
	public List<RoomKind> getRoomKinds() {
		return roomKinds;
	}

	public void setRoomKinds(List<RoomKind> roomKinds) {
		this.roomKinds = roomKinds;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "Hotel_ServiceKind", joinColumns = @JoinColumn(name = "Hotel_id"), inverseJoinColumns = @JoinColumn(name = "ServiceKind_id"))
	public List<ServiceKind> getServiceKinds() {
		return serviceKinds;
	}

	public void setServiceKinds(List<ServiceKind> serviceKinds) {
		this.serviceKinds = serviceKinds;
	}

	
	

}

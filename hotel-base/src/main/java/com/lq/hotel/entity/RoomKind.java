package com.lq.hotel.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_roomKind", schema = "hotel")
public class RoomKind {
	private Integer id; // 客房类型编号
	private String name; // 客房类型名称 双人房 单人房等等
	private Integer bedNumber; // 床位数
	private BigDecimal prePrice; // 预定价格
	private BigDecimal priceOneNight; // 入住价格
	private BigDecimal discount; // 结款折扣（允许设置折扣）
	private BigDecimal perHourPrice; // 计时每小时价（ 钟点房）
	private Integer minHours; // 钟点房的最少入住时间
	private String state;

	//以下是关联属性
	private List<Hotel> hotels;

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
	public Integer getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(Integer bedNumber) {
		this.bedNumber = bedNumber;
	}

	@Column
	public BigDecimal getPrePrice() {
		return prePrice;
	}

	public void setPrePrice(BigDecimal prePrice) {
		this.prePrice = prePrice;
	}

	@Column
	public BigDecimal getPriceOneNight() {
		return priceOneNight;
	}

	public void setPriceOneNight(BigDecimal priceOneNight) {
		this.priceOneNight = priceOneNight;
	}

	@Column
	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column
	public BigDecimal getPerHourPrice() {
		return perHourPrice;
	}

	public void setPerHourPrice(BigDecimal perHourPrice) {
		this.perHourPrice = perHourPrice;
	}

	@Column
	public Integer getMinHours() {
		return minHours;
	}

	public void setMinHours(Integer minHours) {
		this.minHours = minHours;
	}

	@ManyToMany(mappedBy = "roomKinds",fetch = FetchType.LAZY)
	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}

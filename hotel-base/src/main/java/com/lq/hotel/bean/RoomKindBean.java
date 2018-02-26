package com.lq.hotel.bean;

import java.math.BigDecimal;

public class RoomKindBean {
	private Integer id; // 客房类型编号
	private String name; // 客房类型名称 双人房 单人房等等
	private Integer bedNumber; // 床位数
	private BigDecimal prePrice; // 预定价格
	private BigDecimal priceOneNight; // 入住价格
	private BigDecimal discount; // 结款折扣（允许设置折扣）
	private BigDecimal perHourPrice; // 计时每小时价（ 钟点房）
	private Integer minHours; // 钟点房的最少入住时间
	private String state;
	
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
	public Integer getBedNumber() {
		return bedNumber;
	}
	public void setBedNumber(Integer bedNumber) {
		this.bedNumber = bedNumber;
	}
	public BigDecimal getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(BigDecimal prePrice) {
		this.prePrice = prePrice;
	}
	public BigDecimal getPriceOneNight() {
		return priceOneNight;
	}
	public void setPriceOneNight(BigDecimal priceOneNight) {
		this.priceOneNight = priceOneNight;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public BigDecimal getPerHourPrice() {
		return perHourPrice;
	}
	public void setPerHourPrice(BigDecimal perHourPrice) {
		this.perHourPrice = perHourPrice;
	}
	public Integer getMinHours() {
		return minHours;
	}
	public void setMinHours(Integer minHours) {
		this.minHours = minHours;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

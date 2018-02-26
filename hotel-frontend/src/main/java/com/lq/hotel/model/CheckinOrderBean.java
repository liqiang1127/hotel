package com.lq.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CheckinOrderBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; // 入住订单编号
	private Integer guestNumber;// 实际住店人数
	private Date InDate; // 住店日期
	private Date leaveDate; // 离店日期
	private BigDecimal paidMoney; // 已付押金 预订押金会自动算入其中
	private BigDecimal totalCost; // 总费用 用户消费 产生流水 自动统计总费用
	private Date createTime; // 创建时间
	private String state;// 状态 0 生效中 1 已经结账
	private Integer room_id;
	
	private String inDateStr;
	private String leaveDateStr;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGuestNumber() {
		return guestNumber;
	}
	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}
	public Date getInDate() {
		return InDate;
	}
	public void setInDate(Date inDate) {
		InDate = inDate;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public String getInDateStr() {
		return inDateStr;
	}
	public void setInDateStr(String inDateStr) {
		this.inDateStr = inDateStr;
	}
	public String getLeaveDateStr() {
		return leaveDateStr;
	}
	public void setLeaveDateStr(String leaveDateStr) {
		this.leaveDateStr = leaveDateStr;
	}
}

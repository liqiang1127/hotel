package com.lq.hotel.bean;

import java.math.BigDecimal;
import java.util.Date;


public class ReserveOrderBean {
	private Integer id; // 预订单id
	private String resName; // 预订人姓名
	private String resMobile;// 预订人联系电话
	private Integer preNumber;// 预计入住人数
	private Date InDate; // 预计抵店日期
	private Date leaveDate; // 预计离店日期
	private Date arriveTime; // 预计抵店时间
	private String payMethod; // 支付方式 1 现金支付 2 在线支付
	private BigDecimal paidMoney; // 已付押金数
	private String state; // 预定单状态 0 有效状态 1 已入住 作废状态 2 未入住作废 不应该被前台查询 只做数据分析
	private Date createTime; // 预订产生的时间
	private String remark; // 备注
	
	private String roomNo;
	private Integer room_id;
	private Integer roomKind_id;
	private Integer hotel_id;
	private Integer employee_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResMobile() {
		return resMobile;
	}
	public void setResMobile(String resMobile) {
		this.resMobile = resMobile;
	}
	public Integer getPreNumber() {
		return preNumber;
	}
	public void setPreNumber(Integer preNumber) {
		this.preNumber = preNumber;
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
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRoomKind_id() {
		return roomKind_id;
	}
	public void setRoomKind_id(Integer roomKind_id) {
		this.roomKind_id = roomKind_id;
	}
	public Integer getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(Integer hotel_id) {
		this.hotel_id = hotel_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	
}

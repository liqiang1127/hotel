package com.lq.hotel.entity;

import java.math.BigDecimal;
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

/**
 * ReserveOrder 预订单 预订单状态 0 激活 1 取消 2 完成 支付方式 1 现金 2 在线支付
 * 
 * @author liqiang
 *
 */
@Entity
@Table(name = "t_reserveOrder", schema = "hotel")
public class ReserveOrder {
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

	private Employee employee;// 操作员
	private Room room; // 预订的房间 一个预订单只能预订一个房间

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column
	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	@Column
	public String getResMobile() {
		return resMobile;
	}

	public void setResMobile(String resMobile) {
		this.resMobile = resMobile;
	}

	@Column
	public Integer getPreNumber() {
		return preNumber;
	}

	public void setPreNumber(Integer preNumber) {
		this.preNumber = preNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getInDate() {
		return InDate;
	}

	public void setInDate(Date inDate) {
		InDate = inDate;
	}

	@Temporal(TemporalType.DATE)
	@Column
	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column
	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	@Column
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	@Column
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operater_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
package com.lq.hotel.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户 住店期间产生的消费 这个的id 会成为住客消费流水的外键
 * 
 * @author liqiang
 *
 */
@Entity
@Table(name = "t_checkinOrder", schema = "hotel")
public class CheckinOrder {
	private Integer id; // 入住订单编号
	private Integer guestNumber;// 实际住店人数
	private Date InDate; // 住店日期
	private Date leaveDate; // 离店日期
	private BigDecimal paidMoney; // 已付押金 预订押金会自动算入其中
	private BigDecimal totalCost; // 总费用 用户消费 产生流水 自动统计总费用
	private Date createTime; // 创建时间
	private String state;// 状态 0 生效中 1 已经结账

	private Employee employee;// 操作员
	private Room room; // 入住的房间
	
	private List<Guest> guests;
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Column
	public Integer getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getInDate() {
		return InDate;
	}

	public void setInDate(Date inDate) {
		InDate = inDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Column
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	
	@Column
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operater_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@OneToMany(mappedBy = "checkInOrder")
	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}
	
	
}

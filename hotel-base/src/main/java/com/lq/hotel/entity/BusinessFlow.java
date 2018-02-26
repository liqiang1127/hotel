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
 * 记录业务流水
 * 
 * @author liqiang
 *
 */
@Entity
@Table(name = "t_businessFlow", schema = "hotel")
public class BusinessFlow {
	private Integer id;
	private Date createDate;// 创建日期
	private BigDecimal cost;// 费用
	private String remark;// 备注

	private ServiceKind serviceKind;//服务种类
	private Employee employee;// 操作员
	private CheckinOrder checkinOrder;// 对应的入住单 唯一记录一次住房行为

	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
	@Column
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operater_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne
	@JoinColumn(name = "checkinOrder_id")
	public CheckinOrder getCheckinOrder() {
		return checkinOrder;
	}

	public void setCheckinOrder(CheckinOrder checkinOrder) {
		this.checkinOrder = checkinOrder;
	}

	@ManyToOne
	@JoinColumn(name = "serviceKind_id")
	public ServiceKind getServiceKind() {
		return serviceKind;
	}

	public void setServiceKind(ServiceKind serviceKind) {
		this.serviceKind = serviceKind;
	}

	
}

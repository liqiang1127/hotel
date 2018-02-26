package com.lq.hotel.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSON;

public class BusinessFlowBean {
	private Integer id;
	private Date createDate;// 创建日期
	private BigDecimal cost;// 费用
	private String remark;// 备注
	
	private Integer checkinOrder_id ;
	private Integer serviceKind_id;
	private Integer employee_id ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCheckinOrder_id() {
		return checkinOrder_id;
	}
	public void setCheckinOrder_id(Integer checkinOrder_id) {
		this.checkinOrder_id = checkinOrder_id;
	}
	public Integer getServiceKind_id() {
		return serviceKind_id;
	}
	public void setServiceKind_id(Integer serviceKind_id) {
		this.serviceKind_id = serviceKind_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
	
}

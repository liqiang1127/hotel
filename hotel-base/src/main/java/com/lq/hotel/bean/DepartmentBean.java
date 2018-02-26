package com.lq.hotel.bean;


public class DepartmentBean {
	private Integer id;   	//部门id
	private String name;  	//部门名称	
	private String remark;	//备注
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.lq.hotel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CooperationBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id; //id
	private String name;  //企业名称
	private Date createDate; 	//	合作签署时间
	private Date endDate;	//合作结束时间
	private String state;	//合作状态
	private String remark; // 备注
	private String createDstr;
	private String endDstr;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getCreateDstr() {
		return createDstr;
	}
	public void setCreateDstr(String createDstr) {
		this.createDstr = createDstr;
	}
	public String getEndDstr() {
		return endDstr;
	}
	public void setEndDstr(String endDstr) {
		this.endDstr = endDstr;
	}
	public static Map<String, String> statesMap() {
		Map<String, String> map = new HashMap<>();
		map.put("0", "正常");
		map.put("1", "挂起");
		return map;
	}
}

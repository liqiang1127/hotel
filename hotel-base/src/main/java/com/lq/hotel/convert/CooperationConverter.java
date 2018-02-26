package com.lq.hotel.convert;


import com.lq.hotel.bean.CooperationBean;
import com.lq.hotel.entity.Cooperation;

public class CooperationConverter {
	/*
	 * private Integer id; //id
	private String name;  //企业名称
	private Date createDate; 	//	合作签署时间
	private Date endDate;	//合作结束时间
	private String state;	//合作状态
	private String rmark; // 备注
	 */
	public static CooperationBean convert2Bean(Cooperation c){
		CooperationBean cb = new CooperationBean();
		cb.setId(c.getId());
		cb.setName(c.getName());
		cb.setCreateDate(c.getCreateDate());
		cb.setEndDate(c.getEndDate());
		cb.setState(c.getState());
		cb.setRemark(c.getRemark());
		return cb;
	}
	
	public static Cooperation convert2Entity(CooperationBean cb){
		Cooperation c = new Cooperation();
		c.setId(cb.getId());
		c.setName(cb.getName());
		c.setCreateDate(cb.getCreateDate());
		c.setEndDate(cb.getEndDate());
		c.setState(cb.getState());
		c.setRemark(cb.getRemark());
		return c;
		
	}
}

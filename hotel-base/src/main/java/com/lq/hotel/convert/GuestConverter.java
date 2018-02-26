package com.lq.hotel.convert;

import java.util.Date;

import com.lq.hotel.bean.GuestBean;
import com.lq.hotel.entity.Guest;

public class GuestConverter {
//	private Integer id; // 客人编号
//	private String name; // 客人姓名
//	private String IDCardNo; // 客人证件号码
//	private String mobile; // 手机
//	private String gender; // 性别
//	private String state;//状态 0离店 1活跃 在住店状态
//	private Date createTime; // 资料创建时间
	
	public static GuestBean convert2Bean(Guest g){
		GuestBean gb = new GuestBean();
		gb.setId(g.getId());
		gb.setCreateTime(g.getCreateTime());
		gb.setGender(g.getGender());
		gb.setIDCardNo(g.getIDCardNo());
		gb.setMobile(g.getMobile());
		gb.setName(g.getName());
		gb.setState(g.getState());
		return gb;
	}
	
	public static Guest convert2Entity(GuestBean gb){
		Guest g = new Guest();
		g.setId(gb.getId());
		g.setCreateTime(gb.getCreateTime());
		g.setGender(gb.getGender());
		g.setIDCardNo(gb.getIDCardNo());
		g.setMobile(gb.getMobile());
		g.setName(gb.getName());
		g.setState(gb.getState());
		return g;
	}
}

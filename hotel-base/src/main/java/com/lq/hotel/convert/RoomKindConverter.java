package com.lq.hotel.convert;

import java.math.BigDecimal;

import com.lq.hotel.bean.RoomKindBean;
import com.lq.hotel.entity.RoomKind;

public class RoomKindConverter {
	/*
	 * 	private Integer id; // 客房类型编号
	private String name; // 客房类型名称 双人房 单人房等等
	private Integer bedNumber; // 床位数
	private BigDecimal prePrice; // 预定价格
	private BigDecimal priceOneNight; // 入住价格
	private BigDecimal discount; // 结款折扣（允许设置折扣）
	private BigDecimal perHourPrice; // 计时每小时价（ 钟点房）
	private Integer minHours; // 钟点房的最少入住时间
	private String state;//状态
	 */
	public static RoomKind convert2Entity(RoomKindBean rkb){
		RoomKind rk = new RoomKind();
		rk.setId(rkb.getId());
		rk.setBedNumber(rkb.getBedNumber());
		rk.setDiscount(rkb.getDiscount());
		rk.setMinHours(rkb.getMinHours());
		rk.setName(rkb.getName());
		rk.setPerHourPrice(rkb.getPerHourPrice());
		rk.setPrePrice(rkb.getPrePrice());
		rk.setPriceOneNight(rkb.getPriceOneNight());
		rk.setState(rkb.getState());
		return rk;
	}

	public static RoomKindBean convert2Bean(RoomKind rk){
		RoomKindBean rkb = new RoomKindBean();
		rkb.setId(rk.getId());
		rkb.setBedNumber(rk.getBedNumber());
		rkb.setDiscount(rk.getDiscount());
		rkb.setMinHours(rk.getMinHours());
		rkb.setName(rk.getName());
		rkb.setPerHourPrice(rk.getPerHourPrice());
		rkb.setPrePrice(rk.getPrePrice());
		rkb.setPriceOneNight(rk.getPerHourPrice());
		rkb.setState(rk.getState());
		return rkb;
	}

}

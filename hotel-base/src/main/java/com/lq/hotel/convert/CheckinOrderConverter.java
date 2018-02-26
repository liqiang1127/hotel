package com.lq.hotel.convert;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.hotel.bean.CheckinOrderBean;
import com.lq.hotel.entity.CheckinOrder;

public class CheckinOrderConverter {
	//	private Integer id; // 入住订单编号
	//	private Integer guestNumber;// 实际住店人数
	//	private Date InDate; // 住店日期
	//	private Date leaveDate; // 离店日期
	//	private BigDecimal paidMoney; // 已付押金 预订押金会自动算入其中
	//	private BigDecimal totalCost; // 总费用 用户消费 产生流水 自动统计总费用
	//	private Date createTime; // 创建时间
	//	private String state;// 状态 0 生效中 1 已经结账
	public static CheckinOrder convert2Entity(CheckinOrderBean cob){
		CheckinOrder co = new CheckinOrder();
		co.setCreateTime(cob.getCreateTime());
		co.setGuestNumber(cob.getGuestNumber());
		co.setId(cob.getId());
		co.setInDate(cob.getInDate());
		co.setLeaveDate(cob.getLeaveDate());
		co.setPaidMoney(cob.getPaidMoney());
		co.setState(cob.getState());
		co.setTotalCost(cob.getTotalCost());
		return co;
	}
	
	public static CheckinOrderBean convert2Bean(CheckinOrder co){
		CheckinOrderBean cob = new CheckinOrderBean();
		cob.setCreateTime(co.getCreateTime());
		cob.setGuestNumber(co.getGuestNumber());
		cob.setId(co.getId());
		cob.setInDate(co.getInDate());
		cob.setLeaveDate(co.getLeaveDate());
		cob.setPaidMoney(co.getPaidMoney());
		cob.setState(co.getState());
		cob.setTotalCost(co.getTotalCost());
		return cob;
	}
}

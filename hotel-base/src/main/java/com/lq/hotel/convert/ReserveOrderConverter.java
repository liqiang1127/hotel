package com.lq.hotel.convert;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.hotel.bean.ReserveOrderBean;
import com.lq.hotel.entity.ReserveOrder;

public class ReserveOrderConverter {
/*
 * 	private Integer id; // 预订单id
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
 */
	
	public static ReserveOrder convert2Entity(ReserveOrderBean rob){
		ReserveOrder ro = new ReserveOrder();
		ro.setArriveTime(rob.getArriveTime());
		ro.setCreateTime(rob.getCreateTime());
		ro.setId(rob.getId());
		ro.setInDate(rob.getInDate());
		ro.setLeaveDate(rob.getLeaveDate());
		ro.setPaidMoney(rob.getPaidMoney());
		ro.setPayMethod(rob.getPayMethod());
		ro.setPreNumber(rob.getPreNumber());
		ro.setRemark(rob.getRemark());
		ro.setResMobile(rob.getResMobile());
		ro.setResName(rob.getResName());
		ro.setState(rob.getState());
		return ro;
	}
	
	public static ReserveOrderBean convert2Bean(ReserveOrder ro){
		ReserveOrderBean rob = new ReserveOrderBean();
		rob.setArriveTime(ro.getArriveTime());
		rob.setCreateTime(ro.getCreateTime());
		rob.setId(ro.getId());
		rob.setInDate(ro.getInDate());
		rob.setLeaveDate(ro.getLeaveDate());
		rob.setPaidMoney(ro.getPaidMoney());
		rob.setPayMethod(ro.getPayMethod());
		rob.setPreNumber(ro.getPreNumber());
		rob.setRemark(ro.getRemark());
		rob.setResMobile(ro.getResMobile());
		rob.setResName(ro.getResName());
		rob.setState(ro.getState());
		return rob;
	}
	
	
}

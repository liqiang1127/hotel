package com.lq.hotel.convert;

import java.math.BigDecimal;
import java.util.Date;

import com.lq.hotel.bean.BusinessFlowBean;
import com.lq.hotel.entity.BusinessFlow;

public class BusinessFlowConverter {
//	private Integer id;
//	private Date createDate;// 创建日期
//	private BigDecimal cost;// 费用
//	private String remark;// 备注
	
	public static BusinessFlowBean convert2Bean(BusinessFlow bf){
		BusinessFlowBean bfb = new BusinessFlowBean();
		bfb.setCost(bf.getCost());
		bfb.setCreateDate(bf.getCreateDate());
		bfb.setId(bf.getId());
		bfb.setRemark(bf.getRemark());
		return bfb;
	}
	
	public static BusinessFlow convert2Entity(BusinessFlowBean bfb){
		BusinessFlow bf = new BusinessFlow();
		bf.setCost(bfb.getCost());
		bf.setCreateDate(bfb.getCreateDate());
		bf.setId(bfb.getId());
		bf.setRemark(bfb.getRemark());
		return bf;
	}
}

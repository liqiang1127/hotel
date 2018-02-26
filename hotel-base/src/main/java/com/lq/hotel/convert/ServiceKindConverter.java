package com.lq.hotel.convert;

import com.lq.hotel.bean.ServiceKindBean;
import com.lq.hotel.entity.ServiceKind;

public class ServiceKindConverter {
	public static ServiceKind convert2Entity(ServiceKindBean skb){
		ServiceKind sk = new ServiceKind();
		sk.setCost(skb.getCost());
		sk.setId(skb.getId());
		sk.setName(skb.getName());
		sk.setRemark(skb.getRemark());
		return sk;
	}
	
	public static ServiceKindBean convert2Bean(ServiceKind sk){
		ServiceKindBean skb = new ServiceKindBean();
		skb.setCost(sk.getCost());
		skb.setId(sk.getId());
		skb.setName(sk.getName());
		skb.setRemark(sk.getRemark());
		return skb;
	}
}

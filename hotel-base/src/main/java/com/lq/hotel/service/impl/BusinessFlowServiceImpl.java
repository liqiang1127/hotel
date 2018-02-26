package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.BusinessFlowDao;
import com.lq.hotel.entity.BusinessFlow;
import com.lq.hotel.service.BusinessFlowService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("businessFlowService")
public class BusinessFlowServiceImpl extends BaseServiceImpl<BusinessFlow> implements BusinessFlowService{
	private BusinessFlowDao businessFlowDao;
	@Resource
	public void setBusinessFlowDao(BusinessFlowDao businessFlowDao){
		setBaseDao(businessFlowDao);
		this.businessFlowDao = businessFlowDao;
	}

}

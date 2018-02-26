package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.CheckinDao;
import com.lq.hotel.entity.CheckinOrder;
import com.lq.hotel.service.CheckinService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("checkinService")
public class CheckinServiceImpl extends BaseServiceImpl<CheckinOrder> implements CheckinService{
	private CheckinDao checkinDao;
	
	@Resource
	public void setCheckinDao(CheckinDao checkinDao){
		setBaseDao(checkinDao);
		this.checkinDao = checkinDao;
	}
}

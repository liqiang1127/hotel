package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.ReserveDao;
import com.lq.hotel.entity.ReserveOrder;
import com.lq.hotel.service.ReserveService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("reserveService")
public class ReserveServiceImpl extends BaseServiceImpl<ReserveOrder> implements ReserveService{
	private ReserveDao reserveDao;
	
	@Resource
	public void setReserveDao(ReserveDao reserveDao){
		setBaseDao(reserveDao);
		this.reserveDao = reserveDao;
	}

}

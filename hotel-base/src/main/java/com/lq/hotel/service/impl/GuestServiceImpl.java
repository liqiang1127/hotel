package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.GuestDao;
import com.lq.hotel.entity.Guest;
import com.lq.hotel.service.GuestService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("guestService")
public class GuestServiceImpl extends BaseServiceImpl<Guest> implements GuestService{
	private GuestDao guestDao;
	
	@Resource
	public void setGuestDao(GuestDao guestDao){
		setBaseDao(guestDao);
		this.guestDao = guestDao;
	}
}

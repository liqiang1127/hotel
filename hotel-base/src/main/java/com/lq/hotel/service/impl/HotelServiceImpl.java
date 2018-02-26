package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.HotelDao;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.service.HotelService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("hotelService")
public class HotelServiceImpl extends BaseServiceImpl<Hotel> implements HotelService{
	private HotelDao hotelDao;
	
	@Resource
	public void setHotelDao(HotelDao hotelDao){
		setBaseDao(hotelDao);
		this.hotelDao = hotelDao;
	}

}

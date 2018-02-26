package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.HotelDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Hotel;

@Repository("hotelDao")
public class HotelDaoImpl extends BaseDaoImpl<Hotel> implements HotelDao{

}

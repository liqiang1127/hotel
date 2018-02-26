package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.GuestDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Guest;

@Repository("guestDao")
public class GuestDaoImpl extends BaseDaoImpl<Guest> implements GuestDao{

}

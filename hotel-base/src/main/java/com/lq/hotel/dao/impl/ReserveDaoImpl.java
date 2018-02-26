package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.ReserveDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.ReserveOrder;

@Repository("reserveDao")
public class ReserveDaoImpl extends BaseDaoImpl<ReserveOrder> implements ReserveDao{
	
}

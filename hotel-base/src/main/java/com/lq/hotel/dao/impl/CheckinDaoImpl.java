package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.CheckinDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.CheckinOrder;

@Repository("checkinDao")
public class CheckinDaoImpl extends BaseDaoImpl<CheckinOrder> implements CheckinDao{

}

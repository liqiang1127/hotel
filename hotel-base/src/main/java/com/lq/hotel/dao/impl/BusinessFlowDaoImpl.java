package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.BusinessFlowDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.BusinessFlow;

@Repository("businessFlowDao")
public class BusinessFlowDaoImpl extends BaseDaoImpl<BusinessFlow> implements BusinessFlowDao{

}

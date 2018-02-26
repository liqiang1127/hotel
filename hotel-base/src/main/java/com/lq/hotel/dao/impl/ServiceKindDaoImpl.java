package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.ServiceKindDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.ServiceKind;

@Repository("serviceKindDao")
public class ServiceKindDaoImpl extends BaseDaoImpl<ServiceKind> implements ServiceKindDao{

}

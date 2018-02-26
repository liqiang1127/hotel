package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.ServiceKindDao;
import com.lq.hotel.entity.ServiceKind;
import com.lq.hotel.service.ServiceKindService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("serviceKindService")
public class ServiceKindServiceImpl extends BaseServiceImpl<ServiceKind> implements ServiceKindService{
	private ServiceKindDao serviceKindDao;
	
	@Resource
	public void setServiceKindDao(ServiceKindDao serviceKindDao){
		setBaseDao(serviceKindDao);
		this.serviceKindDao = serviceKindDao;
	}
	
}

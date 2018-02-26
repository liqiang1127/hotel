package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.CooperationDao;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.service.CooperationService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("cooperationService")
public class CooperationServiceImpl extends BaseServiceImpl<Cooperation> implements CooperationService{
	private CooperationDao cooperationDao;
	@Resource
	public void setCooperationDao(CooperationDao cooperationDao){
		setBaseDao(cooperationDao);
		this.cooperationDao = cooperationDao;
	}
}

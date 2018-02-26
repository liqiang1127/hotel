package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.KeyDao;
import com.lq.hotel.entity.Key;
import com.lq.hotel.service.KeyService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("keyService")
public class KeyServiceImpl extends BaseServiceImpl<Key> implements KeyService{
	private KeyDao keyDao;
	
	@Resource
	public void setKeyDao(KeyDao keyDao){
		setBaseDao(keyDao);
		this.keyDao = keyDao;
	}
}

package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.RoomKindDao;
import com.lq.hotel.entity.RoomKind;
import com.lq.hotel.service.RoomKindService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("roomKindService")
public class RoomKindServiceImpl extends BaseServiceImpl<RoomKind> implements RoomKindService{
	private RoomKindDao roomKindDao;
	
	@Resource
	public void setRoomKindDao(RoomKindDao roomKindDao){
		setBaseDao(roomKindDao);
		this.roomKindDao = roomKindDao;
	}
}

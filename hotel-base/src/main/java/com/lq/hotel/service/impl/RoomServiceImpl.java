package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.RoomDao;
import com.lq.hotel.entity.Room;
import com.lq.hotel.service.RoomService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("roomService")
public class RoomServiceImpl extends BaseServiceImpl<Room> implements RoomService{
	private RoomDao roomDao;
	
	@Resource
	public void setRoomDao(RoomDao roomDao){
		setBaseDao(roomDao);
		this.roomDao = roomDao;
	}
}

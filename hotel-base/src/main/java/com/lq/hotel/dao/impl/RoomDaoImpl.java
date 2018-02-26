package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.RoomDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Room;

@Repository("roomDao")
public class RoomDaoImpl extends BaseDaoImpl<Room> implements RoomDao{

}

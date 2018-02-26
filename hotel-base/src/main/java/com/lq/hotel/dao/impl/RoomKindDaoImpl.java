package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.RoomKindDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.RoomKind;

@Repository("roomKindDao")
public class RoomKindDaoImpl extends BaseDaoImpl<RoomKind> implements RoomKindDao{

}

package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.KeyDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Key;

@Repository("keyDao")
public class KeyDaoImpl extends BaseDaoImpl<Key> implements KeyDao{

}

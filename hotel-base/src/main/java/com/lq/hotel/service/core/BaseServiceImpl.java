package com.lq.hotel.service.core;

import java.io.Serializable;
import java.util.List;

import com.lq.hotel.dao.core.BaseDao;
import com.lq.webUtils.PageResult;
import com.lq.webUtils.QueryHelper;


public class BaseServiceImpl <T> implements BaseService<T>{
	private BaseDao<T> baseDao;
	
	//设置baseDao
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	public Serializable save(T entity) {
		return baseDao.save(entity);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	public List<T> getAll() {
		return baseDao.getAll();
	}

	public T getObjectById(Serializable id) {
		return baseDao.getObjectById(id);
	}



	public List<T> getAll(QueryHelper helper) {
		return baseDao.getAll(helper);
	}
	
	public PageResult getPageRestult(QueryHelper helper, int pageNumber, int pageSize){
		return baseDao.getPageRestult(helper, pageNumber, pageSize);
	}

	//得到总数
	public int getObjectCount(QueryHelper helper){
		return baseDao.getObjectCount(helper);
	}

	public List<T> getByIds(Integer[] ids) {
		return baseDao.getByIds(ids);
	}
}

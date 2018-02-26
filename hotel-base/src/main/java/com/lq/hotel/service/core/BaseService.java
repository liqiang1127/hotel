package com.lq.hotel.service.core;

import java.io.Serializable;
import java.util.List;

import com.lq.webUtils.PageResult;
import com.lq.webUtils.QueryHelper;


public interface BaseService <T>{
	Serializable save(T entity);

	public void update(T entity);

	public void delete(Serializable id);
	
	public List<T> getByIds(Integer[] ids);

	public List<T> getAll();

	public T getObjectById(Serializable id);
	
	public List<T> getAll(QueryHelper helper);
	
	public PageResult getPageRestult(QueryHelper helper, int pageNumber, int pageSize);
	//得到总数
	public int getObjectCount(QueryHelper helper);

}

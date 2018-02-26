package com.lq.hotel.dao.core;

import java.io.Serializable;
import java.util.List;

import com.lq.webUtils.PageResult;
import com.lq.webUtils.QueryHelper;

public interface BaseDao<T> {
	// 新增
	public Serializable save(T t);

	// 更新
	public void update(T t);

	public List<T> getByIds(Integer[] ids);

	// 删除
	public void delete(Serializable id);

	// 得到所有
	public List<T> getAll();

	// 根据id查找
	public T getObjectById(Serializable id);

	// 搜索
	public List<T> getAll(QueryHelper helper);

	// 分页
	public PageResult getPageRestult(QueryHelper helper, int pageNumber, int pageSize);

	// 得到总数
	public int getObjectCount(QueryHelper helper);
}

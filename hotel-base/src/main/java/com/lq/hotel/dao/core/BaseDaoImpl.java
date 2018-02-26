package com.lq.hotel.dao.core;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.lq.webUtils.PageResult;
import com.lq.webUtils.QueryHelper;

@SuppressWarnings(value="unchecked")
public class BaseDaoImpl<T>implements BaseDao<T> {
	private Class<T> clazz;
	
	
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory =sessionFactory; 
	}

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	public Serializable save(T t) {
		return sessionFactory.getCurrentSession().save(t);
	}

	public void update(T t) {
		this.sessionFactory.getCurrentSession().update(t);
	}

	public void delete(Serializable id) {
		this.sessionFactory.getCurrentSession().delete(this.getObjectById(id));
	}

	public List<T> getAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from " + clazz.getSimpleName()).list();
	}


	public T getObjectById(Serializable id) {
		return (T) this.sessionFactory.getCurrentSession().get(clazz, id);
	}

	/**
	 * 根据数组ids 找到一组对象
	 * 
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Integer[] ids) {
		if (ids != null && ids.length > 0) {
			return this.sessionFactory.getCurrentSession().createQuery("FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")
					.setParameterList("ids", ids).list();
		}
		return null;
	}

	// 搜索
	public List<T> getAll(QueryHelper helper) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(helper.getHql());
		if (helper.getParameters() != null) {
			for (int i = 0; i < helper.getParameters().size(); i++) {
				query.setParameter(i+"", helper.getParameters().get(i));
			}
		}
		return query.list();
	}

	public PageResult getPageRestult(QueryHelper helper, int pageNumber, int pageSize) {
		Query query = this.sessionFactory.getCurrentSession().createQuery(helper.getHql());
		if (helper.getParameters() != null) {
			for (int i = 0; i < helper.getParameters().size(); i++) {
				query.setParameter(i+"", helper.getParameters().get(i));
			}
		}
		if (pageNumber < 1)
			pageNumber = 1;
		if (pageSize < 1)
			pageSize = 3;
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		List items = query.list();
		Query queryCount = this.sessionFactory.getCurrentSession().createQuery(helper.getCountHql());
		if (helper.getParameters() != null) {
			for (int i = 0; i < helper.getParameters().size(); i++) {
				queryCount.setParameter(i+"", helper.getParameters().get(i));
			}
		}
		long totalCount = (Long) queryCount.uniqueResult();
		PageResult pr = new PageResult(totalCount, pageNumber, pageSize, items);
		return pr;
	}

	public int getObjectCount(QueryHelper helper) {
		Query queryCount = this.sessionFactory.getCurrentSession().createQuery(helper.getCountHql());
		if (helper.getParameters() != null) {
			for (int i = 0; i < helper.getParameters().size(); i++) {
				queryCount.setParameter(i+"", helper.getParameters().get(i));
			}
		}
		long totalCount = (Long) queryCount.uniqueResult();
		return (int) totalCount;
	}
}

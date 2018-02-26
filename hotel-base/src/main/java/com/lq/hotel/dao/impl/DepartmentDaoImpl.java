package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.DepartmentDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{
	
}

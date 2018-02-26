package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.DepartmentDao;
import com.lq.hotel.entity.Department;
import com.lq.hotel.service.DepartmentService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService{
	
	private DepartmentDao departmentDao ;
	
	@Resource
	public void setDepartmentDao(DepartmentDao departmentDao) {
		//注入dao
		setBaseDao(departmentDao);
		this.departmentDao = departmentDao;
	}
}

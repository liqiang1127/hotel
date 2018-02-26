package com.lq.hotel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.hotel.dao.EmployeeDao;
import com.lq.hotel.entity.Employee;
import com.lq.hotel.service.EmployeeService;
import com.lq.hotel.service.core.BaseServiceImpl;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements EmployeeService{
	private EmployeeDao employeeDao;
	@Resource
	public void setEmployeeDao(EmployeeDao employeeDao){
		setBaseDao(employeeDao);
		this.employeeDao = employeeDao;
	}
}

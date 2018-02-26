package com.lq.hotel.dao.impl;

import org.springframework.stereotype.Repository;

import com.lq.hotel.dao.EmployeeDao;
import com.lq.hotel.dao.core.BaseDaoImpl;
import com.lq.hotel.entity.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements EmployeeDao{

}

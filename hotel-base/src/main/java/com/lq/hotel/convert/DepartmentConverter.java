package com.lq.hotel.convert;

import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.entity.Department;

public class DepartmentConverter {
	//相互转化的类
	public static DepartmentBean convert2Bean(Department de){
		DepartmentBean db = new DepartmentBean();
		db.setId(de.getId());
		db.setName(de.getName());
		db.setRemark(de.getRemark());
		return db;
	}
	
	public static Department convert2Entity(DepartmentBean db){
		Department de = new Department();
		de.setId(db.getId());
		de.setName(db.getName());
		de.setRemark(db.getRemark());
		return de;
	}
}

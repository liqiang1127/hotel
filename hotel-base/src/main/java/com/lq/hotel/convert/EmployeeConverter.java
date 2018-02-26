package com.lq.hotel.convert;

import java.util.Date;

import com.lq.hotel.bean.EmployeeBean;
import com.lq.hotel.entity.Employee;

public class EmployeeConverter {
//	private Integer id;// 自增id
//	private String name;// 姓名
//	private String password;// 密码
//	private String staffId;// 工号
//	private String IDCardNo;// 身份证号码
//	private String state;// 状态
//	private String role;// 角色
//	private String mobile;// 手机号码
//	private Date workDate;// 入职日期
//
//	//以下属性需要特殊处理
//	private Integer department_id;// 部门
//	private Integer hotel_id;// 酒店
	
	public	static Employee convert2Entity(EmployeeBean eb){
		Employee e = new Employee();
		e.setId(eb.getId());
		e.setIDCardNo(eb.getIDCardNo());
		e.setMobile(eb.getMobile());
		e.setName(eb.getName());
		e.setPassword(eb.getPassword());
		e.setRole(eb.getRole());
		e.setStaffId(eb.getStaffId());
		e.setState(eb.getState());
		e.setWorkDate(eb.getWorkDate());
		return e;
	}
	
	public	static EmployeeBean convert2Bean(Employee e){
		EmployeeBean eb = new EmployeeBean();
		eb.setId(e.getId());
		eb.setIDCardNo(e.getIDCardNo());
		eb.setMobile(e.getMobile());
		eb.setName(e.getName());
		eb.setPassword(e.getPassword());
		eb.setRole(e.getRole());
		eb.setStaffId(e.getStaffId());
		eb.setState(e.getState());
		eb.setWorkDate(e.getWorkDate());
		return eb;
	}

}

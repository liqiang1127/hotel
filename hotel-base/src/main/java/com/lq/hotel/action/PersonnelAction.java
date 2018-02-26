package com.lq.hotel.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.bean.EmployeeBean;
import com.lq.hotel.convert.DepartmentConverter;
import com.lq.hotel.convert.EmployeeConverter;
import com.lq.hotel.entity.Department;
import com.lq.hotel.entity.Employee;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.PasswordEncoder;
import com.lq.webUtils.QueryHelper;

/**
 * 人事管理的类 
 * 部门增删改查 
 * 员工的增删改查
 * 
 * @author liqiang
 *
 */
@ParentPackage("abstract-struts")
@Action(value = "personnel") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class PersonnelAction extends BaseAction {
	// ==============================================================================================
	// 新增部门传入参数 departmentBean ===>
	public String addDepartment() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			DepartmentBean db = JSON.parseObject(JSONMap.get("department").toString(), DepartmentBean.class);
			Department department = DepartmentConverter.convert2Entity(db);
			// 查找该部门是不是已经被添加了
			QueryHelper queryHelper = new QueryHelper(Department.class, "d");
			queryHelper.addCondition("d.name = ?0", department.getName());
			Integer count = departmentService.getObjectCount(queryHelper);
			if (count == 0) {
				// 没有就添加
				departmentService.save(department);
				resMap.put("errorNo", 0);
			} else {
				// 已存在就返回错误
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	// 编辑部门 传入参数department
	public String editDepartment() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			DepartmentBean db = JSON.parseObject(JSONMap.get("department").toString(), DepartmentBean.class);
			Department department = DepartmentConverter.convert2Entity(db);
			// 查找数据库里面已经有的部门
			Department departmentIn = departmentService.getObjectById(department.getId());
			if(departmentIn!=null){
				if (departmentIn.getName().equals(department.getName())) {
					// 名字没改变直接更新
					departmentService.update(department);
					resMap.put("errorNo", 0);
				} else {
					// 更新了名字 查找一下是不是已经有了
					QueryHelper queryHelper = new QueryHelper(Department.class, "d");
					queryHelper.addCondition("d.name = ?0", department.getName());
					Integer count = departmentService.getObjectCount(queryHelper);
					if (count == 0) {
						// 不存在这个名字就更新
						departmentService.update(department);
						resMap.put("errorNo", 0);
					} else {
						// 更新的名字重复了
						resMap.put("errorNo", ErrorConstant.exist.getCode());
						resMap.put("errorInfo", ErrorConstant.exist.getInfo());
					}
				}
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());

		}
		return SUCCESS;
	}

	// 通过id删除部门 传入参数 id
	public String deleteDepartment() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			Department department = departmentService.getObjectById(id);
			Integer count = department.getEmployees().size();
			if (count > 0) {
				resMap.put("errorNo", ErrorConstant.department_contain_employee.getCode());
				resMap.put("errorInfo", ErrorConstant.department_contain_employee.getInfo());
			} else {
				// 该部门没人 就删
				departmentService.delete(id);
				resMap.put("errorNo", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	// 查找部门传入参数 name 返回items 支持模糊查询
	public String queryDepartment() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String name = JSONMap.get("name") == null ? "" : JSONMap.get("name").toString();
			QueryHelper queryHelper = new QueryHelper(Department.class, "d");
			String queryCondition = "%" + name + "%";
			queryHelper.addCondition("d.name like ?0", queryCondition);
			List<Department> des = departmentService.getAll(queryHelper);
			List<DepartmentBean> deBeans = new ArrayList<>();
			for (Department d : des) {
				deBeans.add(DepartmentConverter.convert2Bean(d));
			}
			resMap.put("count", deBeans.size());
			resMap.put("errorNo", 0);
			resMap.put("items", deBeans);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}

	// 根据id 获取department 返回一个department
	public String getDepartment() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			Department department = departmentService.getObjectById(id);
			if (department != null) {
				DepartmentBean db = DepartmentConverter.convert2Bean(department);
				resMap.put("errorNo", 0);
				resMap.put("item", db);
			} else {
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	// ==========================================================================================================
	// 新增员工
	public String addEmployee() {
		/*
		 * private Integer id;// 自增id private String name;// 姓名 private String
		 * password;// 密码 private String ;// 工号 private String IDCardNo;// 身份证号码
		 * private String status;// 状态 private String role;// 角色 private String
		 * mobile;// 手机号码 private Date workDate;// 入职日期 private Department
		 * department;// 部门 private Hotel hotel;// 酒店
		 */
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			EmployeeBean eb = JSON.parseObject(JSONMap.get("employee").toString(), EmployeeBean.class);
			Employee e = EmployeeConverter.convert2Entity(eb);
			String hotel_id = eb.getHotel_id() == null ? "" : eb.getHotel_id().toString();
			String department_id = eb.getDepartment_id() == null ? "" : eb.getDepartment_id().toString();
			e.setHotel(hotelService.getObjectById(Integer.parseInt(hotel_id)));
			e.setDepartment(departmentService.getObjectById(Integer.parseInt(department_id)));
			Integer id = (int) employeeService.save(e);
			String staffid = e.getStaffId() + String.format("%03d", id);
			e.setStaffId(staffid);
			e.setPassword(PasswordEncoder.passwordEncode("000000"));
			employeeService.update(e);
			resMap.put("errorNo", 0);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}

		return SUCCESS;
	}

	// 修改员工的信息
	public String editEmployee() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			EmployeeBean eb = JSON.parseObject(JSONMap.get("employee").toString(), EmployeeBean.class);
			Employee e = EmployeeConverter.convert2Entity(eb);
			String hotel_id = eb.getHotel_id() == null ? "" : eb.getHotel_id().toString();
			String department_id = eb.getDepartment_id() == null ? "" : eb.getDepartment_id().toString();
			e.setHotel(hotelService.getObjectById(Integer.parseInt(hotel_id)));
			e.setDepartment(departmentService.getObjectById(Integer.parseInt(department_id)));
			employeeService.update(e);
			resMap.put("errorNo", 0);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	public String deteleEmployee() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			if (employeeService.getObjectById(id) != null) {
				employeeService.delete(id);
				resMap.put("errorNo", 0);
			} else {
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	public String getEmployee() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			Employee e = employeeService.getObjectById(id);
			if (e != null) {
				EmployeeBean eb = EmployeeConverter.convert2Bean(e);
				eb.setHotel_id(e.getHotel().getId());
				eb.setDepartment_id(e.getDepartment().getId());
				resMap.put("errorNo", 0);
				resMap.put("item", eb);
			} else {
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}

	// private Integer id;// 自增id
	// private String name;// 姓名
	// private String password;// 密码
	// private String staffId;// 工号
	// private String IDCardNo;// 身份证号码
	// private String state;// 状态
	// private String role;// 角色
	// private String mobile;// 手机号码
	// private Date workDate;// 入职日期
	public String queryEmployee() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String name = JSONMap.get("name") == null ? "" : JSONMap.get("name").toString();
			String staffId = JSONMap.get("staffId") == null ? "" : JSONMap.get("staffId").toString();
			String IDCardNo = JSONMap.get("IDCardNo") == null ? "" : JSONMap.get("IDCardNo").toString();
			String state = JSONMap.get("state") == null ? "" : JSONMap.get("state").toString();
			String role = JSONMap.get("role") == null ? "" : JSONMap.get("role").toString();
			String hotel_id = JSONMap.get("hotel_id") == null ? "" : JSONMap.get("hotel_id").toString();
			String department_id = JSONMap.get("department_id") == null ? "" : JSONMap.get("department_id").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if (!"".equals(name))
				condition.append(" and name like '%" + name + "%'");
			if (!"".equals(staffId))
				condition.append(" and staffId =" + staffId);
			if (!"".equals(IDCardNo))
				condition.append(" and IDCardNo =" + IDCardNo);
			if (!"".equals(state))
				condition.append(" and state =" + state);
			if (!"".equals(role))
				condition.append(" and role =" + role);
			if (!"".equals(hotel_id))
				condition.append(" and hotel_id =" + hotel_id);
			if (!"".equals(department_id))
				condition.append(" and department_id =" + department_id);
			QueryHelper helper = new QueryHelper(Employee.class, "e");
			helper.addCondition(condition.toString());
			List<Employee> es = employeeService.getAll(helper);
			List<EmployeeBean> ebs = new ArrayList<>();
			for (Employee employee : es) {
				EmployeeBean eb = EmployeeConverter.convert2Bean(employee);
				eb.setHotel_id(employee.getHotel().getId());
				eb.setDepartment_id(employee.getDepartment().getId());
				ebs.add(eb);
			}
			resMap.put("count", ebs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", ebs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}
}

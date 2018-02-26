package com.lq.hotel.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.constant.FunctionConstant;
import com.lq.hotel.middle.MiddleCilent;
import com.lq.hotel.model.DepartmentBean;
import com.lq.hotel.model.EmployeeBean;
import com.lq.hotel.model.HotelBean;
import com.lq.webUtils.PasswordEncoder;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "employee") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listEmployee.jsp"),
		@Result(name = "add", location = "/files/addEmployee.jsp"),
		@Result(name = "resetPassword", location = "/files/resetPassword.jsp"),
		@Result(name = "employee", location = "/files/employee.jsp"),
		@Result(name = "edit", location = "/files/editEmployee.jsp") })
public class EmployeeAction extends BaseAction implements ModelDriven<EmployeeBean> {
	private EmployeeBean em = new EmployeeBean();

//	public String goAddEmployee() {
//		// 获取部门
//		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryDepartment, JSON.toJSONString(reqMap));
//		resMap = JSON.parseObject(json);
//		List<DepartmentBean> des = JSON.parseObject(resMap.get("items").toString(),
//				new TypeReference<List<DepartmentBean>>() {
//				});
//		session.setAttribute("departmentsAll", des);
//		// 获取酒店
//		json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryHotel, JSON.toJSONString(reqMap));
//		resMap = JSON.parseObject(json);
//		List<HotelBean> hotels = JSON.parseObject(resMap.get("items").toString(), new TypeReference<List<HotelBean>>() {
//		});
//		session.setAttribute("hotelsAll", hotels);
//		return "add";
//	}

	public String addEmployee() {
		if ("".equals(em.getName()) || em.getDepartment_id()==0|| em.getHotel_id()==0
				|| "".equals(em.getIDCardNo()) || "".equals(em.getMobile()) || "".equals(em.getRole())
				|| "".equals(em.getState())){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("employee", em);
			return "add";
		}
		String department_str = String.format("%02d", em.getDepartment_id());
		String hotel_str = String.format("%03d",  em.getHotel_id());
		em.setStaffId(hotel_str+department_str);
		em.setWorkDate(new Date());
		reqMap.put("employee", em);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addEmployee, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "保存成功！");
			request.setAttribute("employee", em);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("employee", em);// 数据回写
		}
		return "add";
	}
	
	public String queryEmployee(){
		String name =request.getParameter("name");
		String staffId = request.getParameter("staffId");
		String department_id = request.getParameter("department_id");
		String role = request.getParameter("role");
		String state = request.getParameter("state");
		reqMap.put("name", name);
		reqMap.put("staffId", staffId);
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		reqMap.put("department_id", department_id);
		reqMap.put("role", role);
		reqMap.put("state", state);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryEmployee, JSON.toJSONString(reqMap));
		Map<String,Object>	map =JSON.parseObject(json);
		Integer 	errorNo = JSON.parseObject(map.get("errorNo").toString(), Integer.class);
		List<DepartmentBean> des = (List<DepartmentBean>)session.getAttribute("departmentsAll");
		if(errorNo == 0){
			List<EmployeeBean> ems = JSON.parseObject(map.get("items").toString(),new TypeReference<List<EmployeeBean>>(){});
			for (EmployeeBean e : ems) {
				e.setState(convertState(e.getState()));
				e.setRole(convertRole(e.getRole()));
				for (DepartmentBean de : des) {
					if(de.getId()==e.getDepartment_id()){
						e.setDepartmentStr(de.getName());
						break;
					}
				}
			}
			request.setAttribute("employees", ems);
			request.setAttribute("name", name);
			request.setAttribute("staffId", staffId);
			request.setAttribute("department_id_b", department_id);
			request.setAttribute("role", role);
			request.setAttribute("state", state);
		}
		return "list";
	}

	public String stateEmployee(){
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getEmployee, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		EmployeeBean em = JSON.parseObject(resMap.get("item").toString(), EmployeeBean.class);
		if ("1".equals(op)) {
			em.setState("0");
		} else {
			em.setState("2");
		}
		reqMap.remove("id");
		reqMap.put("employee", em);
		System.out.println(MiddleCilent.sendHttpJsonRequest(FunctionConstant.editEmployee, JSON.toJSONString(reqMap)));
		return "list";
	}
	
	public String editEmployee(){
		String name = em.getName();
		String mobile = em.getMobile();
		if("".equals(name)||"".equals(mobile)){
			request.setAttribute("errorInfo", "请完善必填字段!");
			return "employee";
		}
		EmployeeBean em = (EmployeeBean)session.getAttribute("user");
		em.setName(name);
		em.setMobile(mobile);
		reqMap.put("employee", em);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editEmployee, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
			session.setAttribute("user", em);
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		return "employee";
	}

	public String resetPassword(){
		String passwordOld = request.getParameter("passwordOld");
		String passwordNew1 = request.getParameter("passwordNew1");
		String passwordNew2 = request.getParameter("passwordNew2");
		if("".equals(passwordNew1)||"".equals(passwordNew2)){
			request.setAttribute("errorInfo", "密码不能为空！");
			return "resetPassword";
		}
		if(!passwordNew1.equals(passwordNew2)){
			request.setAttribute("errorInfo", "两次输入密码不一致！");
			return "resetPassword";
		}
		EmployeeBean em = (EmployeeBean)session.getAttribute("user");
		String password =em .getPassword();
		
		if(!PasswordEncoder.passwordEncode(passwordOld).equals(password)){
			request.setAttribute("errorInfo", "旧密码错误");
			return "resetPassword";
		}
		em.setPassword(PasswordEncoder.passwordEncode(passwordNew1));
		reqMap.put("employee", em);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editEmployee, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		return "resetPassword";
	}
	
	public static String convertState(String state) {
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr = "在职";
			break;
		case "1":
			stateStr = "休假";
			break;
		case "2":
			stateStr = "离职";
			break;
		default:
			stateStr = "不详";
			break;
		}
		return stateStr;
	}
	
	public static String convertRole(String role) {
		String roleStr = new String();
		switch (role) {
		case "0":
			roleStr = "普通员工";
			break;
		case "1":
			roleStr = "部门经理";
			break;
		case "2":
			roleStr = "分店经理";
			break;
		case "3":
			roleStr = "地区经理";
			break;
		case "4":
			roleStr = "总经理";
			break;
		case "5":
			roleStr = "董事长";
			break;
		default:
			roleStr = "不详";
			break;
		}
		return roleStr;
	}
	@Override
	public EmployeeBean getModel() {
		return em;
	}
}

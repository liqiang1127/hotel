package com.lq.hotel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.constant.FunctionConstant;
import com.lq.hotel.middle.MiddleCilent;
import com.lq.hotel.model.EmployeeBean;
import com.lq.webUtils.PasswordEncoder;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "employee") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "resetPassword", location = "/files/resetPassword.jsp"),
		@Result(name = "employee", location = "/files/employee.jsp") })
public class EmployeeAction extends BaseAction implements ModelDriven<EmployeeBean>{
	private EmployeeBean emBean = new EmployeeBean();
	public String editEmployee(){
		String name = emBean.getName();
		String mobile = emBean.getMobile();
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
	@Override
	public EmployeeBean getModel() {
		// TODO Auto-generated method stub
		return emBean;
	}
}

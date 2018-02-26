package com.lq.hotel.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.constant.FunctionConstant;
import com.lq.hotel.middle.MiddleCilent;
import com.lq.hotel.model.DepartmentBean;
import com.lq.hotel.model.ServiceKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "department") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listDepartment.jsp"),
		@Result(name = "add", location = "/files/addDepartment.jsp"),
		@Result(name = "edit", location = "/files/editDepartment.jsp") })
public class DepartmentAction extends BaseAction implements ModelDriven<DepartmentBean>{
	private DepartmentBean  de = new DepartmentBean();
	public String addDepartment(){
		if("".equals(de.getName())){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("department", de);
			return "add";
		}
		reqMap.put("department", de);
		System.out.println(JSON.toJSONString(reqMap));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addDepartment, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
			request.setAttribute("department", de);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("department", de);// 数据回写
		}
		return "add";
	}
	
	public String editDepartment(){
		reqMap.put("department", de);
		System.out.println(JSON.toJSONString(reqMap));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editDepartment, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		System.out.println(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
			request.setAttribute("department", de);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("department", de);// 数据回写
		}
		return "edit";
	}
	
	public String goEditDepartment(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getDepartment, JSON.toJSONString(reqMap));
		System.out.println(json);
		resMap = JSON.parseObject(json);
		DepartmentBean de = JSON.parseObject(resMap.get("item").toString(), DepartmentBean.class);
		request.setAttribute("department", de);
		return "edit";
	}
	
	public String queryDepartment(){
		String name =request.getParameter("name");
		reqMap.put("name", name);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryDepartment, JSON.toJSONString(reqMap));
		Map<String,Object>	map =JSON.parseObject(json);
		Integer 	errorNo = JSON.parseObject(map.get("errorNo").toString(), Integer.class);
		if(errorNo == 0){
			List<DepartmentBean> des = JSON.parseObject(map.get("items").toString(),new TypeReference<List<DepartmentBean>>(){});
			request.setAttribute("departments", des);
		}
		request.setAttribute("name", name);
		return "list";
	}

	@Override
	public DepartmentBean getModel() {
		// TODO Auto-generated method stub
		return de;
	}
}

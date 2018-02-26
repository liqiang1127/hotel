package com.lq.hotel.action;

import java.util.List;

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
import com.lq.hotel.model.RoomKindBean;
import com.lq.hotel.model.ServiceKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "serviceKind") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listServiceKind.jsp"),
		@Result(name = "add", location = "/files/addServiceKind.jsp"),
		@Result(name = "edit", location = "/files/editServiceKind.jsp") })
public class ServiceKindAction extends BaseAction implements ModelDriven<ServiceKindBean>{
	private ServiceKindBean sk = new ServiceKindBean();
	public String addServiceKind(){
		Boolean b = "".equals(sk.getName())||sk.getCost()==null;
		if (b) {
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("serviceKind", sk);
			return "add";
		}
		reqMap.put("serviceKind", sk);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addServiceKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
			request.setAttribute("serviceKind", sk);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("serviceKind", sk);// 数据回写
		}
		return "add";
	}
	
	public String queryServiceKind(){
		String name = request.getParameter("name");
		reqMap.put("name", name);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryServiceKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<ServiceKindBean> sks = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<ServiceKindBean>>() {
				});
		request.setAttribute("serviceKinds", sks);
		request.setAttribute("name", name);
		return "list";
	}
	
	public String goEditServiceKind(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getServiceKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		ServiceKindBean sk = JSON.parseObject(resMap.get("item").toString(), ServiceKindBean.class);
		request.setAttribute("serviceKind", sk);
		return "edit";
	}
	
	public String editServiceKind(){
		reqMap.put("serviceKind", sk);
		System.out.println(JSON.toJSONString(reqMap));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editServiceKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		System.out.println(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
			request.setAttribute("roomKind", sk);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("roomKind", sk);// 数据回写
		}
		return "edit";
	}
	@Override
	public ServiceKindBean getModel() {
		return sk;
	}
}

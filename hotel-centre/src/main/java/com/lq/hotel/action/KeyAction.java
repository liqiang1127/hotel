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

@ParentPackage("abstract-struts")
@Action(value = "key") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listDepartment.jsp"),
		@Result(name = "hotelKey", location = "/files/hotelKey.jsp"),
		@Result(name = "coopKey", location = "/files/coopKey.jsp") })
public class KeyAction extends BaseAction{
	public String newHotelKey(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.newHotelKey, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "成功！");
		} else {
			request.setAttribute("errorInfo", "失败！");
		}
		return "hotelKey";
	}
	public String newCoopKey(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.newCoopKey, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "成功！");
		} else {
			request.setAttribute("errorInfo", "失败！");
		}
		return "coopKey";
	}
}

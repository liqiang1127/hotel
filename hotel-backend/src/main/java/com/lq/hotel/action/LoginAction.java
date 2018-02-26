package com.lq.hotel.action;

import java.util.ArrayList;
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
import com.lq.hotel.model.DepartmentBean;
import com.lq.hotel.model.EmployeeBean;
import com.lq.hotel.model.HotelBean;
import com.lq.hotel.model.RoomKindBean;
import com.lq.hotel.model.ServiceKindBean;
import com.lq.webUtils.PasswordEncoder;

@ParentPackage("abstract-struts")
@Action(value = "login") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({
	   @Result(name="index", location="/index.jsp"),
       @Result(name="login", location="/login.jsp")
})
public final class LoginAction extends BaseAction{
	public String login(){
		String staffid = request.getParameter("staffId");
		String password = request.getParameter("password");
		reqMap.put("staffId", staffid);
		reqMap.put("password", PasswordEncoder.passwordEncode(password));
		
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.login, JSON.toJSONString(reqMap));
		System.out.println(json);
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
		if(errorNo==0){
			//获取酒店相应信息
			List<ServiceKindBean> skbs = JSON.parseObject(resMap.get("hotelServiceKinds").toString(),
					new TypeReference<List<ServiceKindBean>>() {
			});
		 	List<RoomKindBean> rkbs = JSON.parseObject(resMap.get("hotelRoomKinds").toString(),
					new TypeReference<List<RoomKindBean>>() {
			});
		 	HotelBean hotel =JSON.parseObject(resMap.get("hotel").toString(), HotelBean.class);
		 	EmployeeBean em  = JSON.parseObject(resMap.get("employee").toString(), EmployeeBean.class);
		 	if(!"0".equals(em.getState())){
				request.setAttribute("errorInfo", "您不处于在职状态，请联系管理员！");
				return "login";
			}
		 	String key = resMap.get("key").toString();
		 	session.setAttribute("hotel", hotel);
		 	session.setAttribute("hotelServiceKinds", skbs);
		 	session.setAttribute("hotelRoomKinds", rkbs);
		 	session.setAttribute("user", em);
		 	session.setAttribute("hotel_id", hotel.getId());
		 	session.setAttribute("employee_id", em.getId());
		 	session.setAttribute("key", key);
			// 获取部门
		 	System.out.println(JSON.toJSONString(reqMap));
		 	reqMap.remove("staffId");
		 	reqMap.remove("password");
		 	reqMap.put("key", key);
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryDepartment, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
			if(errorNo !=0){
				request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
				return "login";
			}
			List<DepartmentBean> des = JSON.parseObject(resMap.get("items").toString(),
					new TypeReference<List<DepartmentBean>>() {
			});
			
			// 获取房间种类
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryRoomKind, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
			if(errorNo !=0){
				request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
				return "login";
			}
			List<RoomKindBean> roomKindAll = JSON.parseObject(resMap.get("items").toString(),
					new TypeReference<List<RoomKindBean>>() {
			});
			// 获取服务种类
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryServiceKind, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
			if(errorNo !=0){
				request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
				return "login";
			}
			List<ServiceKindBean> serviceKindAll = JSON.parseObject(resMap.get("items").toString(),
					new TypeReference<List<ServiceKindBean>>() {
			});
			session.setAttribute("departmentsAll", des);
			session.setAttribute("roomKindsAll", roomKindAll);
			session.setAttribute("serviceKindsAll", serviceKindAll);
		}else{
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			return "login";
		}

		return "index";
	}
	
	public String logout(){
		session.invalidate();
		return "login";
	}
}

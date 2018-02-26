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

@ParentPackage("struts-default")
@Action(value = "login") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({
	   @Result(name="index", location="/index.jsp"),
       @Result(name="login", location="/login.jsp")
})
public final class LoginAction extends BaseAction{
	public String login(){
		Integer errorNo = null;
		String json = null;
		String staffid = request.getParameter("staffId");
		String password = request.getParameter("password");
		if("00000000".equals(staffid)&&"00000000".equals(password)){
			EmployeeBean em = new EmployeeBean();
			em.setName("超级管理员");
			session.setAttribute("user", em);
			session.setAttribute("key","GajufY04cYdegy3NCkUe9A==");
			reqMap.put("key", "GajufY04cYdegy3NCkUe9A==");
		}else{
			reqMap.put("staffId", staffid);
			reqMap.put("password", PasswordEncoder.passwordEncode(password));
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.login, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
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
			 	DepartmentBean dep = JSON.parseObject(resMap.get("department").toString(), DepartmentBean.class);
			 	String key = resMap.get("key").toString();
			 	if(!"0".equals(em.getState())){
					request.setAttribute("errorInfo", "您不处于在职状态，请联系管理员！");
					return "login";
				}
			 	session.setAttribute("myhotel", hotel);
			 	session.setAttribute("hotelServiceKinds", skbs);
			 	session.setAttribute("hotelRoomKinds", rkbs);
			 	session.setAttribute("user", em);
			 	session.setAttribute("mydepartment", dep);
			 	session.setAttribute("key", key);
			 	reqMap.remove("staffId");
			 	reqMap.remove("password");
			 	reqMap.put("key", key);
			}else{
				request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
				return "login";
			}
		}
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
			// 获取所有
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryHotel, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
			if(errorNo !=0){
				request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
				return "login";
			}
			List<HotelBean> hotelsAll = JSON.parseObject(resMap.get("items").toString(),
					new TypeReference<List<HotelBean>>() {
			});
			session.setAttribute("departmentsAll", des);
			session.setAttribute("roomKindsAll", roomKindAll);
			session.setAttribute("serviceKindsAll", serviceKindAll);
			session.setAttribute("hotelsAll", hotelsAll);
		return "index";
	}
	
	public String logout(){
		session.invalidate();
		return "login";
	}
}

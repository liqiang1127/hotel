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
import com.lq.hotel.model.CooperationBean;
import com.lq.hotel.model.RoomKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "roomKind") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listRoomKind.jsp"),
		@Result(name = "add", location = "/files/addRoomKind.jsp"),
		@Result(name = "edit", location = "/files/editRoomKind.jsp") })
public class RoomKindAction extends BaseAction implements ModelDriven<RoomKindBean> {
	private RoomKindBean rk = new RoomKindBean();

	public String addRoomKind() {
		Boolean b = "".equals(rk.getName()) ||rk.getBedNumber()==null || rk.getMinHours()==null
				|| rk.getPriceOneNight()==null || rk.getPerHourPrice()==null || rk.getPrePrice()==null;
		if (b) {
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("roomKind", rk);
			return "add";
		}
		rk.setState("1");
		reqMap.put("roomKind", rk);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addRoomKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("roomKind", rk);// 数据回写
		}
		return "add";
	}
	public String queryRoomKind() {
		String name = request.getParameter("name");
		String bedNumber = request.getParameter("bedNumber");
		reqMap.put("name", name);
		reqMap.put("bedNumber", bedNumber);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryRoomKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<RoomKindBean> roomKinds = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<RoomKindBean>>() {
				});
		request.setAttribute("roomKinds", roomKinds);
		request.setAttribute("flag", "0");
		request.setAttribute("name", name);
		request.setAttribute("bedNumber", bedNumber);
		return "list";
	}
	
	public String goEditRoomKind(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getRoomKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		RoomKindBean rk = JSON.parseObject(resMap.get("item").toString(), RoomKindBean.class);
		request.setAttribute("roomKind", rk);
		return "edit";
	}
	
	public String editRoomKind(){
		reqMap.put("roomKind", rk);
		System.out.println(JSON.toJSONString(reqMap));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editRoomKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		System.out.println(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("roomKind", rk);// 数据回写
		}
		return "edit";
	}
	
	public String queryHotelRoomKind() {
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryHotelRoomKind, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<RoomKindBean> roomKinds = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<RoomKindBean>>() {
				});
		request.setAttribute("roomKinds", roomKinds);
		request.setAttribute("flag", "1");
		return "list";
	}

	public String editHotelRoomKind(){
		String op = request.getParameter("op");
		String roomKind_id  = request.getParameter("id");
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		reqMap.put("roomKind_id", roomKind_id);
		reqMap.put("op", op);
		 MiddleCilent.sendHttpJsonRequest(FunctionConstant.editHotelRoomKind, JSON.toJSONString(reqMap));
		return "list";
	}
	@Override
	public RoomKindBean getModel() {
		// TODO Auto-generated method stub
		return rk;
	}
}

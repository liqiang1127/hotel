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
import com.lq.hotel.model.DepartmentBean;
import com.lq.hotel.model.EmployeeBean;
import com.lq.hotel.model.RoomBean;
import com.lq.hotel.model.RoomKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "room") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listRoom.jsp"),
	@Result(name = "addCheckin", location = "/files/addCheckin.jsp"),
		@Result(name = "add", location = "/files/addRoom.jsp"),
		@Result(name = "addReserve", location = "/files/addReserve.jsp"),
		@Result(name = "addService", location = "/files/addService.jsp"),
		@Result(name = "edit", location = "/files/editRoom.jsp") })
public class RoomAction extends BaseAction implements ModelDriven<RoomBean>{
	private RoomBean room = new RoomBean();
	public String queryRoom(){
		String fromPage = request.getParameter("fromPage");
		String no = request.getParameter("no");
		String floor = request.getParameter("floor");
		String	available = request.getParameter("available");
		String state = request.getParameter("state");
		String roomKind_id = request.getParameter("roomKind_id");
		reqMap.put("no", no);
		reqMap.put("floor", floor);
		reqMap.put("available", available);
		reqMap.put("state", state);
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		reqMap.put("roomKind_id", roomKind_id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<RoomKindBean> rmks =( List<RoomKindBean>)session.getAttribute("roomKindsAll");
		List<RoomBean> rooms = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<RoomBean>>() {
				});
		for (RoomBean room : rooms) {
			room.setArea(convertAera(room.getArea()));
			room.setAvailable(convertAvailable(room.getAvailable()));
			room.setState(convertState(room.getState()));
			for (RoomKindBean rk : rmks) {
				if(rk.getId()==room.getRoomKind_id()){
					room.setRoomKindStr(rk.getName());
					break;
				}
			}
		}
		request.setAttribute("rooms", rooms);
		request.setAttribute("no", no);
		request.setAttribute("floor", floor);
		request.setAttribute("available", available);
		request.setAttribute("state", state);
		request.setAttribute("roomKind_id_b", roomKind_id);
		return fromPage;
	}
	
	public String addRoom(){
		boolean b = "".equals(room.getNo())||"".equals(room.getFloor())||"".equals(room.getRoomKind_id())||"".equals(room.getArea());
		if(b){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("room", room);
			return "add";
		}
		room.setHotel_id((int)session.getAttribute("hotel_id"));
		reqMap.put("room", room);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		request.setAttribute("room", room);
		return  "add";
	}

	
	public String goEditRoom(){
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getRoom, JSON.toJSONString(reqMap));
		System.out.println(json);
		resMap = JSON.parseObject(json);
		RoomBean room = JSON.parseObject(resMap.get("item").toString(), RoomBean.class);
		request.setAttribute("room", room);
		return "edit";
	}
	
	public String editRoom(){
		reqMap.put("room", room);
		System.out.println(JSON.toJSONString(reqMap));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		System.out.println(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
			request.setAttribute("room", room);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("room", room);// 数据回写
		}
		return "edit";
	}
	
	public String stateRoom(){
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		RoomBean room = JSON.parseObject(resMap.get("item").toString(), RoomBean.class);
		if ("1".equals(op)) {
			room.setState("4");
			room.setAvailable("0");
		} else if("2".equals(op)){
			room.setState("5");
			room.setAvailable("0");
		}else{
			room.setState("0");
			room.setAvailable("1");
		}
		reqMap.remove("id");
		reqMap.put("room", room);
		MiddleCilent.sendHttpJsonRequest(FunctionConstant.editRoom, JSON.toJSONString(reqMap));
		return "list";
	}
	public static String convertAvailable(String state) {
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr = "不可用";
			break;
		case "1":
			stateStr = "可用";
			break;
		default:
			stateStr = "不详";
			break;
		}
		return stateStr;
	}
	
	public static String convertAera(String state) {
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr = "南";
			break;
		case "1":
			stateStr = "北";
			break;
		default:
			stateStr = "不详";
			break;
		}
		return stateStr;
	}
	public static String convertState(String state) {
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr = "空闲";
			break;
		case "1":
			stateStr = "预订";
			break;
		case "2":
			stateStr = "租用";
			break;
		case "3":
			stateStr = "结账";
			break;
		case "4":
			stateStr = "清洁";
			break;
		case "5":
			stateStr = "锁房";
			break;
		default:
			stateStr = "不详";
			break;
		}
		return stateStr;
	}
	
	@Override
	public RoomBean getModel() {
		// TODO Auto-generated method stub
		return room;
	}
}

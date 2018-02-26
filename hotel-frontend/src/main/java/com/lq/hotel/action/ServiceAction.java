package com.lq.hotel.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.lq.hotel.model.BusinessFlowBean;
import com.lq.hotel.model.CheckinOrderBean;
import com.lq.hotel.model.GuestBean;
import com.lq.hotel.model.RoomBean;
import com.lq.hotel.model.RoomKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "service") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "checkout", location = "/files/checkout.jsp"),
		@Result(name = "reserveCheckin", location = "/files/reserveCheckin.jsp"),
		@Result(name = "addReserve", location = "/files/addReserve.jsp"),
		@Result(name = "reserveCheckin", location = "/files/reserveCheckin.jsp"),
		@Result(name = "addCheckin", location = "/files/addCheckin.jsp") })
public class ServiceAction extends BaseAction implements ModelDriven<CheckinOrderBean>{
	private CheckinOrderBean cob = new CheckinOrderBean();
	public String checkInFromRes(){
		String guestNumber = request.getParameter("guestNumber");
		String reserveOrder_id = request.getParameter("reserveOrder_id");
		int guestNumberInt = Integer.parseInt(guestNumber);
		List<GuestBean> guests = new ArrayList<>();
		String guestName = "guestName";
		String IDCardNo = "IDCardNo";
		String mobile = "mobile";
		String gender = "gender";
		for(int i=0;i<guestNumberInt;i++){
			GuestBean guest = new GuestBean();
			guest.setCreateTime(new Date());
			guest.setGender(request.getParameter(gender+i));
			guest.setIDCardNo(request.getParameter(IDCardNo+i));
			guest.setMobile(request.getParameter(mobile+i));
			guest.setState("0");
			guest.setName(request.getParameter(guestName+i));
			guests.add(guest);
		}
		//发送请求
		reqMap.put("reserveOrder_id", reserveOrder_id);
		reqMap.put("employee_id", (int)session.getAttribute("employee_id"));
		reqMap.put("guests", guests);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.checkInFromRes, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "入住成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		return "reserveCheckin";
	}
	
	//无预定直接住店
	public String checkIn(){
		//补充cob
		cob.setCreateTime(new Date());
		cob.setInDate(new Date());
		//生成guest
		String guestNumber = request.getParameter("guestNumber");
		int guestNumberInt = Integer.parseInt(guestNumber);
		List<GuestBean> guests = new ArrayList<>();
		String guestName = "guestName";
		String IDCardNo = "IDCardNo";
		String mobile = "mobile";
		String gender = "gender";
		for(int i=0;i<guestNumberInt;i++){
			GuestBean guest = new GuestBean();
			guest.setCreateTime(new Date());
			guest.setGender(request.getParameter(gender+i));
			guest.setIDCardNo(request.getParameter(IDCardNo+i));
			guest.setMobile(request.getParameter(mobile+i));
			guest.setState("0");
			guest.setName(request.getParameter(guestName+i));
			guests.add(guest);
		}
		
		reqMap.put("checkinOrder", cob);
		reqMap.put("employee_id", (int)session.getAttribute("employee_id"));
		reqMap.put("guests", guests);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.checkIn, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "入住成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		return "addCheckin";
	}

	public String queryCheckin(){
		String room_no = request.getParameter("room_no");
		if("".endsWith(room_no)){
			request.setAttribute("errorInfo", "请输入正确房间号");
			return "checkout";
		}
		reqMap.put("no", room_no);
		reqMap.put("state", "2");
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<RoomBean> rooms = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<RoomBean>>() {
				});
		if(rooms.size()==1){
			RoomBean room =rooms.get(0);
			int room_id = room.getId();
			List<RoomKindBean> rmks =( List<RoomKindBean>)session.getAttribute("roomKindsAll");
			for (RoomKindBean rk : rmks) {
				if(rk.getId()==room.getRoomKind_id()){
					room.setRoomKindStr(rk.getName());
					break;
				}
			}
			reqMap.remove("no");
			reqMap.remove("hotel_id");
			reqMap.remove("state");
			reqMap.put("room_id", room_id);
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryCheckin, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			List<BusinessFlowBean> bfs =  JSON.parseObject(resMap.get("businessFlows").toString(),
					new TypeReference<List<BusinessFlowBean>>() {
			});
			List<GuestBean> gbs =  JSON.parseObject(resMap.get("guests").toString(),
					new TypeReference<List<GuestBean>>() {
			});
			CheckinOrderBean co =  JSON.parseObject(resMap.get("checkinOrder").toString(), CheckinOrderBean.class);
			co.setLeaveDate(new Date());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			co.setInDateStr(df.format(co.getInDate()));
			co.setLeaveDateStr(df.format(co.getLeaveDate()));
			for (BusinessFlowBean bf : bfs) {
				bf.setCreateDateStr(df.format(bf.getCreateDate()));
			}
			BigDecimal remainCost = co.getTotalCost().subtract(co.getPaidMoney());
			request.setAttribute("busineseFlows", bfs);
			request.setAttribute("guests", gbs);
			request.setAttribute("checkinOrder", co);
			request.setAttribute("room",room);
			request.setAttribute("peopleNumber", gbs.size());
			request.setAttribute("remainingCost", remainCost);
		}else{
			request.setAttribute("errorInfo", "请输入正确房间号");
		}
		return "checkout";
	}
	
	public String checkout(){
		String id = request.getParameter("checkinOrder_id");
		reqMap.put("id", id);
		String	json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.checkout, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(),Integer.class);
		if(errorNo==0){
			request.setAttribute("errorInfo", "结账成功");
		}else{
			request.setAttribute("errorInfo",resMap.get("errorInfo").toString());
		}
		return "checkout";
	}
	@Override
	public CheckinOrderBean getModel() {
		// TODO Auto-generated method stub
		return cob;
	}
}

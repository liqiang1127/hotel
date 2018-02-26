package com.lq.hotel.action;

import java.text.SimpleDateFormat;
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
import com.lq.hotel.model.ReserveOrderBean;
import com.lq.hotel.model.RoomBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "reserve") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listRoom.jsp"),
		@Result(name = "reserveCheckin", location = "/files/reserveCheckin.jsp"),
		@Result(name = "addReserve", location = "/files/addReserve.jsp"),
		@Result(name = "edit", location = "/files/editRoom.jsp") })
public class ReserveAction extends BaseAction implements ModelDriven<ReserveOrderBean>{
	private ReserveOrderBean reserveOrder = new ReserveOrderBean();
	public String addReserve(){
		if(reserveOrder.getRoomKind_id()==null||reserveOrder.getLeaveDate()==null||reserveOrder.getInDate()==null){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("reserveOrder", reserveOrder);	// 数据回写
			return "addReserve";
		}
		reserveOrder.setArriveTime(reserveOrder.getInDate());
		reserveOrder.setCreateTime(new Date());
		reserveOrder.setEmployee_id((int)session.getAttribute("employee_id"));
		reserveOrder.setHotel_id((int)session.getAttribute("hotel_id"));
		reserveOrder.setState("0");
		reqMap.put("reserveOrder", reserveOrder);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addReserve, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "预订成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		request.setAttribute("reserveOrder", reserveOrder);	// 数据回写
		return "addReserve";
	}
	
	public String queryReserve(){
		String resMobile = request.getParameter("resMobile");
		String resName = request.getParameter("resName");
		String state =request.getParameter("state");
		String fromPage = request.getParameter("fromPage");
		reqMap.put("resMobile", resMobile);
		reqMap.put("resName", resName);
		reqMap.put("state", state);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryReserve, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		System.out.println(json);
		List<ReserveOrderBean> robs = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<ReserveOrderBean>>() {
				});
		for (ReserveOrderBean reserveOrderBean : robs) {
			//不是本酒店的就忽略
			if(reserveOrderBean.getHotel_id()!=(int)session.getAttribute("hotel_id")){
				robs.remove(reserveOrderBean);
			}else{
				reserveOrderBean.setInDateStr(df.format(reserveOrderBean.getInDate()));
				reserveOrderBean.setLeaveDateStr(df.format(reserveOrderBean.getLeaveDate()));
				reserveOrderBean.setCreateTimeStr(df.format(reserveOrderBean.getCreateTime()));
			}
		}
		request.setAttribute("reserveOrders", robs);
		request.setAttribute("resName", resName);
		request.setAttribute("resMobile", resMobile);
		return fromPage;
	}
	@Override
	public ReserveOrderBean getModel() {
		// TODO Auto-generated method stub
		return reserveOrder;
	}
	
}

package com.lq.hotel.action;

import java.math.BigDecimal;
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
import com.lq.hotel.model.BusinessFlowBean;
import com.lq.hotel.model.RoomBean;
import com.lq.hotel.model.RoomKindBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "businese") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "list", location = "/files/listRoom.jsp"),
		@Result(name = "reserveCheckin", location = "/files/reserveCheckin.jsp"),
		@Result(name = "listBusinese", location = "/files/listBusinese.jsp"),
		@Result(name = "addService", location = "/files/addService.jsp") })
public class BusineseFlowAction extends BaseAction implements ModelDriven<BusinessFlowBean>{
	private BusinessFlowBean bf = new BusinessFlowBean();
	
	public String addService(){
		String room_id = request.getParameter("room_id");
		bf.setEmployee_id((int)session.getAttribute("employee_id"));
		bf.setCreateDate(new Date());
		reqMap.put("room_id", room_id);
		reqMap.put("businessFlow", bf);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addService, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
		}
		return "addService";
	}
	
	public String queryBusinese(){
		String room_no = request.getParameter("room_no");
		reqMap.put("no", room_no);
		reqMap.put("hotel_id", (int)session.getAttribute("hotel_id"));
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryRoom, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<RoomBean> rooms = JSON.parseObject(resMap.get("items").toString(),
				new TypeReference<List<RoomBean>>() {
				});
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(rooms.size()==1){
			RoomBean room = rooms.get(0);
			int room_id = rooms.get(0).getId();
			if(!"2".equals(room.getState())){
				request.setAttribute("errorInfo", "该房间不在租用状态！");
				return "listBusinese";
			}
			reqMap.remove("no");
			reqMap.remove("hotel_id");
			reqMap.put("room_id", room_id);
			json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryCheckin, JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
			List<BusinessFlowBean> bfs =  JSON.parseObject(resMap.get("businessFlows").toString(),
					new TypeReference<List<BusinessFlowBean>>() {
			});
			BigDecimal totalCost = new BigDecimal(0);
			for (BusinessFlowBean businessFlowBean : bfs) {
				totalCost = totalCost.add(businessFlowBean.getCost());
				businessFlowBean.setCreateDateStr(df.format(businessFlowBean.getCreateDate()));
			}
			request.setAttribute("busineseFlows", bfs);
			request.setAttribute("totalCost", totalCost);
			request.setAttribute("count", bfs.size());
		}else{
			request.setAttribute("errorInfo", "请输入正确房间号");
		}
		return "listBusinese";
	}
	@Override
	public BusinessFlowBean getModel() {
		// TODO Auto-generated method stub
		return bf;
	}

}

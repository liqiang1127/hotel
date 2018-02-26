package com.lq.hotel.action;

import java.text.SimpleDateFormat;
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
import com.lq.hotel.model.HotelBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "coop") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "listCoop", location = "/files/listCoop.jsp"),
		@Result(name = "addCooperation", location = "/files/addCoop.jsp"),
		@Result(name = "coopKey", location = "/files/coopKey.jsp"),
		@Result(name = "editCooperation", location = "/files/editCoop.jsp") })
public class CoopAction extends BaseAction implements ModelDriven<CooperationBean>{
	private CooperationBean co = new CooperationBean();
	public String addCoop(){
		if ("".equals(co.getName())||co.getCreateDate()==null||co.getEndDate()==null){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("cooperation", co);
			return "addCooperation";
		}
		reqMap.put("cooperation", co);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addCooperation, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("cooperation", co);// 数据回写
		}
		return "addCooperation";
	}

	public String queryCoop(){
		String fromPage =	request.getParameter("fromPage");
		reqMap.put("name", co.getName());
		reqMap.put("state", co.getState());
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryCooperation, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		List<CooperationBean> cos = JSON.parseObject(resMap.get("items").toString(), new TypeReference<List<CooperationBean>>() {
		});
		for (CooperationBean co : cos) {
			co.setState(convertState(co.getState()));
			if(co.getCreateDate()!=null)
				co.setCreateDstr(df.format(co.getCreateDate()));
			if(co.getEndDate()!=null)
				co.setEndDstr(df.format(co.getEndDate()));
		}
		request.setAttribute("coops", cos);
		request.setAttribute("name", co.getName());
		request.setAttribute("state", co.getState());
		return fromPage;
	}
	
	public String goEditCoop(){
		String id = request.getParameter("id");
		System.out.println(id);
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getCooperation, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		CooperationBean co = JSON.parseObject(resMap.get("item").toString(), CooperationBean.class);
		request.setAttribute("cooperation", co);
		return "editCooperation";
	}
	
	public String editCoop(){
		reqMap.put("cooperation", co);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editCooperation, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "修改成功！");
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("cooperation", co);// 数据回写
		}
		return "editCooperation";
	}
	
	public String stateCoop() {
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		reqMap.put("id", id);
		if ("1".equals(op)) {
			reqMap.put("state", "0");
		} else {
			reqMap.put("state", "1");
		}
		MiddleCilent.sendHttpJsonRequest(FunctionConstant.stateCoop, JSON.toJSONString(reqMap));
		return "listCoop";
	}
	public static String convertState(String state){
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr="正常";
			break;
		case "1":
			stateStr="挂起";
			break;
		default:
			break;
		}
	    return stateStr;
	}
	@Override
	public CooperationBean getModel() {
		return co;
	}
}

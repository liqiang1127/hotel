package com.lq.hotel.action;

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
import com.lq.hotel.model.HotelBean;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("abstract-struts")
@Action(value = "branch") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "listHotel", location = "/files/listHotel.jsp"),
		@Result(name = "addHotel", location = "/files/addHotel.jsp"),
		@Result(name = "hotelKey", location = "/files/hotelKey.jsp"),
		@Result(name = "editHotel", location = "/files/editHotel.jsp") })
public class BranchAction extends BaseAction implements ModelDriven<HotelBean> {
	private HotelBean hotel = new HotelBean();

	public String addHotel() {
		if ("".equals(hotel.getName()) || "".equals(hotel.getLocation())||"".equals(hotel.getX())||"".equals(hotel.getY())){
			request.setAttribute("errorInfo", "请完善必填字段");
			request.setAttribute("hotel", hotel);
			return "addHotel";
		}
		hotel.setCreateDate(new Date());
		hotel.setCoordinate(hotel.getX() + "," + hotel.getY());
		hotel.setBussinessDate(new Date());
		reqMap.put("hotel", hotel);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.addHotel, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "添加成功！");
		} else {
			request.setAttribute("errorInfo", "添加失败！");
			request.setAttribute("hotel", hotel);// 数据回写
		}
		return "addHotel";
	}

	public String queryHotel() {
		String fromPage = request.getParameter("fromPage");
		reqMap.put("name", hotel.getName());
		reqMap.put("location", hotel.getLocation());
		reqMap.put("state", hotel.getState());
		reqMap.put("star", hotel.getStar());
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryHotel, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		List<HotelBean> hotels = JSON.parseObject(resMap.get("items").toString(), new TypeReference<List<HotelBean>>() {
		});
		for (HotelBean h : hotels) {
			h.setStar(convertStar(h.getStar()));
			h.setState(convertState(h.getState()));
		}
		request.setAttribute("hotels", hotels);
		request.setAttribute("name", hotel.getName());
		request.setAttribute("location", hotel.getLocation());
		request.setAttribute("state", hotel.getState());
		request.setAttribute("star", hotel.getStar());
		return fromPage;
	}

	public String goEditHotel() {
		String id = request.getParameter("id");
		reqMap.put("id", id);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.getHotel, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		HotelBean hotel = JSON.parseObject(resMap.get("item").toString(), HotelBean.class);
		String[] cs = hotel.getCoordinate().split(",");
		hotel.setX(cs[0]);
		hotel.setY(cs[1]);
		request.setAttribute("hotel", hotel);
		return "editHotel";
	}

	public String editHotel() {
		hotel.setCreateDate(new Date());
		hotel.setCoordinate(hotel.getX() + "," + hotel.getY());
		hotel.setBussinessDate(new Date());
		reqMap.put("hotel", hotel);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.editHotel, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
//		System.out.println(json);
		Integer errorNo = JSON.parseObject(resMap.get("errorNo").toString(), Integer.class);
		if (errorNo == 0) {
			request.setAttribute("errorInfo", "保存成功！");
			request.setAttribute("hotel", hotel);// 数据回写
		} else {
			request.setAttribute("errorInfo", resMap.get("errorInfo").toString());
			request.setAttribute("hotel", hotel);// 数据回写
		}
		return "editHotel";
	}

	public String stateHotel() {
		String op = request.getParameter("op");
		String id = request.getParameter("id");
		reqMap.put("id", id);
		
		if ("1".equals(op)) {
			reqMap.put("state", "1");
		} else {
			reqMap.put("state", "2");
		}
		MiddleCilent.sendHttpJsonRequest(FunctionConstant.stateHotel, JSON.toJSONString(reqMap));
		return "listHotel";
	}

	public static String convertState(String state) {
		String stateStr = new String();
		switch (state) {
		case "0":
			stateStr = "建设中";
			break;
		case "1":
			stateStr = "营业中";
			break;
		case "2":
			stateStr = "停业中";
			break;
		default:
			stateStr = "建设中";
			break;
		}
		return stateStr;
	}
	
	public static String convertStar(String star) {
		String starStr = new String();
		switch (star) {
		case "1":
			starStr = "一星级";
			break;
		case "2":
			starStr = "二星级";
			break;
		case "3":
			starStr = "三星级";
			break;
		case "4":
			starStr = "四星级";
			break;
		case "5":
			starStr = "五星级";
			break;
		default:
			starStr = "一星级";
			break;
		}
		return starStr;
	}

	@Override
	public HotelBean getModel() {
		return hotel;
	}
}

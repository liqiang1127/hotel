package com.lq.hotel.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.constant.FunctionConstant;
import com.lq.hotel.middle.MiddleCilent;

@ParentPackage("json-default")
@Action(value = "pay") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class PayAction extends BaseAction{
	public String newPayment(){
		String padMoney = request.getParameter("padMoney");
		String content = request.getParameter("content");
		reqMap.put("totalCost", padMoney);
		reqMap.put("content", content);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.newPayment, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		return "success";
	}
	
	public String queryPay(){
		String outTradeNo = request.getParameter("outTradeNo");
		System.out.println(outTradeNo);
		reqMap.put("outTradeNo", outTradeNo);
		String json = MiddleCilent.sendHttpJsonRequest(FunctionConstant.queryPayment, JSON.toJSONString(reqMap));
		resMap = JSON.parseObject(json);
		return "success";
	}
}

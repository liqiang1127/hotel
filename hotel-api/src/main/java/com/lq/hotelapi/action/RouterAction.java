package com.lq.hotelapi.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lq.hotelapi.action.core.BaseAction;
import com.lq.hotelapi.middle.MiddleCilent;

@ParentPackage("abstract-struts")
@Action(value = "router") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class RouterAction extends BaseAction{
	public String  queryHotelList(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Map<String,Object> reqMap = new HashMap<>();
			Map<String, Object> dataMap = JSON.parseObject(JSONMap.get("data").toString(), new TypeReference<Map<String, Object>>(){});
			String name = dataMap.get("name") == null ? "" : dataMap.get("name").toString();
			String location = dataMap.get("location") == null ? "" : dataMap.get("location").toString();
			String  state = dataMap.get("state") == null ? "" : dataMap.get("state").toString();
			String  star = dataMap.get("star") == null ? "" : dataMap.get("star").toString();
			reqMap.put("name", name);
			reqMap.put("location", location);
			reqMap.put("state", state);
			reqMap.put("star", star);
			String keyContent = JSONMap.get("keyContent").toString();
			reqMap.put("key", keyContent);
			String json = MiddleCilent.sendHttpJsonRequest("http://localhost:8280/hotel-base/branch!queryHotel.action", JSON.toJSONString(reqMap));
			resMap = JSON.parseObject(json);
		}catch (Exception e) {
			resMap.put("errorNo","99");
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String test(){
		resMap.put("errorNo", 0);
		resMap.put("errorInfo", "连接正常");
		return SUCCESS;
	}
}

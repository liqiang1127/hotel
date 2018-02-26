package com.lq.hotel.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.enums.ErrorConstant;

@ParentPackage("login-struts")
@Action(value = "api") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class ApiAction extends BaseAction{
	public String getKeyContentByCoop(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("coop_id").toString(),Integer.class);
			Cooperation cop = cooperationService.getObjectById(id);
			if(cop!=null){
				String keyContent = cop.getKey().getKeyContent();
				resMap.put("errorNo", 0);
				resMap.put("keyContent", keyContent);
			}else{
				resMap.put("errorNo", -1);
			}
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
}

package com.lq.hotel.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.entity.Key;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.PasswordEncoder;

@ParentPackage("abstract-struts")
@Action(value = "key") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class KeyAction extends BaseAction{
	public String newHotelKey(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			Hotel hotel = hotelService.getObjectById(id);
			String orgin = ""+id+hotel.getName()+Math.random();
			String keyNew = PasswordEncoder.passwordEncode(orgin);
			Key key = new Key();
			key.setKeyContent(keyNew);
			hotel.setKey(key);
			hotelService.update(hotel);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String newCoopKey(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			Cooperation cop = cooperationService.getObjectById(id);
			String orgin = ""+id+cop.getName()+Math.random();
			String keyNew = PasswordEncoder.passwordEncode(orgin);
			Key key = new Key();
			key.setKeyContent(keyNew);
			cop.setKey(key);
			cooperationService.update(cop);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
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

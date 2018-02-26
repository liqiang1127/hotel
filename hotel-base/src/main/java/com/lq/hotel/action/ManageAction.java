package com.lq.hotel.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.bean.HotelBean;
import com.lq.hotel.bean.RoomBean;
import com.lq.hotel.bean.RoomKindBean;
import com.lq.hotel.bean.ServiceKindBean;
import com.lq.hotel.convert.HotelConverter;
import com.lq.hotel.convert.RoomConverter;
import com.lq.hotel.convert.RoomKindConverter;
import com.lq.hotel.convert.ServiceKindConverter;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.entity.Room;
import com.lq.hotel.entity.RoomKind;
import com.lq.hotel.entity.ServiceKind;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.hotel.service.ServiceKindService;
import com.lq.webUtils.QueryHelper;

/**
 * 经营管理的Action
 * 房间种类
 * 房间
 * 服务种类
 * @author liqiang
 *
 */

@ParentPackage("abstract-struts")
@Action(value = "manage") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class ManageAction  extends BaseAction{
	/*
	 * 	private Integer id; // 客房类型编号
	private String name; // 客房类型名称 双人房 单人房等等
	private Integer bedNumber; // 床位数
	private BigDecimal prePrice; // 预定价格
	private BigDecimal priceOneNight; // 入住价格
	private BigDecimal discount; // 结款折扣（允许设置折扣）
	private BigDecimal perHourPrice; // 计时每小时价（ 钟点房）
	private Integer minHours; // 钟点房的最少入住时间
	private String state;//状态
	 */
	public String addRoomKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			RoomKindBean rkb = JSON.parseObject(JSONMap.get("roomKind").toString(),RoomKindBean.class);
			RoomKind rk = RoomKindConverter.convert2Entity(rkb);
			QueryHelper helper = new QueryHelper(RoomKind.class, "rk");
			helper.addCondition("rk.name =?0", rk.getName());
			int count = roomKindService.getObjectCount(helper);
			if(count==0){
				roomKindService.save(rk);
				resMap.put("errorNo",0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String deleteRoomKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			if(roomKindService.getObjectById(id)!=null){
				roomKindService.delete(id);
				resMap.put("errorNo",0);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String editRoomKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			RoomKindBean rkb = JSON.parseObject(JSONMap.get("roomKind").toString(),RoomKindBean.class);
			RoomKind rk = RoomKindConverter.convert2Entity(rkb);
			QueryHelper helper = new QueryHelper(RoomKind.class, "rk");
			helper.addCondition("rk.name =?0", rk.getName());
			int count = roomKindService.getObjectCount(helper);
			if(count<=1){
				roomKindService.update(rk);
				resMap.put("errorNo",0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;	
	}
	
	public String getRoomKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			RoomKind rk = 	roomKindService.getObjectById(id);
			if(rk!=null){
				RoomKindBean rkb = RoomKindConverter.convert2Bean(rk);
				resMap.put("errorNo",0);
				resMap.put("item", rkb);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	/*
	 * 	private Integer id; // 客房类型编号
	private String name; // 客房类型名称 双人房 单人房等等
	private Integer bedNumber; // 床位数
	private BigDecimal prePrice; // 预定价格
	private BigDecimal priceOneNight; // 入住价格
	private BigDecimal discount; // 结款折扣（允许设置折扣）
	private BigDecimal perHourPrice; // 计时每小时价（ 钟点房）
	private Integer minHours; // 钟点房的最少入住时间
	private String state;//状态
	 */
	public String editHotelRoomKind(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String op = JSONMap.get("op") == null ? "" : JSONMap.get("op").toString();
			Integer hotel_id =  JSON.parseObject(JSONMap.get("hotel_id").toString(),Integer.class);
			Integer roomKind_id =  JSON.parseObject(JSONMap.get("roomKind_id").toString(),Integer.class);
			Hotel hotel = hotelService.getObjectById(hotel_id);
			List<RoomKind> rmks= hotel.getRoomKinds();
			RoomKind rm = roomKindService.getObjectById(roomKind_id);
			if("1".equals(op)){
				rmks.add(rm);
			}else{
				for (RoomKind roomKind : rmks) {
					if(roomKind.getId()==rm.getId()){
						rmks.remove(roomKind);
						break;
					}
				}
			}
			hotel.setRoomKinds(rmks);
			hotelService.update(hotel);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String queryRoomKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String name = JSONMap.get("name") == null ? "" : JSONMap.get("name").toString();
			String bedNumber = JSONMap.get("bedNumber") == null ? "" : JSONMap.get("bedNumber").toString();
			String  state = JSONMap.get("state") == null ? "" : JSONMap.get("state").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(name))
				condition.append(" and name like '%"+name+"%'");
			if(!"".equals(bedNumber))
				condition.append(" and bedNumber ="+bedNumber);
			if(!"".equals(state))
				condition.append(" and state ="+state);
			QueryHelper helper = new QueryHelper(RoomKind.class, "rk");
			helper.addCondition(condition.toString());
			List<RoomKind> rks = roomKindService.getAll(helper);
			List<RoomKindBean> rkbs = new ArrayList<>();
			for (RoomKind rk : rks) {
				rkbs.add( RoomKindConverter.convert2Bean(rk));
			}
			resMap.put("count", rkbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", rkbs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String queryHotelRoomKind(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("hotel_id").toString(),Integer.class);
			Hotel h = hotelService.getObjectById(id);
			List<RoomKind> rks = h.getRoomKinds();
			List<RoomKindBean> rkbs = new ArrayList<>();
			for (RoomKind rk : rks) {
				rkbs.add( RoomKindConverter.convert2Bean(rk));
			}
			resMap.put("count", rkbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", rkbs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
//	private Integer id; // 客房编号
//	private String area; // 朝向朝南朝北 1 north 2 south
//	private String floor; // 所属楼层
//	private String no; // 房间号
//	private String telphone; // 分机电话
//	private String state; // 客房状态
//	private String available; // 是否可用
//	private String picture; // 房间图片 url
	public String addRoom(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			RoomBean rb = JSON.parseObject(JSONMap.get("room").toString(),RoomBean.class);
			Room r = RoomConverter.convert2Entity(rb);
			String hotel_id = rb.getHotel_id()==null?"":rb.getHotel_id().toString();
			String roomKind_id =rb.getRoomKind_id()==null?"":rb.getRoomKind_id().toString();
			String no = rb.getNo()==null?"": rb.getNo();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(hotel_id))
				condition.append(" and hotel_id ="+hotel_id);
			if(!"".equals(no))
				condition.append(" and no ="+no);
			QueryHelper helper = new QueryHelper(Room.class, "r");
			helper.addCondition(condition.toString());
			int count = roomService.getObjectCount(helper);
			if(count==0){
				Hotel h = hotelService.getObjectById(Integer.parseInt(hotel_id));
				RoomKind rk = roomKindService.getObjectById(Integer.parseInt(roomKind_id));
				r.setHotel(h);
				r.setRoomKind(rk);
				r.setAvailable("1");
				r.setState("0");
				roomService.save(r);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}	
		return SUCCESS;
	}
	public String editRoom(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			RoomBean rb = JSON.parseObject(JSONMap.get("room").toString(),RoomBean.class);
			Room r = RoomConverter.convert2Entity(rb);
			String hotel_id = rb.getHotel_id()==null?"":rb.getHotel_id().toString();
			String roomKind_id =rb.getRoomKind_id()==null?"":rb.getRoomKind_id().toString();
			String no = rb.getNo()==null?"": rb.getNo();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(hotel_id))
				condition.append(" and hotel_id ="+hotel_id);
			if(!"".equals(no))
				condition.append(" and no ="+no);
			QueryHelper helper = new QueryHelper(Room.class, "r");
			helper.addCondition(condition.toString());
			int count = roomService.getObjectCount(helper);
			if(count<=1){
				Hotel h = hotelService.getObjectById(Integer.parseInt(hotel_id));
				RoomKind rk = roomKindService.getObjectById(Integer.parseInt(roomKind_id));
				r.setHotel(h);
				r.setRoomKind(rk);
				roomService.update(r);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}	
		return SUCCESS;
	}
	public String deleteRoom(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			if(roomService.getObjectById(id)!=null){
				roomService.delete(id);
				resMap.put("errorNo",0);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String getRoom(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			Room r = 	roomService.getObjectById(id);
			if(r!=null){
				RoomBean rb = RoomConverter.convert2Bean(r);
				rb.setHotel_id(r.getHotel().getId());
				rb.setRoomKind_id(r.getRoomKind().getId());
				resMap.put("errorNo",0);
				resMap.put("item", rb);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
//	private Integer id; // 客房编号
//	private String area; // 朝向朝南朝北 1 north 2 south
//	private String floor; // 所属楼层
//	private String no; // 房间号
//	private String telphone; // 分机电话
//	private String state; // 客房状态
//	private String available; // 是否可用
//	private String picture; // 房间图片 url
	public String queryRoom(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String area = JSONMap.get("area")==null?"":JSONMap.get("area").toString();
			String floor = JSONMap.get("floor")==null?"":JSONMap.get("floor").toString();
			String no = JSONMap.get("no")==null?"":JSONMap.get("no").toString();
			String state = JSONMap.get("state")==null?"":JSONMap.get("state").toString();
			String available = JSONMap.get("available")==null?"":JSONMap.get("available").toString();
			String roomKind_id = JSONMap.get("roomKind_id")==null?"":JSONMap.get("roomKind_id").toString();
			String hotel_id =JSONMap.get("hotel_id")==null?"":JSONMap.get("hotel_id").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(state))
				condition.append(" and state ="+state);
			if(!"".equals(area))
				condition.append(" and area ="+area);
			if(!"".equals(no))
				condition.append(" and no ="+no);
			if(!"".equals(floor))
				condition.append(" and floor ="+floor);
			if(!"".equals(available))
				condition.append(" and available ="+available);
			if(!"".equals(roomKind_id))
				condition.append(" and roomKind_id ="+roomKind_id);
			if(!"".equals(hotel_id))
				condition.append(" and hotel_id ="+hotel_id);
			QueryHelper helper = new QueryHelper(Room.class, "r");
			helper.addCondition(condition.toString());
			List<Room> rs = roomService.getAll(helper);
			List<RoomBean> rbs = new ArrayList<>();
			for (Room r : rs) {
				RoomBean rb = RoomConverter.convert2Bean(r);
				rb.setHotel_id(r.getHotel().getId());
				rb.setRoomKind_id(r.getRoomKind().getId());
				rbs.add(rb);
			}
			resMap.put("count", rbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", rbs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}	
		return SUCCESS;
	}
	
//=====================================================================================
//	private Integer id;//服务的id
//	private String name;//服务的名字
//	private BigDecimal cost;//费用
//	private String remark;//介绍
	//这个没有bean
	public String addServiceKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			ServiceKind sk = JSON.parseObject(JSONMap.get("serviceKind").toString(),ServiceKind.class);
			QueryHelper helper = new QueryHelper(ServiceKind.class, "sk");
			helper.addCondition("sk.name = ?0", sk.getName());
			int count = serviceKindService.getObjectCount(helper);
			if(count ==0){
				serviceKindService.save(sk);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String deleteServiceKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			if(serviceKindService.getObjectById(id)!=null){
				serviceKindService.delete(id);
				resMap.put("errorNo",0);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String editServiceKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			ServiceKind sk = JSON.parseObject(JSONMap.get("serviceKind").toString(),ServiceKind.class);
			QueryHelper helper = new QueryHelper(ServiceKind.class, "sk");
			helper.addCondition("sk.name = ?0", sk.getName());
			int count = serviceKindService.getObjectCount(helper);
			if(count ==1){
				serviceKindService.update(sk);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.exist.getCode());
				resMap.put("errorInfo", ErrorConstant.exist.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String getServiceKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			ServiceKind sk = serviceKindService.getObjectById(id);
			if(sk!=null){
				resMap.put("errorNo",0);
				resMap.put("item", sk);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}			
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String queryServiceKind(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String name = JSONMap.get("name")==null?"":JSONMap.get("name").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(name))
				condition.append(" and name like '%"+name+"%'");
			QueryHelper helper = new QueryHelper(ServiceKind.class, "sk");
			helper.addCondition(condition.toString());
			List<ServiceKind> sks = serviceKindService.getAll(helper);
			List<ServiceKindBean> skbs = new ArrayList<>();
			for (ServiceKind serviceKind : sks) {
				skbs.add(ServiceKindConverter.convert2Bean(serviceKind));
			}
			resMap.put("count", skbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", skbs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}	
		return SUCCESS;
	}
	
	public String queryHotelServiceKind(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id =  JSON.parseObject(JSONMap.get("hotel_id").toString(),Integer.class);
			Hotel h = hotelService.getObjectById(id);
			List<ServiceKind> sks = h.getServiceKinds();
			List<ServiceKindBean> skbs = new ArrayList<>();
			for (ServiceKind serviceKind : sks) {
				skbs.add(ServiceKindConverter.convert2Bean(serviceKind));
			}
			resMap.put("count", skbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", skbs);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	public String editHotelServiceKind(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String op = JSONMap.get("op") == null ? "" : JSONMap.get("op").toString();
			Integer hotel_id =  JSON.parseObject(JSONMap.get("hotel_id").toString(),Integer.class);
			Integer serviceKind_id =  JSON.parseObject(JSONMap.get("serviceKind_id").toString(),Integer.class);
			Hotel hotel = hotelService.getObjectById(hotel_id);
			List<ServiceKind> sks= hotel.getServiceKinds();
			ServiceKind sk =serviceKindService.getObjectById(serviceKind_id);
			if("1".equals(op)){
				sks.add(sk);
			}else{
				for (ServiceKind serviceKind : sks) {
					if(serviceKind.getId()==sk.getId()){
						sks.remove(serviceKind);
						break;
					}
				}
			}
			hotel.setServiceKinds(sks);
			hotelService.update(hotel);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
}

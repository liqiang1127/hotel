package com.lq.hotel.action;


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
import com.lq.hotel.bean.CooperationBean;
import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.bean.ReserveOrderBean;
import com.lq.hotel.bean.RoomBean;
import com.lq.hotel.convert.CooperationConverter;
import com.lq.hotel.convert.DepartmentConverter;
import com.lq.hotel.convert.ReserveOrderConverter;
import com.lq.hotel.convert.RoomConverter;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.entity.Department;
import com.lq.hotel.entity.ReserveOrder;
import com.lq.hotel.entity.Room;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.QueryHelper;
/**
 * 处理预订的Action
 * @author liqiang
 *
 */
@ParentPackage("abstract-struts")
@Action(value = "reserve") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class ReserveAction extends BaseAction{
	public String addReserve(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			ReserveOrderBean rob = JSON.parseObject(JSONMap.get("reserveOrder").toString(), ReserveOrderBean.class);
			ReserveOrder ro = ReserveOrderConverter.convert2Entity(rob);
			QueryHelper helper = new QueryHelper(Room.class, "r");
			//寻找可用房间
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(rob.getHotel_id()!=null)
				condition.append(" and hotel_id ="+rob.getHotel_id());
			if(rob.getRoomKind_id()!=null)
				condition.append(" and roomKind_id ="+rob.getRoomKind_id());
			condition.append(" and available = "+"1");
			helper.addCondition(condition.toString());
			List<Room> rooms = roomService.getAll(helper);
			if(!rooms.isEmpty()){
				Room room = rooms.get(0);
				room.setAvailable("0");//一旦被预订 或者被清洁  都是不可用 只有一个状态是可用的
				room.setState("1");//房间变成预订状态
				roomService.update(room);
				ro.setRoom(room);
				ro.setState("0");//预订生效状态 0
				String employee_id = JSONMap.get("employee_id")==null?"":JSONMap.get("employee_id").toString();
				if(!"".equals(employee_id))
					ro.setEmployee(employeeService.getObjectById(Integer.parseInt(employee_id)));
				reserveService.save(ro);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.already_reserved.getCode());
				resMap.put("errorInfo", ErrorConstant.already_reserved.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String deleteReserve(){
		return SUCCESS;
	}
	
	/*
	 * 	private Integer id; // 预订单id
		private String resName; // 预订人姓名
		private String resMobile;// 预订人联系电话
		private Integer preNumber;// 预计入住人数
		private Date InDate; // 预计抵店日期
		private Date leaveDate; // 预计离店日期
		private Date arriveTime; // 预计抵店时间
		private String payMethod; // 支付方式 1 现金支付 2 在线支付
		private BigDecimal paidMoney; // 已付押金数
		private String state; // 预定单状态 0 有效状态 1 已入住 作废状态 2 未入住作废 不应该被前台查询 只做数据分析
		private Date createTime; // 预订产生的时间
		private String remark; // 备注
	 */
	public String queryReserve(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String resName = JSONMap.get("resName") == null ? "" : JSONMap.get("resName").toString();
			String resMobile = JSONMap.get("resMobile") == null ? "" : JSONMap.get("resMobile").toString();
			String state = JSONMap.get("state") == null ? "" : JSONMap.get("state").toString();
			QueryHelper queryHelper = new QueryHelper(ReserveOrder.class, "ro");
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if (!"".equals(resName))
				condition.append(" and resName like '%" + resName + "%'");
			if (!"".equals(resMobile))
				condition.append(" and resMobile =" + resMobile);
			if (!"".equals(state))
				condition.append(" and state =" + state);
			queryHelper.addCondition(condition.toString());
			List<ReserveOrder> ros = reserveService.getAll(queryHelper);
			List<ReserveOrderBean> robs = new ArrayList<>();
			for (ReserveOrder ro : ros) {
				ReserveOrderBean rob = ReserveOrderConverter.convert2Bean(ro);
				rob.setRoom_id(ro.getRoom().getId());
				if(ro.getEmployee()!=null){
					rob.setEmployee_id(ro.getEmployee().getId());
				}
				rob.setHotel_id(ro.getRoom().getHotel().getId());
				rob.setRoomKind_id(ro.getRoom().getRoomKind().getId());
				rob.setRoomNo(ro.getRoom().getNo());
				robs.add(rob);
			}
			resMap.put("count", robs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", robs);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	public String getReserve(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			ReserveOrder ro = reserveService.getObjectById(id);
			if (ro != null) {
				ReserveOrderBean rob= ReserveOrderConverter.convert2Bean(ro);
				rob.setEmployee_id(ro.getEmployee().getId());
				rob.setHotel_id(ro.getRoom().getHotel().getId());
				rob.setRoom_id(ro.getRoom().getId());
				rob.setRoomKind_id(ro.getRoom().getRoomKind().getId());
				resMap.put("errorNo", 0);
				resMap.put("item", rob);
			} else {
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}
	
	//取消预订
	public String cancelReserve(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			ReserveOrder ro = reserveService.getObjectById(id);
			if (ro != null) {
				ro.setState("2");//取消预订作废
				Room room =ro.getRoom();
				room.setAvailable("1");
				roomService.update(room);
				reserveService.update(ro);
				resMap.put("errorNo", 0);
			} else {
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());// ErrorConstant.department_not_exist.getCode()
				resMap.put("department", ErrorConstant.not_exist.getInfo()); // ErrorConstant.department_not_exist.getInfo()
			}
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", ErrorConstant.system_error.getInfo());
		}
		return SUCCESS;
	}
}

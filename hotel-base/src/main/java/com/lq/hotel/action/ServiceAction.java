package com.lq.hotel.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.annotations.Check;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lq.hotel.action.core.BaseAction;
import com.lq.hotel.bean.BusinessFlowBean;
import com.lq.hotel.bean.CheckinOrderBean;
import com.lq.hotel.bean.GuestBean;
import com.lq.hotel.convert.BusinessFlowConverter;
import com.lq.hotel.convert.CheckinOrderConverter;
import com.lq.hotel.convert.GuestConverter;
import com.lq.hotel.entity.BusinessFlow;
import com.lq.hotel.entity.CheckinOrder;
import com.lq.hotel.entity.Employee;
import com.lq.hotel.entity.Guest;
import com.lq.hotel.entity.ReserveOrder;
import com.lq.hotel.entity.Room;
import com.lq.hotel.entity.ServiceKind;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.QueryHelper;

/**
 * 处理从入住到结账的Action 入住 结款退房 增加服务
 * 
 * @author liqiang
 *
 */
@ParentPackage("abstract-struts")
@Action(value = "service") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class ServiceAction extends BaseAction {
	
	// 预订入住
	public String checkInFromRes() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			// 获取预订单id
			String reserveOrder_id = JSONMap.get("reserveOrder_id") == null ? ""
					: JSONMap.get("reserveOrder_id").toString();
			String employee_id = JSONMap.get("employee_id") == null ? "" : JSONMap.get("employee_id").toString();
			List<Guest> guests = JSON.parseObject(JSONMap.get("guests").toString(), new TypeReference<List<Guest>>() {
			});
			ReserveOrder ro = reserveService.getObjectById(Integer.parseInt(reserveOrder_id));
			
			CheckinOrder co = new CheckinOrder();
			co.setCreateTime(new Date());
			co.setInDate(new Date());
			co.setLeaveDate(ro.getLeaveDate());
			co.setPaidMoney(ro.getPaidMoney());
			co.setGuestNumber(guests.size());
			//全款付清
			co.setTotalCost(new BigDecimal(0));
			co.setState("0");// 生效中
			co.setGuests(guests);// 保存住客
			Room room = ro.getRoom();
			room.setAvailable("0");
			room.setState("2");// 房间变成租用状态
			roomService.update(room);
			co.setRoom(room);
			ro.setState("1");
			reserveService.update(ro);
			if (!"".equals(employee_id))
				co.setEmployee(employeeService.getObjectById(Integer.parseInt(employee_id)));
			checkinService.save(co);
			for (Guest guest : guests) {
				guest.setHotel(ro.getRoom().getHotel());
				guest.setRoom(ro.getRoom());
				guest.setCheckInOrder(co);
				guestService.save(guest);
			}

			resMap.put("errorNo", 0);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}

	// 直接住店
	public String checkIn() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String employee_id = JSONMap.get("employee_id") == null ? "" : JSONMap.get("employee_id").toString();
			List<Guest> guests = JSON.parseObject(JSONMap.get("guests").toString(), new TypeReference<List<Guest>>() {
			});
			CheckinOrderBean cob = JSON.parseObject(JSONMap.get("checkinOrder").toString(), CheckinOrderBean.class);
			CheckinOrder co = CheckinOrderConverter.convert2Entity(cob);
			Room room = roomService.getObjectById(cob.getRoom_id());
			room.setAvailable("0");
			room.setState("2");// 房间变成租用(入住)状态
			roomService.update(room);
			co.setTotalCost(new BigDecimal(0));//刚刚入住费用是0
			co.setGuestNumber(guests.size());//入住人数
			co.setGuests(guests);
			co.setRoom(room);
			co.setState("0");// 生效中
			if (!"".equals(employee_id))
				co.setEmployee(employeeService.getObjectById(Integer.parseInt(employee_id)));
			checkinService.save(co);
			for (Guest guest : guests) {
				guest.setHotel(room.getHotel());
				guest.setRoom(room);
				guest.setCheckInOrder(co);
				guestService.save(guest);
			}

			resMap.put("errorNo", 0);
		} catch (Exception e) {
			e.printStackTrace();
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}

	// 查询入住单 用房间号码查找
	// 查到入住单的基本属性以外 附带了个流水的list
	public String queryCheckin() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer room_id = JSON.parseObject(JSONMap.get("room_id").toString(), Integer.class);// 房间的id
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if (!"".equals(room_id))
				condition.append(" and room_id =" + room_id);
			condition.append(" and state = 0");// 状态0是生效
			QueryHelper helper = new QueryHelper(CheckinOrder.class, "co");
			helper.addCondition(condition.toString());
			List<CheckinOrder> checkinOrders = checkinService.getAll(helper);
			if (checkinOrders.size() == 1) {
				CheckinOrder co = checkinOrders.get(0);
				Date now = new Date();
				Date in = co.getInDate();
				int days = daysBetween(in, now);
				//计算总计的消费  总计消费 = 服务费用+房费
				BigDecimal totalCost = co.getTotalCost().add(co.getRoom().getRoomKind().getPriceOneNight().multiply(new BigDecimal(days)));
				co.setTotalCost(totalCost);
				CheckinOrderBean cob = CheckinOrderConverter.convert2Bean(co);
				cob.setRoom_id(room_id);// 数据回传
				// 以下查询住客
				List<Guest> guests = co.getGuests();
				List<GuestBean> gbs = new ArrayList<>();
				for (Guest guest : guests) {
					GuestBean gb = GuestConverter.convert2Bean(guest);
					gbs.add(gb);
				}
				// 以下查询有关的业务流水（消费明细）
				QueryHelper helper2 = new QueryHelper(BusinessFlow.class, "bf");
				condition = new StringBuffer();
				condition.append("1=1");
				condition.append(" and checkinOrder_id = " +co.getId());
				helper2.addCondition(condition.toString());
				List<BusinessFlow> bfs = businessFlowService.getAll(helper2);
				List<BusinessFlowBean> bfbs = new ArrayList<>();
				for (BusinessFlow f : bfs) {
					BusinessFlowBean bfb = BusinessFlowConverter.convert2Bean(f);
					bfbs.add(bfb);
				}
				resMap.put("checkinOrder", cob);
				resMap.put("guests", gbs);
				resMap.put("businessFlows", bfbs);
				resMap.put("errorNo", 0);
			} else {
				resMap.put("errorNo", ErrorConstant.parameter_error.getCode());
				resMap.put("guests", ErrorConstant.parameter_error.getInfo());
			}

		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}

	// 添加服务
	public String addService() {
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			BusinessFlowBean bfb = JSON.parseObject(JSONMap.get("businessFlow").toString(), BusinessFlowBean.class);
			Integer room_id = JSON.parseObject(JSONMap.get("room_id").toString(), Integer.class);// 房间的id
			QueryHelper helper = new QueryHelper(CheckinOrder.class, "co");
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if (!"".equals(room_id))
				condition.append(" and room_id =" + room_id);
			condition.append(" and state = 0");// 状态0是生效
			helper.addCondition(condition.toString());
			List<CheckinOrder> checkinOrders = checkinService.getAll(helper);
			if (checkinOrders.size() == 1) {
				CheckinOrder co = checkinOrders.get(0);
				ServiceKind sk = serviceKindService.getObjectById(bfb.getServiceKind_id());
				Employee e = employeeService.getObjectById(bfb.getEmployee_id());
				
				BusinessFlow bf = BusinessFlowConverter.convert2Entity(bfb);
				bf.setEmployee(e);
				bf.setServiceKind(sk);
				bf.setCheckinOrder(co);
				bf.setRemark(sk.getName());
				bf.setCost(sk.getCost());
				co.setTotalCost(co.getTotalCost().add(bf.getCost()));
				
				checkinService.update(co);
				businessFlowService.save(bf);
				resMap.put("errorNo", 0);
			}else{
				resMap.put("errorNo", ErrorConstant.parameter_error.getCode());
				resMap.put("errorInfo", ErrorConstant.parameter_error.getInfo());
			}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}

	// 退房
	public String checkout() {
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);// 房间的id
			CheckinOrder co = checkinService.getObjectById(id);
			Date now = new Date();
			Date in = co.getInDate();
			int days = daysBetween(in, now);
			//计算总计的消费  总计消费 = 服务费用+房费
			BigDecimal totalCost = co.getTotalCost().add(co.getRoom().getRoomKind().getPriceOneNight().multiply(new BigDecimal(days)));
			co.setTotalCost(totalCost);
			//结款
			co.setPaidMoney(totalCost);
			//修改入住单状态
			co.setState("1");//结款状态
			//修改房间状态
			Room room = co.getRoom();
			room.setState("3");
			roomService.update(room);
			checkinService.update(co);
			//住客离开状态
			List<Guest> guests=	co.getGuests();
			for (Guest guest : guests) {
				guest.setState("1");
				guestService.update(guest);
			}
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	private int daysBetween(Date date1,Date date2){
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		return (int)((time2-time1)/(24*60*60*1000))+1;
	}
}

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
import com.lq.hotel.bean.DepartmentBean;
import com.lq.hotel.bean.EmployeeBean;
import com.lq.hotel.bean.HotelBean;
import com.lq.hotel.bean.RoomKindBean;
import com.lq.hotel.bean.ServiceKindBean;
import com.lq.hotel.convert.DepartmentConverter;
import com.lq.hotel.convert.EmployeeConverter;
import com.lq.hotel.convert.HotelConverter;
import com.lq.hotel.convert.RoomConverter;
import com.lq.hotel.convert.RoomKindConverter;
import com.lq.hotel.convert.ServiceKindConverter;
import com.lq.hotel.entity.Department;
import com.lq.hotel.entity.Employee;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.entity.RoomKind;
import com.lq.hotel.entity.ServiceKind;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.QueryHelper;

@ParentPackage("login-struts")
@Action(value = "login") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class LoginAction extends BaseAction{
	public String login(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String staffId = JSONMap.get("staffId")==null?"":JSONMap.get("staffId").toString();
			String password = JSONMap.get("password")==null?"":JSONMap.get("password").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(staffId))
				condition.append(" and staffId = "+staffId);
			if(!"".equals(password))
				condition.append(" and password = '"+password+"'");
			QueryHelper helper = new QueryHelper(Employee.class, "e");
			helper.addCondition(condition.toString());
			List<Employee> ems = employeeService.getAll(helper);
			if(ems.size()==1){
					Employee employee = ems.get(0);
					EmployeeBean eb = EmployeeConverter.convert2Bean(employee);
					eb.setHotel_id(employee.getHotel().getId());
					eb.setDepartment_id(employee.getDepartment().getId());
					Hotel hotel  = employee.getHotel();
					HotelBean hb = HotelConverter.convert2Bean(hotel); 
					Department de= employee.getDepartment();
					DepartmentBean deb = DepartmentConverter.convert2Bean(de);
					List<ServiceKind> serviceKinds = hotel.getServiceKinds();
				 	List<RoomKind> roomKinds = hotel.getRoomKinds();
				 	List<ServiceKindBean> skbs = new ArrayList<>();
				 	List<RoomKindBean> rkbs = new ArrayList<>();
				 	for (RoomKind roomKind : roomKinds) {
				 		rkbs.add(RoomKindConverter.convert2Bean(roomKind));
					}
				 	for (ServiceKind serviceKind : serviceKinds) {
				 		skbs.add(ServiceKindConverter.convert2Bean(serviceKind));
						
					}
					resMap.put("errorNo", 0);
					resMap.put("employee", eb);
					resMap.put("hotel",hb);
					resMap.put("hotelServiceKinds", skbs);
					resMap.put("hotelRoomKinds", rkbs);
					resMap.put("department",deb);
					resMap.put("key",hotel.getKey().getKeyContent());
				}else{
					resMap.put("errorNo",ErrorConstant.login_fail.getCode());
					resMap.put("errorInfo", ErrorConstant.login_fail.getInfo());
				}
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
}

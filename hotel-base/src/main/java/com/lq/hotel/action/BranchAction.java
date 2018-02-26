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
import com.lq.hotel.annotation.MyAuthority;
import com.lq.hotel.bean.HotelBean;
import com.lq.hotel.convert.HotelConverter;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.QueryHelper;

/**
 * 分店的管理类
 * @author liqiang
 *
 */

@ParentPackage("abstract-struts")
@Action(value = "branch") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class BranchAction extends BaseAction{
	// add edit 取entity的名字
	//query delete 提供需要的字段
	@MyAuthority(privilege="hotel")
	public String addHotel(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			//获取hotelBean
			HotelBean hb  = JSON.parseObject(JSONMap.get("hotel").toString(),HotelBean.class);
			//转化成hotel
			System.out.println(hb.getStar());
			Hotel h = HotelConverter.convert2Entity(hb);
			hotelService.save(h);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		
		return SUCCESS;
	}
	
	@MyAuthority(privilege="hotel")
	public String editHotel(){
		try {
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			HotelBean hb  = JSON.parseObject(JSONMap.get("hotel").toString(),HotelBean.class);

			Hotel h = HotelConverter.convert2Entity(hb);
			Hotel old = hotelService.getObjectById(h.getId());
			h.setKey(old.getKey());
			hotelService.update(h);
			resMap.put("errorNo", 0);
		} catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	//通过id单一删除
	@MyAuthority(privilege="hotel")
	public String deleteHotel(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
//			Hotel h = hotelService.getObjectById(id);
//			可能级联操作已经删了
//			Key key = h.getKey();
//			keyService.delete(key.getId());
			hotelService.delete(id);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	//通过id获取某一个的具体信息
	public String getHotel(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer id = JSON.parseObject(JSONMap.get("id").toString(), Integer.class);
			Hotel h =hotelService.getObjectById(id);
			if(h!=null){
				HotelBean hb = HotelConverter.convert2Bean(h);
				resMap.put("errorNo", 0);
				resMap.put("item", hb);
			}else{
				resMap.put("errorNo", ErrorConstant.not_exist.getCode());
				resMap.put("errorInfo", ErrorConstant.not_exist.getInfo());
			}
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
	//查询
	public String queryHotel(){
		/*		private Integer id;
				private String location;// (浙江省,杭州市,滨江区,下沙六号大街二十五号路口)省市区以英文逗号分隔
				private String coordinate; // 坐标 可用于调度百度地图api 106.581515,29.615467 x,y
											// 英文,分割
				private String state;// 状态 1正常营业 2尚未营业 3停业整顿 4其他
				private String star;// 星级
				private Date createDate;// 插入这条记录的日期
				private Date bussinessDate;// 开始营业的日期
				private String remark;// 描述
		*/	
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			String name = JSONMap.get("name") == null ? "" : JSONMap.get("name").toString();
			String location = JSONMap.get("location") == null ? "" : JSONMap.get("location").toString();
			String  state = JSONMap.get("state") == null ? "" : JSONMap.get("state").toString();
			String  star = JSONMap.get("star") == null ? "" : JSONMap.get("star").toString();
			StringBuffer condition = new StringBuffer();
			condition = condition.append("1=1");
			if(!"".equals(name))
				condition.append(" and name like '%"+name+"%'");
			if(!"".equals(location))
				condition.append(" and location like '%"+location+"%'");
			if(!"".equals(state))
				condition.append(" and state ='"+state+"'");
			if(!"".equals(star))
				condition.append(" and star ='"+star+"'");
			QueryHelper helper = new QueryHelper(Hotel.class, "h");
			helper.addCondition(condition.toString());
//			System.out.println(helper.getHql());
			List<Hotel> hotels = hotelService.getAll(helper);
			List<HotelBean> hbs = new ArrayList<>();
			for (Hotel hotel : hotels) {
			 	HotelBean hb = HotelConverter.convert2Bean(hotel);
			 	hb.setKeyContent(hotel.getKey()==null?"":hotel.getKey().getKeyContent());
				hbs.add(hb);
			}
			resMap.put("count", hbs.size());
			resMap.put("errorNo", 0);
			resMap.put("items", hbs);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
	
//=====================================================================
	@MyAuthority(privilege="hotel")
	public String stateHotel(){
		try{
			JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
			Integer hotel_id =JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
			Hotel h = hotelService.getObjectById(hotel_id);
			String state = JSONMap.get("state").toString();
			h.setState(state);
			hotelService.update(h);
			resMap.put("errorNo", 0);
		}catch (Exception e) {
			resMap.put("errorNo", ErrorConstant.system_error.getCode());
			resMap.put("errorInfo", e.getMessage());
		}
		return SUCCESS;
	}
}

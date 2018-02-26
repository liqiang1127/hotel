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
import com.lq.hotel.convert.CooperationConverter;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.webUtils.QueryHelper;

/**
 * 第三方合作的管理类
 * @author liqiang
 *
 */

@ParentPackage("abstract-struts")
@Action(value = "cooperation") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class CooperAction extends BaseAction{
	/*
	 * 	private Integer id; //id
	private String name;  //企业名称
	private Date createDate; 	//	合作签署时间
	private Date endDate;	//合作结束时间
	private String state;	//合作状态 0 正常 1 挂起
	private String rmark; // 备注
	 */
		public String addCooperation(){
			try {
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				CooperationBean cb = JSON.parseObject(JSONMap.get("cooperation").toString(), CooperationBean.class);
				Cooperation co = CooperationConverter.convert2Entity(cb);
				QueryHelper helper = new QueryHelper(Cooperation.class, "c");
				helper.addCondition("c.name = ?0", co.getName());
				List<Cooperation> cos = cooperationService.getAll(helper);
				if(cos.isEmpty()){
					cooperationService.save(co);
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
		
		
		public String editCooperation(){
			try{
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				CooperationBean cb = JSON.parseObject(JSONMap.get("cooperation").toString(), CooperationBean.class);
				Cooperation co = CooperationConverter.convert2Entity(cb);
				Cooperation old = cooperationService.getObjectById(co.getId());
				co.setKey(old.getKey());
				cooperationService.update(co);
				resMap.put("errorNo", 0);
			}catch (Exception e) {
				resMap.put("errorNo", ErrorConstant.system_error.getCode());
				resMap.put("errorInfo", e.getMessage());
			}
			return SUCCESS;
		}
		
		
		public String deteteCooperation(){
			try{
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				Integer id = JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
				if(cooperationService.getObjectById(id)!=null){
					cooperationService.delete(id);
					resMap.put("errorNo", 0);					
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
		
		
		public String getCooperation(){
			try{
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				Integer id = JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
				Cooperation co = cooperationService.getObjectById(id);
				if(co!=null){
					CooperationBean cb = CooperationConverter.convert2Bean(co);
					resMap.put("errorNo", 0);
					resMap.put("item", cb);
				}else{
					resMap.put("errorNo", ErrorConstant.not_exist.getCode());
					resMap.put("errorInfo",ErrorConstant.not_exist.getInfo());
				}
			}catch (Exception e) {
				resMap.put("errorNo", ErrorConstant.system_error.getCode());
				resMap.put("errorInfo", e.getMessage());
			}
			return SUCCESS;
		}
		
		/*
		 * 	private Integer id; //id
		private String name;  //企业名称
		private Date createDate; 	//	合作签署时间
		private Date endDate;	//合作结束时间
		private String state;	//合作状态 0 正常 1 挂起
		private String rmark; // 备注
		 */
		public String queryCooperation(){
			try {
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				String name = JSONMap.get("name") == null ? "" : JSONMap.get("name").toString();
				String  state = JSONMap.get("state") == null ? "" : JSONMap.get("state").toString();
				StringBuffer condition = new StringBuffer();
				condition = condition.append("1=1");
				if(!"".equals(name))
					condition.append(" and name like '%"+name+"%'");
				if(!"".equals(state))
					condition.append(" and state ="+state);
				QueryHelper helper = new QueryHelper(Cooperation.class, "c");
				helper.addCondition(condition.toString());
				List<Cooperation> cos = cooperationService.getAll(helper);
				List<CooperationBean> cbs = new ArrayList<>();
				for (Cooperation cooperation : cos) {
					CooperationBean cb = CooperationConverter.convert2Bean(cooperation);
					cb.setKeyContend(cooperation.getKey()==null?"":cooperation.getKey().getKeyContent());
					cbs.add(cb);
				}
				resMap.put("count", cbs.size());
				resMap.put("errorNo", 0);
				resMap.put("items", cbs);
			} catch (Exception e) {
				resMap.put("errorNo", ErrorConstant.system_error.getCode());
				resMap.put("errorInfo", e.getMessage());
			}
			return SUCCESS;
		}
		
		public String stateCoop(){
			try{
				JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
				Integer coop_id =JSON.parseObject(JSONMap.get("id").toString(),Integer.class);
				Cooperation h = cooperationService.getObjectById(coop_id);
				String state = JSONMap.get("state").toString();
				h.setState(state);
				cooperationService.update(h);
				resMap.put("errorNo", 0);
			}catch (Exception e) {
				resMap.put("errorNo", ErrorConstant.system_error.getCode());
				resMap.put("errorInfo", e.getMessage());
			}
			return SUCCESS;
		}
}

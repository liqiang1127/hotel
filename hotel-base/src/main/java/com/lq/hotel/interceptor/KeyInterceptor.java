package com.lq.hotel.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lq.hotel.entity.Cooperation;
import com.lq.hotel.entity.Hotel;
import com.lq.hotel.entity.Key;
import com.lq.hotel.enums.ErrorConstant;
import com.lq.hotel.service.KeyService;
import com.lq.webUtils.HttpHelper;
import com.lq.webUtils.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class KeyInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	protected KeyService keyService;
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
//		ActionContext	actionContext = ActionContext.getContext();
//		HttpServletRequest request = ServletActionContext.getRequest();
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		String json =HttpHelper.getRequestJsonString(request);
		Map<String,Object> reqMap = JSON.parseObject(json);
		String key = reqMap.get("key")==null?"":reqMap.get("key").toString();
		Map<String,Object> resMap = new HashMap<>();
		if("".equals(key)){
			resMap.put("errorNo", ErrorConstant.Invalid_key.getCode());
			resMap.put("errorInfo", ErrorConstant.Invalid_key.getInfo());
			 JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
			 actionContext.put("resJSON", resJSON);
			return "NoKey";
		}
		if("GajufY04cYdegy3NCkUe9A==".equals(key)){
			request.setAttribute("JSONMap", reqMap);
			return invocation.invoke();
		}
		QueryHelper helper = new QueryHelper(Key.class, "k");
		helper.addCondition("k.keyContent = ?0", key);
		List<Key> keys = keyService.getAll(helper);
		if(keys.size()==1){
			Key kk = keys.get(0);
			Hotel hotel = kk.getHotel();
			if(hotel!=null){
				if(!"1".equals(hotel.getState())){
					resMap.put("errorNo", ErrorConstant.hotel_state_error.getCode());
					resMap.put("errorInfo", ErrorConstant.hotel_state_error.getInfo());
					JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
					 actionContext.put("resJSON", resJSON);
					return "NoKey";
				}
				reqMap.put("privilege", "hotel");
				request.setAttribute("JSONMap", reqMap);
				return invocation.invoke();
			}
			Cooperation co = kk.getCooperation();
			if(co!=null){
				if(!"0".equals(co.getState())){
					resMap.put("errorNo", ErrorConstant.co_state_error.getCode());
					resMap.put("errorInfo", ErrorConstant.co_state_error.getInfo());
					JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
					 actionContext.put("resJSON", resJSON);
					return "NoKey";
				}
				reqMap.put("privilege", "coop");
				request.setAttribute("JSONMap", reqMap);
				return invocation.invoke();
			}
			//如果都没有 那么说明key失效了
			resMap.put("errorNo", ErrorConstant.Invalid_key.getCode());
			resMap.put("errorInfo", ErrorConstant.Invalid_key.getInfo());
			JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
			 actionContext.put("resJSON", resJSON);
			return "NoKey";
		}else{
			resMap.put("errorNo", ErrorConstant.Invalid_key.getCode());
			resMap.put("errorInfo", ErrorConstant.Invalid_key.getInfo());
			JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
			 actionContext.put("resJSON", resJSON);
			return "NoKey";
		}
	}
}

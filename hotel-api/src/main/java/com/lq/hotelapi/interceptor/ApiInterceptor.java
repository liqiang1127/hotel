package com.lq.hotelapi.interceptor;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.alibaba.fastjson.JSON;
import com.lq.hotelapi.middle.MiddleCilent;
import com.lq.webUtils.HttpHelper;
import com.lq.webUtils.PasswordEncoder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class ApiInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		Map<String,Object> resMap = new HashMap<>();
		String json =HttpHelper.getRequestJsonString(request);
		Map<String,Object> reqMap = JSON.parseObject(json);
		request.setAttribute("JSONMap", reqMap);  
		String coop_id = reqMap.get("coop_id")==null?"":reqMap.get("coop_id").toString();
		//查询id所对应的key content 作为secret
		Map<String,Object> reqMap2Base = new HashMap<>();
		reqMap2Base.put("coop_id", coop_id);
		json  =  MiddleCilent.sendHttpJsonRequest("http://localhost:8280/hotel-base/api!getKeyContentByCoop.action", JSON.toJSONString(reqMap2Base));
		Map<String,Object> reqMap2 = JSON.parseObject(json);
		if("0".equals(reqMap2.get("errorNo").toString())){
			String keyContent = reqMap2.get("keyContent").toString();
			String data = reqMap.get("data").toString();
			String timestamp = reqMap.get("timestamp").toString();
			Long timeReq = Long.parseLong(timestamp);
			Long timeNow = new Date().getTime();
			if(timeNow-timeReq > 1800*1000){
				String errorInfo = "该请求已过期！";
				resMap.put("errorInfo", errorInfo);
				resMap.put("errorNo", "-1");
				actionContext.put("resJSON", JSON.toJSONString(resMap));
				return "apiRefuse";
			}
			String sign = reqMap.get("sign").toString();
			Map<String,Object> dataMap = JSON.parseObject(data);
			//字典序排序
			List<String> keys = new ArrayList<>(dataMap.keySet());
			Collections.sort(keys);
			String datastr = "";
			for (String string : keys) {
				datastr+=(String)dataMap.get(string);
			}
			datastr = datastr+ keyContent+timestamp;//该字符串用于签名
			String signSer = PasswordEncoder.passwordEncode(datastr);//md5加密 base64明文传输
			if(sign.equals(signSer)){
				reqMap.put("keyContent", keyContent);
				request.setAttribute("JSONMap", reqMap);
				return invocation.invoke();
			}else{
				//签名错误
				String errorInfo = "签名错误！";
				resMap.put("errorInfo", errorInfo);
				resMap.put("errorNo", "-1");
				actionContext.put("resJSON", JSON.toJSONString(resMap));
				return "apiRefuse";
			}
		}
		String errorInfo = "无效的coop_id！";
		resMap.put("errorInfo", errorInfo);
		resMap.put("errorNo", "-1");
		actionContext.put("resJSON", JSON.toJSONString(resMap));
		return "apiRefuse";
	}
}

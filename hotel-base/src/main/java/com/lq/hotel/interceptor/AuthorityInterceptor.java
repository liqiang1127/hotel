package com.lq.hotel.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lq.hotel.annotation.MyAuthority;
import com.lq.hotel.enums.ErrorConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@SuppressWarnings("serial")
public class AuthorityInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String,Object> resMap = new HashMap<>();
		String methodName = invocation.getProxy().getMethod();
		Class clazz = invocation.getAction().getClass(); // 获取类对象
		Method currentMethod = clazz.getMethod(methodName);
		// 检查Action类AnnotationTest是否含有@Authority注解
		if (currentMethod.isAnnotationPresent(MyAuthority.class)) {
			// 从request中获取map
			Map<String, Object> map = (Map<String, Object>) ServletActionContext.getRequest().getAttribute("JSONMap");
			// 取得权限验证的注解
			MyAuthority authority = currentMethod.getAnnotation(MyAuthority.class);
			// 取得当前请求需要的权限
			String privilegeNeed = authority.privilege() == null ? "" : authority.privilege();
			String privilege = map.get("privilege") == null ? "" : map.get("privilege").toString();
			if (!"".equals(privilegeNeed) && privilegeNeed.equals(privilege)) {
				return invocation.invoke();
			}else{
				resMap.put("errorNo", ErrorConstant.no_privilege.getCode());
				resMap.put("errorInfo", ErrorConstant.no_privilege.getInfo());
				 JSONObject resJSON= JSONObject.parseObject(JSON.toJSONString(resMap));
				 actionContext.put("resJSON", resJSON);
				return "NoKey";
			}
		}
		return invocation.invoke();
	}
}

package com.lq.hotel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.lq.hotel.action.core.BaseAction;

//@ParentPackage("json-default")abstract-struts
@ParentPackage("abstract-struts")
@Action(value = "test") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为struts2Test
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class TestAction extends BaseAction {
	/**
	 * MethodName: test Description:
	 * @author lq
	 */
	
	//localhost:8280/hotel-base/test!test.action 测试连接的类
	public String test() {
		resMap.put("errorNo", 0);
		resMap.put("errorInfo", "连接正常");
		return SUCCESS;
	}
}

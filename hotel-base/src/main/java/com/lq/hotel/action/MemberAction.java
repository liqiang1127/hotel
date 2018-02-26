package com.lq.hotel.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.lq.hotel.action.core.BaseAction;

/**
 * 会员暂缓
 * 处理会员操作的Action
 * 会员增删改查
 * 会员种类的增删改查
 * @author liqiang
 *
 */
@ParentPackage("abstract-struts")
@Action(value = "member") // 使用convention-plugin插件提供的@Action注解将一个普通java类标注为一个可以处理用户请求的Action，Action的名字为employee
@Namespace("/") // 使用convention-plugin插件提供的@Namespace注解为这个Action指定一个命名空间
@Results({ @Result(name = "success", type = "json", params = { "root", "resMap" }) })
public class MemberAction extends BaseAction{
	public String addMemberType(){
		return SUCCESS;
	}
	
	public String deleteMemberType(){
		return SUCCESS;
	}
	
}

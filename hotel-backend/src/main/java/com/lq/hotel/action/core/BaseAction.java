package com.lq.hotel.action.core;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

@SuppressWarnings("serial")
public class BaseAction {
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected HttpSession session = ServletActionContext.getRequest().getSession();
	protected ServletContext application  = ServletActionContext.getServletContext();
	protected  Map<String,Object> reqMap = new HashMap<String,Object>(){{
		put("key",session.getAttribute("key"));
	}};
	protected  Map<String,Object> resMap = new HashMap<>();
}

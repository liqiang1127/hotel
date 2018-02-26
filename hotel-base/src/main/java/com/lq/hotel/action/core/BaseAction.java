package com.lq.hotel.action.core;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.lq.hotel.service.BusinessFlowService;
import com.lq.hotel.service.CheckinService;
import com.lq.hotel.service.CooperationService;
import com.lq.hotel.service.DepartmentService;
import com.lq.hotel.service.EmployeeService;
import com.lq.hotel.service.GuestService;
import com.lq.hotel.service.HotelService;
import com.lq.hotel.service.KeyService;
import com.lq.hotel.service.ReserveService;
import com.lq.hotel.service.RoomKindService;
import com.lq.hotel.service.RoomService;
import com.lq.hotel.service.ServiceKindService;
import com.lq.webUtils.HttpHelper;
import com.lq.webUtils.PageResult;
import com.opensymphony.xwork2.Preparable;

public class BaseAction implements Preparable{
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected ReserveService reserveService;
	@Resource
	protected RoomKindService roomKindService;
	@Resource
	protected CheckinService checkinService;
	@Resource
	protected KeyService keyService;
	@Resource
	protected RoomService roomService;
	@Resource
	protected HotelService hotelService;
	@Resource
	protected GuestService guestService;
	@Resource
	protected EmployeeService employeeService;
	@Resource
	protected CooperationService cooperationService;
	@Resource
	protected BusinessFlowService businessFlowService;
	@Resource
	protected ServiceKindService serviceKindService;
	// ==========================================================
	protected String strTitle;
	protected String[] selectedRow;
	protected PageResult pageResult;
	private int pageNumber;
	private int pageSize;
	// ==========================================================
	protected static final String SUCCESS = "success";
	// ==========================================================
	protected Map<String, Object> resMap = new HashMap<>();
	protected HttpServletRequest request = ServletActionContext.getRequest();
	protected HttpServletResponse response = ServletActionContext.getResponse();
	protected HttpSession session = ServletActionContext.getRequest().getSession();
	protected ServletContext application  = ServletActionContext.getServletContext();
	protected Map<String, Object> JSONMap;
	public Map<String, Object> getResMap() {
		return resMap;
	}

	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public String[] getSelectedRow() {
		return selectedRow;
	}

	public void setSelectedRow(String[] selectedRow) {
		this.selectedRow = selectedRow;
	}

	public String getStrTitle() {
		return strTitle;
	}

	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public void prepare() throws Exception {
		JSONMap = (Map<String, Object>)request.getAttribute("JSONMap");
	}
}

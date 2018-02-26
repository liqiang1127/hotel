package com.lq.hotel.constant;

public class FunctionConstant {
	private static String baseUrl = "http://localhost:8280/hotel-base/";
	//=======================================================
	public static final String addDepartment = baseUrl+"personnel!addDepartment.action";
	public static final String editDepartment = baseUrl +"personnel!editDepartment.action";
	public static final String queryDepartment = baseUrl +"personnel!queryDepartment.action";
	public static final String getDepartment = baseUrl +"personnel!getDepartment.action";
	
	
	
	public static final String addHotel = baseUrl +"branch!addHotel.action";
	public static final String queryHotel = baseUrl +"branch!queryHotel.action";
	public static final String getHotel = baseUrl +"branch!getHotel.action";
	public static final String editHotel = baseUrl +"branch!editHotel.action";
	
	public static final String addCooperation = baseUrl +"cooperation!addCooperation.action";
	public static final String queryCooperation = baseUrl +"cooperation!queryCooperation.action";
	public static final String getCooperation = baseUrl +"cooperation!getCooperation.action";
	public static final String editCooperation = baseUrl +"cooperation!editCooperation.action";
	
	
	public static final String addRoomKind = baseUrl +"manage!addRoomKind.action";
	public static final String queryRoomKind = baseUrl +"manage!queryRoomKind.action";
	public static final String getRoomKind = baseUrl +"manage!getRoomKind.action";
	public static final String editRoomKind = baseUrl +"manage!editRoomKind.action";
	public static final String queryHotelRoomKind = baseUrl +"manage!queryHotelRoomKind.action";
	public static final String editHotelRoomKind = baseUrl +"manage!editHotelRoomKind.action";
	
	
	public static final String addServiceKind = baseUrl +"manage!addServiceKind.action";
	public static final String queryServiceKind = baseUrl +"manage!queryServiceKind.action";
	public static final String getServiceKind = baseUrl +"manage!getServiceKind.action";
	public static final String editServiceKind = baseUrl +"manage!editServiceKind.action";
	public static final String queryHotelServiceKind = baseUrl +"manage!queryHotelServiceKind.action";
	public static final String editHotelServiceKind = baseUrl +"manage!editHotelServiceKind.action";
	
	public static final String addEmployee = baseUrl+"personnel!addEmployee.action";
	public static final String editEmployee = baseUrl +"personnel!editEmployee.action";
	public static final String queryEmployee = baseUrl +"personnel!queryEmployee.action";
	public static final String getEmployee = baseUrl +"personnel!getEmployee.action";
	
	public static final String queryRoom = baseUrl +"manage!queryRoom.action";
	public static final String addRoom = baseUrl +"manage!addRoom.action";
	public static final String getRoom = baseUrl +"manage!getRoom.action";
	public static final String editRoom = baseUrl +"manage!editRoom.action";
	
	public static final String addReserve = baseUrl +"reserve!addReserve.action";
	public static final String queryReserve = baseUrl +"reserve!queryReserve.action";
	
	
	public static final String checkInFromRes = baseUrl +"service!checkInFromRes.action";
	public static final String checkIn = baseUrl +"service!checkIn.action";
	public static final String addService = baseUrl +"service!addService.action";
	public static final String queryCheckin = baseUrl +"service!queryCheckin.action";
	public static final String checkout = baseUrl +"service!checkout.action";
	
	public static final String login = baseUrl+"login!login.action";
	public static final String newPayment = baseUrl+"pay!newPayment.action";
	public static final String queryPayment = baseUrl+"pay!queryPayment.action";
	
}

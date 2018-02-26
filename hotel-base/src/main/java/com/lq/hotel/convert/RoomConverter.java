package com.lq.hotel.convert;

import com.lq.hotel.bean.RoomBean;
import com.lq.hotel.entity.Room;

public class RoomConverter {
//	private Integer id; // 客房编号
//	private String area; // 朝向朝南朝北 1 north 2 south
//	private String floor; // 所属楼层
//	private String no; // 房间号
//	private String telphone; // 分机电话
//	private String state; // 客房状态
//	private String available; // 是否可用
//	private String picture; // 房间图片 url
	
	public static Room convert2Entity(RoomBean rb){
		Room r = new Room();
		r.setId( rb.getId());
		r.setAvailable(rb.getAvailable());
		r.setFloor(rb.getFloor());
		r.setNo(rb.getNo());
		r.setPicture(rb.getPicture());
		r.setState(rb.getState());
		r.setTelphone( rb.getTelphone());
		r.setArea(rb.getArea());
		return r;
	}
	public static RoomBean convert2Bean(Room r){
		RoomBean rb = new RoomBean();
		rb.setId( r.getId());
		rb.setAvailable(r.getAvailable());
		rb.setFloor(r.getFloor());
		rb.setNo(r.getNo());
		rb.setPicture(r.getPicture());
		rb.setState(r.getState());
		rb.setTelphone( r.getTelphone());
		rb.setArea(r.getArea());
		return rb;
	}
}

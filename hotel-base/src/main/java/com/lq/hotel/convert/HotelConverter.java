package com.lq.hotel.convert;


import com.lq.hotel.bean.HotelBean;
import com.lq.hotel.entity.Hotel;

public class HotelConverter {
	public static HotelBean convert2Bean(Hotel h){
		HotelBean hb = new HotelBean();
/*		private Integer id;
		private String location;// (浙江省,杭州市,滨江区,下沙六号大街二十五号路口)省市区以英文逗号分隔
		private String coordinate; // 坐标 可用于调度百度地图api 106.581515,29.615467 x,y
									// 英文,分割
		private String state;// 状态 1正常营业 2尚未营业 3停业整顿 4其他
		private String star;// 星级
		private Date createDate;// 插入这条记录的日期
		private Date bussinessDate;// 开始营业的日期
		private String remark;// 描述
*/		
		hb.setId(h.getId());
		hb.setLocation(h.getLocation());
		hb.setCoordinate(h.getCoordinate());
		hb.setState(h.getState());
		hb.setStar(h.getStar());
		hb.setCreateDate(h.getCreateDate());
		hb.setBussinessDate(h.getBussinessDate());
		hb.setRemark(h.getRemark());
		hb.setName(h.getName());
		return hb;
	}
	
	public static Hotel convert2Entity(HotelBean hb){
		Hotel h = new Hotel();
		h.setId(hb.getId());
		h.setLocation(hb.getLocation());
		h.setCoordinate(hb.getCoordinate());
		h.setState(hb.getState());
		h.setStar(hb.getStar());
		h.setCreateDate(hb.getCreateDate());
		h.setBussinessDate(hb.getBussinessDate());
		h.setRemark(hb.getRemark());
		h.setName(hb.getName());
		return h;
	}
}

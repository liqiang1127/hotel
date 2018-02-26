package com.lq.hotel.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "t_key", schema = "hotel")
public class Key {
	private Integer id;
	private String keyContent;
	private Date createTime;
	private Date updateTime;
	
	private Hotel hotel;
	private Cooperation cooperation;
	
    @GenericGenerator(name = "generator", strategy = "increment")
    @Id
    @GeneratedValue(generator = "generator")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column
	public String getKeyContent() {
		return keyContent;
	}
	public void setKeyContent(String keyContent) {
		this.keyContent = keyContent;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@OneToOne(mappedBy = "key",fetch=FetchType.LAZY)
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	@OneToOne(mappedBy = "key",fetch=FetchType.LAZY)
	public Cooperation getCooperation() {
		return cooperation;
	}
	public void setCooperation(Cooperation cooperation) {
		this.cooperation = cooperation;
	}
	
}

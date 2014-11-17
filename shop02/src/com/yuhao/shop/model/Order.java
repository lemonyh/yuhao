package com.yuhao.shop.model;

import java.util.Date;
import java.util.List;

public class Order {
	private int id;
	private Date buyData;
	private Date payData;
	private Date confirmData;
	private int status;
	private User user;
	private Address address;
	private List<Goods> goods;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBuyData() {
		return buyData;
	}
	public void setBuyData(Date buyData) {
		this.buyData = buyData;
	}
	public Date getPayData() {
		return payData;
	}
	public void setPayData(Date payData) {
		this.payData = payData;
	}
	public Date getConfirmData() {
		return confirmData;
	}
	public void setConfirmData(Date confirmData) {
		this.confirmData = confirmData;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Goods> getGoods() {
		return goods;
	}
	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

}

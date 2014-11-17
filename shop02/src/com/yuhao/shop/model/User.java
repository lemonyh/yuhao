package com.yuhao.shop.model;

import java.util.List;

/*
 * 定义用户实体类
 * 
 * 生成对应的set、get
 */
public class User {
	@Override
	public String toString() {
		return "User [address=" + address + ", id=" + id + ", nickname="
				+ nickname + ", password=" + password + ", type=" + type
				+ ", username=" + username + "]";
	}
	private int id;
	@ValidataForm(type=ValidataType.NotNull,errorMsg="用户名不能为空")
	private String username;
	@ValidataForm(type=ValidataType.NotNull,errorMsg="密码不能为空")
	private String password;
	@ValidataForm(type=ValidataType.NotNull,errorMsg="用户昵称能为空")
	private String nickname;
	private int type;
	private  List<Address> address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

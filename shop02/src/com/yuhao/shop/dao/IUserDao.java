package com.yuhao.shop.dao;

import com.yuhao.shop.model.Pager;
import com.yuhao.shop.model.User;

public interface IUserDao {
	public void add(User user);
	public void delete(int id);
	public void update(User user);
	public User loadByUserName(String name);
	public User load(int id);
	public Pager<User> find(String username);
	public User login(String username,String password);

}

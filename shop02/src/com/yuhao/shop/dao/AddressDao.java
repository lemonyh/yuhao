package com.yuhao.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yuhao.shop.model.Address;
import com.yuhao.shop.model.ShopDi;
import com.yuhao.shop.model.ShopException;
import com.yuhao.shop.model.User;

public class AddressDao extends BaseDao<Address> implements IAddressDao {
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}
	@ShopDi("userDao")
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void add(Address address, int userId) {
		User u=userDao.load(userId);
		if(u==null)throw new ShopException("添加地址的用户不存在");
		address.setUser(u);
		super.add(address);
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		super.delete(Address.class, id);
	}

	public List<Address> list(int userId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return super.list(Address.class, params);

	}

	public Address load(int id) {
		return super.load(Address.class, id);
	}

	public void update(Address address) {
		super.update(address);

	}
	
	

	

}

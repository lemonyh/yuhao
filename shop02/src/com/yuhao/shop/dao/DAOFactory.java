package com.yuhao.shop.dao;

public class DAOFactory {
	public static IAddressDao getAddressDao()
	{
		return new AddressDao();
	}
	public static UserDao getUserDao()
	{
		return new UserDao();
	}
}

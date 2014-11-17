/**
 * 
 */
package com.yuhao.shop.dao;

/**Administrator
 * 2014
 *@qq 903450130
 */
public interface IFactoryDao {
//	public IUserDao createUserDao();
//	public IAddressDao createAddressDao();
	public Object getDao(String name);

}

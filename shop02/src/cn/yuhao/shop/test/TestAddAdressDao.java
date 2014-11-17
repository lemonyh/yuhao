package cn.yuhao.shop.test;

import java.util.List;

import org.junit.Test;


import cn.yuhao.shop.util.DaoUtil;

import com.yuhao.shop.dao.AddressDao;
import com.yuhao.shop.dao.DAOFactory;
import com.yuhao.shop.dao.IAddressDao;
import com.yuhao.shop.dao.IUserDao;
import com.yuhao.shop.model.Address;
import com.yuhao.shop.model.Pager;
import com.yuhao.shop.model.SystemContext;
import com.yuhao.shop.model.User;

public class TestAddAdressDao extends BaseTest{
	private IAddressDao addressDao=(IAddressDao) DaoUtil.createDaOFactory().getDao("addressDao");
	private IUserDao ud;
	@Test
	public void test9()
	{
		new AddressDao();
	}
	@Test
	public void testLoad()
	{
		System.out.println(addressDao.load(1).getName());
	}
	@Test
	public void testlist()
	{
		List<Address> list=addressDao.list(1);
		System.out.println(list);
		for(Address i:list)
		{
			System.out.println(i.getName());
		}
	}
	
	@Test
	public void  test01()
	{
		Address address=new Address();
		//address.setId(1);
		address.setName("长沙开福路长沙大学9号");
		address.setPhone("12346");
		address.setPostcode("yuhao");
		addressDao.add(address,1);
	}

	@Test
	public void testadduser()
	{
		ud=DAOFactory.getUserDao();
		User u=new User();
		u.setNickname("余昊");
		u.setPassword("123");
		u.setType(1);
		u.setUsername("yy");
		ud.add(u);
		
	}
	@Test
	public void testfind()
	{
		ud=DAOFactory.getUserDao();
		SystemContext.setPageOffset(0);
		SystemContext.setPageSize(15);//
		SystemContext.setOrder("desc");//设置升降排序方式
		SystemContext.setSort("id");//设置排序的根据
		Pager<User> pa=ud.find("");
		System.out.println(pa.getTotalRecord());
		for(User u: pa.getDatas())
		{
			System.out.println(u);
		}
		
	}
	@Test
	public void testload()
	{
		ud=(IUserDao) DaoUtil.createDaOFactory().getDao("userDao");
		User user=ud.load(1);
		System.out.println(user);
		
	}
	

}

package com.yuhao.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.yuhao.shop.util.MYBatisUtil;

import com.yuhao.shop.model.Pager;
import com.yuhao.shop.model.ShopException;
import com.yuhao.shop.model.SystemContext;
import com.yuhao.shop.model.User;

public class UserDao extends BaseDao<User> implements IUserDao {

	public void add(User user) {

		User tu = this.loadByUserName(user.getUsername());

		if (tu != null)
			throw new ShopException("用户已经存在");
		super.add(user);

	}

	public void delete(int id) {
		//TODO 需要先删除关联对象
		super.delete(User.class, id);
	}

	public Pager<User> find(String name) {
		Map<String,Object> params = new HashMap<String, Object>();
		if(name!=null&&!name.equals(""))
			params.put("name", "%"+name+"%");
		return super.find(User.class, params);
	}

	public User load(int id) {
		return super.load(User.class, id);
	}

	public User loadByUserName(String username) {
		SqlSession session = null;
		User user = null;
		try {

			session = MYBatisUtil.createSession();
			user = (User) session.selectOne(User.class.getName()
					+ ".load_by_name", username);

		} finally {
			MYBatisUtil.closeSession(session);
		}
		return user;
	}

	public void update(User user) {
		super.update(user);
	}

	public User login(String username, String password) {
		User u = this.loadByUserName(username);
		if (u == null)
			throw new ShopException("用户不存在");
		if (!password.endsWith(u.getPassword()))
			throw new ShopException("用户密码存在");
		return u;
	}

}

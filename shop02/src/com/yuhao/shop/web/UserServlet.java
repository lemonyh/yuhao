/**
 * 
 */
package com.yuhao.shop.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yuhao.shop.util.RequestUtil;

import com.yuhao.shop.dao.IUserDao;
import com.yuhao.shop.dao.UserDao;
import com.yuhao.shop.model.Pager;
import com.yuhao.shop.model.ShopDi;
import com.yuhao.shop.model.ShopException;
import com.yuhao.shop.model.User;

/**Administrator
 * 2014
 *@qq 903450130
 */
public class UserServlet extends BaseServlet {

	
	private IUserDao userDao;
	@ShopDi
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	public String list(HttpServletRequest request,HttpServletResponse response)
	{
		Pager<User> users=userDao.find("");
		request.setAttribute("users", users);
		return "user/list.jsp";
	}
	public String addInput(HttpServletRequest request,HttpServletResponse response)
	{
		return "user/addInput.jsp";
	}
	public String add(HttpServletRequest request,HttpServletResponse response)
	{
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
//		String nickname=request.getParameter("nickname");
//		User u=new User();
//		u.setNickname(nickname);
//		u.setPassword(password);
//		u.setUsername(username);
	//	userDao.add(u);
		User u=(User) RequestUtil.setParam(User.class, request);
		boolean isvalidate= RequestUtil.validate(User.class, request);
		if(!isvalidate)
		{
		    return "user/addInput.jsp";	
		}
		try{
			userDao.add(u);
		}
		catch(ShopException e)
		{
			request.setAttribute("e",e);
			return "inc/error.jsp";
		}
		return redirPath+"user.do?method=list";
	}
	public String delete(HttpServletRequest request,HttpServletResponse response)
	{
		int id=Integer.parseInt(request.getParameter("id"));
		userDao.delete(id);
		return redirPath+"user.do?method=list";
	}
	public String updateInput(HttpServletRequest request,HttpServletResponse response)
	{
		int id=Integer.parseInt(request.getParameter("id"));
		User u=userDao.load(id);
		request.setAttribute("user", u);
		return "user/updateInput.jsp";
	}
	public String update(HttpServletRequest request,HttpServletResponse response)
	{
		User tu=(User) RequestUtil.setParam(User.class, request);
		boolean isvalidate= RequestUtil.validate(User.class, request);
		int id=Integer.parseInt(request.getParameter("id"));
		User user=userDao.load(id);
		user.setNickname(tu.getNickname());
		if(!isvalidate)
		{
			request.setAttribute("user", user);
		    return "user/updateInput.jsp";	
		}

	
		user.setPassword(tu.getPassword());
		user.setNickname(tu.getNickname());
		userDao.update(user);
		return redirPath+"user.do?method=list";
	}
	public String changType(HttpServletRequest request,HttpServletResponse response)
	{
		int id=Integer.parseInt(request.getParameter("id"));
		User u=userDao.load(id);
		if(u.getType()==0){
			u.setType(1);
		}
		else{
			u.setType(0);
		}
		userDao.update(u);
		return redirPath+"user.do?method=list";
	}
	
	
	
	
	
	
	
}

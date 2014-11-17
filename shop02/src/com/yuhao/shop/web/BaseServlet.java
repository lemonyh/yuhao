/**
 * 
 */
package com.yuhao.shop.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yuhao.shop.util.DaoUtil;

/**Administrator
 * 2014
 *@qq 903450130
 */
public class BaseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String redirPath="redirect";
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				DaoUtil.diDao(this);
				String method=request.getParameter("method");
				Method m = this.getClass().getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
				String path=(String)m.invoke(this, request,response);
				if(path.startsWith(redirPath))
				{
					System.out.println(path);
					String rp=path.substring(redirPath.length());
					response.sendRedirect(rp);
				}
				else{
					request.getRequestDispatcher("/WEB-INF/"+path).forward(request, response);
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}

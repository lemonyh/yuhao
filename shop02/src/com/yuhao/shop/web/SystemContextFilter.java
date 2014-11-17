/**
 * 
 */
package com.yuhao.shop.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.yuhao.shop.model.SystemContext;

/**Administrator
 * 2014
 *@qq 903450130
 */
public class SystemContextFilter implements Filter{

	public void destroy() {

	}
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		try {
			int pageOffset = 0;
			int pageSize = 15;
			String sort = req.getParameter("sort");
			String order = req.getParameter("order");
			try {
				pageOffset = Integer.parseInt(req.getParameter("pager.offset"));
			} catch (NumberFormatException e) {
			}
			SystemContext.setOrder(order);
			SystemContext.setSort(sort);
			SystemContext.setPageOffset(pageOffset);
			SystemContext.setPageSize(pageSize);
			//SystemContext.setRealpath(RequestUtil.PATH);
			chain.doFilter(req, resp);
		} finally {
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
			SystemContext.removeOrder();
			SystemContext.removeSort();
		}
	}


	public void init(FilterConfig arg0) throws ServletException {

	}
}

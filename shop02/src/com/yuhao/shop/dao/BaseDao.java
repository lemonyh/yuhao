package com.yuhao.shop.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.yuhao.shop.util.DaoUtil;
import cn.yuhao.shop.util.MYBatisUtil;

import com.yuhao.shop.model.Pager;
import com.yuhao.shop.model.SystemContext;
import com.yuhao.shop.model.User;

public class BaseDao<T> {
	/**
	 * 所有插入数据库操作
	 * 
	 * @param obj
	 */
	public BaseDao(){
		DaoUtil.diDao(this);
	}
	public void add(T obj) {
		SqlSession session = null;
		try {
			session = MYBatisUtil.createSession();
			session.insert(obj.getClass().getName() + ".add", obj);
			session.commit();
		} finally {
			MYBatisUtil.closeSession(session);
		}
	}

	/**
	 * 所有updata操作
	 * 
	 * @param obj
	 */
	public void update(T obj) {
		SqlSession session = null;
		try {
			session = MYBatisUtil.createSession();
			session.update(obj.getClass().getName() + ".update", obj);

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();

		} finally {
			MYBatisUtil.closeSession(session);
		}
	}

	public void delete(Class<T> clz, int id) {
		SqlSession session = null;

		try {
			session = MYBatisUtil.createSession();
			session.delete(clz.getName() + ".delete", id);

			session.commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			MYBatisUtil.closeSession(session);
		}

	}

	public T load(Class<T> clz, int id) {
		SqlSession session = null;
		T t = null;
		try {
			session = MYBatisUtil.createSession();
			t = (T) session.selectOne(clz.getName() + ".load", id);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return t;
	}
	
	
	@SuppressWarnings("unchecked")
	public T loadBySqlId(String sqlId,Map<String,Object> params) {
		SqlSession session = null;
		T t = null;
		try {
			session = MYBatisUtil.createSession();
			t = (T)session.selectOne(sqlId,params);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public T loadBySqlId(String sqlId,Object obj) {
		SqlSession session = null;
		T t = null;
		try {
			session = MYBatisUtil.createSession();
			t = (T)session.selectOne(sqlId,obj);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return t;
	}
	

	public Pager<T> find(Class<T> clz, Map<String, Object> params) {
		SqlSession session = null;
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Pager<T> pages = new Pager<T>();
		try {
			session = MYBatisUtil.createSession();

			if (params == null)
				params = new HashMap<String, Object>();
			params.put("pageSize", pageSize);
			params.put("pageOffset", pageOffset);
			params.put("order", order);
			params.put("sort", sort);
			List<User> datas = session.selectList(clz.getName() + ".find",
					params);
			pages.setDatas((List<T>) datas);
			pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			int totalRecord = session.selectOne(clz.getName() + ".find_count",
					params);
			pages.setTotalRecord(totalRecord);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return pages;
	}
	
	public Pager<T> find(String sqlId,Map<String,Object> params) {
		int pageSize = SystemContext.getPageSize();
		int pageOffset = SystemContext.getPageOffset();
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		Pager<T> pages = new Pager<T>();
		SqlSession session = null;
		try{
			session = MYBatisUtil.createSession();
			if(params==null) params = new HashMap<String, Object>();
			params.put("pageSize", pageSize);
			params.put("pageOffset", pageOffset);
			params.put("sort", sort);
			params.put("order", order);
			List<T> datas = session.selectList(sqlId, params);
			pages.setDatas(datas);
			pages.setPageOffset(pageOffset);
			pages.setPageSize(pageSize);
			int totalRecord = session.selectOne(sqlId+"_count",params);
			pages.setTotalRecord(totalRecord);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return pages;
	}
	
	public List<T> list(Class<T> clz,Map<String,Object> params) {
		return this.list(clz.getName()+".list", params);
	}
	
	public List<T> list(String sqlId, Map<String, Object> params) {
		List<T> list = null;
		SqlSession session = null;
		try {
			session = MYBatisUtil.createSession();
			list = session.selectList(sqlId, params);
		} finally {
			MYBatisUtil.closeSession(session);
		}
		return list;
	}

}

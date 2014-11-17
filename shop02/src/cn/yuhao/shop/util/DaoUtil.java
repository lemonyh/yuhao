/**
 * 
 */
package cn.yuhao.shop.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import com.yuhao.shop.dao.IFactoryDao;
import com.yuhao.shop.model.ShopDi;

/**Administrator
 * 2014
 *@qq 903450130
 */
	
public class DaoUtil {
	
	public static void diDao(Object obj)
	{
		try {
			/**
			 * 获取所有的方法
			 */
			Method[] ms = obj.getClass().getDeclaredMethods();
			
			for(Method m:ms) {
				/**
				 * 判断方法上面是否有ShopDi的Annotation，有这个Annotation才进行注入
				 */
				if(m.isAnnotationPresent(ShopDi.class)) {
					/*
					 * 获取method上的ShopDi对象
					 */
					ShopDi sd = m.getAnnotation(ShopDi.class);
					/**
					 * 获取这个Annotation的值
					 */
					String mn = sd.value();
					/**
					 * 判断shopDi的value是否为空，如果为空，就等于要使用setXXX这个方法名称
					 * 来完成注入
					 */
					if(mn==null||"".equals(mn.trim())) {
						mn = m.getName().substring(3);
						mn = mn.substring(0,1).toLowerCase()+mn.substring(1);
					}
					Object o = DaoUtil.createDaOFactory().getDao(mn);
					m.invoke(obj, o);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 获取自己的所有方法 ，判断是否有set，如果有，进行对象的注入
	 */
	/*
	public static void diDao(Object obj)
	{
		
		Method[] ms=obj.getClass().getDeclaredMethods();
		for(Method m:ms){
			String mn=m.getName();
			if(mn.startsWith("set"))
			{
				*
				 * 获取需要注入的Dao
				 *
				mn=mn.substring(3);
				mn=mn.substring(0, 1).toLowerCase()+mn.substring(1);
				**
				 * 条用工厂获取Dao
				 *
				Object o= DaoUtil.createDaOFactory().getDao(mn);
				try {
					m.invoke(obj, o);
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
	}*/
	public static IFactoryDao createDaOFactory()
	{
		IFactoryDao f=null;
		Properties prop=PropertiesUtil.getDaoProp();
		String fs=prop.getProperty("factory");
		
		try {
			Class clz=Class.forName(fs);
			String method="getInstance";
			Method m=clz.getMethod(method);
			f=(IFactoryDao) m.invoke(clz);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return f;
	}
}

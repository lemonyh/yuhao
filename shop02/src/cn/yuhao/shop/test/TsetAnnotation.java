/**
 * 
 */
package cn.yuhao.shop.test;

import java.lang.reflect.Method;

import org.junit.Test;

import com.yuhao.shop.model.ShopDi;

/**Administrator
 * 2014
 *@qq 903450130
 */
public class TsetAnnotation {

	@ShopDi("addressDao")
	public void setUserDao()
	{
		
	}
	@Test
	public void test()
	{
		Method[] ms=this.getClass().getDeclaredMethods();
		for(Method m:ms)
		{
			System.out.println(m.getName()+":"+m.isAnnotationPresent(ShopDi.class));
			if(m.isAnnotationPresent(ShopDi.class)){
				ShopDi sd= m.getAnnotation(ShopDi.class);
				String str=sd.value();
				if(sd.value()==null||sd.value().equals(""))
				{
					str=m.getName().substring(3);
				}
				System.out.println(str);
			}
		}
	}
}

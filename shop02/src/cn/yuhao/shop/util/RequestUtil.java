/**
 * 
 */
package cn.yuhao.shop.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.yuhao.shop.model.ValidataForm;
import com.yuhao.shop.model.ValidataType;

/*
 * 请求参数的beamutil
 */
public class RequestUtil {
	public static Object setParam(Class clz, HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		Set<String> keys = params.keySet();
		Object o = null;
		try {
			o = clz.newInstance();
			for (String key : keys) {
				String[] vv = params.get(key);
				// 如果获取的不是length不是1
				if (vv.length > 1) {
					BeanUtils.copyProperty(o, key, vv);
				} else {
					BeanUtils.copyProperty(o, key, params.get(key)[0]);
				}

			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	public static boolean validate(Class clz, HttpServletRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		boolean isValidta = true;// 设置默认判断成功
		Field[] fs = clz.getDeclaredFields();// 得到所有类型属性
		for (Field f : fs) {
			/*
			 * 判断在Annotation中收否存在 若存在判断属性类型 不同类型对应不同的操作
			 */
			if (f.isAnnotationPresent(ValidataForm.class)) {
				ValidataForm vf = f.getAnnotation(ValidataForm.class);
				ValidataType vt = vf.type();
				/*
				 * 判断类型是否为空 做部位空的操作 若不为空
				 */
				if (vt == ValidataType.NotNull) {
					boolean b = validateNotNull(f.getName(), request);// 调用下面的传参函数

					if (!b)// 表明验证失败
					{
						isValidta = false;
						errors.put(f.getName(), vf.errorMsg());// 存入错误消息
					}

				} else if (vt == ValidataType.Lenngth) {
					boolean b = vadidateLength(f.getName(), request, vf.value());
					if (!b)// 表明验证失败
					{
						isValidta = false;
						errors.put(f.getName(), vf.errorMsg());// 存入错误消息
					}

				}
				else if (vt == ValidataType.Number) {
					boolean b = vadidateNumber(f.getName(),request);
					if (!b)// 表明验证失败
					{
						isValidta = false;
						errors.put(f.getName(), vf.errorMsg());// 存入错误消息
					}
				}
			}
		}
		return isValidta;
	}

	private static boolean vadidateNumber(String name,
			HttpServletRequest request) {
		try{
		Integer.parseInt(request.getParameter(name));
		}catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}

	private static boolean vadidateLength(String name,
			HttpServletRequest request, String value) {
		String v = request.getParameter(name);
		if (v == null || "".equals(v.trim())) {
			return false;
		}
		if (v.length() < 6) {
			return false;
		}
		return true;
	}

	/*
	 * 出入
	 */
	public static boolean validateNotNull(String name,
			HttpServletRequest request) {
		//表示表单中没有要验证的值
		if(!request.getParameterMap().containsKey(name))
		{
			return true;
		}
		String v = request.getParameter(name);
		if (v == null || "".equals(v.trim())) {
			return false;
		}

		return true;
	}

}

/**
 * 
 */
package com.yuhao.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**Administrator
 * 2014
 *@qq 903450130
 *
 *表示为这个annotation加上一个属性值，如果么哦有定义default，必须在该annotation中定义这个属性
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface ShopDi {
	//String abc() default "";//设置默认值
	/*
	 * value 是annotation的默认属性
	 * 当要设置两个属性时 ，默认属性会不起作用
	 */
	String value() default "";
}

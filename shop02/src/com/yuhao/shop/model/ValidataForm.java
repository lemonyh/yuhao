package com.yuhao.shop.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidataForm {
	
	/*type表示验证的类型
	 * 
	 */
	public ValidataType type();
	public String errorMsg();
	public String value() default "";

}

package com.zxy.miaosha.access;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * @ClassName AccessLimit
 * @Description 防刷拦截器自定义注解
 * @Author Zhang xingyu
 * @Date 2020/12/28 09:25
 * @Version 1.0
 **/
@Retention(RUNTIME)
@Target(METHOD)
public @interface AccessLimit {
	int seconds();
	int maxCount();
	boolean needLogin() default true;
}

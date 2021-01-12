package com.zxy.miaosha.access;

import com.zxy.miaosha.domain.MiaoshaUser;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/28 09:41
 * @Version 1.0
 **/
public class UserContext {
	
	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
	
	public static void setUser(MiaoshaUser user) {
		userHolder.set(user);
	}
	
	public static MiaoshaUser getUser() {
		return userHolder.get();
	}

}

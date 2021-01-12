package com.zxy.miaosha.redis;
/**
 * @ClassName AccessKey
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/28 09:41
 * @Version 1.0
 **/
public class AccessKey extends BasePrefix{

	private AccessKey( int expireSeconds, String prefix) {
		super(expireSeconds, prefix);
	}
	
	public static AccessKey withExpire(int expireSeconds) {
		return new AccessKey(expireSeconds, "access");
	}
	
}

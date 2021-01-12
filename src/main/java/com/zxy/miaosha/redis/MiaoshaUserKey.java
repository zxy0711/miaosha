package com.zxy.miaosha.redis;
/**
 * @ClassName MiaoshaUserKey
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/9 21:15
 * @Version 1.0
 **/
public class MiaoshaUserKey extends BasePrefix {

  //有效期两天
  public static final int TOKEN_EXPIRE = 3600*24 * 2;
  private MiaoshaUserKey(int expireSeconds, String prefix) {
    super(expireSeconds, prefix);
  }
  public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");
  public static MiaoshaUserKey getById = new MiaoshaUserKey(0, "id");
}

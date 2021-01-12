package com.zxy.miaosha.redis;
/**
 * @ClassName OrderKey
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/2 15:38
 * @Version 1.0
 **/
public class OrderKey extends BasePrefix {

  public OrderKey(String prefix) {
    super(prefix);
  }
  public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}

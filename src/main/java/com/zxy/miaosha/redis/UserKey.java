package com.zxy.miaosha.redis;
/**
 * @ClassName UserKey
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/2 15:41
 * @Version 1.0
 **/
public class UserKey extends BasePrefix {

  //私有构造，防止new
  private UserKey(String prefix) {
    super(prefix);
  }

  //构造静态方法，返回本类实例对象，其他类通过此方法获得实例
  public static UserKey getById = new UserKey("id");
  public static UserKey getByName = new UserKey("name");
}

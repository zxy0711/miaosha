package com.zxy.miaosha.redis;
/**
 * @ClassName UserContext
 * @Description 自定义前缀接口，防止redis key重复
 * @Author Zhang xingyu
 * @Date 2020/12/2 15:34
 * @Version 1.0
 **/
public interface KeyPrefix {

  public int expireSeconds();

  public String getPrefix();

}

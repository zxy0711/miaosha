package com.zxy.miaosha.util;

import java.util.UUID;
/**
 * @ClassName UUIDUtil
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 20:46
 * @Version 1.0
 **/
public class UUIDUtil {
  public static String uuid(){
    return UUID.randomUUID().toString().replace("-","");
  }

}

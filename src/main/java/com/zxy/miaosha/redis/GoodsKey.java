package com.zxy.miaosha.redis;
/**
 * @ClassName GoodsKey
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/16 14:21
 * @Version 1.0
 **/
public class GoodsKey extends BasePrefix {

  //私有构造，防止new
  private GoodsKey(int expireSeconds, String prefix) {
    super(expireSeconds, prefix);
  }

  //构造静态方法，返回本类实例对象，其他类通过此方法获得实例

  public static GoodsKey getGoodsList = new GoodsKey(60, "gl");
  public static GoodsKey getGoodsDetail = new GoodsKey(60, "gd");
  public static GoodsKey getMiaoshaGoodsStock= new GoodsKey(0, "gs");
}

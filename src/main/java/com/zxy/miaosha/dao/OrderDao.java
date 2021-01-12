package com.zxy.miaosha.dao;

import com.zxy.miaosha.domain.MiaoshaOrder;
import com.zxy.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;
/**
 * @ClassName OrderDao
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/12 20:11
 * @Version 1.0
 **/
@Mapper
@Repository
public interface OrderDao {

  @Select("select * from miaosha_order where user_id=#{userId} and goods_id=#{goodsId}")
  public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

  /**
   * 通过@SelectKey使insert成功后返回主键id，也就是订单id
   * @param orderInfo
   * @return
   */
  @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
      + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
  @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
  public long insert(OrderInfo orderInfo);

  @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
  public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

  @Select("select * from order_info where id = #{orderId}")
  public OrderInfo getOrderById(@Param("orderId")long orderId);

  @Delete("delete from order_info")
  public void deleteOrders();

  @Delete("delete from miaosha_order")
  public void deleteMiaoshaOrders();
}

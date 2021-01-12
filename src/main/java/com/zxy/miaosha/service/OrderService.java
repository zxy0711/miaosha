package com.zxy.miaosha.service;

import com.zxy.miaosha.dao.GoodsDao;
import com.zxy.miaosha.dao.OrderDao;
import com.zxy.miaosha.domain.MiaoshaGoods;
import com.zxy.miaosha.domain.MiaoshaOrder;
import com.zxy.miaosha.domain.MiaoshaUser;
import com.zxy.miaosha.domain.OrderInfo;
import com.zxy.miaosha.redis.OrderKey;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.vo.GoodsVo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @ClassName OrderService
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/14 20:18
 * @Version 1.0
 **/
@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	RedisService redisService;

	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
		//return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
		return redisService.get(OrderKey.getMiaoshaOrderByUidGid, ""+userId+"_"+goodsId, MiaoshaOrder.class);
	}

	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}


	/**
	 * @description 因为要同时分别在订单详情表和秒杀订单表都新增一条数据，所以要保证两个操作是一个事物
	 * @author Zhang Xingyu
	 * @date 2020/12/14 20:41
	 * @param user
	 * @param goods
	 * @return com.tan.seckill.bean.OrderInfo
	 **/
	@Transactional
	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		orderDao.insert(orderInfo);
		MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
		miaoshaOrder.setGoodsId(goods.getId());
		miaoshaOrder.setOrderId(orderInfo.getId());
		miaoshaOrder.setUserId(user.getId());
		orderDao.insertMiaoshaOrder(miaoshaOrder);

		redisService.set(OrderKey.getMiaoshaOrderByUidGid, ""+user.getId()+"_"+goods.getId(), miaoshaOrder);

		return orderInfo;
	}

	public void deleteOrders() {
		orderDao.deleteOrders();
		orderDao.deleteMiaoshaOrders();
	}
}

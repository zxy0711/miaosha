package com.zxy.miaosha.vo;

import com.zxy.miaosha.domain.OrderInfo;
/**
 * @ClassName OrderDetailVo
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2021/1/11 09:41
 * @Version 1.0
 **/
public class OrderDetailVo {
	private GoodsVo goods;
	private OrderInfo order;
	public GoodsVo getGoods() {
		return goods;
	}
	public void setGoods(GoodsVo goods) {
		this.goods = goods;
	}
	public OrderInfo getOrder() {
		return order;
	}
	public void setOrder(OrderInfo order) {
		this.order = order;
	}
}

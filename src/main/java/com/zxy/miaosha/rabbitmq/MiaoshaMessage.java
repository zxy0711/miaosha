package com.zxy.miaosha.rabbitmq;

import com.zxy.miaosha.domain.MiaoshaUser;
/**
 * @ClassName MiaoshaMessage
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/22 09:25
 * @Version 1.0
 **/
public class MiaoshaMessage {
	private MiaoshaUser user;
	private long goodsId;
	public MiaoshaUser getUser() {
		return user;
	}
	public void setUser(MiaoshaUser user) {
		this.user = user;
	}
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(long goodsId) {
		this.goodsId = goodsId;
	}
}

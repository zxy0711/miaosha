package com.zxy.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxy.miaosha.domain.MiaoshaUser;
import com.zxy.miaosha.domain.OrderInfo;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.result.CodeMsg;
import com.zxy.miaosha.result.Result;
import com.zxy.miaosha.service.GoodsService;
import com.zxy.miaosha.service.MiaoshaUserService;
import com.zxy.miaosha.service.OrderService;
import com.zxy.miaosha.vo.GoodsVo;
import com.zxy.miaosha.vo.OrderDetailVo;
/**
 * @ClassName OrderController
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/18 21:15
 * @Version 1.0
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model,MiaoshaUser user,
    		@RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	OrderInfo order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}

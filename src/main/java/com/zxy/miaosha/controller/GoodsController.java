package com.zxy.miaosha.controller;

import com.zxy.miaosha.redis.GoodsKey;
import com.zxy.miaosha.result.Result;
import com.zxy.miaosha.service.GoodsService;
import com.zxy.miaosha.vo.GoodsDetailVo;
import com.zxy.miaosha.vo.GoodsVo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zxy.miaosha.domain.MiaoshaUser;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.service.MiaoshaUserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @ClassName GoodsController
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/10 09:25
 * @Version 1.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController {

  @Autowired
  MiaoshaUserService userService;

  @Autowired
  RedisService redisService;

  @Autowired
  GoodsService goodsService;

  @Autowired
  ThymeleafViewResolver thymeleafViewResolver;

  @Autowired
  ApplicationContext applicationContext;

  /**
   * @description 商品列表页面
   * @author Zhangxingyu
   * @date 2020/12/10 09:25
   * @param request
   * @param response
   * @param model
   * @param user
   * @return java.lang.String
   **/
  @RequestMapping(value="/to_list", produces="text/html")
  @ResponseBody
  public String list(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user) {
    model.addAttribute("user", user);
    //取缓存
//    	String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
//    	if(!StringUtils.isEmpty(html)) {
//    		return html;
//    	}
    List<GoodsVo> goodsList = goodsService.listGoodsVo();
    model.addAttribute("goodsList", goodsList);
//    	 return "goods_list";
    SpringWebContext ctx = new SpringWebContext(request,response,
        request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    //手动渲染
    String html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
    if(!StringUtils.isEmpty(html)) {
      redisService.set(GoodsKey.getGoodsList, "", html);
    }
    return html;
  }

  @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
  @ResponseBody
  public String detail2(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user,
      @PathVariable("goodsId")long goodsId) {
    model.addAttribute("user", user);

    //取缓存
    String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
    if(!StringUtils.isEmpty(html)) {
      return html;
    }
    //手动渲染
    GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    model.addAttribute("goods", goods);

    long startAt = goods.getStartDate().getTime();
    long endAt = goods.getEndDate().getTime();
    long now = System.currentTimeMillis();

    int miaoshaStatus = 0;
    int remainSeconds = 0;
    if(now < startAt ) {//秒杀还没开始，倒计时
      miaoshaStatus = 0;
      remainSeconds = (int)((startAt - now )/1000);
    }else  if(now > endAt){//秒杀已经结束
      miaoshaStatus = 2;
      remainSeconds = -1;
    }else {//秒杀进行中
      miaoshaStatus = 1;
      remainSeconds = 0;
    }
    model.addAttribute("miaoshaStatus", miaoshaStatus);
    model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";

    SpringWebContext ctx = new SpringWebContext(request,response,
        request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
    if(!StringUtils.isEmpty(html)) {
      redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
    }
    return html;
  }

  /**
   * @description 商品详情页面 前后端分离
   * @author Zhangxingyu
   * @date 2020/12/10 09:25
   * @param request
   * @param response
   * @param model
   * @param user
   * @param goodsId
   * @return Result<GoodsDetailVo>
   **/
  @RequestMapping(value="/detail/{goodsId}")
  @ResponseBody
  public Result<GoodsDetailVo> detail(HttpServletRequest request, HttpServletResponse response, Model model,MiaoshaUser user,
      @PathVariable("goodsId")long goodsId) {
    GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    long startAt = goods.getStartDate().getTime();
    long endAt = goods.getEndDate().getTime();
    long now = System.currentTimeMillis();
    int miaoshaStatus = 0;
    int remainSeconds = 0;
    if(now < startAt ) {//秒杀还没开始，倒计时
      miaoshaStatus = 0;
      remainSeconds = (int)((startAt - now )/1000);
    }else  if(now > endAt){//秒杀已经结束
      miaoshaStatus = 2;
      remainSeconds = -1;
    }else {//秒杀进行中
      miaoshaStatus = 1;
      remainSeconds = 0;
    }
    GoodsDetailVo vo = new GoodsDetailVo();
    vo.setGoods(goods);
    vo.setUser(user);
    vo.setRemainSeconds(remainSeconds);
    vo.setMiaoshaStatus(miaoshaStatus);
    return Result.success(vo);
  }


}

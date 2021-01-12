package com.zxy.miaosha.controller;

import com.zxy.miaosha.domain.User;
import com.zxy.miaosha.rabbitmq.MQReceiver;
import com.zxy.miaosha.rabbitmq.MQSender;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.redis.UserKey;
import com.zxy.miaosha.result.Result;
import com.zxy.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @ClassName SampleController
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/2 15:51
 * @Version 1.0
 **/
@Controller
@RequestMapping("/demo")
public class SampleController {

  @Autowired
  UserService userService;

  @Autowired
  RedisService redisService;

//  @RequestMapping("/thymeleaf")
//  public String thymeleaf(Model model){
//    model.addAttribute("name","zhang");
//    return "hello";
//  }

  @Autowired
  MQSender mqSender;

  @Autowired
  MQReceiver mqReceiver;

  @RequestMapping("/db/get")
  @ResponseBody
  public Result<User> dbget(){
    User user = userService.getById(1);
    return Result.success(user);
  }

  @RequestMapping("/rabbitmq")
  @ResponseBody
  public String rabbitmq(){
    //mqSender.sendMiaoshaMessage("nihao");
    return "111";
  }

  @RequestMapping("/db/tx")
  @ResponseBody
  public Result<Boolean> dbtx(){
    userService.tx();
    return Result.success(true);
  }

  @RequestMapping("/redis/get")
  @ResponseBody
  public Result<User> redisGet(){
    User v1 = redisService.get(UserKey.getById,""+1,User.class);
    return Result.success(v1);
  }

  @RequestMapping("/redis/set")
  @ResponseBody
  public Result<Boolean> redisSet(){
    User user = new User();
    user.setId(2);
    user.setName("2222");
    redisService.set(UserKey.getById,""+1,user);
    return Result.success(true);
  }
}

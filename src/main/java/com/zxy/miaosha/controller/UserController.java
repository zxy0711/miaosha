package com.zxy.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxy.miaosha.domain.MiaoshaUser;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.result.Result;
import com.zxy.miaosha.service.MiaoshaUserService;
/**
 * @ClassName UserController
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 21:15
 * @Version 1.0
 **/
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model,MiaoshaUser user) {
        return Result.success(user);
    }
    
}

package com.zxy.miaosha.service;

import com.zxy.miaosha.exception.GlobalException;
import com.zxy.miaosha.redis.MiaoshaUserKey;
import com.zxy.miaosha.util.UUIDUtil;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxy.miaosha.dao.MiaoshaUserDao;
import com.zxy.miaosha.domain.MiaoshaUser;
import com.zxy.miaosha.redis.RedisService;
import com.zxy.miaosha.result.CodeMsg;
import com.zxy.miaosha.util.MD5Util;
import com.zxy.miaosha.vo.LoginVo;
/**
 * @ClassName MiaoshaUserService
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 14:26
 * @Version 1.0
 **/
@Service
public class MiaoshaUserService {
	
	
	public static final String COOKI_NAME_TOKEN = "token";
	
	@Autowired
	MiaoshaUserDao miaoshaUserDao;
	
	@Autowired
	RedisService redisService;

	
	public MiaoshaUser getById(long id) {
		//取缓存
		MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, ""+id, MiaoshaUser.class);
		if(user != null) {
			return user;
		}
		//取数据库
		user = miaoshaUserDao.getById(id);
		if(user != null) {
			redisService.set(MiaoshaUserKey.getById, ""+id, user);
		}
		return user;
	}

	/**
	 * @description 典型缓存同步场景：更新密码
	 * @author Zhang Xingyu
	 * @date 2020/12/22 16:17
	 * @param token
	 * @param id
	 * @param formPass
	 * @return boolean
	 **/
	//出现修改密码的方法
	// http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
	public boolean updatePassword(String token, long id, String formPass) {
		//取user
		MiaoshaUser user = getById(id);
		if(user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//更新数据库
		MiaoshaUser toBeUpdate = new MiaoshaUser();
		toBeUpdate.setId(id);
		toBeUpdate.setPassword(MD5Util.formPassToDbPass(formPass, user.getSalt()));
		miaoshaUserDao.update(toBeUpdate);
		//处理缓存
		redisService.delete(MiaoshaUserKey.getById, ""+id);
		user.setPassword(toBeUpdate.getPassword());
		redisService.set(MiaoshaUserKey.token, token, user);
		return true;
	}

	/**
	 * @description 根据token获取用户信息
	 * @author Zhang Xingyu
	 * @date 2020/12/19 14:18
	 * @param response
	 * @param token
	 * @return com.zxy.miaosha.domain.User
	 **/
	public MiaoshaUser getByToken(HttpServletResponse response, String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);

		//延长有效期
		if(user!=null){
			addCookie(response,token,user);
		}
		return user;
	}


	/**
	 * 登录判断
	 * 抛出异常交由GlobalExceptionHandle进行处理
	 */
	public boolean login(HttpServletResponse response, LoginVo loginVo) {
		if(loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		//判断手机号是否存在
		MiaoshaUser user = getById(Long.parseLong(mobile));
		if(user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//验证密码
		String dbPass = user.getPassword();
		String saltDB = user.getSalt();
		String calcPass = MD5Util.formPassToDbPass(formPass, saltDB);
		if(!calcPass.equals(dbPass)) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		//生成cookie
		String token = UUIDUtil.uuid();
		addCookie(response,token,user);
		return true;
	}
	
	private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
		redisService.set(MiaoshaUserKey.token, token, user);
		Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}

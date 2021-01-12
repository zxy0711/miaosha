package com.zxy.miaosha.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zxy.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
/**
 * @ClassName MiaoshaUserDao
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 11:25
 * @Version 1.0
 **/
@Mapper
@Component
public interface MiaoshaUserDao {
	
	@Select("select * from miaosha_user where id = #{id}")
	public MiaoshaUser getById(@Param("id") long id);

	@Update("update miaosha_user set password = #{password} where id = #{id}")
	public void update(MiaoshaUser toBeUpdate);
}

package com.zxy.miaosha.dao;

import com.zxy.miaosha.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/2 16:31
 * @Version 1.0
 **/
@Mapper
@Component
public interface UserDao {


  @Select("select * from user where id=#{id}")
  public User getById(@Param("id") int id);

  @Insert("insert into user(id, name)values(#{id},#{name})")
  public int insert(User user);

}

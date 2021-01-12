package com.zxy.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * @ClassName RedisPoolFactory
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/2 15:21
 * @Version 1.0
 **/
@Service
public class RedisPoolFactory {

  @Autowired
  RedisConfig redisConfig;

  @Bean
  public JedisPool JedisFactory(){
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
    poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
    poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() *1000);

    JedisPool jp = new JedisPool(poolConfig,redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout() *1000,redisConfig.getPassword(),0);
    return jp;
  }

}

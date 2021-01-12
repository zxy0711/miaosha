package com.zxy.miaosha;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zxy.miaosha.dao")
public class MainApplication {

  public static void main(String[] args) throws Exception{
    SpringApplication.run(MainApplication.class,args);
  }
}

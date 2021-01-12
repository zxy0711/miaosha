package com.zxy.miaosha.vo;

import com.zxy.miaosha.validator.IsMobile;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
/**
 * @ClassName LoginVo
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 14:15
 * @Version 1.0
 **/
public class LoginVo {

  @NotNull
  @IsMobile
  private String mobile;

  @NotNull
  @Length(min=32)
  private String password;

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "LoginVo{" +
        "mobile='" + mobile + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}

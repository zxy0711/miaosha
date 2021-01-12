package com.zxy.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @ClassName MD5Util
 * @Description 明文密码2次MD5传入数据库中
 * @Author Zhang xingyu
 * @Date 2020/12/8 19:11
 * @Version 1.0
 **/
public class MD5Util {

  public static String md5(String src){
    return DigestUtils.md5Hex(src);
  }

  //安全起见
  private static final String salt = "1a2b3c4d";

  public static String inputPassToFormPass(String inputPass){
    String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
    return md5(str);
  }

  public static String formPassToDbPass(String formPass,String salt){
    String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
    return md5(str);
  }

  public static String inputPassToDbPass(String input,String saltDB){
    String formPass = inputPassToFormPass(input);
    String dbPass = formPassToDbPass(formPass,saltDB);
    return dbPass;
  }

//  public static void main(String[] args) {
//    System.out.println(inputPassToDbPass("123456","1a2b3c4d"));
//  }

}

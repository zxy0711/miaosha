package com.zxy.miaosha.exception;

import com.zxy.miaosha.result.CodeMsg;

/**
 *
 * 作为业务层返回值，出现异常直接抛出即可
 *
 */
/**
 * @ClassName GlobalException
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/9 15:11
 * @Version 1.0
 **/
public class GlobalException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private CodeMsg cm;

  public GlobalException(CodeMsg cm){
    super(cm.toString());
    this.cm = cm;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public CodeMsg getCm() {
    return cm;
  }

  public void setCm(CodeMsg cm) {
    this.cm = cm;
  }
}

package com.zxy.miaosha.exception;

import com.zxy.miaosha.result.CodeMsg;
import com.zxy.miaosha.result.Result;
import java.util.List;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常全局处理。
 * @ControllerAdvice
 * 结果会返回给调用者，所有有@RequestMapping注解的方法均能获取
 */
/**
 * @ClassName GlobalExceptionHandle
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 16:25
 * @Version 1.0
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandle {

  //使用@ExceptionHandler注解可以指定spring捕获到所有抛出的CustomException异常，由这个被注解的方法处理
  @ExceptionHandler(value = Exception.class)
  public Result<String> exceptionHandler(HttpServletRequest request,Exception e){
    e.printStackTrace();
    if(e instanceof GlobalException){
      GlobalException ex = (GlobalException)e;
      return Result.error(ex.getCm());
    }
    else if(e instanceof BindException){
      BindException ex = (BindException)e;
      List<ObjectError> errors = ex.getAllErrors();
      ObjectError error = errors.get(0);
      String msg = error.getDefaultMessage();
      return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
    }
    else{
      return Result.error(CodeMsg.SERVER_ERROR);
    }
  }
}

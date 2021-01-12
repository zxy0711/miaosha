package com.zxy.miaosha.validator;


import com.zxy.miaosha.util.ValidatorUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
/**
 * @ClassName IsMobileValidator
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/8 11:56
 * @Version 1.0
 **/
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

  private boolean required = false;

  //初始化
  public void initialize(IsMobile constraintAnnotation) {
    required = constraintAnnotation.required();
  }

  //是否合法
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(required) {
      return ValidatorUtil.isMobile(value);
    }else {
      if(StringUtils.isEmpty(value)) {
        return true;
      }else {
        return ValidatorUtil.isMobile(value);
      }
    }
  }

}
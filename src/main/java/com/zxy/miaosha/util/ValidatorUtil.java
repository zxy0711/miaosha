package com.zxy.miaosha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 * @ClassName ValidatorUtil
 * @Description TODO
 * @Author Zhang xingyu
 * @Date 2020/12/9 13:25
 * @Version 1.0
 **/
public class ValidatorUtil {
	
	private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");
	
	public static boolean isMobile(String src) {
		if(StringUtils.isEmpty(src)) {
			return false;
		}
		Matcher m = mobile_pattern.matcher(src);
		return m.matches();
	}

}

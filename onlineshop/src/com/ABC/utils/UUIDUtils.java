package com.ABC.utils;

import java.util.UUID;

/**
 * 生成随机字符串的工具类
 * @author 陈   	
 * e-mail: 592992464@qq.com
 * @version 创建时间：2017年4月25日  上午10:01:07
 */
public class UUIDUtils {

	// 获取随机字符串的方法
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}

package com.coding.sales.business.utils;

/**
 * 字符串工具类
 * 
 * @author Sizhen Created at 2019-07-02 18:21:49
 */
public class StringUtils {

	private StringUtils() {
	}

	/**
	 * 判断字符串是否为空
	 *
	 * @param str
	 *            需要判断的字符串
	 * @return 如果字符串为null或全为空格，返回true，否则返回false
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim()) ? true : false;
	}
}

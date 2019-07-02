package com.coding.sales.business.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {

	private static final String ZERO_STR = "0";
	private static final BigDecimal ZERO_NUM = new BigDecimal(ZERO_STR);

	public static int roundDown(BigDecimal dividend, int onethousand) {
		BigDecimal bd = dividend.divide(new BigDecimal(onethousand));
		return bd.setScale(0, BigDecimal.ROUND_DOWN).intValue();
	}

	/**
	 * 两数字相乘
	 *
	 * @param numA
	 *            数字A
	 * @param numB
	 *            数字B
	 * @param newScale
	 *            精确度
	 * @param roundingMode
	 *            取值策略
	 * @return 乘积
	 */
	public static BigDecimal multiply(BigDecimal numA, BigDecimal numB, int newScale, RoundingMode roundingMode) {
		return numA.multiply(numB).setScale(newScale, roundingMode);
	}

	/**
	 * 数字是否小于0
	 *
	 * @param num
	 *            数字
	 * @return
	 */
	public static boolean lessThanZero(BigDecimal num) {
		if (!isNull(num) && num.compareTo(ZERO_NUM) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 是否为空
	 *
	 * @param num
	 * @return
	 */
	public static boolean isNull(Number num) {
		return num == null;
	}
}

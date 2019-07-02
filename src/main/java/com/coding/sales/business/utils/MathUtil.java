package com.coding.sales.business.utils;

import java.math.BigDecimal;

public class MathUtil {

	public static int roundDown(BigDecimal dividend, int onethousand){
		BigDecimal bd = dividend.divide(new BigDecimal(onethousand));
		return bd.setScale(0, BigDecimal.ROUND_DOWN).intValue();
	}
}

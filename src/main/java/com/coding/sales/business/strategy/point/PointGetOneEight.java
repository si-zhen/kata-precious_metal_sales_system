package com.coding.sales.business.strategy.point;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.coding.sales.business.utils.MathUtil;

/**
 * 1.8倍基准积分
 * 
 * @author Sizhen Created at 2019-07-02 19:36:40
 */
public class PointGetOneEight extends AbstractPointStrategy {
	
	private static final BigDecimal TIMES = new BigDecimal("1.8");

	@Override
	public int calculatePoints(BigDecimal payMoney) {
		if(MathUtil.lessThanZero(payMoney)){
			throw new RuntimeException("参数不合法");
		}
		return (payMoney.setScale(0, RoundingMode.DOWN)).multiply(TIMES).intValue();
	}

}

package com.coding.sales.business.strategy.point;

import java.math.BigDecimal;

/**
 * 积分计算策略
 * 
 * @author Sizhen Created at 2019-07-02 19:24:30
 */
public abstract class AbstractPointStrategy {

	/**
	 * 获取支付金额对应的积分
	 *
	 * @param payMoney
	 *            支付金额
	 */
	public abstract int calculatePoints(BigDecimal payMoney);
}

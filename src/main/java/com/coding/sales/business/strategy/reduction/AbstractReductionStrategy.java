package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

import com.coding.sales.business.bean.Result;

/**
 * 满减方式
 * 
 * @author Sizhen Created at 2019-07-02 17:15:15
 */
public abstract class AbstractReductionStrategy {

	/**
	 * 计算满减后的金额
	 *
	 * @param unitPrice
	 *            单价
	 * @param amount
	 *            购买数量
	 * @return 优惠后金额总计
	 */
	public abstract Result calRealPay(BigDecimal unitPrice, int amount);
	
}

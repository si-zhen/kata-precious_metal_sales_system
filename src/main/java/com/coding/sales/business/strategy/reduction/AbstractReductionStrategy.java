package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

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
	 * @return 满减后的真实金额
	 */
	public abstract BigDecimal calRealPay(BigDecimal unitPrice, int amount);

}

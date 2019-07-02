package com.coding.sales.business.strategy.discount;

import java.math.BigDecimal;

/**
 * 打折策略
 * 
 * @author Sizhen Created at 2019-07-02 16:59:56
 */
public abstract class AbstractDiscountStrategy {

	/**
	 * 计算打折后的金额
	 *
	 * @param totalMoney
	 *            原总金额
	 * @return 打折后的金额
	 */
	public abstract BigDecimal calRealPay(BigDecimal totalMoney);

}

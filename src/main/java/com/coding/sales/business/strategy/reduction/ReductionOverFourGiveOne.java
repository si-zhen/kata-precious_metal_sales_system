package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

/**
 * 满3送1（买4件及以上，其中1件免费）
 * 
 * @author Sizhen Created at 2019-07-02 17:29:44
 */
public class ReductionOverFourGiveOne extends AbstractReductionStrategy {

	@Override
	public BigDecimal calRealPay(BigDecimal unitPrice, int amount) {
		return null;
	}
}

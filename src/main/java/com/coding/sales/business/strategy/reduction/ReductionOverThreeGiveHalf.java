package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

/**
 * 第3件半价（买3件及以上，其中1件半价）
 * 
 * @author Sizhen Created at 2019-07-02 17:29:44
 */
public class ReductionOverThreeGiveHalf extends AbstractReductionStrategy {

	@Override
	public BigDecimal calRealPay(BigDecimal unitPrice, int amount) {
		return null;
	}

}

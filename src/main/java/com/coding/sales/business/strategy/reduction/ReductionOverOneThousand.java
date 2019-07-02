package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

/**
 * 每满1000减10
 * 
 * @author Sizhen Created at 2019-07-02 17:29:44
 */
public class ReductionOverOneThousand extends AbstractReductionStrategy {

	@Override
	public BigDecimal calRealPay(BigDecimal unitPrice, int amount) {
		return null;
	}

}

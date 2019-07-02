package com.coding.sales.business.strategy.discount;

import java.math.BigDecimal;

import com.coding.sales.business.constants.Constants;

/**
 * 9折券
 * 
 * @author Sizhen Created at 2019-07-02 17:05:06
 */
public class DiscountNinety extends AbstractDiscountStrategy {

	@Override
	public BigDecimal calRealPay(BigDecimal totalMoney) {
		if(totalMoney == null || totalMoney.compareTo(new BigDecimal("0")) < 0){
			throw new RuntimeException("传入金额非法！");
		}
		return totalMoney.multiply(new BigDecimal(Constants.NINETYFIVE));
	}

}

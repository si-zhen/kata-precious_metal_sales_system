package com.coding.sales.business.strategy.discount;

import java.math.BigDecimal;

import com.coding.sales.business.bean.Result;
import com.coding.sales.business.constants.Constants;

/**
 * 9折券
 * 
 * @author Sizhen Created at 2019-07-02 17:05:06
 */
public class DiscountNinety extends AbstractDiscountStrategy {

	@Override
	public Result calRealPay(BigDecimal totalMoney) {
		Result result = new Result();

		if (totalMoney == null || totalMoney.compareTo(new BigDecimal("0")) < 0) {
			throw new RuntimeException("传入金额非法！");
		}
		// 总金额
		BigDecimal allTotal = totalMoney;
		// 应付金额
		BigDecimal realTotal = null;
		// 优惠金额
		BigDecimal discountAmount = null;
		realTotal = totalMoney.multiply(new BigDecimal(Constants.NINETY));
		discountAmount = allTotal.subtract(realTotal);
		result.setAllTotal(allTotal);
		result.setRealTotal(realTotal);
		result.setDiscountAmount(discountAmount);
		return result;
	}

}

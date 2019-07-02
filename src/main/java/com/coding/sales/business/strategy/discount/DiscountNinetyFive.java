package com.coding.sales.business.strategy.discount;

import java.math.BigDecimal;

import com.coding.sales.business.bean.Result;
import com.coding.sales.business.constants.Constants;

/**
 * 95折券
 * 
 * @author Sizhen Created at 2019-07-02 17:04:41
 */
public class DiscountNinetyFive extends AbstractDiscountStrategy {

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
		realTotal = totalMoney.multiply(new BigDecimal(Constants.NINETYFIVE));
		discountAmount = allTotal.subtract(realTotal);
		result.setAllTotal(allTotal);
		result.setRealTotal(realTotal);
		result.setDiscountAmount(discountAmount);
		return result;
	}

	@Override
	public String getTag() {
		return "95折券";
	}

}

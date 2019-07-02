package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

import com.coding.sales.business.bean.Result;
import com.coding.sales.business.constants.Constants;
import com.coding.sales.business.utils.MathUtil;

/**
 * 每满3000减350
 * 
 * @author Sizhen Created at 2019-07-02 17:29:44
 */
public class ReductionOverThreeThousand extends AbstractReductionStrategy {

	@Override
	public Result calRealPay(BigDecimal unitPrice, int amount) {
		Result result = new Result();
		//总金额
		BigDecimal allTotal = null;
		//应付金额
		BigDecimal realTotal = null;
		//优惠金额
		BigDecimal discountAmount = null;
		
		if(unitPrice == null || amount <= 0){
			throw new RuntimeException("传入参数不合法");
		}
		//计算总金额
		allTotal = unitPrice.multiply(new BigDecimal(amount));
		int times = MathUtil.roundDown(allTotal, Constants.THREETHOUSAND);
		if(times > 0){
			discountAmount = Constants.THREEHUNDREDFIFTY.multiply(new BigDecimal(times));
			realTotal = allTotal.subtract(discountAmount);
		}else{
			discountAmount = new BigDecimal(0);
			realTotal = allTotal;
		}
		result.setAllTotal(allTotal);
		result.setRealTotal(realTotal);
		result.setDiscountAmount(discountAmount);
		return result;
	}

	@Override
	public String getTag() {
		return "threethousand";
	}

}

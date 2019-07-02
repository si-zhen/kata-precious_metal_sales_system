package com.coding.sales.business.strategy.reduction;

import java.math.BigDecimal;

import com.coding.sales.business.bean.Result;
import com.coding.sales.business.constants.Constants;

/**
 * 满3送1（买4件及以上，其中1件免费）
 * 
 * @author Sizhen Created at 2019-07-02 17:29:44
 */
public class ReductionOverFourGiveOne extends AbstractReductionStrategy {

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
		//个数大于等于4个时，总金额减去一个商品单价,否则总金额按照原价计算
		if(amount >= Constants.FOUR){
			realTotal = unitPrice.multiply(new BigDecimal(amount - 1));
			allTotal = unitPrice.multiply(new BigDecimal(amount));
			discountAmount = unitPrice.multiply(new BigDecimal(1));
		}else{
			realTotal = unitPrice.multiply(new BigDecimal(amount));
			allTotal = unitPrice.multiply(new BigDecimal(amount));
			discountAmount = new BigDecimal(0);
		}
		result.setAllTotal(allTotal);
		result.setRealTotal(realTotal);
		result.setDiscountAmount(discountAmount);
		return result;
	}

	@Override
	public String getTag() {
		return "threetoone";
	}

}

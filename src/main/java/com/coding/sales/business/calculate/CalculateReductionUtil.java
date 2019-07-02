package com.coding.sales.business.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.bean.Result;
import com.coding.sales.business.strategy.discount.AbstractDiscountStrategy;
import com.coding.sales.business.strategy.reduction.AbstractReductionStrategy;
import com.coding.sales.business.utils.MathUtil;

/**
 * 满减费用计算
 * 
 * @author Sizhen Created at 2019-07-02 20:49:10
 */
public class CalculateReductionUtil {

	private CalculateReductionUtil() {
	}

	/**
	 * 计算金属的满减金额
	 *
	 * @param metal 要购买的金属信息
	 * @param amount 购买的数量
	 * @return 
	 */
	public static CalculateDiscountResult calMiniestPay(Metal metal, BigDecimal amount) {
		CalculateDiscountResult result = new CalculateDiscountResult();
		BigDecimal totalMoney = MathUtil.multiply(metal.getPrice(), amount, 2, RoundingMode.HALF_UP);
		result.setTotalMoney(totalMoney);
		
		result.setDiscountMoney(new BigDecimal("0"));
		result.setRealPayMoney(totalMoney);
		
		// 金属对应的满减策略
		List<AbstractReductionStrategy> reductionStrategies = metal.getReductionStrategies();
		if(reductionStrategies != null && reductionStrategies.size() > 0){
			BigDecimal total = new BigDecimal(Integer.MAX_VALUE);
			for (AbstractReductionStrategy strategy : reductionStrategies) {
				
			}
		}
		
		
		
		
		
		
		
//		List<AbstractDiscountStrategy> discountStrategies = metal.getDiscountStrategies();
//		boolean hasTickets = discountTickets != null && discountTickets.size() > 0;
//		
//		// 订单金额合计
//		BigDecimal totalMoney = MathUtil.multiply(metal.getPrice(), amount, 2, RoundingMode.HALF_UP);
//		result.setTotalMoney(totalMoney);
//		// 默认无券时
//		result.setDiscountMoney(new BigDecimal("0"));
//		result.setRealPayMoney(totalMoney);
//		
//		if(hasTickets && discountStrategies != null && discountStrategies.size() > 0){
//			for (AbstractDiscountStrategy strategy : discountStrategies) {
//				for (String ticket : discountTickets) {
//					if (strategy.getTag().equals(ticket)) {
//						Result realPay = strategy.calRealPay(totalMoney);
//						result.setDiscountMoney(realPay.getDiscountAmount());
//						result.setRealPayMoney(realPay.getRealTotal());
//						result.setStrategyName(ticket);
//					}
//				}
//			}
//		}
		
		return result;
	}

	public static class CalculateDiscountResult {
		private String strategyName;
		private BigDecimal totalMoney;
		private BigDecimal realPayMoney;
		private BigDecimal discountMoney;

		public String getStrategyName() {
			return strategyName;
		}

		public void setStrategyName(String strategyName) {
			this.strategyName = strategyName;
		}

		public BigDecimal getTotalMoney() {
			return totalMoney;
		}

		public void setTotalMoney(BigDecimal totalMoney) {
			this.totalMoney = totalMoney;
		}

		public BigDecimal getDiscountMoney() {
			return discountMoney;
		}

		public void setDiscountMoney(BigDecimal discountMoney) {
			this.discountMoney = discountMoney;
		}

		public BigDecimal getRealPayMoney() {
			return realPayMoney;
		}

		public void setRealPayMoney(BigDecimal realPayMoney) {
			this.realPayMoney = realPayMoney;
		}
	}

}

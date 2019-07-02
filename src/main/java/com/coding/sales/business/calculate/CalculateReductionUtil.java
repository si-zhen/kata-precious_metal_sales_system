package com.coding.sales.business.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.strategy.reduction.AbstractReductionStrategy;
import com.coding.sales.business.strategy.reduction.ReductionOverFourGiveOne;
import com.coding.sales.business.strategy.reduction.ReductionOverOneThousand;
import com.coding.sales.business.strategy.reduction.ReductionOverThreeGiveHalf;
import com.coding.sales.business.strategy.reduction.ReductionOverThreeThousand;
import com.coding.sales.business.strategy.reduction.ReductionOverTwoThousand;
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
		// 1：计算出合计金额
		BigDecimal totalMoney = MathUtil.multiply(metal.getPrice(), amount, 2, RoundingMode.HALF_UP);
		result.setTotalMoney(totalMoney);
		
		// 2：设置优惠以及真实支付金额的默认值
		result.setDiscountMoney(new BigDecimal("0"));
		result.setRealPayMoney(totalMoney);
		
		// 3：金属对应的满减策略
		List<AbstractReductionStrategy> reductionStrategies = metal.getReductionStrategies();
		if(reductionStrategies != null && reductionStrategies.size() > 0){
			BigDecimal threeThousand = new BigDecimal("3000");
			
			// 定义满减以及满送的默认值
			BigDecimal reductionMoney = new BigDecimal("0");// 满减金额
			BigDecimal forMoney = new BigDecimal("0"); // 满送的金额

			if (totalMoney.compareTo(threeThousand) >= 0 && metal.hasStrategy(new ReductionOverThreeThousand())) {
				// 金额超过三千，且该商品拥有满3000的优惠活动
				int newReductionNum = totalMoney.divide(threeThousand).intValue();
				reductionMoney = new BigDecimal("350").multiply(new BigDecimal(newReductionNum));
				BigDecimal surplusMoney = totalMoney.subtract(threeThousand.multiply(new BigDecimal(newReductionNum)));// 满减后的
				if (surplusMoney.compareTo(new BigDecimal("2000")) >= 0
						&& metal.hasStrategy(new ReductionOverTwoThousand())) {
					reductionMoney.add(new BigDecimal("30"));
				} else if (surplusMoney.compareTo(new BigDecimal("1000")) >= 0
						&& metal.hasStrategy(new ReductionOverOneThousand())) {
					reductionMoney.add(new BigDecimal("10"));
				}
			} else if (totalMoney.compareTo(new BigDecimal("2000")) >= 0
					&& metal.hasStrategy(new ReductionOverTwoThousand())) {
				// 金额超过两千，且拥有两千的活动
				reductionMoney = calOverTwoThousand(metal, amount);
			} else if (totalMoney.compareTo(new BigDecimal("1000")) >= 0
					&& metal.hasStrategy(new ReductionOverOneThousand())) {
				// 金额超过一千，且拥有一千的活动时
				reductionMoney = calDiscountOverOneThousand(totalMoney);
			}

			if (amount.compareTo(new BigDecimal("4")) >= 0 && metal.hasStrategy(new ReductionOverFourGiveOne())) {
				// 第四件免费
				forMoney = totalMoney.subtract(metal.getPrice());
			} else if (amount.compareTo(new BigDecimal("3")) >= 0 && metal.hasStrategy(new ReductionOverThreeGiveHalf())) {
				// 第三件半价
				forMoney = totalMoney
						.subtract(metal.getPrice().divide(new BigDecimal("2")).setScale(2, RoundingMode.HALF_UP));
			}

			// 比较满减与满送，取折扣大者
			BigDecimal big = reductionMoney.compareTo(forMoney) >= 0 ? reductionMoney : forMoney;
			
			result.setDiscountMoney(big);
			result.setRealPayMoney(totalMoney.subtract(big));
		}
		
		return result;
	}
	
	/**
	 * 每满2000减30
	 *
	 * @param metal 商品信息
	 * @param amount 购买的数量
	 * @return
	 */
	public static BigDecimal calOverTwoThousand(Metal metal, BigDecimal amount){
		BigDecimal totalMoney = MathUtil.multiply(metal.getPrice(), amount, 2, RoundingMode.HALF_UP);
		BigDecimal newReductionNum = totalMoney.divide(new BigDecimal("2000")).setScale(0, RoundingMode.HALF_DOWN);
		BigDecimal discountMoney = new BigDecimal("30").multiply(newReductionNum);
		BigDecimal has = totalMoney.subtract(new BigDecimal("2000").multiply(newReductionNum));// 剩余的
		
		 if (has.compareTo(new BigDecimal("1000")) >= 0
					&& metal.hasStrategy(new ReductionOverOneThousand())) {
			 discountMoney.add(new BigDecimal("10"));
		}
		return discountMoney;
	}
	
	/**
	 * 每满1000减10元
	 *
	 * @param totalMoney 总金额
	 * @return
	 */
	public static BigDecimal calDiscountOverOneThousand(BigDecimal totalMoney){
		BigDecimal newReductionNum = totalMoney.divide(new BigDecimal("1000")).setScale(0, RoundingMode.HALF_DOWN);
		return new BigDecimal("10").multiply(newReductionNum);
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

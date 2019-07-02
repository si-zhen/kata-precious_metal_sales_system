package com.coding.sales.business.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.coding.sales.business.bean.CalculateResult;
import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.bean.Result;
import com.coding.sales.business.strategy.discount.AbstractDiscountStrategy;
import com.coding.sales.business.utils.MathUtil;

/**
 * 打折费用计算
 * 
 * @author Sizhen Created at 2019-07-02 20:49:10
 */
public class CalculateDiscountUtil {

	private CalculateDiscountUtil() {
	}

	/**
	 * 计算打折费用
	 *
	 * @param metal
	 *            要购买的贵金属
	 * @param amount
	 *            购买数量
	 * @param discountTickets
	 *            用户拥有的打折券
	 * @return 商品打折后的金额（如果有打折券）
	 */
	public static CalculateResult calMiniestPay(Metal metal, BigDecimal amount, List<String> discountTickets) {
		CalculateResult result = new CalculateResult();
		List<AbstractDiscountStrategy> discountStrategies = metal.getDiscountStrategies();
		boolean hasTickets = discountTickets != null && discountTickets.size() > 0;
		
		// 订单金额合计
		BigDecimal totalMoney = MathUtil.multiply(metal.getPrice(), amount, 2, RoundingMode.HALF_UP);
		result.setTotalMoney(totalMoney);
		// 默认无券时
		result.setDiscountMoney(new BigDecimal("0"));
		result.setRealPayMoney(totalMoney);
		
		if(hasTickets && discountStrategies != null && discountStrategies.size() > 0){
			for (AbstractDiscountStrategy strategy : discountStrategies) {
				for (String ticket : discountTickets) {
					if (strategy.getTag().equals(ticket)) {
						Result realPay = strategy.calRealPay(totalMoney);
						result.setDiscountMoney(realPay.getDiscountAmount());
						result.setRealPayMoney(realPay.getRealTotal());
						result.setStrategyName(ticket);
					}
				}
			}
		}
		
		return result;
	}
}

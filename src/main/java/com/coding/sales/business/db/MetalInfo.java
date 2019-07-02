package com.coding.sales.business.db;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.coding.sales.business.bean.Metal;
import com.coding.sales.business.strategy.discount.DiscountNinety;
import com.coding.sales.business.strategy.discount.DiscountNinetyFive;
import com.coding.sales.business.strategy.reduction.ReductionOverFourGiveOne;
import com.coding.sales.business.strategy.reduction.ReductionOverOneThousand;
import com.coding.sales.business.strategy.reduction.ReductionOverThreeGiveHalf;
import com.coding.sales.business.strategy.reduction.ReductionOverThreeThousand;
import com.coding.sales.business.strategy.reduction.ReductionOverTwoThousand;

/**
 * 贵金属商品信息
 * 
 * @author Sizhen Created at 2019-07-02 18:15:11
 */
public class MetalInfo {
	private static Map<String, Metal> infoMap = new HashMap<>();
	
	static {
		infoMap.put("001001", new Metal("001001", "册", new BigDecimal("998.00"), "世园会五十国钱币册"));
		infoMap.put("001002",
				new Metal("001002", "盒", new BigDecimal("1380.00"), "2019北京世园会纪念银章大全40g").addDiscountStrategy(new DiscountNinety()));
		infoMap.put("003001",
				new Metal("003001", "条", new BigDecimal("1580.00"), "招财进宝").addDiscountStrategy(new DiscountNinetyFive()));
		infoMap.put("003002",
				new Metal("003002", "条", new BigDecimal("998.00"), "水晶之恋").addReductionStrategy(new ReductionOverThreeGiveHalf())
						.addReductionStrategy(new ReductionOverFourGiveOne()));
		infoMap.put("002002",
				new Metal("002002", "套", new BigDecimal("998.00"), "中国经典钱币套装").addReductionStrategy(new ReductionOverTwoThousand())
						.addReductionStrategy(new ReductionOverOneThousand()));
		infoMap.put("002001",
				new Metal("002001", "条", new BigDecimal("1080.00"), "守扩之羽比翼双飞4.8g").addDiscountStrategy(new DiscountNinetyFive())
						.addReductionStrategy(new ReductionOverThreeGiveHalf())
						.addReductionStrategy(new ReductionOverFourGiveOne()));
		infoMap.put("002003",
				new Metal("002003", "套", new BigDecimal("698.00"), "中国银象棋12g ").addDiscountStrategy(new DiscountNinety())
						.addReductionStrategy(new ReductionOverThreeThousand())
						.addReductionStrategy(new ReductionOverTwoThousand())
						.addReductionStrategy(new ReductionOverOneThousand()));
	}

	/**
	 * 根据贵金属id获取贵金属商品信息
	 *
	 * @param metalId
	 * @return
	 */
	public static Metal findMetalById(String metalId){
		return infoMap.get(metalId);
	}
}

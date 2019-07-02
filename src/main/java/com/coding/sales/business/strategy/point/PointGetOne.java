package com.coding.sales.business.strategy.point;

import java.math.BigDecimal;

/**
 * 1倍基准积分
 * 
 * @author Sizhen Created at 2019-07-02 19:36:40
 */
public class PointGetOne extends AbstractPointStrategy {

	@Override
	public int calculatePoints(BigDecimal payMoney) {
		return 0;
	}

}

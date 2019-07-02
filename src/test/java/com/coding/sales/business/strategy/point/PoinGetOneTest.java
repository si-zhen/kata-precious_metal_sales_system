package com.coding.sales.business.strategy.point;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PoinGetOneTest {
	
	PointGetOne one = null;

	@Before
	public void init(){
		one = new PointGetOne();
	}
	
	@Test(expected = RuntimeException.class)
	public void testCalculatePoints() {
		PointGetOne one = new PointGetOne();
		one.calculatePoints(null);
	}
	
	@Test(expected = RuntimeException.class)
	public void throw_exception_if_less_than_zero(){
		PointGetOne one = new PointGetOne();
		one.calculatePoints(new BigDecimal("-1"));
	}

}

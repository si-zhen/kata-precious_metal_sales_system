package com.coding.sales.business.bean;

import java.math.BigDecimal;

public class Result {

	/**
	 * 商品总金额
	 */
	private BigDecimal allTotal;
	/**
	 * 应付总金额
	 */
	private BigDecimal realTotal;
	/**
	 * 优惠金额
	 */
	private BigDecimal discountAmount;
	
	public BigDecimal getAllTotal() {
		return allTotal;
	}
	public void setAllTotal(BigDecimal allTotal) {
		this.allTotal = allTotal;
	}
	public BigDecimal getRealTotal() {
		return realTotal;
	}
	public void setRealTotal(BigDecimal realTotal) {
		this.realTotal = realTotal;
	}
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}
	
}

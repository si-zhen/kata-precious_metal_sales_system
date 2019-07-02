package com.coding.sales.business.bean;

import java.util.ArrayList;
import java.util.List;

import com.coding.sales.business.strategy.discount.AbstractDiscountStrategy;
import com.coding.sales.business.strategy.reduction.AbstractReductionStrategy;

/**
 * 贵金属
 * 
 * @author Sizhen Created at 2019-07-02 17:56:50
 */
public class Metal {

	/**
	 * 编号
	 */
	private String id;
	
	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 单价
	 */
	private String price;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 打折策略
	 */
	private List<AbstractDiscountStrategy> discountStrategies = new ArrayList<>();
	
	/**
	 * 满减策略
	 */
	private List<AbstractReductionStrategy> reductionStrategies = new ArrayList<>();

	public Metal(String id, String unit, String price, String name, List<AbstractDiscountStrategy> discountStrategies,
			List<AbstractReductionStrategy> reductionStrategies) {
		super();
		this.id = id;
		this.unit = unit;
		this.price = price;
		this.name = name;
		this.discountStrategies = discountStrategies;
		this.reductionStrategies = reductionStrategies;
	}
	
	public Metal(String id, String unit, String price, String name, List<AbstractDiscountStrategy> discountStrategies) {
		this(id, unit, price, name, discountStrategies, null);
	}
	
	public Metal(String id, String unit, String price, String name) {
		this(id, unit, price, name, null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AbstractDiscountStrategy> getDiscountStrategies() {
		return discountStrategies;
	}

	public Metal addDiscountStrategy(AbstractDiscountStrategy discountStrategy) {
		this.discountStrategies.add(discountStrategy);
		return this;
	}

	public List<AbstractReductionStrategy> getReductionStrategies() {
		return reductionStrategies;
	}

	public Metal addReductionStrategy(AbstractReductionStrategy reductionStrategy) {
		this.reductionStrategies.add(reductionStrategy);
		return this;
	}
}

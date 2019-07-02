package com.coding.sales.business.bean;

import com.coding.sales.business.constants.MemberLevel;
import com.coding.sales.business.strategy.point.AbstractPointStrategy;
import com.coding.sales.business.strategy.point.PointGetOne;
import com.coding.sales.business.strategy.point.PointGetOneEight;
import com.coding.sales.business.strategy.point.PointGetOneFive;
import com.coding.sales.business.strategy.point.PointGetTwo;

/**
 * 会员信息JavaBean
 * 
 * @author SK
 *
 */
public class Member {

	/**
	 * 会员姓名
	 */
	private String name;
	/**
	 * 会员等级
	 */
	private MemberLevel level;
	/**
	 * 会员卡号
	 */
	private String memberId;
	/**
	 * 会员积分
	 */
	private int point;
	
	/**
	 * 会员积分计算规则
	 */
	private AbstractPointStrategy popintStrategy;

	public AbstractPointStrategy getPopintStrategy() {
		return popintStrategy;
	}

	private void setPopintStrategy(int point) {
		if(point < 10000){
			popintStrategy = new PointGetOne();
		}else if(point < 50000){
			popintStrategy = new PointGetOneFive();
		}else if(point < 100000){
			popintStrategy = new PointGetOneEight();
		}else{
			popintStrategy = new PointGetTwo();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
		this.setPopintStrategy(point);
	}

	public MemberLevel getLevel() {
		return level;
	}

	public void setLevel(MemberLevel level) {
		this.level = level;
	}

	public Member(String name, MemberLevel level, String memberId, int point) {
		super();
		this.name = name;
		this.level = level;
		this.memberId = memberId;
		this.point = point;
		this.setPopintStrategy(point);
	}
}

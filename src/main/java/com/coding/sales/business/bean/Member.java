package com.coding.sales.business.bean;

/**
 * 会员信息JavaBean
 * @author SK
 *
 */
public class Member {

	/**
	 * 	会员姓名
	 */
	private String name;
	/**
	 * 会员等级
	 */
	private String level;
	/**
	 * 会员卡号
	 */
	private String memberId;
	/**
	 * 会员积分
	 */
	private String point;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
	@Override
	public String toString() {
		return "Member [name=" + name + ", level=" + level + ", memberId=" + memberId + ", point=" + point + "]";
	}
	
}

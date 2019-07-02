package com.coding.sales.business.constants;

/**
 * 客户等级
 * 
 * @author Sizhen Created at 2019-07-02 16:45:39
 */
public enum MemberLevel {

	NORMAL("普卡"), 
	GOLD("金卡"), 
	PLATINUM("白金卡"), 
	DIAMONDS("钻石卡");
	
	private String levelDesc;
	
	MemberLevel(String levelDesc){
		this.levelDesc = levelDesc;
	}
	
	public String getLevelDesc(){
		return this.levelDesc;
	}
	
	
}

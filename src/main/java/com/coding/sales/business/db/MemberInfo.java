package com.coding.sales.business.db;

import java.util.HashMap;
import java.util.Map;

import com.coding.sales.business.bean.Member;
import com.coding.sales.business.constants.MemberLevel;

/**
 * 会员信息
 * 
 * @author Sizhen Created at 2019-07-02 18:15:11
 */
public class MemberInfo {
	private static Map<String, Member> infoMap = new HashMap<>();

	static {
		infoMap.put("6236609999", new Member("马丁", MemberLevel.NORMAL, "6236609999", 9860));
		infoMap.put("6630009999", new Member("王立", MemberLevel.GOLD, "6630009999", 48860));
		infoMap.put("8230009999", new Member("李想", MemberLevel.PLATINUM, "8230009999", 98860));
		infoMap.put("9230009999", new Member("张三", MemberLevel.DIAMONDS, "9230009999", 198860));
	}

	/**
	 * 根据会员id获取会员信息
	 *
	 * @param memberId
	 *            会员id
	 * @return 会员id对应的会员信息
	 */
	public static Member findMemberById(String memberId) {
		return infoMap.get(memberId);
	}
}

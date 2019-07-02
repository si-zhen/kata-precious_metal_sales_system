package com.coding.sales.business.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

	@Test
	public void test_true_if_all_blank() {
		assertTrue(StringUtils.isBlank("   "));
	}
	
	@Test
	public void test_true_if_is_null() {
		assertTrue(StringUtils.isBlank(null));
	}
	
	@Test
	public void test_false_if_is_null_str() {
		assertFalse(StringUtils.isBlank("null"));
	}

}

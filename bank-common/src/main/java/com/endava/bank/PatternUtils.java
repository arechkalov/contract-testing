package com.endava.bank;

import java.util.regex.Pattern;

public class PatternUtils {

	public static Pattern amountIsLessThan_50000() {
		return Pattern.compile("^[1-4]\\d{0,4}(\\.\\d{2})?\\$");
	}

	public static Pattern amountIsGreaterThan_50000() {
		return Pattern.compile("^[5-9]\\d{5,}(\\.\\d{2})?\\$");
	}

}

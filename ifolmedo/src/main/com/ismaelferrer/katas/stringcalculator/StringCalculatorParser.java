package com.ismaelferrer.katas.stringcalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculatorParser {

	private static final String DEFAULT_DELIMITER_REGEXP = "[,\n]";
	private static final String CHANGE_DELIMITER_PREFIX = "//";
	private static final String CHANGE_DELIMITER_SUFFIX = "\n";
	private static final String CHANGE_MULTICHARACTER_DELIMITER_PREFIX = CHANGE_DELIMITER_PREFIX + "[";
	private static final String CHANGE_MULTICHARACTER_DELIMITER_SUFFIX = "]\n";	
	
	static Collection<Integer> parseAddends( final String addOperationAsString) {
		String delimiterRegExp = obtainDelitimiterRegExp(addOperationAsString);
		String addendsSubstring = obtainAddendsSubstring(addOperationAsString); 
		return convertToIntegerCollection(addendsSubstring.split(delimiterRegExp));
	}

	private static String obtainDelitimiterRegExp(final String addOperationAsString) {
		if (!isDelimiterChanged(addOperationAsString)) return DEFAULT_DELIMITER_REGEXP;
		return obtainDelimiterChangedRegExp(addOperationAsString);
	}

	private static boolean isDelimiterChanged(final String addendsAsString) {
		return addendsAsString.startsWith(CHANGE_DELIMITER_PREFIX);
	}

	private static String obtainDelimiterChangedRegExp(
			final String addOperationAsString) {
		String delimiterChangeExpression = obtainDelimiterChangeExpression(addOperationAsString);
		if (!isDelimiterChangedToMulticharacter(addOperationAsString)) { 
			delimiterChangeExpression = "[" + delimiterChangeExpression + "]";
		}
		return convertDelimitierChangeExpressionToRegExp(delimiterChangeExpression);
	}

	private static boolean isDelimiterChangedToMulticharacter(
			final String addendsAsString) {
		return addendsAsString.startsWith(CHANGE_MULTICHARACTER_DELIMITER_PREFIX) &&
				addendsAsString.contains(CHANGE_MULTICHARACTER_DELIMITER_SUFFIX);
	}
	
	private static String obtainDelimiterChangeExpression(
			String addOperationAsString) {
		return addOperationAsString.substring(0, addOperationAsString.indexOf(CHANGE_DELIMITER_SUFFIX))
				.substring(CHANGE_DELIMITER_PREFIX.length());
	}

	private static String convertDelimitierChangeExpressionToRegExp(
			String delimiterChangeExpression) {
		return delimiterChangeExpression
				.replaceAll(Pattern.quote("]["), "\\\\E)|(\\\\Q")
				.replaceAll(Pattern.quote("["), "(\\\\Q")
				.replaceAll(Pattern.quote("]"), "\\\\E)");
	}	
	
	private static String obtainAddendsSubstring(final String addendsAsString) {
		if (!isDelimiterChanged(addendsAsString)) return addendsAsString;
		return addendsAsString.substring(
				addendsAsString.indexOf(CHANGE_DELIMITER_SUFFIX)+1);
	}

	private static Collection<Integer> convertToIntegerCollection(
			final String[] numbersAsStrings){
		Collection<Integer> integers = new ArrayList<Integer>();
		for (String numberAsString : numbersAsStrings) {
			integers.add(new Integer(numberAsString));
		}
		return integers;
	}



}

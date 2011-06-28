package com.ismaelferrer.katas.stringcalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class StringCalculator {

	private static final String DEFAULT_DELIMITER_REGEXP = "[,\n]";
	private static final String CHANGE_DELIMITER_PREFIX = "//";
	private static final char CHANGE_DELIMITER_SUFFIX = '\n';

	public static int add(final String string) {
		if (string.isEmpty()) return 0;
		return addNonEmptyString(string);
	}

	private static int addNonEmptyString(final String string) {
		String delimiterRegExp = DEFAULT_DELIMITER_REGEXP;
		String processedString = string;
		if (string.startsWith(CHANGE_DELIMITER_PREFIX)) {
			String nonEscapedDelimiterRegExp = string.substring(
					CHANGE_DELIMITER_PREFIX.length(), 
					string.indexOf(CHANGE_DELIMITER_SUFFIX));
			delimiterRegExp = Pattern.quote(nonEscapedDelimiterRegExp);
			processedString = removeDelimiterChangeSubstring(string);
		}
		Collection<Integer> addends = convertToIntegerCollection(processedString.split(delimiterRegExp));
		checkNegativeNumbers(addends);
		addends = removeNumbersGreaterThanAThousand(addends);
		return summation(addends);
	}

	private static Collection<Integer> removeNumbersGreaterThanAThousand(
			Collection<Integer> integers) {
		Collection<Integer> filteredIntegers = new ArrayList<Integer>();
		for(Integer integer: integers) {
			if (integer <= 1000) filteredIntegers.add(integer);
		}
		return filteredIntegers;
	}

	private static String removeDelimiterChangeSubstring(final String string) {
		return string.substring(
				string.indexOf(CHANGE_DELIMITER_SUFFIX)+1);
	}
	
	private static Collection<Integer> convertToIntegerCollection(
			final String[] numbersAsStrings){
		Collection<Integer> integers = new ArrayList<Integer>();
		for (String numberAsString : numbersAsStrings) {
			integers.add(new Integer(numberAsString));
		}
		return integers;
	}
	
	private static void checkNegativeNumbers(
			final Collection<Integer> numbers) {
		List<Integer> illegalArguments = new ArrayList<Integer>();
		for (Integer number : numbers) {
			if (number < 0) illegalArguments.add(new Integer(number));
		}
		if (!illegalArguments.isEmpty()) 
			throw new IllegalArgumentException("negatives not allowed: " + join(illegalArguments, ","));		
	}
	
	private static String join(final Collection<Integer> integers, 
			final String delimiter) {
	    if (integers.isEmpty()) return "";
	    Iterator<Integer> iter = integers.iterator();
	    StringBuffer buffer = new StringBuffer(iter.next().toString());
	    while (iter.hasNext()) 
	    	buffer.append(delimiter).append(iter.next());
	    return buffer.toString();
	}
	
	private static int summation(final Collection<Integer> addends) {
		int sum = 0;
		for (Integer addend : addends) {
			sum += addend;
		}
		return sum;
	}
	

}

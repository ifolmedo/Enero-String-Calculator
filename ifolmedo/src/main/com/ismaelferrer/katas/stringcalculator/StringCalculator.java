package com.ismaelferrer.katas.stringcalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class StringCalculator {

	public static int add(final String string) {
		if (string.isEmpty()) return 0;
		return addNonEmptyString(string);
	}

	private static int addNonEmptyString(final String addOperationAsString) {
		Collection<Integer> addends;
		addends = StringCalculatorParser.parseAddends(addOperationAsString);
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

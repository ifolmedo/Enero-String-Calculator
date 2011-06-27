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
		return addStringArray(processedString.split(delimiterRegExp));

	}

	private static String removeDelimiterChangeSubstring(final String string) {
		return string.substring(
				string.indexOf(CHANGE_DELIMITER_SUFFIX)+1);
	}

	private static int addStringArray(final String[] addendsArray) {
		int sum = 0;
		List<Integer> illegalArguments = new ArrayList<Integer>();
		for (String addend : addendsArray) {
			int intAddend = Integer.parseInt(addend);
			if (intAddend < 0) illegalArguments.add(new Integer(addend));
			sum += intAddend;
		}
		if (illegalArguments.isEmpty()) return sum;
		throw new IllegalArgumentException("negatives not allowed: " +
				join(illegalArguments, ","));
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
}

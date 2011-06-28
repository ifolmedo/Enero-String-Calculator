package com.ismaelferrer.katas.stringcalculator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.ismaelferrer.katas.stringcalculator.StringCalculator;

public class StringCalculatorTest {

	@Test
	public void whenAddingEmptyStringThenShouldReturnZero() {
		assertThat(StringCalculator.add(""), is(0));
	}
	
	@Test
	public void whenAddingOneNumberThenShouldReturnSameNumber() {
		String [] sums={"1","2","10","100"};
		int [] expectedResults ={1,2,10,100};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}


	
	@Test
	public void whenAddingTwoNumbersThenShouldReturnTheSum() {
		String [] sums={"1,2","2,10","3,70","15,100"};
		int [] expectedResults ={3,12,73,115};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}
	
	@Test
	public void whenAddingSeveralNumbersThenShouldReturnTheSum() {
		String [] sums={"1,2,7","2,9,33,45","3,70,21,43,560"};
		int [] expectedResults ={10,89,697};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}	
	
	@Test
	public void whenUsingNewLineDelimeterThenShouldReturnTheSum() {
		String [] sums={"1\n2,3","6\n54\n66"};
		int [] expectedResults ={6,126};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}
	
	@Test
	public void whenSelectingNewDelimeterThenShouldReturnTheSum() {
		String [] sums={"//;\n1;2;7","//+\n19+10+221"};
		int [] expectedResults ={10,250};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}
	
	@Test
	public void whenAddingGreaterThanAThousandNumbersThenShouldIgnoreThoseNumbers() {
		String [] sums={"2,1000","3,7777,5","2000"};
		int [] expectedResults ={1002,8,0};
		assertThamSumsResultsAreTheExpected(sums, expectedResults);
	}

	private void assertThamSumsResultsAreTheExpected(String[] sums, int[] expectedResults) {
		for(int i=0; i<sums.length; i++){		
			int result = StringCalculator.add(sums[i]);
			assertThat(result, is(expectedResults[i]));
		}
	}		
	
}

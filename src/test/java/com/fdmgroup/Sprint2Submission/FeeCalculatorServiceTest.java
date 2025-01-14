package com.fdmgroup.Sprint2Submission;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FeeCalculatorServiceTest {

	FeeCalculatorServiceImpl calculator;
	
	@BeforeEach
	void setUp() throws Exception {
		calculator = new FeeCalculatorServiceImpl();
	}

	@Test
	void testWhenBalanceIsLessThanOrEqualToOneHundred_FeeEqualsTwenty() {
		
		// Arrange
		double expected = 20;
		
		// Act
		double actual = calculator.calculateFee(30);

		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenBalanceIsMoreThanOneHundredAndLessThanOrEqualToFiveHundred_FeeEqualsFifteen() {
		
		// Arrange
		double expected = 15;
		
		// Act
		double actual = calculator.calculateFee(267);

		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenBalanceIsMoreThanFiveHundredAndLessThanOrEqualToOneThousand_FeeEqualsTen() {
		
		// Arrange
		double expected = 10;
		
		// Act
		double actual = calculator.calculateFee(777);

		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenBalanceIsMoreThanOneThousandAndLessThanOrEqualToTwoThousand_FeeEqualsFive() {
		
		// Arrange
		double expected = 5;
		
		// Act
		double actual = calculator.calculateFee(1200);

		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenBalanceIsMoreThanTwoThousand_FeeEqualsZero() {
		
		// Arrange
		double expected = 0;
		
		// Act
		double actual = calculator.calculateFee(100000000);

		// Assert
		assertEquals(actual, expected);
	}
	
	@Test
	void testWhenBalanceIsNegative_FeeEqualsTwenty() {
		
		// Arrange
		double expected = 20;
		
		// Act
		double actual = calculator.calculateFee(-10);
		
		// Assert
		assertEquals(actual, expected);
	}

}

package com.fdmgroup.Sprint2Submission;

public class FeeCalculatorServiceImpl implements FeeCalculatorService {
	public double calculateFee(double x) {
		if (x <= 100) {
			return 20;
		} else if (x <= 500) {
			return 15;
		} else if (x <= 1000) {
			return 10;
		} else if (x <= 2000) {
			return 5;
		} else {
			return 0;
		}
	}

}

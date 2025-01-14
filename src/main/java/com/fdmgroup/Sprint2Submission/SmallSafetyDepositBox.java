package com.fdmgroup.Sprint2Submission;

public class SmallSafetyDepositBox extends SafetyDepositBox {
	private double capacity;

	public SmallSafetyDepositBox(double id, double capacity) {
		super(id);
		this.capacity = capacity;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
}

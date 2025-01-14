package com.fdmgroup.Sprint2Submission;

public class SafetyDepositBox {
	private boolean isAllotted;
	private double id;

	public SafetyDepositBox(double id) {
		this.id = id;
		this.isAllotted = false;
	}

	public boolean isAllotted() {
		return isAllotted;
	}

	public void setAllotted(boolean isAllotted) {
		this.isAllotted = isAllotted;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}
}

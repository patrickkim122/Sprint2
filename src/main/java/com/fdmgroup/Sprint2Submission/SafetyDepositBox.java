package com.fdmgroup.Sprint2Submission;

public class SafetyDepositBox {
	private boolean isAllotted;
	private double id;
	private int numTimesUsed = 0;

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

	public int getNumTimesUsed() {
		return numTimesUsed;
	}

	public void setNumTimesUsed(int numTimesUsed) {
		this.numTimesUsed = numTimesUsed;
	}
	
	
}

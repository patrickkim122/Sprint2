package com.fdmgroup.Sprint2Submission;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SafetyDepositBoxService {
	private static SafetyDepositBoxService safetyDepositBoxService;
	private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
	private int numberOfSafetyDepositBox;
	private boolean isFalse;

	private SafetyDepositBoxService() {
	}

	public static SafetyDepositBoxService getInstance() {
		if (safetyDepositBoxService == null) {
			safetyDepositBoxService = new SafetyDepositBoxService();
		}
		return safetyDepositBoxService;
	}

	public void setNumberOfSafetyDepositBoxes(int numberOfSafetyDepositBox) {
		this.numberOfSafetyDepositBox = numberOfSafetyDepositBox;
		int i = 0;
		while (i < numberOfSafetyDepositBox) {
			SafetyDepositBox safetyDepositBox = new SafetyDepositBox(i);
			safetyDepositBoxes.add(safetyDepositBox);
		}
	}

	public int getNumberOfSafetyDepositBoxes() {
		return numberOfSafetyDepositBox;
	}

	public synchronized SafetyDepositBox allocateSafetyDepositBox() {
		// Don't throw Exception; the method needs to wait() until a box is available
		System.out.println("Allocating");
		for (SafetyDepositBox box : safetyDepositBoxes) {
			if (!box.isAllotted()) {
				box.setAllotted(true);
				return box;
			}
		}
		Optional<SafetyDepositBox> optionalBox = getReleasedSafetyDepositBox();

		while (!optionalBox.isPresent()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			optionalBox = getReleasedSafetyDepositBox();
		}
		optionalBox.get().setAllotted(true);
		return optionalBox.get();
	}

	public synchronized void releaseSafetyDepositBox(SafetyDepositBox box) {
		box.setAllotted(false);
		notifyAll();
	}

	public synchronized int getNumberOfAvailableSafetyDepositBoxes() {
		return (int) safetyDepositBoxes.stream().filter(box -> !box.isAllotted()).count();
	}

	public synchronized Optional<SafetyDepositBox> getReleasedSafetyDepositBox() {
		return safetyDepositBoxes.stream().filter(box -> !box.isAllotted()).findFirst();
	}

	public synchronized List<SafetyDepositBox> getSafetyDepositBoxes() {
		return safetyDepositBoxes;
	}

	public boolean isFalse() {
		return isFalse;
	}

	public void setFalse(boolean isFalse) {
		this.isFalse = isFalse;
	}
	
	
	
}

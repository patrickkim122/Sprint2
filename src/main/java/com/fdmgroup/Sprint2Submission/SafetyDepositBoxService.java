package com.fdmgroup.Sprint2Submission;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SafetyDepositBoxService {
	private static SafetyDepositBoxService safetyDepositBoxService;
	private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
	private static int numberOfSafetyDepositBox;

	private SafetyDepositBoxService() {
	}

	public static SafetyDepositBoxService getInstance() {
		if (safetyDepositBoxService == null) {
			safetyDepositBoxService = new SafetyDepositBoxService();
		}
		return safetyDepositBoxService;
	}

	public static void setNumberOfSafetyDepositBoxes(int num) {
		numberOfSafetyDepositBox = num;

	}

	public int getNumberOfSafetyDepositBoxes() {
		return numberOfSafetyDepositBox;
	}

	public synchronized SafetyDepositBox allocateSafetyDepositBox() {
		SafetyDepositBox box;
		// Look for a Box that has been created and released
		if (getNumberOfAvailableSafetyDepositBoxes() > 0) {
			box = getReleasedSafetyDepositBox().get();
		} else if (safetyDepositBoxes.size() < numberOfSafetyDepositBox) {
			// Create new Box
			box = new SmallSafetyDepositBox(safetyDepositBoxes.size() + 1, 5);
			safetyDepositBoxes.add(box);
		} else {
			// Wait for the next Box to be released
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			box = getReleasedSafetyDepositBox().get();
		}

		box.setAllotted(true);
		box.setNumTimesUsed(box.getNumTimesUsed() + 1);
		return box;
	}

	public synchronized void releaseSafetyDepositBox(SafetyDepositBox box) {
		box.setAllotted(false);
		notify();
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

}

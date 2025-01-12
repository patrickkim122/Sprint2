package com.fdmgroup.Sprint2Submission;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SafetyDepositBoxService {
    private static SafetyDepositBoxService safetyDepositBoxService;
    private List<SafetyDepositBox> safetyDepositBoxes = new ArrayList<>();
    private int numberOfSafetyDepositBox;

    private SafetyDepositBoxService() {}
    

    public static SafetyDepositBoxService getInstance() {
        if (safetyDepositBoxService == null) {
            safetyDepositBoxService = new SafetyDepositBoxService();
        }
        return safetyDepositBoxService;
    }

    public void setNumberOfSafetyDepositBoxes(int numberOfSafetyDepositBox) {
        this.numberOfSafetyDepositBox = numberOfSafetyDepositBox;
        for (int i = 0; i < numberOfSafetyDepositBox; i++) {
            safetyDepositBoxes.add(new SafetyDepositBox(i));
        }
    }

    public int getNumberOfSafetyDepositBoxes() {
        return numberOfSafetyDepositBox;
    }

    public SafetyDepositBox allocateSafetyDepositBox() {
        for (SafetyDepositBox box : safetyDepositBoxes) {
            if (!box.isAllotted()) {
                box.setAllotted(true);
                return box;
            }
        }
        throw new IllegalStateException("No available safety deposit boxes.");
    }

    public void releaseSafetyDepositBox(SafetyDepositBox box) {
        box.setAllotted(false);
    }

    public int getNumberOfAvailableSafetyDepositBoxes() {
        return (int) safetyDepositBoxes.stream().filter(box -> !box.isAllotted()).count();
    }

    public Optional<SafetyDepositBox> getReleasedSafetyDepositBox() {
        return safetyDepositBoxes.stream().filter(box -> !box.isAllotted()).findFirst();
    }

    public List<SafetyDepositBox> getSafetyDepositBoxes() {
        return safetyDepositBoxes;
    }
}


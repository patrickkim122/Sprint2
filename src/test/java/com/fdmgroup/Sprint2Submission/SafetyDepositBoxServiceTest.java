package com.fdmgroup.Sprint2Submission;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SafetyDepositBoxServiceTest {

	SafetyDepositBoxService sdbService;

	@BeforeEach
	void setUp() throws Exception {
		sdbService = SafetyDepositBoxService.getInstance();
		SafetyDepositBoxService.setNumberOfSafetyDepositBoxes(2);
	}

	@AfterEach
	void wipe() throws Exception {
		sdbService.getSafetyDepositBoxes().clear();
	}

	// Test to confirm that only one instance can exist
	@Test
	void testSafetyDepositBoxService_GetInstance_Returns_SameInstance() {
		SafetyDepositBoxService sdbService1 = SafetyDepositBoxService.getInstance();
		SafetyDepositBoxService sdbService2 = SafetyDepositBoxService.getInstance();

		assertEquals(sdbService1, sdbService2);
	}
	
	// Test to confirm that getCapacity and setCapacity are functional
	@Test
	void testResetCapacity_Returns_NewCapacity() {
		// Arrange
		SmallSafetyDepositBox sdb = (SmallSafetyDepositBox) sdbService.allocateSafetyDepositBox();
		double expected = sdb.getCapacity();
		sdb.setCapacity(expected + 1);
		assertNotEquals(sdb.getCapacity(), expected);
	}
	
	// Test to confirm that getId and setId are functional
	@Test
	void testResetID_Returns_NewID() {
		// Arrange
		SafetyDepositBox sdb = sdbService.allocateSafetyDepositBox();
		double expected = sdb.getId();
		sdb.setId(expected + 1);
		assertNotEquals(sdb.getId(), expected);
	}

	// Test to confirm that two SafetyDepositBoxes are allocated simultaneously
	@Test
	void testTwoSafetyDepositBoxesAllocatedAtTheSameTime_NoWaiting_BothSafetyDepositBoxesUsedOnce()
			throws InterruptedException {
		// Arrange
		Thread thread1 = new Thread(() -> {
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb1);
		});

		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb2);
		});

		// Act
		thread1.start();
		thread2.start();
		thread1.join();
		thread2.join();

		// Assert
		int[] frequencies = new int[2];
		int frequency = 0;
		for (SafetyDepositBox sdb : sdbService.getSafetyDepositBoxes()) {
			frequency = sdb.getNumTimesUsed();
			frequencies[frequency]++;
		}

		assertEquals(frequencies[0], 0);
		assertEquals(frequencies[1], 2);

	}

	// Test to confirm that, when three SafetyDepositBoxes are needed, one is kept waiting
	@Test
	void testThreeSafetyDepositBoxesAllocatedAtTheSameTime_OneWaiting_OneSafetyDepositBoxUsedTwice()
			throws InterruptedException {
		// Arrange
		Thread thread1 = new Thread(() -> {
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb1);
		});

		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb2);
		});

		Thread thread3 = new Thread(() -> {
			SafetyDepositBox sdb3 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb3);
		});

		// Act
		thread1.start();
		thread2.start();
		thread3.start();
		thread1.join();
		thread2.join();
		thread3.join();

		// Assert
		int[] frequencies = new int[3];
		int frequency = 0;
		for (SafetyDepositBox sdb : sdbService.getSafetyDepositBoxes()) {
			frequency = sdb.getNumTimesUsed();
			frequencies[frequency]++;
		}

		assertEquals(frequencies[0], 0);
		assertEquals(frequencies[1], 1);
		assertEquals(frequencies[2], 1);

	}

	// Test that confirms that, when three SafetyDepositBoxes are required, the maximum limit is not exceeded
	@Test
	void testNumberOfSafetyDepositBoxesCannotGoOver_NumberOfSafetyDepositBox() throws InterruptedException {
		// Arrange
		int expected = sdbService.getNumberOfSafetyDepositBoxes();
		Thread thread1 = new Thread(() -> {
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb1);
		});

		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb2);
		});

		Thread thread3 = new Thread(() -> {
			SafetyDepositBox sdb3 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb3);
		});

		// Act
		thread1.start();
		thread2.start();
		thread3.start();
		thread1.join();
		thread2.join();
		thread3.join();

		// Assert
		assertEquals(sdbService.getSafetyDepositBoxes().size(), expected);

	}

	// Test that a late allocation will get an already released SafetyDepositBox rather than waiting or creating a new one
	@Test
	void testThreeSafetyDepositBoxesAllocatedAtSameTime_FourthSafetyDepositBoxAllocatedAfterWaitingForAllToBeReleasedFirst()
			throws InterruptedException {
		// Arrange
		Thread thread1 = new Thread(() -> {
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb1);
		});

		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb2);
		});

		Thread thread3 = new Thread(() -> {
			SafetyDepositBox sdb3 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb3);
		});

		// This thread should wait 15 seconds before starting, and hence will allocate a pre-made Box
		Thread thread4 = new Thread(() -> {

			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SafetyDepositBox sdb3 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb3);
		});

		// This thread is used to check, after 11 seconds, that only three of the Threads have allocated a Box thus far
		// The fourth thread should do it after 15 seconds
		Thread thread5 = new Thread(() -> {

			try {
				Thread.sleep(11000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int[] frequencies = new int[3];
			int totalUsage = 0;
			int expectedTotalUsage = 3;
			int frequency = 0;
			for (SafetyDepositBox sdb : sdbService.getSafetyDepositBoxes()) {
				frequency = sdb.getNumTimesUsed();
				frequencies[frequency]++;
				totalUsage += frequency;
			}

			assertEquals(frequencies[0], 0);
			assertEquals(frequencies[1], 1);
			assertEquals(frequencies[2], 1);
			assertEquals(totalUsage, expectedTotalUsage);
		});

		// Act
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();
		thread5.join();

		// Assert
		int totalUsage = 0;
		int expectedTotalUsage = 4;
		int frequency = 0;
		for (SafetyDepositBox sdb : sdbService.getSafetyDepositBoxes()) {
			frequency = sdb.getNumTimesUsed();
			totalUsage += frequency;
		}

		assertEquals(totalUsage, expectedTotalUsage);

	}
	
	@Test
	void testFourSafetyDepositBoxesAllocatedAtTheSameTime_TwoWaiting_BothSafetyDepositBoxesUsedTwice()
			throws InterruptedException {
		// Arrange
		Thread thread1 = new Thread(() -> {
			SafetyDepositBox sdb1 = sdbService.allocateSafetyDepositBox();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb1);
		});

		Thread thread2 = new Thread(() -> {
			SafetyDepositBox sdb2 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb2);
		});

		Thread thread3 = new Thread(() -> {
			SafetyDepositBox sdb3 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb3);
		});
		Thread thread4 = new Thread(() -> {
			SafetyDepositBox sdb4 = sdbService.allocateSafetyDepositBox();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			sdbService.releaseSafetyDepositBox(sdb4);
		});

		// Act
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread1.join();
		thread2.join();
		thread3.join();
		thread4.join();

		// Assert
		int[] frequencies = new int[3];
		int frequency = 0;
		for (SafetyDepositBox sdb : sdbService.getSafetyDepositBoxes()) {
			frequency = sdb.getNumTimesUsed();
			frequencies[frequency]++;
		}

		assertEquals(frequencies[0], 0);
		assertEquals(frequencies[1], 0);
		assertEquals(frequencies[2], 2);

	}

}